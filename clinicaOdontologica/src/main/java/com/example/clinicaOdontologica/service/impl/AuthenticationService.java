package com.example.clinicaOdontologica.service.impl;

import com.example.clinicaOdontologica.entity.Rol;
import com.example.clinicaOdontologica.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Optional<com.example.clinicaOdontologica.entity.User> user = userRepository.findByUsername(username);

        Set<GrantedAuthority> autorizaciones = new HashSet<>();
        GrantedAuthority autorizacion = null;
        for (Rol rol : user.get().getRoles()) {
            autorizacion = new SimpleGrantedAuthority(rol.getNombre());
            autorizaciones.add(autorizacion);
        }
        String password = user.get().getPassword();
        String passEncode = bCryptPasswordEncoder.encode(password);
        org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(user.get().getUsername(),passEncode,true, true, true,true,autorizaciones);
        return userDetail;
    }


}
