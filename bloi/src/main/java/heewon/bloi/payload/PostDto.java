package heewon.bloi.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Schema(
        description = "PostDto Model Information"
)
public class PostDto {
    private long id;

    @Schema(
            description = "Blog Post Title"
    )
    @NotEmpty(message = "Post title should not be empty!")
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;

    @Schema(
            description = "Blog Post description"
    )
    @NotEmpty(message = "Post description should not be empty!")
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;

    @Schema(
            description = "Blog Post Content"
    )
    @NotEmpty(message = "Post content should not be empty!")
    private String content;
    private Set<CommentDto> comments;
    @Schema(
            description = "Blog Post Category Id"
    )
    private long categoryId;
}
