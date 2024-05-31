package br.com.api.produtos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.produtos.models.AuthenticationDTO;
import br.com.api.produtos.models.RegisterDTO;
import br.com.api.produtos.models.UserModel;
import br.com.api.produtos.repository.UserRepository;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
  
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserRepository repository;


  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody @Validated AuthenticationDTO data){
    var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
    var auth = this.authenticationManager.authenticate(usernamePassword);

    return ResponseEntity.ok().build();



  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody @Validated RegisterDTO data) {
      
      if (this.repository.findByLogin(data.login()) != null) {
        return ResponseEntity.badRequest().build();
      }
      String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
      UserModel newUser = new UserModel(data.login(), encryptedPassword, data.role());

      this.repository.save(newUser);
      return ResponseEntity.ok().build();
  }
  

}


