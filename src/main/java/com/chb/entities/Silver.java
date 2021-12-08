package com.chb.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SIL")
public class Silver extends Formule{
    final int nbRdv= 7;

    public Silver() {
        super();
    }
}
