package net.csa.conference.tweets;

import net.csa.conference.model.Konferenz;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/orchestrator")
public class Orchestrator {

    @RequestMapping("/findbyid/{hashtag}")
    @ResponseBody
    String orchestrate(@PathVariable String hashtag) {
        Collection twitter_response;
        Konferenz mogodb_response;
        String resultstring;
        twitter_response = twitter_response(hashtag);
        mogodb_response = mogodb_response(hashtag);
        resultstring = twitter_response.toString() + "\n" + "\n" + "\n" + mogodb_response.formatstring();
        //mogodb_response(hashtag);
        return resultstring;
    }


    public Collection twitter_response(String hashtag) {
        ResponseEntity<Collection> twitter_response = null;
        RestTemplate restTemplate = new RestTemplate();
        String twitter_fooRessourceurl = null;
        twitter_fooRessourceurl = "http://csa-twitter:9020/twitter/search/" + hashtag;
        //"http://csa-twitter:9020/twitter/search/"
        //System.out.println(twitter_fooRessourceurl);
        twitter_response = restTemplate.getForEntity(twitter_fooRessourceurl, Collection.class);
        //System.out.println(twitter_response.getBody());
        return twitter_response.getBody();
    }

    public Konferenz mogodb_response(String hashtag) {
        RestTemplate restTemplate = new RestTemplate();
        String mogodb_fooResourceUrl = null;
        ResponseEntity<Konferenz> mogodb_response = null;

        mogodb_fooResourceUrl = "http://csa-conference:8090/conference/search/findbyname/" + hashtag;
        //"http://csa-conference:8090/conference/search/findbyname/"
        //System.out.println(mogodb_fooResourceUrl);
        mogodb_response = restTemplate.getForEntity(mogodb_fooResourceUrl, Konferenz.class);
        return mogodb_response.getBody();
    }
    //testcommit
}
