package net.csa.conference.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Konferenz {
    @Id
    private String uuid;
    private String konferenz_name;
    private String von;
    private String bis;
    private Twitterhashtag twitterhash;
    private Veranstaltungsort ort;
    private GeoLocation geolocation;
    private Sponsor sponsor;
    private Person person;
    private Organisator organisator;


    public Konferenz(String uuid, String konferenz_name, Veranstaltungsort ort, GeoLocation geolocation, Sponsor sponsor, Person person, Twitterhashtag twitterhash, Organisator organisator, String von, String bis){
        this.uuid = uuid;
        this.konferenz_name = konferenz_name;
        this.ort = ort;
        this.geolocation = geolocation;
        this.sponsor = sponsor;
        this.person = person;
        this.twitterhash = twitterhash;
        this.organisator = organisator;
        this.von = von;
        this.bis = bis;
    }



    public Veranstaltungsort getOrt() {
        return ort;
    }

    public void setOrt(Veranstaltungsort ort) {
        this.ort = ort;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uUID) {
        uuid = uUID;
    }

    public String getKonferenz_name() {
        return konferenz_name;
    }

    public void setKonferenz_name(String konferenz_name) {
        this.konferenz_name = konferenz_name;
    }


    public String formatstring() {
        String returnstring = uuid + " " + konferenz_name + " " + von + bis;
        return returnstring;
    }

    public GeoLocation getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(GeoLocation geolocation) {
        this.geolocation = geolocation;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Twitterhashtag getTwitterhash() {
        return twitterhash;
    }

    public void setTwitterhash(Twitterhashtag twitterhash) {
        this.twitterhash = twitterhash;
    }

    public String getVon() {
        return von;
    }

    public void setVon(String von) {
        this.von = von;
    }

    public String getBis() {
        return bis;
    }

    public void setBis(String bis) {
        this.bis = bis;
    }

    public Organisator getOrganisator() {
        return organisator;
    }

    public void setOrganisator(Organisator organisator) {
        this.organisator = organisator;
    }
}
