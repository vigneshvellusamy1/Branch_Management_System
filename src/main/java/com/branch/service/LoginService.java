package com.branch.service;

import com.branch.model.Login;
import java.util.List;
import java.util.Optional;

public interface LoginService {
	Login saveLogin(Login login);

	Optional<Login> getLoginById(int loginId);

	List<Login> getAllLogins();

	Login updateLogin(Login login);

	void deleteLogin(int loginId);

	boolean authenticateLogin(String managerEmail, String managerPassword);
}
