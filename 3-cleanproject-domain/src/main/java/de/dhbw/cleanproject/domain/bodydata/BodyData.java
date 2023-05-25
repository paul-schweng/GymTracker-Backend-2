package de.dhbw.cleanproject.domain.bodydata;

import de.dhbw.cleanproject.domain.user.User;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class BodyData {

    @Id
    @Type(type="uuid-char")
    private UUID userId;


    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BodyPart> bodyParts = new ArrayList<>();



/*
    public static BodyData create(User user) {
        return BodyData.builder()
                .bicep(RightLeft.create())
                .forearm(RightLeft.create())
                .leg(RightLeft.create())
                .weight(new ArrayList<>())
                .breast(new ArrayList<>())
                .hip(new ArrayList<>())
                .waist(new ArrayList<>())
                .shoulders(new ArrayList<>())
                .user(user)
                .id(user.getId())
                .build();
    }

 */
}
