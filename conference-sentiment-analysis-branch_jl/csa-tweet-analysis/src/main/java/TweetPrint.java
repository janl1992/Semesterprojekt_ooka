import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by janloeffelsender on 30.03.17.
 */
public class TweetPrint {
    private List<TweetAnalyzed> tweets_to_analyse = new ArrayList<>(  );

    public void add_result_tweet_list(TweetAnalyzed tweetAnalyzed){
        this.tweets_to_analyse.add( tweetAnalyzed );
    }

    public void print_tweet_result(){
        Collections.sort( tweets_to_analyse, new Comparator<TweetAnalyzed>() {
            @Override
            public int compare(TweetAnalyzed o1, TweetAnalyzed o2) {
                return o2.tweetcount()- o1.tweetcount();
            }
        } );
        System.out.println("---------------------------------------------------");
        System.out.println("RESULT");
        System.out.println("---------------------------------------------------");
        System.out.println("TweetCount Ranking:");
        int i = 1;
        for (TweetAnalyzed tweetAnalysis : tweets_to_analyse) {
            System.out.println(i + ". Place");
            tweetAnalysis.printList();
            System.out.println("------------------------");
            i++;
        }

        Collections.sort(tweets_to_analyse, new Comparator<TweetAnalyzed>() {
            @Override
            public int compare(TweetAnalyzed o1, TweetAnalyzed o2) {
                return o2.get_negative_rated_tweet() - o1.get_positive_rated_tweet();
            }
        });
        System.out.println("---------------------------------------------------");
        System.out.println("Tweet Positive Ranking:");
        i = 1;
        for (TweetAnalyzed tweetAnalysis : tweets_to_analyse) {
            System.out.println(i + ". Place");
            tweetAnalysis.printList();
            System.out.println("------------------------");
            i++;
        }

    }

}
