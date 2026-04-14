package com.example.HMS_AI.Service;

import com.example.HMS_AI.Entity.User;
import com.example.HMS_AI.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceIMPL implements UserDetailsService {

    private UserRepository userRepository;

    public  UserDetailsServiceIMPL(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Vaild"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .roles(String.valueOf(user.getRole()))
                .build();
    }
}
