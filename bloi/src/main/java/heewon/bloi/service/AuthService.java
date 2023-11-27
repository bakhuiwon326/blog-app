package heewon.bloi.service;

import heewon.bloi.payload.LoginDto;
import heewon.bloi.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
