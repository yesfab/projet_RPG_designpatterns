package rpg.settings;

import rpg.core.Character;

public class GameSettings {
    private static GameSettings instance;

    private int maxStatPoints;
    private int maxCharactersPerGroup;
    private int minNameLength;

    private GameSettings() {
        this.maxStatPoints = 100;
        this.maxCharactersPerGroup = 10;
        this.minNameLength = 3;
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

    public int getMaxCharactersPerGroup() {
        return maxCharactersPerGroup;
    }

    public void setMaxCharactersPerGroup(int maxCharactersPerGroup) {
        this.maxCharactersPerGroup = maxCharactersPerGroup;
    }

    public int getMinNameLength() {
        return minNameLength;
    }

    public void setMinNameLength(int minNameLength) {
        this.minNameLength = minNameLength;
    }

    public boolean isValid(Character character) {
        int totalStats = character.getStrength() +
                character.getAgility() +
                character.getIntelligence();

        boolean isValid = totalStats <= maxStatPoints;

        if (!isValid) {
            System.out.println("Personnage invalide : " + character.getName() +
                    " (Stats: " + totalStats + " > Max: " + maxStatPoints + ")");
        } else {
            System.out.println("Personnage valide : " + character.getName() +
                    " (Stats: " + totalStats + " <= Max: " + maxStatPoints + ")");
        }

        return isValid;
    }

    public void displaySettings() {
        System.out.println("=== Règles du Jeu (GameSettings) ===");
        System.out.println("Max points de stats : " + maxStatPoints);
        System.out.println("Max personnages par groupe : " + maxCharactersPerGroup);
        System.out.println("Longueur minimale du nom : " + minNameLength);
        System.out.println("====================================");
    }
}
