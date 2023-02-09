package org.bank.bankaccount.infrastracture.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(String username) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return Jwts.builder()
                .addClaims(Map.of("username", username))
                .setIssuedAt(new Date())
                .setExpiration(dateFormat.parse("01/01/2030"))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        return (String) claims.get("username");
    }

    // validate token
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.warn("Invalid JWT signature.");
        } catch (MalformedJwtException ex) {
            log.warn("Invalid JWT token.");
        } catch (ExpiredJwtException ex) {
            log.warn("Expired JWT token.");
        } catch (UnsupportedJwtException ex) {
            log.warn("Unsupported JWT token.");
        } catch (IllegalArgumentException ex) {
            log.warn("JWT claims string is empty.");
        }
        return false;
    }
}
