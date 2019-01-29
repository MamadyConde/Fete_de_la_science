package fr.m2ccn.istic.fete_de_la_science.model;

import com.google.android.gms.maps.model.LatLng;

public class Ville {
    private double latitude;
    private double longitude;
    private String nomVille;

    public Ville(double latitude, double longitude, String nomVille) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.nomVille = nomVille;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }


}
