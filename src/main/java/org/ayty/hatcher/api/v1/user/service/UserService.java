package org.ayty.hatcher.api.v1.user.service;

import java.util.List;
import java.util.Optional;

import org.ayty.hatcher.api.v1.user.Entity.hatcher_user;
import org.ayty.hatcher.api.v1.user.jpa.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserJPA userjpa;
	
	
	public hatcher_user adiconarUser(hatcher_user user) {
		return userjpa.save(user);
	}
	
	public List<hatcher_user> getUsers() {
		return userjpa.findAll();
	}
	
	public Optional<hatcher_user> getUserByid(Long id){
		return userjpa.findById(id);
		
	}
}
