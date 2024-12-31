package com.branch.serviceimp;

import com.branch.dao.LoginDao;
import com.branch.model.Login;
import com.branch.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;

	@Override
	public Login saveLogin(Login login) {
		// You might want to add password encoding here
		return loginDao.save(login);
	}

	@Override
	public Optional<Login> getLoginById(int loginId) {
		return loginDao.findById(loginId);
	}

	@Override
	public List<Login> getAllLogins() {
		return loginDao.findAll();
	}

	@Override
	public Login updateLogin(Login login) {
		// Check if login exists before updating
		if (loginDao.existsById(login.getLoginId())) {
			return loginDao.save(login);
		}
		throw new RuntimeException("Login not found with id: " + login.getLoginId());
	}

	@Override
	public void deleteLogin(int loginId) {
		if (loginDao.existsById(loginId)) {
			loginDao.deleteById(loginId);
		} else {
			throw new RuntimeException("Login not found with id: " + loginId);
		}
	}

	@Override
	public boolean authenticateLogin(String managerEmail, String managerPassword) {
		Login login = loginDao.findByManagerEmail(managerEmail);
		return login != null && login.getManagerPassword().equals(managerPassword);
	}
}