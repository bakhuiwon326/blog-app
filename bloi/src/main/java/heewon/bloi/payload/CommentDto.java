package heewon.bloi.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
        description = "Comment Model Information"
)
public class CommentDto {
    private long id;

    @Schema(
            description = "Comment Name"
    )
    @NotEmpty(message = "Comment name should not be empty!")
    private String name;

    @Schema(
            description = "Comment User Email"
    )
    @NotEmpty(message = "Comment email should not be empty!")
    @Email(message = "Comment email must be Email Form")
    private String email;

    @Schema(
            description = "Comment Body"
    )
    @NotEmpty(message = "Comment body should not be empty!")
    @Size(min = 10, message = "Comment body must be minimum 10 characters")
    private String body;
}
