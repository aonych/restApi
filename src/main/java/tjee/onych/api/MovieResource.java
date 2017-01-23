package tjee.onych.api;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tjee.onych.domain.Movie;
import tjee.onych.service.MovieService;

@Stateless
@Path("/movies")
public class MovieResource {
	
	@EJB
	MovieService movieService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movie> getMovie() {
		return movieService.getAll();
	}
	
	@PUT
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Movie add(@FormParam("title") String title, @FormParam("country") String country, 
			@FormParam("production") String production, @FormParam("year") int year) {
		Movie movie = new Movie();
		movie.setTitle(title);
		movie.setCountry(country);
		movie.setProduction(production);
		movie.setYear(year);
		movieService.add(movie);

		return movie;
	}

	@POST
	@Path("/edit/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Movie edit(@PathParam("id") int id, @FormParam("title") String title, 
			@FormParam("country") String country, @FormParam("production") String production,
			@FormParam("year") int year) {
		Movie movie = movieService.getById(id);
		movie.setTitle(title);
		movie.setCountry(country);
		movie.setProduction(production);
		movie.setYear(year);
		movieService.update(movie);

		return movie;
	}

	@GET
	@Path("/show/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Movie show(@PathParam("id") int id) {
		return movieService.getById(id);
	}

	@GET
	@Path("/showByCountry/{country}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movie> showByTitle(@PathParam("country") String country) {
		return movieService.getByCountry(country);
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") int id) {
		movieService.delete(id);
	}
}