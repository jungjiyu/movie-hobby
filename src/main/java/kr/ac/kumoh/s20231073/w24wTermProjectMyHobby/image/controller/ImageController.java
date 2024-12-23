package kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.image.controller;

import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.dto.response.CustomResponseBody;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.global.utils.ResponseUtil;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.image.dto.response.ImageDetailResponseDto;
import kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
@Slf4j
public class ImageController {

    private final ImageService imageService;

    /**
     * 이미지 업로드
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public ResponseEntity<CustomResponseBody<ImageDetailResponseDto>> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        ImageDetailResponseDto imageDetail = imageService.saveImage(file);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(imageDetail));
    }


    /**
     * 이미지 데이터 조회
     * @param id
     * @return
     * @throws IOException
     */
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) throws IOException {
        byte[] imageData = imageService.getImageData(id); // 이미지 데이터를 가져옴
        String contentType = imageService.getImageContentType(id); // Content-Type 가져옴

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(imageData); // 순수 이미지 데이터 반환
    }


    /**
     * 이미지 데이터들 모두 조회
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<CustomResponseBody<List<ImageDetailResponseDto>>> getUploadedImages() {
        List<ImageDetailResponseDto> imageDetails = imageService.getUploadedImagesWithDetails();
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(imageDetails));
    }


    /**
     * 이미지 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponseBody<Void>> deleteImage(@PathVariable String id) {
        imageService.deleteImage(id);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse());
    }

}