package rpg.decorator;

import rpg.core.Character;

public class Telepathy extends CharacterDecorator {

    public Telepathy(Character character) {
        super(character);
    }

    @Override
    public String getDescription() {
        return character.getDescription() + " + [Télépathie]";
    }

    @Override
    public int getPowerLevel() {
        // + 25 points de puissance
        return character.getPowerLevel() + 25;
    }
}