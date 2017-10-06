package net.csa.conference.model;

public class Person {
	private String person_vorname;
	private String person_nachname;

	public Person(String person_vorname, String person_nachname) {
		this.person_vorname = person_vorname;
		this.person_nachname = person_nachname;
	}

    public String getPerson_vorname() {
        return person_vorname;
    }

    public void setPerson_vorname(String person_vorname) {
        this.person_vorname = person_vorname;
    }

    public String getPerson_nachname() {
        return person_nachname;
    }

    public void setPerson_nachname(String person_nachname) {
        this.person_nachname = person_nachname;
    }
}
