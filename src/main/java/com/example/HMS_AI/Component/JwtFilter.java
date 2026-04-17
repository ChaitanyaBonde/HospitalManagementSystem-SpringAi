package com.example.HMS_AI.Component;

import com.example.HMS_AI.Service.UserDetailsServiceIMPL;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final UserDetailsServiceIMPL userDetailsServiceIMPL;
    private final JwtUtility jwtUtility;

    public JwtFilter(UserDetailsServiceIMPL userDetailsServiceIMPL,JwtUtility jwtUtility){
        this.jwtUtility = jwtUtility;
        this.userDetailsServiceIMPL = userDetailsServiceIMPL;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")){
            String token = header.substring(7);
            String userName = jwtUtility.extractUserName(token);
            log.info("username :: {}", userName);
            if (userName != null  && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = userDetailsServiceIMPL.loadUserByUsername(userName);
                log.info(userDetails.getAuthorities().toString());

                if (jwtUtility.validateToken(token,userName)){
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails,
                                    null,
                                    userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
