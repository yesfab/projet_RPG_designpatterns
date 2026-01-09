package rpg.main;

import rpg.builder.CharacterBuilder;
import rpg.core.Character;
import rpg.core.CharacterGroup;
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


        // ========== US 1.4 : Composite - Groupes hiérarchisés ==========
        System.out.println("\n\n========== US 1.4 : Organisation Hiérarchique (Composite) ==========\n");

        // Création de l'armée principale
        CharacterGroup army = new CharacterGroup("Grande Armée");

        // Division d'infanterie
        CharacterGroup infantry = new CharacterGroup("Division d'Infanterie");

        // Escouade 1
        CharacterGroup squad1 = new CharacterGroup("Escouade Alpha");
        Character soldier1 = new CharacterBuilder()
                .setName("Grunt")
                .setStrength(30)
                .setAgility(15)
                .setIntelligence(10)
                .build();
        Character soldier2 = new CharacterBuilder()
                .setName("Brute")
                .setStrength(35)
                .setAgility(10)
                .setIntelligence(8)
                .build();

        squad1.addMember(soldier1);
        squad1.addMember(soldier2);
        squad1.addMember(warrior);

        // Escouade 2
        CharacterGroup squad2 = new CharacterGroup("Escouade Beta");
        Character soldier3 = new CharacterBuilder()
                .setName("Scout")
                .setStrength(20)
                .setAgility(40)
                .setIntelligence(15)
                .build();

        squad2.addMember(soldier3);
        squad2.addMember(rogue);

        // Ajout des escouades à l'infanterie
        infantry.addSubGroup(squad1);
        infantry.addSubGroup(squad2);

        // Division magique
        CharacterGroup magicDivision = new CharacterGroup("Division Magique");
        magicDivision.addMember(mage);
        magicDivision.addMember(fireResistantMage);
        magicDivision.addMember(superMage);

        // Unité d'élite
        CharacterGroup eliteUnit = new CharacterGroup("Unité d'Élite");
        eliteUnit.addMember(invisibleWarrior);
        eliteUnit.addMember(telepathicRogue);

        // Ajout à l'armée principale
        army.addSubGroup(infantry);
        army.addSubGroup(magicDivision);
        army.addSubGroup(eliteUnit);

        // Affichage de la structure
        System.out.println("\n--- Structure de l'Armée ---");
        army.displayStructure();

        // Statistiques
        System.out.println("\n\n========== Statistiques ==========\n");
        System.out.println("📊 Nombre total de membres dans l'armée : " + army.getTotalMembers());
        System.out.println("📊 Puissance totale de l'armée : " + army.getTotalPower());
        System.out.println("📊 Membres dans l'Escouade Alpha : " + squad1.getTotalMembers());
        System.out.println("📊 Puissance de la Division Magique : " + magicDivision.getTotalPower());

        // Test de modification
        System.out.println("\n\n========== Test de Modification ==========\n");

        Character newRecruit = new CharacterBuilder()
                .setName("Rookie")
                .setStrength(25)
                .setAgility(20)
                .setIntelligence(12)
                .build();

        squad1.addMember(newRecruit);

        System.out.println("\n--- Structure après ajout ---");
        squad1.displayStructure();

        squad1.removeMember(soldier2);

        System.out.println("\n--- Structure après retrait ---");
        squad1.displayStructure();

    }
}