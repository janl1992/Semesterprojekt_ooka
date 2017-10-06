package net.csa.conference.rest;

import net.csa.conference.model.*;
import net.csa.conference.repository.CRUD_operationen.KonferenzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Vector;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@RestController
@RequestMapping("/conference/search")
public class ConferenceController implements KonferenzRepository {
    private final MongoOperations mongoOps;
    private final static String [] testhashtag = {"FrostCon","CeBIT","Bitkom","googleio","E3","scaladays"};
    private final static String datum = "13.03.201";

    @Autowired
    public ConferenceController(MongoOperations mongoOps) {
        this.mongoOps = mongoOps;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/findbyid/uuid/{id}", method = RequestMethod.GET, produces = "application/json" )
    @ResponseBody
    public ResponseEntity<Konferenz> findById(@PathVariable String id) {
        //System.out.println("k_search id: "+k_search.getUuid()+"\n"+"k_search name: "+k_search.getKonferenz_name()+"\n"+"k_search Zeitinterval: "+k_search.getZeitinterval()+"\n");
        return new ResponseEntity<Konferenz>( mongoOps.findById( id, Konferenz.class ), HttpStatus.OK );
        //mongoOps.findById(id, Konferenz.class)
        //return new ResponseEntity<List<Conference>>(conferences, HttpStatus.OK);
    }



    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/insertonebyparameter/{id}/{name}/{strasse}/{hausnummer}/{stadt}/{zipcode}/{land}/{ort_name}/{geolocation}/{twitterhashtag}/{vorname}/{nachname}/{organisatoren_name}/{sponsoren_name}/{von}/{bis}", method = RequestMethod.POST)
    public ResponseEntity<Konferenz> insertentity(@PathVariable String id, @PathVariable String name, @PathVariable String strasse, @PathVariable Integer hausnummer, @PathVariable String stadt, @PathVariable String zipcode, @PathVariable String land, @PathVariable String ort_name, @PathVariable String geolocation, @PathVariable String twitterhashtag, @PathVariable String organisatoren_name, @PathVariable String sponsoren_name, @PathVariable String vorname, @PathVariable String nachname, @PathVariable String von, @PathVariable String bis) {
        //Konferenz k = new Konferenz();
        String datum_von;
        String datum_bis;
        Konferenz konferenz;
        Adresse adresse;
        Veranstaltungsort veranstaltungsort;
        Person person;
        Sponsor sponsor;
        GeoLocation geoloc;
        Twitterhashtag twitterhash;
        Organisator organisator;
        //HttpStatus ht;
        //ht = new HttpStatus(200, "Konferenz was inserted")
        datum_von = von;
        datum_bis = bis;
        adresse = new Adresse(strasse, hausnummer, stadt, zipcode, land);
        veranstaltungsort = new Veranstaltungsort(ort_name, adresse);
        person = new Person(vorname, nachname);
        sponsor = new Sponsor(person, sponsoren_name);
        geoloc = new GeoLocation( geolocation);
        twitterhash = new Twitterhashtag(twitterhashtag);
        organisator = new Organisator( person, name );


        konferenz = new Konferenz( id, name, veranstaltungsort, geoloc, sponsor, person, twitterhash, organisator, datum_von, datum_bis);
        /*k.setUuid(id);
        k.setKonferenz_name(name);
        k.setZeitinterval(timeinterval);*/
        mongoOps.insert(konferenz);
        return new ResponseEntity<Konferenz>( konferenz, HttpStatus.OK );
    }

    @Override
    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/inserttestdata", method = RequestMethod.POST, produces = "application/json")
    public Vector<Konferenz> createtestdate() {
        String stringinteger;
        Konferenz konferenz;
        Adresse adresse;
        Veranstaltungsort veranstaltungsort;
        Person person;
        Sponsor sponsor;
        GeoLocation geoloc;
        Twitterhashtag twitterhash;
        Organisator organisator;
        Vector<Konferenz> ve_k = new Vector<Konferenz>(  );
        for (int i = 1; i < testhashtag.length; i++) {
            stringinteger = Integer.toString( i );
            adresse = new Adresse("teststraße"+stringinteger, i, "teststadt"+stringinteger, "4345"+stringinteger, stringinteger);
            veranstaltungsort = new Veranstaltungsort("testort"+stringinteger, adresse);
            person = new Person("testvorname"+stringinteger, "testnachname"+stringinteger);
            sponsor = new Sponsor(person, "testperson"+stringinteger);
            geoloc = new GeoLocation( "testgeo"+stringinteger);
            twitterhash = new Twitterhashtag(testhashtag[i]);
            organisator = new Organisator( person, "testname"+stringinteger );
            konferenz = new Konferenz( stringinteger, "testname"+stringinteger, veranstaltungsort, geoloc, sponsor, person, twitterhash, organisator, datum+stringinteger, datum + Integer.toString( i + 1));
            ve_k.add(konferenz);

            mongoOps.insert(konferenz);
        }
        return ve_k;

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/findbyname/{twitterhash}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Konferenz> findByName(@PathVariable String twitterhash) {
        //List<Konferenz> ve_ko;
        //Konferenz k_search_n;
        //ve_ko = new Vector<Konferenz>();
        //ve_ko = mongoOps.find(org.springframework.data.mongodb.core.query.Query.query(Criteria.where("name").is(input_name)), Konferenz.class);
        //k_search_n = mongoOps.findOne(org.springframework.data.mongodb.core.query.Query.query(Criteria.where("name").is(name)), Konferenz.class);
        //System.out.println("k_search: "+k_search_n.getUuid()+"\n"+"name: "+k_search_n.getKonferenz_name()+"\n"+"Zeitinterval: "+k_search_n.getZeitinterval()+"\n");
        /*for (Konferenz aVe_ko : ve_ko) {
            System.out.println("ve_ko id: " + aVe_ko.getUuid() + "\n" + "ve_ko name: " + aVe_ko.getKonferenz_name() + "\n" + "ve_ko Zeitinterval: " + aVe_ko.getZeitinterval() + "\n");
        }*/

        return new ResponseEntity<Konferenz>(mongoOps.findOne(Query.query(where("twitterhash.hashtag").is(twitterhash)), Konferenz.class), HttpStatus.OK);

        //return new ResponseEntity<List<Conference>>(conferences, HttpStatus.OK);
        //mongoOps.findOne(Query.query(where("twitterhash.hashtag").is(twitterhash)), Konferenz.class)
    }

    @Override
    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/deleteall", method = RequestMethod.POST)
    public void deleteAll() {
        mongoOps.dropCollection( Konferenz.class );
//        Set<String> collectionnames;
//        collectionnames = mongoOps.getCollectionNames();
//        //collectionnames.toArray().length;
//        for (int i = 0; i < collectionnames.toArray().length; i++) {
//            mongoOps.dropCollection( Integer.toString( i ) );
//        }
        // TODO Auto-generated method stub

    }

    //----------------------------------------------------------------------------------//

    @RequestMapping(path = "/findone", method = RequestMethod.GET)
    public Konferenz findOne(String id) {
        return null;
    }

    @RequestMapping(path = "/deleteone", method = RequestMethod.DELETE)
    public void delete(Konferenz entity) {
        // TODO Auto-generated method stub
    }

    public <S extends Konferenz> S insert(S entity) {
        return null;
    }


    @RequestMapping(path = "/saveone", method = RequestMethod.PUT)
    public <S extends Konferenz> S save(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Konferenz> List<S> save(Iterable<S> entites) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Konferenz> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Konferenz> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public <S extends Konferenz> List<S> insert(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Konferenz> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Konferenz> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<Konferenz> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public boolean exists(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Iterable<Konferenz> findAll(Iterable<String> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub

    }


    @Override
    public void delete(Iterable<? extends Konferenz> entities) {
        // TODO Auto-generated method stub

    }


    @Override
    public <S extends Konferenz> S findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Konferenz> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Konferenz> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends Konferenz> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }


}
 