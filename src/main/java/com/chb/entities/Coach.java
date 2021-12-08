package com.chb.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Coach implements Serializable {
    @Id
    @Column(length = 25)
    private String codeCoach;
    @Column(length = 25)
    private String nomCoach;
    @OneToMany(mappedBy = "coach", fetch = FetchType.LAZY)
    private Collection<Client> clients;

    public Collection<Client> getClients() {
        return clients;
    }

    public void setClients(Collection<Client> clients) {
        this.clients = clients;
    }

    public Collection<Point> getPoints() {
        return points;
    }

    public void setPoints(Collection<Point> points) {
        this.points = points;
    }

    @OneToMany(mappedBy = "coach", fetch = FetchType.LAZY)
    private Collection<Point> points;

    public Coach() {
        super();
    }

    public Coach(String codeCoach) {
        super();
        this.codeCoach = codeCoach;
    }

    public Coach(String codeCoach, String nomCoach) {
        super();
        this.codeCoach = codeCoach;
        this.nomCoach = nomCoach;
    }


    public String getCodeCoach() {
        return codeCoach;
    }

    public void setCodeCoach(String codeCoach) {
        this.codeCoach = codeCoach;
    }

    public String getNomCoach() {
        return nomCoach;
    }

    public void setNomCoach(String nomCoach) {
        this.nomCoach = nomCoach;
    }
}
