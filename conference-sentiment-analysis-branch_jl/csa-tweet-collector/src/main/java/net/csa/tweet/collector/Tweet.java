package net.csa.tweet.collector;

/**
 * Created by janloeffelsender on 27.03.17.
 */
public class Tweet {
    private String tweet;

    private int rate;

    public Tweet(String tweet) {
        this.tweet = tweet;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
