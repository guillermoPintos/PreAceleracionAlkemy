package com.DisneyAlkemy.auth.filter;


import com.DisneyAlkemy.auth.service.JwtUtils;
import com.DisneyAlkemy.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsCustomService userDetailsCustomService;
    @Autowired
    private JwtUtils jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtRequestFilter( @Lazy UserDetailsCustomService userDetailsCustomService,  @Lazy JwtUtils jwtUtil,  @Lazy AuthenticationManager authenticationManager) {
        this.userDetailsCustomService = userDetailsCustomService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("bearer ")){
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails = this.userDetailsCustomService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authReq =
                        new UsernamePasswordAuthenticationToken(
                                userDetails.getUsername(),
                                userDetails.getPassword());
                Authentication auth = authenticationManager.authenticate(authReq);

                SecurityContextHolder.getContext().setAuthentication(auth);

            }
        }
        chain.doFilter(request, response);
    }
}
