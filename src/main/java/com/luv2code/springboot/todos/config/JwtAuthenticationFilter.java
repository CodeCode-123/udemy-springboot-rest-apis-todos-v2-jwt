package com.luv2code.springboot.todos.config;

import com.luv2code.springboot.todos.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, @Lazy UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7); // "Bearer " 7 characters of Bearer and a space
        // extract email from the jwt
        userEmail = jwtService.extractUsername(jwt);
        // if userEmail is extracted from the token, and SecurityContextHolder do not have authentication
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // retrieve the user-specific data from a data source (like a database or external service)
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            // check whether the jwt and retrieved userDetails from the database can match or validate
            if (jwtService.isTokenValid(jwt, userDetails)) {
                // if jwt token validate, generate usernamePasswordAuthenticationToken
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                // authToken set details according to the request
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // SecurityContextHolder set authentication according to the authToken
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // crucial for passing a request and response to the next filter in the chain
        // without calling this method, the request wil be blocked
        filterChain.doFilter(request, response);
    }
}
