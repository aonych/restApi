package tjee.onych.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import manager.MovieManager;
import tjee.onych.domain.Movie;

@Stateless
public class MovieService {
	
	@EJB
	MovieManager movieManager;
	
	public void add(Movie movie) {
		if(!movieManager.isExistMovie(movie.getTitle()))
			movieManager.add(movie);		
	}

	
	public void update(Movie movie) {
		movieManager.update(movie);	
	}
	
	public void delete(int id) {
		movieManager.delete(id);
		
	}

	public List<Movie> getAll() {
		return movieManager.getAll();
	}

	
	public Movie getById(int id) {
		return movieManager.getById(id);
	}
	
	public List<Movie> getByCountry(String country) {	
		return movieManager.getByCountry(country);
		
	}
	
}