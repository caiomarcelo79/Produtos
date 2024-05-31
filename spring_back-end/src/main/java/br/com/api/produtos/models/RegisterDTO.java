package br.com.api.produtos.models;

import br.com.api.produtos.models.role.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
  
}
