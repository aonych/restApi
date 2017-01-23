package manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tjee.onych.domain.Movie;


@Stateless
public class MovieManager {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public void add(Movie movie) {
		if(!isExistMovie(movie.getTitle())){
			entityManager.persist(movie);
			entityManager.flush();
		} else {
			System.out.println("Film o takim tytule juz istnieje!");
		}

	}

	public void update(Movie movie) {
		entityManager.merge(movie);

	}

	public void delete(int id) {
		entityManager.remove(entityManager.find(Movie.class, id));

	}

	@SuppressWarnings("unchecked")
	public List<Movie> getAll() {
		Query query = entityManager.createQuery("SELECT m FROM Movie m");
		List<Movie> movies = query.getResultList();
		return movies;
	}

	public Movie getById(int id) {
		return entityManager.find(Movie.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Movie> getByCountry(String country) {
		Query query = entityManager.createQuery("SELECT m FROM Movie m WHERE m.country = :Country")
				.setParameter("Country", country);
		List<Movie> movies = query.getResultList();
		return movies;
	}

	
	public boolean isExistMovie(String title) {
		int result = Integer.parseInt(entityManager.createQuery
				("SELECT COUNT(m.id) FROM Movie m where m.title=:Title")
				.setParameter("Title", title).getResultList().get(0).toString());
		if(result==0){
			return false;
		}else{
			return true;
		}
	}
	
}