package com.example.demo.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.Role;
import com.example.demo.entidad.Cliente;
import com.example.demo.entidad.UserEntity;
import com.example.demo.entidad.Veterinario;
import com.example.demo.repositorio.RoleRepository;
import com.example.demo.repositorio.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        UserEntity userDB = userRepository.findByUsername(username).orElseThrow(
            () -> new UsernameNotFoundException("Usuario no encontrado")
        );

        UserDetails userDetails = new User(userDB.getUsername(),
            userDB.getPassword(), 
            mapToGrantedAuthorities(userDB.getRoles()));

        return userDetails;
    }

    private Collection<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public UserEntity ClienteToUser(Cliente cliente){
        UserEntity user = new UserEntity();
        user.setUsername(cliente.getCedula());
        user.setPassword(passwordEncoder.encode("123"));
        
        Role roles = roleRepository.findByName("Cliente").get();
        user.setRoles(List.of(roles));
        return userRepository.save(user);         
    }

    public UserEntity AdminToUser (Veterinario admin) {
        UserEntity user = new UserEntity();
        user.setUsername(admin.getCedula());
        user.setPassword(passwordEncoder.encode(admin.getContrasena()));

        Role roles = roleRepository.findByName("Admin").get();
        user.setRoles(List.of(roles));

        return userRepository.save(user);
    }

    public UserEntity VeterinarioToUser(Veterinario veterinario){
        UserEntity user = new UserEntity();
        user.setUsername(veterinario.getCedula());
        user.setPassword(passwordEncoder.encode(veterinario.getContrasena()));
        
        Role roles = roleRepository.findByName("Veterinario").get();
        user.setRoles(List.of(roles));
        return userRepository.save(user);
    }
    
}
