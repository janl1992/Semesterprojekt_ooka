package net.csa.conference.model;

public class Veranstaltungsort {
	private String ort_name;
	private Adresse ort_adresse;

    public Veranstaltungsort(String ort_name, Adresse ort_adresse) {
        this.ort_name = ort_name;
        this.ort_adresse = ort_adresse;
    }

    public String getOrt_name() {
        return ort_name;
    }

    public void setOrt_name(String ort_name) {
        this.ort_name = ort_name;
    }

    public Adresse getOrt_adresse() {
        return ort_adresse;
    }

    public void setOrt_adresse(Adresse ort_adresse) {
        this.ort_adresse = ort_adresse;
    }


}
