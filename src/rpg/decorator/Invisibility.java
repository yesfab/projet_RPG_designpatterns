package rpg.decorator;

import rpg.core.Character;

public class Invisibility extends CharacterDecorator {

    public Invisibility(Character character) {
        super(character);
    }

    @Override
    public String getDescription() {
        return character.getDescription() + " + [Invisibilité]";
    }

    @Override
    public int getPowerLevel() {
        // + 15 points de puissance
        return character.getPowerLevel() + 15;
    }
}
