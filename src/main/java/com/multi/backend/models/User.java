package com.multi.backend.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

    private static final long serialVersionUID = 7042921310858009366L;
    private static final String ROLE_PREFIX = "ROLE_";
    // private static final ArrayList<Character> CHARACTERS = new
    // Array<Character>();
    private static final String[] CHARACTERS = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i",
            "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3",
            "4", "5", "6", "7", "8", "9", "@" };

    public enum Role {
        SUPER_ADMIN, ADMIN, SUPERVISEUR
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.SUPERVISEUR;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "is_account_non_expired")
    private boolean isAccountNonExpired;
    @Column(name = "is_account_non_locked")
    private boolean isAccountNonLocked;
    @Column(name = "is_credentials_non_expired")
    private boolean isCredentialsNonExpired;
    @Column(name = "is_enabled")
    private boolean isEnabled;

    @Transient
    private Set<? extends GrantedAuthority> grantedAuthorities;

    public User() {
        // Constructor
    }

    public User(String username, String password, Set<? extends GrantedAuthority> grantedAuthorities,
            boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired,
            boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));
        return list;
    }

    private static String choseAChar() {
        int n = (int) Math.floor((Math.random() * CHARACTERS.length));
        return CHARACTERS[n];

    }

    public static String generatePassword() {
        int i = 0;
        String passw = "";
        while (i < 8) {
            passw += choseAChar();
            i++;

        }
        return passw;
    }

    // @Override
    // public String getPassword() {
    // return password;
    // }

    // @Override
    // public String getUsername() {
    // return username;
    // }

    // @Override
    // public boolean isAccountNonExpired() {
    // return isAccountNonExpired;
    // }

    // @Override
    // public boolean isAccountNonLocked() {
    // return isAccountNonLocked;
    // }

    // @Override
    // public boolean isCredentialsNonExpired() {
    // return isCredentialsNonExpired;
    // }

    // @Override
    // public boolean isEnabled() {
    // return isEnabled;
    // }

}
