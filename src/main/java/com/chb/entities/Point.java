package com.chb.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Point implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codePoint;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datePoint;
    private double poidsPerdus;
    private int semaine;

    private Boolean routineAlimentaire;
    private Boolean mindfulEating;
    private Boolean mindfulEatingAffecte;
    private Boolean hydratation;
    private Boolean hydratationAffecte;
    private Boolean sport;
    private Boolean sportAffecte;
    private Boolean stress;
    private Boolean stressAffecte;
    private Boolean sommeil;
    private Boolean sommeilAffecte;
    private String infosSupplementaire;

    @ManyToOne
    @JoinColumn(name = "CODE_COACH")
    private Coach coach;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "CODE_CLI")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "CODE_FORM")
    private Formule formule;

    public Point() {
        super();
    }

    public Point(Date datePoint, double poidsPerdus, int semaine, Boolean routineAlimentaire, Boolean mindfulEating, Boolean hydratation, Boolean sport, String infosSupplementaire, Boolean stress, Boolean sommeil, Coach coach, Client client,Formule formule) {
        super();
        this.datePoint = datePoint;
        this.poidsPerdus = poidsPerdus;
        this.semaine = semaine;
        this.routineAlimentaire = routineAlimentaire;
        this.mindfulEating = mindfulEating;
        this.hydratation = hydratation;
        this.sport = sport;
        this.infosSupplementaire = infosSupplementaire;
        this.stress = stress;
        this.sommeil = sommeil;
        this.coach = coach;
        this.client = client;
        this.formule = formule;
    }

    public Long getCodePoint() {
        return codePoint;
    }

    public void setCodePoint(Long codePoint) {
        this.codePoint = codePoint;
    }

    public Date getDatePoint() {
        return datePoint;
    }

    public void setDatePoint(Date datePoint) {
        this.datePoint = datePoint;
    }

    public double getPoidsPerdus() {
        return poidsPerdus;
    }

    public void setPoidsPerdus(double poidsPerdus) {
        this.poidsPerdus = poidsPerdus;
    }

    public int getSemaine() {
        return semaine;
    }

    public void setSemaine(int semaine) {
        this.semaine = semaine;
    }

    public Boolean getRoutineAlimentaire() {
        return routineAlimentaire;
    }

    public void setRoutineAlimentaire(Boolean routineAlimentaire) {
        this.routineAlimentaire = routineAlimentaire;
    }

    public Boolean getMindfulEating() {
        return mindfulEating;
    }

    public void setMindfulEating(Boolean mindfulEating) {
        this.mindfulEating = mindfulEating;
    }

    public Boolean getHydratation() {
        return hydratation;
    }

    public void setHydratation(Boolean hydratation) {
        this.hydratation = hydratation;
    }

    public Boolean getSport() {
        return sport;
    }

    public void setSport(Boolean sport) {
        this.sport = sport;
    }

    public String getInfosSupplementaire() {
        return infosSupplementaire;
    }

    public void setInfosSupplementaire(String infosSupplementaire) {
        this.infosSupplementaire = infosSupplementaire;
    }

    public Boolean getStress() {
        return stress;
    }

    public void setStress(Boolean stress) {
        this.stress = stress;
    }

    public Boolean getSommeil() {
        return sommeil;
    }

    public void setSommeil(Boolean sommeil) {
        this.sommeil = sommeil;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Formule getFormule() {
        return formule;
    }

    public void setFormule(Formule formule) {
        this.formule = formule;
    }


    public Boolean getMindfulEatingAffecte() {
        return mindfulEatingAffecte;
    }

    public void setMindfulEatingAffecte(Boolean mindfulEatingAffecte) {
        this.mindfulEatingAffecte = mindfulEatingAffecte;
    }
    public Boolean getHydratationAffecte() {
        return hydratationAffecte;
    }

    public void setHydratationAffecte(Boolean hydratationAffecte) {
        this.hydratationAffecte = hydratationAffecte;
    }

    public Boolean getSportAffecte() {
        return sportAffecte;
    }

    public void setSportAffecte(Boolean sportAffecte) {
        this.sportAffecte = sportAffecte;
    }

    public Boolean getStressAffecte() {
        return stressAffecte;
    }

    public void setStressAffecte(Boolean stressAffecte) {
        this.stressAffecte = stressAffecte;
    }

    public Boolean getSommeilAffecte() {
        return sommeilAffecte;
    }

    public void setSommeilAffecte(Boolean sommeilAffecte) {
        this.sommeilAffecte = sommeilAffecte;
    }

}
