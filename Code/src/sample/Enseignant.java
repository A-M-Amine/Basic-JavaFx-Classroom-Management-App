package sample;

public class Enseignant {

    int matricule_ens;
    String nom_ens;
    String prenom_ens;

    public Enseignant(int matricule_ens, String nom_ens, String prenom_ens) {
        this.matricule_ens = matricule_ens;
        this.nom_ens = nom_ens;
        this.prenom_ens = prenom_ens;
    }

    public int getMatricule_ens() {
        return matricule_ens;
    }

    public void setMatricule_ens(int matricule_ens) {
        this.matricule_ens = matricule_ens;
    }

    public String getNom_ens() {
        return nom_ens;
    }

    public void setNom_ens(String nom_ens) {
        this.nom_ens = nom_ens;
    }

    public String getPrenom_ens() {
        return prenom_ens;
    }

    public void setPrenom_ens(String prenom_ens) {
        this.prenom_ens = prenom_ens;
    }
}
