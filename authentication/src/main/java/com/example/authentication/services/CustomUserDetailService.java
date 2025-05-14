package com.example.authentication.services;

import com.example.authentication.entities.Utilisateur;
import com.example.authentication.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UtilisateurRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur userEntity = userRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));

        return new User(userEntity.getEmail(), userEntity.getPassword(),
                Arrays.stream(userEntity.getRole().split("\\|"))
                        .map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
}
