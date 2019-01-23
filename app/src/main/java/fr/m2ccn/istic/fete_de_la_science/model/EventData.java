package fr.m2ccn.istic.fete_de_la_science.model;

public class EventData {

    private String thematique;
    private String titre;
    private String ville;
    private String image;
    private String animation;
    private String description;
    private String descriptionlongue;
    private String adresse;
    private String codePostale;
    private String horaire;
    private String nomLieu;


    public EventData(String thematique, String titre, String ville, String image, String animation, String description, String descriptionlongue, String adresse, String codePostale, String horaire, String nomLieu) {
        this.thematique = thematique;
        this.titre = titre;
        this.ville = ville;
        this.image = image;
        this.animation = animation;
        this.description = description;
        this.descriptionlongue = descriptionlongue;
        this.adresse = adresse;
        this.codePostale = codePostale;
        this.horaire = horaire;
        this.nomLieu = nomLieu;
    }

    public String getThematique() {
        return thematique;
    }

    public void setThematique(String thematique) {
        this.thematique = thematique;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAnimation() {
        return animation;
    }

    public void setAnimation(String animation) {
        this.animation = animation;
    }

    public String getDescriptionlongue() {
        return descriptionlongue;
    }

    public void setDescriptionlongue(String descriptionlongue) {
        this.descriptionlongue = descriptionlongue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostale() {
        return codePostale;
    }

    public void setCodePostale(String codePostale) {
        this.codePostale = codePostale;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public String getNomLieu() {
        return nomLieu;
    }

    public void setNomLieu(String nomLieu) {
        this.nomLieu = nomLieu;
    }
}
