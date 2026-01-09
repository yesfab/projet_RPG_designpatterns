package rpg.validation;

import rpg.core.Character;
import rpg.settings.GameSettings;

public class NameValidator extends ValidationHandler {

    @Override
    public boolean validate(Character character) {
        GameSettings settings = GameSettings.getInstance();
        String name = character.getName();

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Validation échouée : Le nom ne peut pas être vide");
            return false;
        }

        if (name.length() < settings.getMinNameLength()) {
            System.out.println("Validation échouée : Le nom doit contenir au moins " +
                    settings.getMinNameLength() + " caractères (actuel: " + name.length() + ")");
            return false;
        }

        if (!name.matches("[a-zA-ZÀ-ÿ ]+")) {
            System.out.println("Validation échouée : Le nom ne peut contenir que des lettres");
            return false;
        }

        System.out.println("Validation du nom : OK");
        return passToNext(character);
    }
}