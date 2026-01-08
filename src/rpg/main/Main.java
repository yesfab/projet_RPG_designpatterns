package rpg.main;

import rpg.builder.CharacterBuilder;
import rpg.core.Character;

public class Main {
    public static void main(String[] args) {
        System.out.println("==== Générateur de Personnages - Jeu de Rôle ====");

        // ========== US 1.1 : Builder - Création de personnages ==========
        Character warrior = new CharacterBuilder()
                .setName("Jean")
                .setStrength(40)
                .setAgility(20)
                .setIntelligence(15)
                .build();
        System.out.println("Personnage créé : " + warrior.getDescription());

        Character mage = new CharacterBuilder()
                .setName("Basvit")
                .setStrength(10)
                .setAgility(15)
                .setIntelligence(50)
                .build();
        System.out.println("Personnage créé : " + mage.getDescription());

        Character rogue = new CharacterBuilder()
                .setName("Adam")
                .setStrength(20)
                .setAgility(45)
                .setIntelligence(10)
                .build();
        System.out.println("Personnage créé : " + rogue.getDescription());
    }
}