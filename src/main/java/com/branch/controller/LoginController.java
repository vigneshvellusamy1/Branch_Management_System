package com.branch.controller;

import com.branch.model.Login;
import com.branch.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/create")
    public ResponseEntity<Login> createLogin(@RequestBody Login login) {
        Login savedLogin = loginService.saveLogin(login);
        return new ResponseEntity<>(savedLogin, HttpStatus.CREATED);
    }

    @GetMapping("/{loginId}")
    public ResponseEntity<Login> getLoginById(@PathVariable int loginId) {
        Optional<Login> login = loginService.getLoginById(loginId);
        return login.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Login>> getAllLogins() {
        List<Login> logins = loginService.getAllLogins();
        return new ResponseEntity<>(logins, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Login> updateLogin(@RequestBody Login login) {
        Login updatedLogin = loginService.updateLogin(login);
        return new ResponseEntity<>(updatedLogin, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{loginId}")
    public ResponseEntity<Void> deleteLogin(@PathVariable int loginId) {
        loginService.deleteLogin(loginId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Boolean> authenticateLogin(@RequestBody Login loginCredentials) {
        boolean isAuthenticated = loginService.authenticateLogin(
            loginCredentials.getManagerEmail(), 
            loginCredentials.getManagerPassword()
        );
        return new ResponseEntity<>(isAuthenticated, HttpStatus.OK);
    }
}