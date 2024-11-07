package com.serviceback.serviceback.config.security.filter;

import java.io.IOException;

import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.serviceback.serviceback.application.services.IUser;
import com.serviceback.serviceback.application.services.auth.JwtService;
import com.serviceback.serviceback.domain.entities.security.JwtToken;
import com.serviceback.serviceback.domain.entities.security.User;
import com.serviceback.serviceback.infrastructure.repositories.jwttoken.JwtTokenRepository;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUser userService;

    @Autowired
    private JwtTokenRepository jwtRepository;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = jwtService.extractJwtFromRequest(request);
        if (jwt == null || !StringUtils.hasText(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }

        Optional<JwtToken> token = jwtRepository.findByToken(jwt);
        boolean isValid = validateToken(token);

        if (!isValid) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extraer el username del encabezado
        String username = jwtService.extractUsername(jwt);

        // Extraer el user del encabezado
        User user = userService.findOneByUsername(username)
                .orElseThrow(() -> new GlobalExceptions("Usuario no encontrado con el nombre: " + username));

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                username, null, user.getAuthorities());

        authToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }

    private boolean validateToken(Optional<JwtToken> optionalJwtToken) {
        if (!optionalJwtToken.isPresent()) {
            System.out.println("El token no existe o no fue generado en nuestro sistema");
            return false;
        }

        JwtToken token = optionalJwtToken.get();
        Date now = new Date(System.currentTimeMillis());
        boolean isValid = token.isIsValid() && token.getExpiration().after(now);

        if (!isValid) {
            System.out.println("Token invalido");
            updateTokenStatus(token);
        }

        return isValid;
    }

    private void updateTokenStatus(JwtToken token) {
        token.setIsValid(false);
        jwtRepository.save(token);
    }
}
