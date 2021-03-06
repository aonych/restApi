package manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tjee.onych.domain.Character;

@Stateless
public class CharacterManager {
	
	@PersistenceContext
	EntityManager entityManager;

	public void add(Character character) {
		entityManager.persist(character);
		entityManager.flush();

	}

	public void update(Character character) {
		entityManager.merge(character);

	}

	public void delete(int id) {
		entityManager.remove(entityManager.find(Character.class, id));

	}

	@SuppressWarnings("unchecked")
	public List<Character> getAll() {
		Query query = entityManager.createQuery("SELECT c FROM Character c");
		List<Character> character = query.getResultList();
		return character;
	}

	public Character getById(int id) {
		return entityManager.find(Character.class, id);
	}

	public boolean isExistCharacter(String name) {
		int result = Integer.parseInt(entityManager
				.createQuery("SELECT COUNT(c.id) FROM Character c where c.name=:Name")
				.setParameter("Name", name).getResultList().get(0).toString());
		if(result != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Character> getByType(String type) {
		Query query = entityManager.createQuery("SELECT c FROM Character c WHERE c.type = :Type")
				.setParameter("Type", type);
		List<Character> character = query.getResultList();
		return character;
	}
	
	public List<Character> getByMovie(int id) {
		List<Character> listByMovie = new ArrayList<Character>();
		List<Character> list = getAll();
		for(Character character:list) {
			if(character.getMovie().getId()==id)
				listByMovie.add(character);
		}
		
		return listByMovie;
	}
	
}
