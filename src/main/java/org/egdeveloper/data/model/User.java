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
@Table(name = "user")
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
    private boolean geolocEnabled;

    @Getter
    @Setter
    private boolean medStatEnabled;

    @Getter
    @Setter
    private Integer normalHeartRateMin;

    @Getter
    @Setter
    private Integer normalHeartRateMax;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "security_tokens", joinColumns = @JoinColumn(name = "user_id"))
//    @Column(name = "security_token")
    @JoinColumn(name = "user_id")
    private Collection<SecurityToken> secTokens = new HashSet<>();

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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Collection<Confident> confidents = new HashSet<>();

    public void copyFrom(User another){
        name = another.name;
        login = another.login;
        password = another.password;
        email = another.email;
        geolocEnabled = another.geolocEnabled;
        medStatEnabled = another.medStatEnabled;
        normalHeartRateMin = another.normalHeartRateMin;
        normalHeartRateMax = another.normalHeartRateMax;
    }
}
