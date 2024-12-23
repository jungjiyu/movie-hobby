package kr.ac.kumoh.s20231073.w24wTermProjectMyHobby.image.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageDetailResponseDto {
    private String id; // 파일 고유 ID
    private String filename; // 파일 이름
    private String contentType; // 파일의 MIME 타입
    private Date uploadDate; // 업로드 날짜
}
