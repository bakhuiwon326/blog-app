package heewon.bloi.security;

import heewon.bloi.exception.APIException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${JWT_SECRET_KEY}")
    private String jwtSecret;
    @Value("${JWT_EXP_TIME_MS}")
    private long jwtExpirationDate;

    public String createJwt(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
        String jwt = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date()) // 토큰 발급시간
                .setExpiration(expireDate) // 토큰 만료시간
                .signWith(key())
                .compact();
        return jwt;
    }
    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUsername(String jwt){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        String username = claims.getSubject();
        return username;
    }

    public boolean validateJwt(String jwt){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(jwt);
            return true;
        }
        catch (MalformedJwtException e) {
            throw new APIException(HttpStatus.BAD_REQUEST, "Invalid JWT");
        }
        catch (ExpiredJwtException e) {
            throw new APIException(HttpStatus.BAD_REQUEST, "Expired JWT");
        }
        catch (UnsupportedJwtException e){
            throw new APIException(HttpStatus.BAD_REQUEST, "Unsupported JWT");
        }
        catch (IllegalArgumentException e){
            throw new APIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");
        }
    }
}
