package heewon.bloi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGeneratorEncoder {
    public static void main(String[] args){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("1234"));

    }
}
