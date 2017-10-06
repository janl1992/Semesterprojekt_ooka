package net.csa.conference.model;

/**
 * Created by janloeffelsender on 13.03.17.
 */
public class Organisator {
    private Person person;
    private String name;

    public Organisator(Person person, String name) {
        this.person = person;
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
