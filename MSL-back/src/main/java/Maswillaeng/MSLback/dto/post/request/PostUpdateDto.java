package Maswillaeng.MSLback.dto.post.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class PostUpdateDto {

    // 이건 필요 없음
    private Long postId;

    private String thumbnail;

    @NotBlank
    private String title;

    @NotNull
    private String content;

}