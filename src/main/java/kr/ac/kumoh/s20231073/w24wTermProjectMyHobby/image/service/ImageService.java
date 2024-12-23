package kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.image.service;

import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.dto.response.CustomResponseBody;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.enums.ExceptionType;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.utils.BusinessException;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.utils.ResponseUtil;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.image.dto.response.ImageDetailResponseDto;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import com.mongodb.client.gridfs.model.GridFSFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final GridFsTemplate gridFsTemplate;

    public ImageDetailResponseDto saveImage(MultipartFile file) throws IOException {

        // 파일 이름 중복 확인
        GridFSFile existingFile = gridFsTemplate.findOne(new Query(Criteria.where("filename").is(file.getOriginalFilename())));
        if (existingFile != null) throw new BusinessException(ExceptionType.DUPLICATE_VALUE_ERROR);

        // 중복이 없으면 파일 저장
        ObjectId imageId = gridFsTemplate.store(
                file.getInputStream(),
                file.getOriginalFilename(),
                file.getContentType()
        );


        return ImageDetailResponseDto.builder().
                id(imageId.toString()).
                filename(file.getOriginalFilename()).
                contentType(file.getContentType()).
                uploadDate(new Date()).build();
    }

    public byte[] getImageData(String id) throws IOException {
        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        if (file == null) throw new BusinessException(ExceptionType.FILE_NOT_FOUND);

        try {
            GridFsResource resource = gridFsTemplate.getResource(file);
            return StreamUtils.copyToByteArray(resource.getInputStream());
        } catch (IOException e) {
            throw new BusinessException(ExceptionType.FILE_NOT_FOUND);
        }
    }

    public String getImageContentType(String id) {
        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        if (file == null) {
            throw new BusinessException(ExceptionType.FILE_NOT_FOUND);
        }

        return file.getMetadata() != null
                ? file.getMetadata().get("_contentType").toString()
                : MediaType.APPLICATION_OCTET_STREAM_VALUE;
    }




    public List<ImageDetailResponseDto> getUploadedImagesWithDetails() {
        List<ImageDetailResponseDto> imageDetails = new ArrayList<>();

        // GridFSFindIterable을 반복하며 List로 변환
        gridFsTemplate.find(new Query()).forEach(file -> {
            ImageDetailResponseDto detail = ImageDetailResponseDto.builder()
                    .id(file.getObjectId().toString())
                    .filename(file.getFilename())
                    .contentType(file.getMetadata() != null ? file.getMetadata().getString("_contentType") : null)
                    .uploadDate(file.getUploadDate())
                    .build();
        });

        return imageDetails;
    }

    public void deleteImage(String id) {
        Query query = new Query(where("_id").is(id));
        boolean exists = gridFsTemplate.findOne(query) != null;
        if (!exists) throw new BusinessException(ExceptionType.FILE_NOT_FOUND);

        gridFsTemplate.delete(query);
    }

}
