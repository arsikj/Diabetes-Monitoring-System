package mk.ukim.finki.nsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mk.ukim.finki.nsi.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username);
}
