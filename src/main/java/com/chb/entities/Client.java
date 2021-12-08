package com.chb.entities;
import com.sun.istack.NotNull;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Client implements Serializable {
    @Id @GeneratedValue
    private Long codeClient;
    @Column(length = 25)
    @NotNull
    private String nomClient;
    @Column(length = 25)
    @NotNull
    private String prenomClient;
    private int ageClient;
    @NotNull
    private double poidsClient;
    @NotNull
    private double objectif;
    private double taille;
    private String sexe;
    @NotNull
    private String emailClient;
    private String numClient;
    private String adresse;
    private String antFamilliaux;
    private String antMedicaux;
    private double poidsActuel;
    private Boolean cureEnPause = false;

    @ManyToOne
    @JoinColumn(name="CODE_COACH")
    private Coach coach;
    @ManyToOne
    @JoinColumn(name = "CODE_FORM")
    private Formule formule;
    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    private Collection<Point> points;

    public Client() {
        super();
    }

    public Client(String prenomClient, String nomClient, int ageClient, double poidsClient, double objectif, double taille, String sexe, String emailClient, String numClient, String adresse, String antFamilliaux,
            String antMedicaux, Coach coach, Formule formule) {
        super();
        this.prenomClient = prenomClient;
        this.nomClient = nomClient;
        this.ageClient = ageClient;
        this.poidsClient = poidsClient;
        this.poidsActuel = poidsClient;
        this.objectif = objectif;
        this.taille = taille;
        this.sexe = sexe;
        this.emailClient = emailClient;
        this.numClient = numClient;
        this.adresse = adresse;
        this.antFamilliaux = antFamilliaux;
        this.antMedicaux = antMedicaux;
        this.coach = coach;
        this.formule = formule;
    }

    public Long getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(Long codeClient) {
        this.codeClient = codeClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public int getAgeClient() {
        return ageClient;
    }

    public void setAgeClient(int ageClient) {
        this.ageClient = ageClient;
    }

    public double getPoidsClient() {
        return poidsClient;
    }

    public void setPoidsClient(double poidsClient) {
        this.poidsClient = poidsClient;
    }

    public double getObjectif() {
        return objectif;
    }

    public void setObjectif(double objectif) {
        this.objectif = objectif;
    }

    public double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public String getNumClient() {
        return numClient;
    }

    public void setNumClient(String numClient) {
        this.numClient = numClient;
    }
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getAntFamilliaux() {
        return antFamilliaux;
    }

    public void setAntFamilliaux(String antFamilliaux) {
        this.antFamilliaux = antFamilliaux;
    }

    public String getAntMedicaux() {
        return antMedicaux;
    }

    public void setAntMedicaux(String antMedicaux) {
        this.antMedicaux = antMedicaux;
    }

    public double getPoidsActuel() {
        return poidsActuel;
    }

    public void setPoidsActuel(double poidsActuel) {
        this.poidsActuel = poidsActuel;
    }

    public Boolean getCureEnPause() {
        return cureEnPause;
    }

    public void setCureEnPause(Boolean cureEnPause) {
        this.cureEnPause = cureEnPause;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Formule getFormule() {
        return formule;
    }

    public void setFormule(Formule formule) {
        this.formule = formule;
    }

    public Collection<Point> getPoints() {
        return points;
    }

    public void setPoints(Collection<Point> points) {
        this.points = points;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }
}
