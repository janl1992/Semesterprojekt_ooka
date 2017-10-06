import net.csa.tweet.collector.Tweet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by janloeffelsender on 30.03.17.
 */
public class TweetAnalyzed {
    private List<Tweet> tweetlist = new ArrayList<>(  );

    private int id;

    public void addtweet(net.csa.tweet.collector.Tweet tweet){
        tweetlist.add(tweet);
    }

    public TweetAnalyzed(int id){
        this.id = id;
    }

    public void printList() {
        System.out.println("Hashtag-ID: " + id);
        System.out.println("TweetCount: " + tweetcount());
        System.out.println("PositiveTweetCount: " + get_positive_rated_tweet());
        System.out.println("NegativeTweetCount: " + get_negative_rated_tweet());
        System.out.println("NeutralTweetCount: " + get_neutral_rated_tweet());
        System.out.println("------------------------");
        int i = 1;
        for(Tweet tweet : tweetlist) {
            System.out.println("Tweet " + i);
            System.out.println("Score: " + tweet.getRate() + ((tweet.getRate() == 1) ? " (positive)" : (tweet.getRate() == -1) ? " (negative)" : " (neutral)"));
            System.out.println("Text: " + tweet.getTweet());
            System.out.println("------------------------");
            i++;
        }
    }

    public int tweetcount(){
        return tweetlist.size();
    }

    public int get_positive_rated_tweet(){
        int count_tweet_positive = 0;
        for (Tweet tweet:tweetlist)
        {
            if(tweet.getRate()==1){
                count_tweet_positive++;
            }
        }
        return count_tweet_positive;
    }

    public int get_neutral_rated_tweet(){
        int count_tweet_neutral = 0;
        for (Tweet tweet:tweetlist)
        {
            if(tweet.getRate() == 0){
                count_tweet_neutral++;
            }
        }
        return count_tweet_neutral;
    }

    public int get_negative_rated_tweet(){
        int count_tweet_negative = 0;
        for (Tweet tweet:tweetlist)
        {
            if(tweet.getRate() == -1){
                count_tweet_negative++;
            }
        }
        return count_tweet_negative;
    }

}
