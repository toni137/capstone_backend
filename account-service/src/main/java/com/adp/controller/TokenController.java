package com.adp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adp.domain.User;
import com.adp.dto.Token;
import com.adp.service.TokenService;

@RestController
@RequestMapping("/token")
public class TokenController {
  @Autowired
  TokenService tokenService;

  @PostMapping
  public ResponseEntity<?> getToken(@RequestBody User user){
    boolean isValid = tokenService.validateUser(user);
    if (isValid) {
      Token token = tokenService.generateToken(user);
      return ResponseEntity.ok(token);
    } else {
      return ResponseEntity.status(401).body("Invalid credentials");
    }
  }
}
