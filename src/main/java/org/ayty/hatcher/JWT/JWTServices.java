package org.ayty.hatcher.JWT;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.ServletException;

import org.ayty.hatcher.api.v1.user.Entity.hatcher_user;
import org.ayty.hatcher.api.v1.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Service
public class JWTServices {

	@Autowired
	private UserService usuariosService;
	
	@Value("${security.jwt.token.expire-length}")
    private String expiration;
    @Value("${security.jwt.token.secret-key}")
    private String secretKey;
    
    
    public RespostaDeLogin autentica(hatcher_user user) {
    	if (!usuariosService.validaUsuarioSenha(user)) {
			return new RespostaDeLogin("Usuario ou senha invalidos. Nao foi realizado o login.");
		}

		String token = geraToken(user.getEmail());
		return new RespostaDeLogin(token);
    	
    }
    
    private String geraToken(String email) {
    	Long expSting = Long.valueOf(expiration);
    	LocalDateTime expireLength = LocalDateTime.now().plusMinutes(expSting);
    	Instant instant = expireLength.atZone(ZoneId.systemDefault()).toInstant();
    	Date date = Date.from(instant);
    	
    	
		return Jwts.builder().setSubject(email)
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.setExpiration(date)
				.compact();//1week
	}
    
    public String getSujeitoDoToken(String authorizationHeader) throws ServletException {
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new ServletException("Token inexistente ou mal formatado!");
		}

		// Extraindo apenas o token do cabecalho.
		String token = authorizationHeader.substring(TokenFilter.TOKEN_INDEX);

		String subject = null;
		try {
			subject = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
		} catch (SignatureException e) {
			throw new ServletException("Token invalido ou expirado!");
		}
		return subject;
	}
	
}
