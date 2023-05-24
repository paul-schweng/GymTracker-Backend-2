package de.dhbw.cleanproject.domain.bodydata;

import javax.persistence.Embeddable;

@Embeddable
public class TimeSeriesBodyData {

    private String date;
    private Double value;

}
