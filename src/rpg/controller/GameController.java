package rpg.controller;

import rpg.builder.CharacterBuilder;
import rpg.core.Character;
import rpg.core.CharacterGroup;
import rpg.model.GameModel;
import rpg.view.GameView;
import rpg.validation.*;
import rpg.settings.GameSettings;
import rpg.command.*;
import rpg.decorator.*;

import java.util.List;

public class GameController {
    private GameModel model;
    private GameView view;
    private ValidationHandler validator;
    private CommandInvoker invoker;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.invoker = new CommandInvoker();

        ValidationHandler nameValidator = new NameValidator();
        ValidationHandler roleValidator = new RoleValidator();
        ValidationHandler statsValidator = new StatsValidator();

        nameValidator.setNext(roleValidator);
        roleValidator.setNext(statsValidator);

        this.validator = nameValidator;
    }

    public void start() {
        view.displayTitle("Générateur de Personnages RPG");

        boolean running = true;

        while (running) {
            view.displayMainMenu();
            int choice = view.getUserChoice();

            switch (choice) {
                case 1:
                    createCharacter();
                    break;
                case 2:
                    listCharacters();
                    break;
                case 3:
                    createGroup();
                    break;
                case 4:
                    addCharacterToGroup();
                    break;
                case 5:
                    displayGroups();
                    break;
                case 6:
                    createArmy();
                    break;
                case 7:
                    addGroupToArmy();
                    break;
                case 8:
                    displayArmies();
                    break;
                case 9:
                    sortCharacters();
                    break;
                case 10:
                    demonstrationEpic1();
                    break;
                case 11:
                    demonstrationEpic2();
                    break;
                case 0:
                    running = false;
                    view.displayTitle("Au revoir !");
                    break;
                default:
                    view.displayError("Choix invalide !");
            }
        }

        view.close();
    }

    private void createCharacter() {
        view.displayTitle("Créer un personnage");

        String name = view.readString("\nNom : ");
        String role = view.readString("Rôle (Guerrier, Mage, Voleur, Archer, Paladin, Prêtre, Soldat, Éclaireur) : ");
        int strength = view.readInt("Force : ");
        int agility = view.readInt("Agilité : ");
        int intelligence = view.readInt("Intelligence : ");

        Character character = new CharacterBuilder()
                .setName(name)
                .setRole(role)
                .setStrength(strength)
                .setAgility(agility)
                .setIntelligence(intelligence)
                .build();

        view.displayMessage("\n--- Validation du personnage ---");
        if (validator.validate(character)) {
            model.saveCharacter(character);
            view.displaySuccess("Personnage créé avec succès !");
        } else {
            view.displayError("Création annulée (validation échouée)");
        }
    }

    private void listCharacters() {
        view.displayTitle("Liste des personnages");
        List<Character> characters = model.getAllCharacters();
        view.displayCharacters(characters);
    }

    private void createGroup() {
        view.displayTitle("Créer un groupe");
        String name = view.readString("\nNom du groupe : ");

        CharacterGroup group = new CharacterGroup(name);
        model.addGroup(group);

        view.displaySuccess("Groupe créé !");
    }

    private void addCharacterToGroup() {
        List<CharacterGroup> groups = model.getAllGroups();
        List<Character> characters = model.getAllCharacters();

        if (groups.isEmpty()) {
            view.displayError("Aucun groupe disponible. Créez d'abord un groupe.");
            return;
        }

        if (characters.isEmpty()) {
            view.displayError("Aucun personnage disponible. Créez d'abord un personnage.");
            return;
        }

        view.displayTitle("Ajouter un personnage à un groupe");

        view.displayMessage("\nGroupes disponibles :");
        view.displayGroups(groups);

        int groupIndex = view.readInt("\nChoisir un groupe (numéro) : ") - 1;
        CharacterGroup selectedGroup = model.getGroup(groupIndex);

        if (selectedGroup == null) {
            view.displayError("Groupe invalide");
            return;
        }

        view.displayMessage("\nPersonnages disponibles :");
        view.displayCharacters(characters);

        int charIndex = view.readInt("\nChoisir un personnage (numéro) : ") - 1;

        if (charIndex < 0 || charIndex >= characters.size()) {
            view.displayError("Personnage invalide");
            return;
        }

        selectedGroup.addMember(characters.get(charIndex));
        view.displaySuccess("Personnage ajouté au groupe !");
    }

    private void displayGroups() {
        view.displayTitle("Liste des groupes");
        List<CharacterGroup> groups = model.getAllGroups();
        view.displayGroupStructures(groups);
    }

    private void createArmy() {
        view.displayTitle("Créer une armée");
        String name = view.readString("\nNom de l'armée : ");

        CharacterGroup army = new CharacterGroup(name);
        model.addArmy(army);

        view.displaySuccess("Armée créée !");
    }

    private void addGroupToArmy() {
        List<CharacterGroup> armies = model.getAllArmies();
        List<CharacterGroup> groups = model.getAllGroups();

        if (armies.isEmpty()) {
            view.displayError("Aucune armée disponible. Créez d'abord une armée.");
            return;
        }

        if (groups.isEmpty()) {
            view.displayError("Aucun groupe disponible. Créez d'abord un groupe.");
            return;
        }

        view.displayTitle("Ajouter un groupe à une armée");

        view.displayMessage("\nArmées disponibles :");
        view.displayGroups(armies);

        int armyIndex = view.readInt("\nChoisir une armée (numéro) : ") - 1;
        CharacterGroup selectedArmy = model.getArmy(armyIndex);

        if (selectedArmy == null) {
            view.displayError("Armée invalide");
            return;
        }

        view.displayMessage("\nGroupes disponibles :");
        view.displayGroups(groups);

        int groupIndex = view.readInt("\nChoisir un groupe (numéro) : ") - 1;
        CharacterGroup selectedGroup = model.getGroup(groupIndex);

        if (selectedGroup == null) {
            view.displayError("Groupe invalide");
            return;
        }

        selectedArmy.addSubGroup(selectedGroup);
        view.displaySuccess("Groupe ajouté à l'armée !");
    }

    private void displayArmies() {
        view.displayTitle("Liste des armées");
        List<CharacterGroup> armies = model.getAllArmies();
        view.displayGroupStructures(armies);
    }

    private void sortCharacters() {
        view.displaySortMenu();
        int choice = view.getUserChoice();

        List<Character> characters;

        switch (choice) {
            case 1:
                view.displayTitle("Personnages triés par nom");
                characters = model.getCharactersSortedByName();
                view.displayCharacters(characters);
                break;
            case 2:
                view.displayTitle("Personnages triés par puissance");
                characters = model.getCharactersSortedByPower();
                view.displayCharacters(characters);
                break;
            case 3:
                view.displayTitle("Personnages triés par rôle");
                characters = model.getCharactersSortedByRole();
                view.displayCharacters(characters);
                break;
            case 0:
                break;
            default:
                view.displayError("Choix invalide");
        }
    }

    private void demonstrationEpic1() {
        view.displayTitle("📚 DÉMONSTRATION ÉPIC 1");
        view.displayMessage("Création et Gestion des Personnages\n");

        view.displayMessage("========== US 1.1 : Builder ==========\n");

        Character warrior = new CharacterBuilder()
                .setName("Arthas")
                .setRole("Guerrier")
                .setStrength(40)
                .setAgility(20)
                .setIntelligence(15)
                .build();
        view.displayMessage("Créé : " + warrior.getDescription());

        Character mage = new CharacterBuilder()
                .setName("Merlin")
                .setRole("Mage")
                .setStrength(10)
                .setAgility(15)
                .setIntelligence(50)
                .build();
        view.displayMessage("Créé : " + mage.getDescription());

        view.displayMessage("\n========== US 1.2 : Decorator ==========\n");

        Character invisibleWarrior = new Invisibility(warrior);
        view.displayMessage(invisibleWarrior.getDescription());

        Character superMage = new Telepathy(new FireResistance(mage));
        view.displayMessage(superMage.getDescription());

        view.displayMessage("\n========== US 1.3 : DAO ==========\n");

        model.saveCharacter(warrior);
        model.saveCharacter(mage);

        view.displayMessage("\n========== US 1.4 : Composite ==========\n");

        CharacterGroup demoGroup = new CharacterGroup("Équipe Demo");
        demoGroup.addMember(warrior);
        demoGroup.addMember(mage);

        view.displayMessage("");
        view.displayGroupStructure(demoGroup);

        view.displaySuccess("Fin de la démonstration Épic 1");
        view.waitForEnter();
    }

    private void demonstrationEpic2() {
        view.displayTitle("📚 DÉMONSTRATION ÉPIC 2");
        view.displayMessage("Règles du Jeu et Actions\n");

        view.displayMessage("========== US 2.1 : Singleton ==========\n");

        GameSettings settings = GameSettings.getInstance();
        settings.displaySettings();

        view.displayMessage("\n========== US 2.3 : Chain of Responsibility ==========\n");

        Character testChar = new CharacterBuilder()
                .setName("TestHero")
                .setRole("Guerrier")
                .setStrength(40)
                .setAgility(30)
                .setIntelligence(25)
                .build();

        validator.validate(testChar);

        view.displayMessage("\n========== US 2.2 : Command ==========\n");

        Character warrior = new CharacterBuilder()
                .setName("Arthas")
                .setRole("Guerrier")
                .setStrength(40)
                .setAgility(25)
                .setIntelligence(15)
                .build();

        Character mage = new CharacterBuilder()
                .setName("Merlin")
                .setRole("Mage")
                .setStrength(10)
                .setAgility(20)
                .setIntelligence(50)
                .build();

        Command attack = new AttackCommand(warrior, mage);
        invoker.executeCommand(attack);

        Command defend = new DefendCommand(mage);
        invoker.executeCommand(defend);

        Command power = new PowerCommand(mage, "Boule de Feu");
        invoker.executeCommand(power);

        invoker.displayHistory();

        view.displaySuccess("Fin de la démonstration Épic 2");
        view.waitForEnter();
    }
}