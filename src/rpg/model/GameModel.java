package rpg.model;

import rpg.core.Character;
import rpg.core.CharacterGroup;
import rpg.dao.CharacterDAO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GameModel {
    private CharacterDAO dao;
    private List<CharacterGroup> groups;
    private List<CharacterGroup> armies;

    public GameModel() {
        this.dao = new CharacterDAO();
        this.groups = new ArrayList<>();
        this.armies = new ArrayList<>();
    }

    // Gestion des personnages
    public void saveCharacter(Character character) {
        dao.save(character);
    }

    public Character findCharacterByName(String name) {
        return dao.findByName(name);
    }

    public List<Character> getAllCharacters() {
        return dao.findAll();
    }

    public void deleteCharacter(String name) {
        dao.delete(name);
    }

    // Tri des personnages
    public List<Character> getCharactersSortedByName() {
        List<Character> characters = new ArrayList<>(dao.findAll());
        characters.sort(Comparator.comparing(Character::getName));
        return characters;
    }

    public List<Character> getCharactersSortedByPower() {
        List<Character> characters = new ArrayList<>(dao.findAll());
        characters.sort(Comparator.comparingInt(Character::getPowerLevel).reversed());
        return characters;
    }

    public List<Character> getCharactersSortedByRole() {
        List<Character> characters = new ArrayList<>(dao.findAll());
        characters.sort(Comparator.comparing(Character::getRole));
        return characters;
    }

    // Gestion des groupes
    public void addGroup(CharacterGroup group) {
        groups.add(group);
    }

    public List<CharacterGroup> getAllGroups() {
        return new ArrayList<>(groups);
    }

    public CharacterGroup getGroup(int index) {
        if (index >= 0 && index < groups.size()) {
            return groups.get(index);
        }
        return null;
    }

    // Gestion des armées
    public void addArmy(CharacterGroup army) {
        armies.add(army);
    }

    public List<CharacterGroup> getAllArmies() {
        return new ArrayList<>(armies);
    }

    public CharacterGroup getArmy(int index) {
        if (index >= 0 && index < armies.size()) {
            return armies.get(index);
        }
        return null;
    }
}