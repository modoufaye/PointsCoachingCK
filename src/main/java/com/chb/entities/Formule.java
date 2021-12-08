package com.chb.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_FORM",discriminatorType = DiscriminatorType.STRING,length=3)
public class Formule implements Serializable {
    @Id @GeneratedValue
    private Long codeFormule;

    public Formule() {
        super();
    }

    public Long getCodeFormule() {
        return codeFormule;
    }

    public void setCodeFormule(Long codeFormule) {
        this.codeFormule = codeFormule;
    }
}
