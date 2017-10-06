package net.csa.conference.model;

public class GeoLocation {
	String geolocation;

	public GeoLocation(String geolocation) {
		this.geolocation = geolocation;
	}

    public String getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(String geolocation) {
        this.geolocation = geolocation;
    }
}
