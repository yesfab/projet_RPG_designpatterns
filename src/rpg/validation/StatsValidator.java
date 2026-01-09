package rpg.validation;

import rpg.core.Character;
import rpg.settings.GameSettings;

public class StatsValidator extends ValidationHandler {

    @Override
    public boolean validate(Character character) {
        GameSettings settings = GameSettings.getInstance();

        int strength = character.getStrength();
        int agility = character.getAgility();
        int intelligence = character.getIntelligence();
        int totalStats = strength + agility + intelligence;

        if (strength < 0 || agility < 0 || intelligence < 0) {
            System.out.println("Validation échouée : Les statistiques ne peuvent pas être négatives");
            return false;
        }

        if (strength < 5 || agility < 5 || intelligence < 5) {
            System.out.println("Validation échouée : Chaque statistique doit être au moins 5");
            return false;
        }

        if (totalStats > settings.getMaxStatPoints()) {
            System.out.println("Validation échouée : Total des stats (" + totalStats +
                    ") dépasse le maximum autorisé (" + settings.getMaxStatPoints() + ")");
            return false;
        }

        System.out.println("Validation des stats : OK (Total: " + totalStats + "/" +
                settings.getMaxStatPoints() + ")");
        return passToNext(character);
    }
}