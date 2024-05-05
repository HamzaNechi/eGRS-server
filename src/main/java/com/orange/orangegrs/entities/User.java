package com.orange.orangegrs.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Users")
@Data
@EqualsAndHashCode
public class User implements UserDetails {

    @Id
    @Column(name = "Login", length = 20, nullable = false, unique = true)
    private String login;


    @ManyToOne
    @JoinColumn(name = "ProfileId", referencedColumnName = "ProfileId")
    private Profile profile;

    @Column(name = "Password")
    @Lob
    private String password;


    @Column(name = "LastName", length = 100, nullable = false)
    private String lastName;


    @Column(name = "FirstName", length = 100)
    private String firstName;


    @Column(name = "Email", length = 50)
    private String email;


    @Column(name = "`Function`", length = 100)
    private String function;


    @Column(name = "Status", nullable = false)
    private byte status;

    @Column(name = "IsAD", nullable = false)
    private byte isAd ;


    /************************* méthode user détails ************/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(profile.getProfile()));
    }

    @Override
    public String getUsername() {
        return login;
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
}
