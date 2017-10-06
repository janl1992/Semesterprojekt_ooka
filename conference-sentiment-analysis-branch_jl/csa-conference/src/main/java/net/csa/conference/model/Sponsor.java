package net.csa.conference.model;

public class Sponsor {
	private Person sponsor_person;
	private String sponsor_name;

    public Sponsor(Person sponsor_person, String sponsor_name) {
        this.sponsor_person = sponsor_person;
        this.sponsor_name = sponsor_name;
    }

    public Person getSponsor_person() {
        return sponsor_person;
    }

    public void setSponsor_person(Person sponsor_person) {
        this.sponsor_person = sponsor_person;
    }

    public String getSponsor_name() {
        return sponsor_name;
    }

    public void setSponsor_name(String sponsor_name) {
        this.sponsor_name = sponsor_name;
    }
}
