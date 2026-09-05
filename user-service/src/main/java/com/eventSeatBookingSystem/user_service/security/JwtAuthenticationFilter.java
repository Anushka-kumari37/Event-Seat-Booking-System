package com.eventSeatBookingSystem.user_service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // Get Authorization header
        final String authHeader = request.getHeader("Authorization");

        // If Authorization header is missing or does not start with Bearer
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Remove "Bearer " from token
        final String jwtToken = authHeader.substring(7);

        String username;

        try {
            // Extract email/username from JWT
            username = jwtService.extractUsername(jwtToken);
        } catch (Exception e) {
            // Invalid JWT
            filterChain.doFilter(request, response);
            return;
        }

        // Check whether user is already authenticated
        if (username != null
                && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails;

            try {
                // Load user from database
                userDetails = userDetailsService.loadUserByUsername(username);
            } catch (Exception e) {
                filterChain.doFilter(request, response);
                return;
            }

            // Validate JWT
            if (jwtService.isTokenValid(jwtToken)) {

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                // Set authenticated user in SecurityContext
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);
            }
        }

        // Continue request
        filterChain.doFilter(request, response);
    }
}