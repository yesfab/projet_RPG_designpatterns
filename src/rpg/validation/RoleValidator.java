package rpg.validation;

import rpg.core.Character;
import java.util.Arrays;
import java.util.List;

public class RoleValidator extends ValidationHandler {
    private static final List<String> VALID_ROLES = Arrays.asList(
            "Guerrier", "Mage", "Voleur", "Archer", "Paladin",
            "Prêtre", "Soldat", "Éclaireur", "Recrue", "Aventurier"
    );

    @Override
    public boolean validate(Character character) {
        String role = character.getRole();

        if (role == null || role.trim().isEmpty()) {
            System.out.println("Validation échouée : Le rôle ne peut pas être vide");
            return false;
        }

        if (!VALID_ROLES.contains(role)) {
            System.out.println("Validation échouée : Rôle '" + role + "' non reconnu");
            System.out.println("   Rôles valides : " + VALID_ROLES);
            return false;
        }

        System.out.println("Validation du rôle : OK");
        return passToNext(character);
    }
}