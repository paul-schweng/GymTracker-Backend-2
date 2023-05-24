package de.dhbw.cleanproject.domain.user;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class User implements UserDetails, Serializable {


    @Id
    @Column(nullable = false)
    @GenericGenerator(name = "client_id", strategy = "de.dhbw.cleanproject.abstractioncode.JpaIdGenerator")
    @GeneratedValue(generator = "client_id")
    private UUID id;

    @Column(unique=true)
    private String username;

    // The user's password
    private String password;

    private String name;

    public void setName(String name) {
        this.name = name.trim();
    }

    public void setUsername(String username) {
        this.username = username.trim();
    }


    /* --------------------------------- */
    /* UserDetails interface methods */

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
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

}
