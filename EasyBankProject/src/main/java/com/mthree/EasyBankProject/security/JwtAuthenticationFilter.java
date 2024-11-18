///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.mthree.EasyBankProject.security;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//
///**
// *
// * @author khali
// */
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final UserDetailsService userDetailsService;
//    private final JwtUtil jwtTokenUtil;
//
//    @Autowired
//    public JwtAuthenticationFilter(UserDetailsService userDetailsService, JwtUtil jwtTokenUtil) {
//        this.userDetailsService = userDetailsService;
//        this.jwtTokenUtil = jwtTokenUtil;
//    }
//
//    /**
//     * This method extracts the JWT token from the HTTP request, validates it, and sets the authentication context.
//     */
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        final String authorizationHeader = request.getHeader("Authorization");
//        String username = null;
//        String jwtToken = null;
//
//        // Check if the Authorization header is present and starts with "Bearer "
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            jwtToken = authorizationHeader.substring(7); // Extract the token part (after "Bearer ")
//            try {
//                // Extract the username from the JWT token
//                username = jwtTokenUtil.extractUsername(jwtToken);
//            } catch (Exception e) {
//                // Log the error and send an unauthorized response if token extraction fails
//                logger.warn("Error extracting username from token: " + e.getMessage());
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
//                return;
//            }
//        }
//
//        // If a username is extracted and the authentication context is not already set
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            try {
//                // Load the user details by the extracted username
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//                // Validate the token
//                if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
//                    // If token is valid, create an authentication token
//                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                            userDetails, null, userDetails.getAuthorities());
//                    
//                    // Set additional details (such as remote address, session ID, etc.)
//                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                    // Set the authentication in the SecurityContext
//                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                }
//            } catch (Exception e) {
//                // Handle any exception in user authentication or token validation
//                logger.error("Authentication failed for user: " + username + " - " + e.getMessage());
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
//                return;
//            }
//        }
//
//        // Continue with the next filter in the chain
//        filterChain.doFilter(request, response);
//    }
//}
