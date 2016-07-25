package org.egdeveloper.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

@Entity
@ToString
@EqualsAndHashCode(callSuper = false)
public class User extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String login;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
//    @JoinTable(name = "user_healthSOS",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "healthSOS_id")
//    )
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Collection<HealthSOS> healthSOS = new HashSet<>();

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
//    @JoinTable(name = "user_healthSOS",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "healthSOS_id")
//    )
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Collection<HealthStat> healthStat = new HashSet<>();

    @Getter
    @Setter
    @OneToMany
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Collection<Confident> confidents = new HashSet<>();
}
