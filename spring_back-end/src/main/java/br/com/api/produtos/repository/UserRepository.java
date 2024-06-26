package br.com.api.produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.api.produtos.models.UserModel;


@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
  UserDetails findByLogin(String login);
}
