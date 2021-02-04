package org.ayty.hatcher.JWT;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.PrematureJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class TokenFilter extends GenericFilterBean {
	
	@Value("${security.jwt.token.expire-length}")
    private String expiration;


    @Value("${security.jwt.token.secret-key}")
    private String secretKey;
	
	public final static int TOKEN_INDEX = 7;

	public void doFilter(ServletRequest request , ServletResponse response, FilterChain chain)throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		String header = req.getHeader("Authorization");
		
		if (header == null || !header.startsWith("Bearer ")) {
			throw new ServletException("Token inexistente ou mal formatado!");
		}
		
		// Extraindo apenas o token do cabecalho.
		String token = header.substring(TOKEN_INDEX);
		
		
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
			
		} catch (SignatureException | ExpiredJwtException | MalformedJwtException | PrematureJwtException | UnsupportedJwtException | IllegalArgumentException e) {
			//aqui optamos por tratar todas as exceções que podem ser lançadas da mesma forma e simplesmente
			//repassar a mensagem de erro
			//se quiser enviar mensagens em portugues mais personalizadas teria que capturar exceção
			//por exceção
			
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
			return;
		}
		
		chain.doFilter(request, response);
	}
	
	

}
