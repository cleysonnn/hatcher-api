package org.ayty.hatcher.api.v1.user.jpa;

import java.util.Optional;

import org.ayty.hatcher.api.v1.user.Entity.hatcher_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJPA extends JpaRepository<hatcher_user, Long>{
	
	//@Query()
	Optional<hatcher_user> findByLogin(String Login);

}
