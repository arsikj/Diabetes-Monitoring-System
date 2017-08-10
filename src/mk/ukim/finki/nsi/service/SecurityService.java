package mk.ukim.finki.nsi.service;

public interface SecurityService {

	String findLoggedInUsername();

	void autologin(String username, String password);
}
