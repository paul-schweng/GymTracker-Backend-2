package de.dhbw.cleanproject.domain.user;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class User {


    @Id
    @Column(nullable = false)
    private UUID id;

    @Column(unique=true)
    private String username;

    // The user's password
    private String password;

    private String name;


}
