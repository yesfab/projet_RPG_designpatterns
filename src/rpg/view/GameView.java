package rpg.view;

import rpg.core.Character;
import rpg.core.CharacterGroup;

import java.util.List;
import java.util.Scanner;

public class GameView {
    private Scanner scanner;

    public GameView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMainMenu() {
        System.out.println("\n");
        System.out.println("===     RPG MENU     ===");
        System.out.println("\n");
        System.out.println("  1. Créer un personnage                               ");
        System.out.println("  2. Lister les personnages                            ");
        System.out.println("  3. Créer un groupe                                   ");
        System.out.println("  4. Ajouter un personnage à un groupe                 ");
        System.out.println("  5. Afficher les groupes                              ");
        System.out.println("  6. Créer une armée                                   ");
        System.out.println("  7. Ajouter un groupe à une armée                     ");
        System.out.println("  8. Afficher les armées                               ");
        System.out.println("  9. 🔄 Trier les personnages                          ");
        System.out.println(" 10. 📚 Démonstration Épic 1                           ");
        System.out.println(" 11. 📚 Démonstration Épic 2                           ");
        System.out.println("  0. Quitter                                           ");
        System.out.print("\nChoix : ");
    }

    public void displaySortMenu() {
        System.out.println("\n");
        System.out.println("              Trier les personnages                    ");
        System.out.println("\n");
        System.out.println("  1. Trier par nom (alphabétique)                      ");
        System.out.println("  2. Trier par puissance (décroissant)                 ");
        System.out.println("  3. Trier par rôle                                    ");
        System.out.println("  0. Retour                                            ");
        System.out.print("\nChoix : ");
    }

    public int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int readInt(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displaySuccess(String message) {
        System.out.println("\n✅ " + message);
    }

    public void displayError(String message) {
        System.out.println("\n❌ " + message);
    }

    public void displayWarning(String message) {
        System.out.println("\n⚠️  " + message);
    }

    public void displayTitle(String title) {
        System.out.println("\n");
        System.out.println(centerText(title, 51));
        System.out.println("\n");
    }

    public void displayCharacters(List<Character> characters) {
        if (characters.isEmpty()) {
            displayError("Aucun personnage");
        } else {
            System.out.println();
            for (int i = 0; i < characters.size(); i++) {
                System.out.println((i + 1) + ". " + characters.get(i).getDescription());
            }
        }
    }

    public void displayGroups(List<CharacterGroup> groups) {
        if (groups.isEmpty()) {
            displayError("Aucun groupe");
        } else {
            System.out.println();
            for (int i = 0; i < groups.size(); i++) {
                System.out.println((i + 1) + ". " + groups.get(i).getName());
            }
        }
    }

    public void displayGroupStructure(CharacterGroup group) {
        group.displayStructure();
    }

    public void displayGroupStructures(List<CharacterGroup> groups) {
        if (groups.isEmpty()) {
            displayError("Aucun groupe");
        } else {
            System.out.println();
            for (CharacterGroup group : groups) {
                group.displayStructure();
                System.out.println();
            }
        }
    }

    public void waitForEnter() {
        System.out.println("\nAppuyez sur Entrée pour continuer...");
        scanner.nextLine();
    }

    private String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < padding; i++) {
            sb.append(" ");
        }
        sb.append(text);
        while (sb.length() < width) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public void close() {
        scanner.close();
    }
}