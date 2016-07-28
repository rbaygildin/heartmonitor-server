package org.egdeveloper.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
public class GPSPosition {

    @Getter
    @Setter
    private Double latitude;

    @Getter
    @Setter
    private Double longitude;
}
