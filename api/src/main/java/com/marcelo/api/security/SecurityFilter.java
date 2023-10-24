package com.marcelo.api.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcelo.api.dto.MessageDTO;
import com.marcelo.api.repository.UserRepository;
import com.marcelo.api.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    private final UserRepository userRepository;

    @Autowired
    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (this.noRequiresAuthentication(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        var tokenOptional = this.recoverToken(request);
        if (tokenOptional.isEmpty()) {
            MessageDTO customResponse = new MessageDTO("Unauthorized", 1);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = null;
            try {
                jsonResponse = objectMapper.writeValueAsString(customResponse);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(jsonResponse);
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                filterChain.doFilter(request, response);
                return;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        tokenOptional.ifPresent(token -> {
            try {
                var login = tokenService.validateToken(token);
                var userDetails = userRepository.findByLogin(login);
                var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JWTVerificationException e) {
                MessageDTO customResponse = new MessageDTO("Unauthorized - invalid session", 2);
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonResponse = null;
                try {
                    jsonResponse = objectMapper.writeValueAsString(customResponse);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(jsonResponse);
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        filterChain.doFilter(request, response);
    }

    private boolean noRequiresAuthentication(HttpServletRequest request) {
        List<RequestMatcher> publicRoutes = List.of(
                new AntPathRequestMatcher("/users"),
                new AntPathRequestMatcher("/signin"),
                new AntPathRequestMatcher("/swagger-ui/**"),
                new AntPathRequestMatcher("/v3/api-docs/**"));
        return publicRoutes.stream().anyMatch(route -> route.matches(request));
    }

    private Optional<String> recoverToken(HttpServletRequest request) {
        if (request.getCookies() == null) return Optional.empty();
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("Authentication"))
                .map(Cookie::getValue)
                .findAny();
    }
}
