package org.egdeveloper.data.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "health_stat")
public class HealthStat extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Getter
//    @Setter
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
////    @JoinTable(name = "user_healthSOS",
////            joinColumns = @JoinColumn(name = "user_id"),
////            inverseJoinColumns = @JoinColumn(name = "healthSOS_id")
////    )
//    @JsonIgnore
//    private User user;

//    @Getter
//    @Setter
////    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
//    @JsonFormat(pattern="dd.MM.yyyy")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    private LocalDateTime measureDate;

    @Getter
    @Setter
    private String measureDate;

    @Getter
    @Setter
    private Integer heartRate;

    @Getter
    @Setter
    @Embedded
    private GPSPosition gpsPosition;
}
