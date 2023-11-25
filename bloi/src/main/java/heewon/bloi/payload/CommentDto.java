package heewon.bloi.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private long id;
    @NotEmpty(message = "Comment name should not be empty!")
    private String name;
    @NotEmpty(message = "Comment email should not be empty!")
    @Email(message = "Comment email must be Email Form")
    private String email;
    @NotEmpty(message = "Comment body should not be empty!")
    @Size(min = 10, message = "Comment body must be minimum 10 characters")
    private String body;
}
