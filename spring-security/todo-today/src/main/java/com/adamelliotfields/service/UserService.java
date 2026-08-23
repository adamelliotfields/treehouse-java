package com.adamelliotfields.service;

import com.adamelliotfields.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  User findByUsername(String username);
}
