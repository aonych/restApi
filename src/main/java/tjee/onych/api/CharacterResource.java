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

import tjee.onych.domain.Character;
import tjee.onych.service.CharacterService;
import tjee.onych.service.MovieService;

@Stateless
@Path("/characters")
public class CharacterResource {
	
	@EJB
	CharacterService characterService;
	@EJB
	MovieService movieService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Character> getCharacter() {
		return characterService.getAll();
	}
	
	@GET
	@Path("/{movie}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Character> getByMovie(@PathParam("movie") int id) {
		return characterService.getByMovie(id);
	}
	
	@PUT
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Character add(@FormParam("name") String name, @FormParam("quality") String quality, 
			@FormParam("type") String type, @FormParam("movie") int movieID) {
		Character character = new Character();
		character.setName(name);
		character.setQuality(quality);
		character.setType(type);
		character.setMovie(movieService.getById(movieID));
		characterService.add(character);

		return character;
	}

	@POST
	@Path("/edit/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Character edit(@PathParam("id") int id, @FormParam("name") String name, @FormParam("quality") String quality, 
			@FormParam("type") String type, @FormParam("movie") int movieID) {
		Character character = characterService.getById(id);
		character.setName(name);
		character.setQuality(quality);
		character.setType(type);
		character.setMovie(movieService.getById(movieID));
		characterService.update(character);

		return character;
	}
	
	@GET
	@Path("/show/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Character view(@PathParam("id") int id) {
		return characterService.getById(id);
	}

	@GET
	@Path("/showByType/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Character> showByType(@PathParam("type") String type) {
		return characterService.getByType(type);
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") int id) {
		characterService.delete(id);
	}
	
	
	
	
	
}