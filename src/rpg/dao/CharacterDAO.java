package rpg.dao;

import rpg.core.Character;
import java.util.ArrayList;
import java.util.List;

public class CharacterDAO implements DAO<Character> {
    private List<Character> characters;

    public CharacterDAO() {
        this.characters = new ArrayList<>();
    }

    @Override
    public void save(Character character) {
        Character existing = findByName(character.getName());
        if (existing != null) {
            characters.remove(existing);
            System.out.println("[DAO] Personnage mis à jour : " + character.getName());
        } else {
            System.out.println("[DAO] Personnage sauvegardé : " + character.getName());
        }
        characters.add(character);
    }

    @Override
    public Character findByName(String name) {
        for (Character character : characters) {
            if (character.getName().equalsIgnoreCase(name)) {
                return character;
            }
        }
        return null;
    }

    @Override
    public List<Character> findAll() {
        return new ArrayList<>(characters);
    }

    @Override
    public void delete(String name) {
        Character toRemove = findByName(name);
        if (toRemove != null) {
            characters.remove(toRemove);
            System.out.println("[DAO] Personnage supprimé : " + name);
        } else {
            System.out.println("[DAO] Personnage non trouvé : " + name);
        }
    }
}