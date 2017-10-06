package net.csa.conference.model;

import net.csa.conference.rest.ConferenceController;
import org.junit.Test;
import org.springframework.data.mongodb.core.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by janloeffelsender on 20.03.17.
 */
public class testclass {
    ConferenceController testconference;
    ResponseEntity<Konferenz> konferenz;
    ResponseEntity<Konferenz> konferenz_;
    private final MongoOperations mongoOps;
    private final static String [] testhashtag = {"FrostCon","CeBIT","Bitkom","googleio","E3"};

    public testclass(MongoOperations mongoOps) {
        this.mongoOps = mongoOps;
    }

    @Test
    public void testfunction(){
//        ResponseEntity<Konferenz> testkonferenz;
//        String id = "256";
//        testconference.insertentity(id,"testkonferenz",7,"teststraße",8,"teststadt","54234","testland","testort","testgeo","testhashtag","testorga","testsponsor","testvorname","testnachname"  );
//        testkonferenz = testconference.findById( id );
//        Konferenz k = (Konferenz) testkonferenz.getClass();
//        Assert.assertTrue( , k.);

//        HttpURLConnection conn = null;
//        URL url = null;
//        try {
//            URL url = new URL("localhost:8080/conference/search/insertonebyparameter/390/meinetestkonferenz_/5/hansaring/6/Koeln/53111/de/Ehrenfeld/testgeo/koelnkonferenz_/jan/loeffelsender/testorga/dtag");
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            url = new URL("localhost:8080/conference/search/findbyid/uuid/390");
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String line;
//            String result = null;
//            while ((line = rd.readLine()) != null) {
//                result.concat( line );
//            }
//            rd.close();
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Konferenz k = new Konferenz(  );
//        RestTemplate rt = new RestTemplate(  );
//        Konferenz rk = rt.postForEntity("localhost:8080/insertonebyparameter/{id}/{name}/{timeinterval}/{strasse}/{hausnummer}/{stadt}/{zipcode}/{land}/{ort_name}/{geolocation}/{twitterhashtag}/{vorname}/{nachname}/{organisatoren_name}/{sponsoren_name}", k, ResponseEntity.class);
//        rk.getUuid();
    }
    //    TJWSEmbeddedJaxrsServer server = new TJWSEmbeddedJaxrsServer();
//server.setPort("12345");
//server.getDeployment().getResources().add(myResourceInstance);
}
