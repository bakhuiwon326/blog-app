package heewon.bloi.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "Signup Model Information"
)
public class RegisterDto {
    @Schema(
            description = "Signup Name"
    )
    private String name;
    @Schema(
            description = "Signup Username"
    )
    private String username;
    @Schema(
            description = "Signup Email"
    )
    private String email;

    @Schema(
            description = "Signup Password"
    )
    private String password;
}
