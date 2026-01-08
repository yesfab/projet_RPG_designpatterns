package rpg.main;

import rpg.builder.CharacterBuilder;
import rpg.core.Character;
import rpg.dao.CharacterDAO;
import rpg.decorator.FireResistance;
import rpg.decorator.Invisibility;
import rpg.decorator.Telepathy;

public class Main {
    public static void main(String[] args) {
        System.out.println("========= Générateur de Personnages - Jeu de Rôle ===========\n");

        System.out.println("\n========== Initialisation des personnages ==========\n");
        // ========== US 1.1 : Builder - Création de personnages ==========
        Character warrior = new CharacterBuilder()
                .setName("Jean")
                .setRole("Guerrier")
                .setStrength(40)
                .setAgility(20)
                .setIntelligence(15)
                .build();
        System.out.println("Personnage créé : " + warrior.getDescription());

        Character mage = new CharacterBuilder()
                .setName("Basvit")
                .setRole("Mage")
                .setStrength(10)
                .setAgility(15)
                .setIntelligence(50)
                .build();
        System.out.println("Personnage créé : " + mage.getDescription());

        Character rogue = new CharacterBuilder()
                .setName("Adam")
                .setRole("Tireur")
                .setStrength(20)
                .setAgility(45)
                .setIntelligence(10)
                .build();
        System.out.println("Personnage créé : " + rogue.getDescription());


        // ========== US 1.2 : Decorator - Capacités spéciales ==========
        System.out.println("\n========== Ajout des capacités ==========\n");

        Character invisibleWarrior = new Invisibility(warrior);
        System.out.println(invisibleWarrior.getDescription());

        Character fireResistantMage = new FireResistance(mage);
        System.out.println(fireResistantMage.getDescription());

        Character telepathicRogue = new Telepathy(rogue);
        System.out.println(telepathicRogue.getDescription());

        // Cumul de capacités
        System.out.println("\nCumul de capacités :");
        Character superMage = new Telepathy(new FireResistance(new Invisibility(mage)));
        System.out.println(superMage.getDescription());


        // ========== US 1.3 : DAO - Persistance ==========
        System.out.println("\n========== Stockage DAO ==========\n");

        CharacterDAO dao = new CharacterDAO();
        dao.save(warrior);
        dao.save(mage);
        dao.save(rogue);
        dao.save(invisibleWarrior);
        dao.save(fireResistantMage);
        dao.save(telepathicRogue);
        dao.save(superMage);

        System.out.println("\n--- Recherche dans le DAO ---");
        Character foundMage = dao.findByName("Basvit");
        if (foundMage != null) {
            System.out.println("Trouvé : " + foundMage.getDescription());
        }

        System.out.println("\n--- Liste de tous les personnages ---");
        for (Character c : dao.findAll()) {
            System.out.println("  " + c.getDescription());
        }

    }
}