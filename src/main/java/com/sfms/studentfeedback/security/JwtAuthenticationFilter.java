package com.sfms.studentfeedback.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;

    }


    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain filterChain) throws ServletException, IOException {
//        testing
//        System.out.println("⛳ JwtAuthenticationFilter triggered on: " + req.getRequestURI());




        String authHeader = req.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(req, res);
            return;
        }

        String token = authHeader.substring(7);
        if (!jwtUtil.isTokenValid(token)) {
            filterChain.doFilter(req, res);
            return;
        }

        String username = jwtUtil.extractUsername(token);
        String roleClaim = jwtUtil.extractRole(token);               // e.g. "ROLE_STUDENT"
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load the rest of the user details (e.g. password isn’t really used now)
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // **Here’s the key: grant exactly the authority from the token**
            var authorities = Collections.singletonList((GrantedAuthority) () -> roleClaim);

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authToken);
            System.out.println("✅ Authenticated user=" + username + " with " + roleClaim);

        }

        filterChain.doFilter(req, res);
    }
}
