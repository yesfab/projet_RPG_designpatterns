package rpg.decorator;

import rpg.core.Character;

public abstract class CharacterDecorator extends Character {
    public Character character;

    public CharacterDecorator(Character character) {
        super(character.getName(),
                character.getStrength(),
                character.getAgility(),
                character.getIntelligence());
        this.character = character;
    }

    @Override
    public abstract String getDescription();

    @Override
    public abstract int getPowerLevel();
}
