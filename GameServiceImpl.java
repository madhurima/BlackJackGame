package org.blackjack.service;

import java.util.List;

import org.blackjack.model.Game;
import org.blackjack.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;
	
	@Override
	public void save(Game game) {
		gameRepository.save(game);
	}

	@Override
	public List<Game> findByUsername(String username) {
		return gameRepository.findByUsername(username);
	}

}
