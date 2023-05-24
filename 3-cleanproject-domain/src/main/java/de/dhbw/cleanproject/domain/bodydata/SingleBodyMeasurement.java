package de.dhbw.cleanproject.domain.bodydata;

import de.dhbw.cleanproject.domain.bodydata.rightleft.Side;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("SINGLE")
@Getter
@Setter
@NoArgsConstructor
public class SingleBodyMeasurement extends BodyMeasurement{

    @ElementCollection
    private List<TimeSeriesBodyData> measurements;

    @Override
    public void addMeasurement(TimeSeriesBodyData data, Side side) {
        measurements.add(data);
    }


}
