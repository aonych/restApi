package tjee.onych.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import manager.CharacterManager;
import manager.MovieManager;
import tjee.onych.domain.Character;

@Stateless
public class CharacterService {
	
	@EJB
	MovieManager movieManager;
	@EJB
	CharacterManager characterManager;
	
	public void add(Character character) {
		if (!characterManager.isExistCharacter(character.getName())) {
			characterManager.add(character);
		}
	}
	
	public List<Character> getByMovie(int id) {
		return characterManager.getByMovie(id);
	}

	public void update(Character character) {
		characterManager.update(character);
	}

	public void delete(int id) {
		characterManager.delete(id);

	}

	public List<Character> getAll() {
		return characterManager.getAll();
	}

	public Character getById(int id) {
		return characterManager.getById(id);
	}

	public List<Character> getByType(String type) {
		return characterManager.getByType(type);
	}
	
}