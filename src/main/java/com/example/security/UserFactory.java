package com.example.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

final class UserFactory {
    private UserFactory() {

    }

    static User Create(User user) {
        return new User(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities());
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
