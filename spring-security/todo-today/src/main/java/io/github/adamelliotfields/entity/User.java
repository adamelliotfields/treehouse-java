package io.github.adamelliotfields.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Document
public class User implements UserDetails {
  @Id
  private String id;

  @Indexed(unique = true)
  private String username;

  private String password;
  private String role;
  private boolean enabled;
  private boolean accountNonExpired;
  private boolean accountNonLocked;
  private boolean credentialsNonExpired;
  private List<GrantedAuthority> authorities;

  public User(String username, String password, String role) {
    this.username = username;
    this.password = password;
    this.role = role;
    this.enabled = true;
    this.accountNonLocked = true;
    this.accountNonExpired = true;
    this.credentialsNonExpired = true;

    this.authorities = new ArrayList<>();

    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
    authorities.add(grantedAuthority);
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  @Override
  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }

  @Override
  public boolean isAccountNonExpired() {
    return accountNonExpired;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return credentialsNonExpired;
  }
}
