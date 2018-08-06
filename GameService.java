package org.blackjack.service;

import java.util.List;

import org.blackjack.model.Game;

public interface GameService {
	 void save(Game game);
	 List<Game> findByUsername(String username);

}
