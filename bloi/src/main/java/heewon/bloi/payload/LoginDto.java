package heewon.bloi.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
        description = "Login Model Information"
)
public class LoginDto {
    @Schema(
            description = "Login Username or Email"
    )
    private String usernameOrEmail;
    @Schema(
            description = "Login Password"
    )
    private String password;
}
