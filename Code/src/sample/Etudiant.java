package sample;

public class Etudiant {

    int matricule_etu;
    String nom_etu;
    String prenom_etu;
    String date_naissance;
    String adresse;


    public Etudiant(int matricule_etu, String nom_etu, String prenom_etu, String date_naiss, String adr) {
        this.matricule_etu = matricule_etu;
        this.nom_etu = nom_etu;
        this.prenom_etu = prenom_etu;
        this.date_naissance = date_naiss;
        this.adresse = adr;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getMatricule_etu() {
        return matricule_etu;
    }

    public void setMatricule_etu(int matricule_etu) {
        this.matricule_etu = matricule_etu;
    }

    public String getNom_etu() {
        return nom_etu;
    }

    public void setNom_etu(String nom_etu) {
        this.nom_etu = nom_etu;
    }

    public String getPrenom_etu() {
        return prenom_etu;
    }

    public void setPrenom_etu(String prenom_etu) {
        this.prenom_etu = prenom_etu;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }


}
