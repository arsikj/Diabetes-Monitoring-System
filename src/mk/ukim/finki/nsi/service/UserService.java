package mk.ukim.finki.nsi.service;

import mk.ukim.finki.nsi.model.User;

public interface UserService {
	
	void save(User user);
	User findByUsername(String username);
}
