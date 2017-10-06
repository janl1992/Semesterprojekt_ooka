package net.csa.conference.repository;

import net.csa.conference.model.Konferenz;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Vector;


public class CRUD_operationen {
	/*@NoRepositoryBean
	public interface CrudRepository<T, ID extends Serializable> extends Repository<T, ID> 
    {
		/*
		<S extends T> S save(S entity); 

		T findOne(ID primaryKey);       

		//Iterable<T> findAll();          

		//Long count();                   

		void delete(T entity);          

		boolean exists(ID primaryKey);
		  

	}
	
//typisiertes Repository für Konferenzen mit CRUD Operationen
	public interface CrudRepository_konferenz extends CrudRepository<Konferenz, String>{
		List<Konferenz> findById(String id);
		List<Konferenz> readById(String id);
		
	}*/
	public interface KonferenzRepository extends MongoRepository<Konferenz, String>{
		ResponseEntity<Konferenz> findById(String id);
		//void insertentity (String id, String name, Integer timeinterval);

//        @RequestMapping(path = "/insertonebyparameter/{id}/{name}/{timeinterval/{strasse}/{hausnummer}/{stadt}/{zipcode}/{land}/{ort_name}/{geo_location}/{twitterhashtag}/{organisatoren}/{sponsoren}", method = RequestMethod.POST)
//        void insertentity(@PathVariable String id, @PathVariable String name, @PathVariable Integer timeinterval, @PathVariable String strasse, @PathVariable Integer hausnummer, @PathVariable String stadt, @PathVariable String zipcode, @PathVariable String land, @PathVariable String ort_name, @PathVariable String geolocation, @PathVariable String twitterhashtag, @PathVariable String organisatoren, @PathVariable String sponsoren);

		//@RequestMapping(path = "/insertonebyparameter/{id}/{name}/{timeinterval/{strasse}/{hausnummer}/{stadt}/{zipcode}/{land}/{ort_name}/{geo_location}/{twitterhashtag}/{vorname}/{nachname}/{organisatoren_name}/{sponsoren_name}", method = RequestMethod.POST)
		ResponseEntity<Konferenz> insertentity(@PathVariable String id, @PathVariable String name, @PathVariable String strasse, @PathVariable Integer hausnummer, @PathVariable String stadt, @PathVariable String zipcode, @PathVariable String land, @PathVariable String ort_name, @PathVariable String geolocation, @PathVariable String twitterhashtag, @PathVariable String organisatoren_name, @PathVariable String sponsoren_name, @PathVariable String vorname, @PathVariable String nachname, @PathVariable String von, @PathVariable String bis);

		Vector<Konferenz> createtestdate();
		@Async
		ResponseEntity<Konferenz> findByName(String name);

	}
}
