package net.csa.twitter.search;

import org.slf4j.Logger;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

class CustomTwitterTemplate extends TwitterTemplate {

    private static final Logger log = getLogger(CustomTwitterTemplate.class);

    CustomTwitterTemplate(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
        super(consumerKey, consumerSecret, accessToken, accessTokenSecret);
    }

    CustomTwitterTemplate(String clientToken) {
        super(clientToken);
    }

    CustomTwitterTemplate(String consumerKey, String consumerSecret) {
        super(consumerKey, consumerSecret);
    }

    @Override
    protected void configureRestTemplate(RestTemplate restTemplate) {
        super.configureRestTemplate(restTemplate);

        // note about this crude but inevitable workaround
        // to prevent interceptors from being called twice instead of once...
        // intended call is at the RestTemplate level
        // another undesired call would occur at the request execution level
        // the problem is that restTemplate.getRequestFactory()
        // registers present interceptors at the request execution level
        // this is undesired here, which is why we temporarily set the interceptors to an empty list
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        restTemplate.setInterceptors(Collections.emptyList());

        // allow for reading the response body multiple times through utilizing a buffer
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(restTemplate.getRequestFactory()));

        // workaround part 2
        restTemplate.setInterceptors(interceptors);

        // register an additional interceptor for logging the request and the response
        restTemplate.getInterceptors().add(new LoggingRequestResponseInterceptor());
    }

}
