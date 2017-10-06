package net.csa.twitter.search;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.social.twitter.api.Twitter;

@Configuration
//@PropertySource("file:/Users/janloeffelsender/git/conference-sentiment-analysis_copie_/conference-sentiment-analysis/csa-twitter-search/src/main/java/net/csa/twitter/search/my_twitter_consumer_key.rtf")
//@PropertySource("file:/Users/janloeffelsender/git/conference-sentiment-analysis_copie_/conference-sentiment-analysis/csa-twitter-search/src/main/java/net/csa/twitter/search/my_twitter_consumer_secret.rtf")
class TwitterSearchConfiguration {

    @Value("${key}")
    private String key;

    @Value("${secret}")
    private String secret;

    @Bean
    public Twitter twitter() {
        return new CustomTwitterTemplate(key, secret);
    }

}
