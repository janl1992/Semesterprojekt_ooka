package net.csa.conference.model;

/**
 * Created by janloeffelsender on 13.03.17.
 */
public class Twitterhashtag {
    private String hashtag;

    public Twitterhashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }
}
