/**
 * 
 */
package org.blackjack.repository;

import java.util.List;

import org.blackjack.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Padmini
 *
 */
public interface GameRepository  extends JpaRepository<Game, Long>{
	Game findById(Long id);
	List<Game> findByUsername(String username);
}
