package org.ayty.hatcher.JWT;

import javax.servlet.ServletException;

import org.ayty.hatcher.api.v1.user.Entity.hatcher_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {
	
	@Autowired
	private JWTServices jwtService;
	
	@PostMapping("/login")
	public ResponseEntity<RespostaDeLogin> autentica (@RequestBody hatcher_user user) throws ServletException {
		return ResponseEntity.status(HttpStatus.OK).body(jwtService.autentica(user));
	}

}
