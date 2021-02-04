package org.ayty.hatcher.api.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JWTFilter {

    @Value("${security.jwt.token.expire-length}")
    private String expiration;


    @Value("${security.jwt.token.secret-key}")
    private String secretKey;


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        String header = req.getHeader("Authorization");

        if(header == null || !header.startsWith("Bearer ")) {
            throw new ServletException("missing or invalid token");
        }
        String token = header.substring(7);
        try {
            Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
        }catch(SignatureException e) {
            throw new ServletException("Token Inválido");
        }
        chain.doFilter(request, response);
    }




}
