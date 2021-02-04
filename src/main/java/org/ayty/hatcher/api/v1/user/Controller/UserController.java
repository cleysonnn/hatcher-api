package org.ayty.hatcher.api.v1.user.Controller;

import org.ayty.hatcher.api.v1.user.Entity.hatcher_user;
import org.ayty.hatcher.api.v1.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/v1user")
	public ResponseEntity<?> adicionarPessoa(@RequestBody hatcher_user user){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.adiconarUser(user));
		
	}
	
	@GetMapping("/api/v1user")
	public ResponseEntity<?> GetPessoa(hatcher_user user){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.getUsers());
	}
	
	

}
