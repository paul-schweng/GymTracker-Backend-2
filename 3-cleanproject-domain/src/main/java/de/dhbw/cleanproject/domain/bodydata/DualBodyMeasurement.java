package de.dhbw.cleanproject.domain.bodydata;

import de.dhbw.cleanproject.domain.bodydata.rightleft.Side;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("DUAL")
@Getter
@Setter
@NoArgsConstructor
public class DualBodyMeasurement extends BodyMeasurement{

    @ElementCollection
    private List<TimeSeriesBodyData> right, left;



    @Override
    public void addMeasurement(TimeSeriesBodyData data, Side side) {
        if (side == Side.RIGHT) {
            right.add(data);
        } else {
            left.add(data);
        }
    }




}
