package rpg.builder;

import rpg.core.Character;

public class CharacterBuilder {

    private String name;
    private String role;
    private int strength;
    private int agility;
    private int intelligence;

    public CharacterBuilder() {
        this.name = "Inconnu";
        this.role = "Aventurier";
        this.strength = 10;
        this.agility = 10;
        this.intelligence = 10;
    }

    public CharacterBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public CharacterBuilder setRole(String role) {
        this.role = role;
        return this;
    }

    public CharacterBuilder setStrength(int strength) {
        this.strength = strength;
        return this;
    }

    public CharacterBuilder setAgility(int agility) {
        this.agility = agility;
        return this;
    }

    public CharacterBuilder setIntelligence(int intelligence) {
        this.intelligence = intelligence;
        return this;
    }

    public Character build() {
        return new Character(name, role, strength, agility, intelligence);
    }
}
