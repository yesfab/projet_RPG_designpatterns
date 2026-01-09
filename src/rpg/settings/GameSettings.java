package rpg.settings;

import rpg.core.Character;

public class GameSettings {
    // Instance Singleton
    private static GameSettings instance;

    // Règles du jeu
    private int maxStatPoints;

    // Constructeur privé pour empêcher l'instanciation directe
    private GameSettings() {
        this.maxStatPoints = 100;
    }

    public static GameSettings getInstance() {
        if (instance == null) {
            instance = new GameSettings();
        }
        return instance;
    }

    public int getMaxStatPoints() {
        return maxStatPoints;
    }

    public void setMaxStatPoints(int maxStatPoints) {
        this.maxStatPoints = maxStatPoints;
    }

    public boolean isValid(Character character) {
        int totalStats = character.getStrength() +
                character.getAgility() +
                character.getIntelligence();

        boolean isValid = totalStats <= maxStatPoints;

        if (!isValid) {
            System.out.println("Personnage invalide : " + character.getName() +
                    " (Stats totales: " + totalStats + " > Max: " + maxStatPoints + ")");
        } else {
            System.out.println("Personnage valide : " + character.getName() +
                    " (Stats totales: " + totalStats + " <= Max: " + maxStatPoints + ")");
        }

        return isValid;
    }
}
