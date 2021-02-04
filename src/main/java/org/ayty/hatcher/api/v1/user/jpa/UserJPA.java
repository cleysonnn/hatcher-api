package org.ayty.hatcher.api.v1.user.jpa;

import org.ayty.hatcher.api.v1.user.Entity.hatcher_user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPA extends JpaRepository<hatcher_user, Long>{

}
