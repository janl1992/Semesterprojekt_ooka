package net.csa.conference.model;

/**
 * Created by janloeffelsender on 13.03.17.
 */
public class Adresse {
    String strasse;
    Integer hausnummer;
    String stadt;
    String zipcode;
    String land;

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public Integer getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(Integer hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public Adresse(String strasse, Integer hausnummer, String stadt, String zipcode, String land){
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.stadt = stadt;
        this.zipcode = zipcode;
        this.land = land;
    }

}
