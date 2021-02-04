package org.ayty.hatcher.api.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.ayty.hatcher.api.v1.user.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class JWTService {


    @Value("${security.jwt.token.expire-length}")
    private String expiration;
    @Value("${security.jwt.token.secret-key}")
    private String secretKey;


    public String genetedToken( LoginDTO user){
        long expSting = Long.valueOf(expiration);
        LocalDateTime expireLength = LocalDateTime.now().plusMinutes(expSting);
        Instant instant = expireLength.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        String Token = Jwts.builder()
                .setSubject(user.getLogin())
                .signWith(SignatureAlgorithm.HS512,secretKey)
                .setExpiration(date)
                .compact();
        return Token;

    }

    private Claims getClaim(String token) throws ExpiredJwtException{
    return Jwts.parser().setSigningKey(secretKey)
            .parseClaimsJws(token)
            .getBody();
    }

    public boolean validToken(String token){
        try {
            Claims claims = getClaim(token);
            Date expirationDate = claims.getExpiration();
            LocalDateTime date = expirationDate.toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(date);

        }catch (Exception e){
            return false;
        }

    }

    public String getUserLogin(String token )  throws ExpiredJwtException {
        return (String) getClaim(token).getSubject();

    }


}
