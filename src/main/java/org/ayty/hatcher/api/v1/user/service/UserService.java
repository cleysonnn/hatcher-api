package org.ayty.hatcher.api.v1.user.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;

import org.ayty.hatcher.JWT.JWTServices;
import org.ayty.hatcher.api.v1.user.Entity.hatcher_user;
import org.ayty.hatcher.api.v1.user.jpa.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserJPA userjpa;
	@Autowired
	private JWTServices jwtService;
	
	
	
	public hatcher_user adiconarUser(hatcher_user user) {
		return userjpa.save(user);
	}
	
	public List<hatcher_user> getUsers() {
		return userjpa.findAll();
	}
	
	public Optional<hatcher_user> getUserByid(Long id){
		return userjpa.findById(id);
		
	}
	
	public List<hatcher_user> getUsersbyLogin() {
		return userjpa.findAll();
	}
	
	

	
	

	
	
	
	
	private boolean usuarioTemPermissao(String authorizationHeader, String login) throws ServletException {
		String subject = jwtService.getSujeitoDoToken(authorizationHeader);
		Optional<hatcher_user> optUsuario = userjpa.findByLogin(subject);
		return optUsuario.isPresent() && optUsuario.get().getEmail().equals(login);
	}

	public boolean validaUsuarioSenha(hatcher_user usuario) {
		Optional<hatcher_user> optUsuario = userjpa.findByLogin(usuario.getEmail());
		if (optUsuario.isPresent() && optUsuario.get().getSenha().equals(usuario.getSenha()))
			return true;
		return false;
	}
	
	
}
