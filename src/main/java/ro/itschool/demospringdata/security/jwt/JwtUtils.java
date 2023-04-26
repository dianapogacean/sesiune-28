package ro.itschool.demospringdata.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtUtils {

    @Value("${university.app.jwtSecret}")
    private String jwtSecret;

    @Value("${university.app.jwtExpirationMs}")
    private int expirationMs;

    public String generateToken(Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                //date now + expiration in miliseconds
                .setExpiration(new Date(new Date().getTime() + expirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();


    }

    /*
    header.payload.signature
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (ExpiredJwtException e) {
            log.debug("Invalid JWT signature: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.debug("JWT not supported: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.debug("JWT is malformed: {} ", e.getMessage());
        } catch (SignatureException e) {
            log.debug("Invalid signature for JWT : {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.debug("Claim set is empty {}", e.getMessage());
        }

        return false;

    }

    public String extractUsernameFromJwt(String authToken) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken).getBody().getSubject();
    }
}
