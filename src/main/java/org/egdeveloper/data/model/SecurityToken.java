package org.egdeveloper.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class SecurityToken extends AbstractEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String guid;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User user;
}
