import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.csa.tweet.collector.*;
import net.csa.tweet.collector.Tweet;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by janloeffelsender on 30.03.17.
 */
public class TweetCloudAnalysis {
    private final String apiKey = "c090975119a5740a235d90669f491c90";

    private String createRequestUrl(String textToByAnalyzed) throws UnsupportedEncodingException {
        String url = "https://api.meaningcloud.com/sentiment-2.1" + "?key=" + apiKey + "&of=json" + "&lang=en" + "&model=general" + "&txt=" + URLEncoder.encode( textToByAnalyzed, "UTF-8" );
        return url;
    }

    public Tweet analyzed_tweet(Tweet tweet) {
        try {
            URL url = new URL( createRequestUrl( tweet.getTweet() ) );
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod( "POST" );
            connection.setRequestProperty( "Accept", "*/*" );
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( connection.getInputStream() ) );
            String return_value_mc = bufferedReader.readLine();
            while (return_value_mc != null) {
                stringBuilder.append( return_value_mc );
                return_value_mc = bufferedReader.readLine();
            }
            String tweet_result = stringBuilder.toString();
            JsonObject jsonobject = new Gson().fromJson( tweet_result, JsonObject.class );
            String rate = jsonobject.get( "score_tag" ).getAsString();
            if (rate.equals( "P+" ) || rate.equals( "P" )) {
                tweet.setRate( 1 );
            } else if (rate.equals( "NEU" ) || rate.equals( "NONE" )) {
                tweet.setRate( 0 );
            } else if (rate.equals( "N" ) || rate.equals( "N+" )) {
                tweet.setRate( -1 );
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tweet;
    }
}
