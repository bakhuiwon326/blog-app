package heewon.bloi.controller;

import heewon.bloi.payload.JWTAuthResponse;
import heewon.bloi.payload.LoginDto;
import heewon.bloi.payload.RegisterDto;
import heewon.bloi.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(@Qualifier("authServiceImpl") AuthService authService) {
        this.authService = authService;
    }

    @Operation(
            summary = "Login REST API",
            description = "Login REST API is used to login"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
        String jwt = authService.login(loginDto);
        JWTAuthResponse response = new JWTAuthResponse();
        response.setAccessToken(jwt);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(
            summary = "Signup REST API",
            description = "Signup REST API is used to signup"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PostMapping(value = {"/register", "signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
