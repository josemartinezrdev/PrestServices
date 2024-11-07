package com.serviceback.serviceback.domain.entities.security;

import java.util.Collection;
import java.util.stream.Collectors;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @Column(name = "id", length = 40, unique = true)
    private String username;

    @Column(length = 40)
    private String nombre;

    @Column(length = 255)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Rol role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<JwtToken> tokens;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<JwtToken> users;
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (role == null)
            return null;
        if (role.getPermissions() == null)
            return null;

        List<SimpleGrantedAuthority> authorities = role.getPermissions().stream()
                .map(each -> each.getOperation().getName())
                .map(each -> new SimpleGrantedAuthority(each))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.role.getNombre()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public User() {
    }

    public User(String username, String nombre, String password, Rol role) {
        this.username = username;
        this.nombre = nombre;
        this.password = password;
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRole() {
        return this.role;
    }

    public void setRole(Rol role) {
        this.role = role;
    }

}
