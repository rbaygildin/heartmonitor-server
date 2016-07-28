package org.egdeveloper.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "confident")
@EqualsAndHashCode(callSuper = false)
@ToString
public class Confident extends AbstractEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String email;

//    @Getter
//    @Setter
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonIgnore
//    private User user;
}
