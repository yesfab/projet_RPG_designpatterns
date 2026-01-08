package rpg.decorator;

import rpg.core.Character;

public class FireResistance extends CharacterDecorator {

    public FireResistance(Character character) {
        super(character);
    }

    @Override
    public String getDescription() {
        return character.getDescription() + " + [Résistance au Feu]";
    }

    @Override
    public int getPowerLevel() {
        // + 20 points de puissance
        return character.getPowerLevel() + 20;
    }
}
