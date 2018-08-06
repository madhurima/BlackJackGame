package org.blackjack.repository;

import org.blackjack.model.Role;
import org.blackjack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByName(String name);
}
