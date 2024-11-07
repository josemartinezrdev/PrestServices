package com.serviceback.serviceback.application.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

import com.serviceback.serviceback.application.services.IUser;
import com.serviceback.serviceback.domain.dtos.RegisterUser;
import com.serviceback.serviceback.domain.dtos.UserDto;
import com.serviceback.serviceback.domain.dtos.auth.AuthenticationRequest;
import com.serviceback.serviceback.domain.dtos.auth.AuthenticationResponse;
import com.serviceback.serviceback.domain.entities.security.JwtToken;
import com.serviceback.serviceback.domain.entities.security.User;
import com.serviceback.serviceback.infrastructure.repositories.jwttoken.JwtTokenRepository;
import com.serviceback.serviceback.infrastructure.utils.exceptions.GlobalExceptions;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AuthenticationService {
    @Autowired
    private IUser userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenRepository jwtRepository;

    public RegisterUser registerOneCustomer(UserDto newUser) {
        User user = userService.registerOneCustomer(newUser);
        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        saveUserToken(user, jwt);
        RegisterUser userDto = new RegisterUser();
        userDto.setUsername(user.getUsername());
        userDto.setNombre(user.getNombre());
        userDto.setRole(user.getRole().getNombre());

        userDto.setJwt(jwt);

        return userDto;
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("nombre", user.getNombre());
        extraClaims.put("role", user.getRole().getNombre());
        extraClaims.put("authorities", user.getAuthorities());

        return extraClaims;
    }

    public AuthenticationResponse login(AuthenticationRequest authRequest) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(), authRequest.getPassword());

        authenticationManager.authenticate(authentication);

        UserDetails user = userService.findOneByUsername(authRequest.getUsername()).get();
        String jwt = jwtService.generateToken(user, generateExtraClaims((User) user));

        saveUserToken((User) user, jwt);

        AuthenticationResponse authResponse = new AuthenticationResponse();
        authResponse.setJwt(jwt);
        return authResponse;
    }

    private void saveUserToken(User user, String jwt) {
        JwtToken token = new JwtToken();
        token.setToken(jwt);
        token.setUser(user);
        token.setExpiration(jwtService.extractExpiration(jwt));
        token.setIsValid(true);
        jwtRepository.save(token);
    }

    public boolean validateToken(String jwt) {
        try {
            jwtService.extractUsername(jwt);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public User findLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
            String username = (String) auth.getPrincipal();
            return userService.findOneByUsername(username)
                    .orElseThrow(() -> new GlobalExceptions("Usuario no encontrado: " + username));
        }

        throw new GlobalExceptions("El usuario no está autenticado o es anónimo");
    }

    public void logout(HttpServletRequest request) {

        String jwt = jwtService.extractJwtFromRequest(request);
        if (jwt == null || !StringUtils.hasText(jwt))
            return;

        Optional<JwtToken> token = jwtRepository.findByToken(jwt);

        if (token.isPresent() && token.get().isIsValid()) {
            token.get().setIsValid(false);
            jwtRepository.save(token.get());
        }
    }

}
