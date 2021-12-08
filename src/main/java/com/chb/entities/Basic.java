package com.chb.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BAS")
public class Basic extends Formule{
    final int nbRdv = 3;

    public Basic() {
        super();
    }


}
