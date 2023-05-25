package de.dhbw.cleanproject.domain.bodydata;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class BodyMeasurement {

    @Id
    @GenericGenerator(name = "client_id", strategy = "de.dhbw.cleanproject.abstractioncode.JpaIdGenerator")
    @GeneratedValue(generator = "client_id")
    @Type(type="uuid-char")
    private UUID id;

    private String date;

    private Double value;


}
