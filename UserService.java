package org.blackjack.service;

import org.blackjack.model.User;

public interface UserService {
    void save(User user);
    void update(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    User findByEmailhash(String emailhash);
}
