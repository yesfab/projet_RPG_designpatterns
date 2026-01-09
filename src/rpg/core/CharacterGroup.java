package rpg.core;

import java.util.ArrayList;
import java.util.List;

public class CharacterGroup {
    private String name;
    private CharacterGroup parent;
    private List<Character> members;
    private List<CharacterGroup> subGroups;

    public CharacterGroup(String name) {
        this.name = name;
        this.members = new ArrayList<>();
        this.subGroups = new ArrayList<>();
    }

    // Ajouter un personnage
    public void addMember(Character character) {
        members.add(character);
        System.out.println(character.getName() + " a rejoint le groupe " + name);
    }

    // Retirer un personnage
    public void removeMember(Character character) {
        if (members.remove(character)) {
            System.out.println(character.getName() + " a quitté le groupe " + name);
        } else {
            System.out.println(character.getName() + " n'est pas dans le groupe " + name);
        }
    }

    // Ajouter un sous-groupe
    public void addSubGroup(CharacterGroup group) {
        group.parent = this;
        subGroups.add(group);
        System.out.println("Groupe " + group.getName() + " ajouté à " + name);
    }

    // Retirer un sous-groupe
    public void removeSubGroup(CharacterGroup group) {
        if (subGroups.remove(group)) {
            group.parent = null;
            System.out.println("Groupe " + group.getName() + " retiré de " + name);
        }
    }

    // Calculer la puissance totale
    public int getTotalPower() {
        int total = 0;

        // Puissance des membres directs
        for (Character character : members) {
            total += character.getPowerLevel();
        }

        // Puissance des sous-groupes
        for (CharacterGroup subGroup : subGroups) {
            total += subGroup.getTotalPower();
        }

        return total;
    }

    // Compter le nombre total de membres
    public int getTotalMembers() {
        int count = members.size();

        for (CharacterGroup subGroup : subGroups) {
            count += subGroup.getTotalMembers();
        }

        return count;
    }

    // Afficher la structure
    public void displayStructure() {
        displayStructure("");
    }

    private void displayStructure(String indent) {
        System.out.println(indent + "📁 " + name + " [Membres: " + members.size() +
                ", Sous-groupes: " + subGroups.size() +
                ", Puissance totale: " + getTotalPower() + "]");

        // Afficher les membres
        for (Character character : members) {
            System.out.println(indent + "  ⚔️  " + character.getDescription());
        }

        // Afficher les sous-groupes (récursif)
        for (CharacterGroup subGroup : subGroups) {
            subGroup.displayStructure(indent + "  ");
        }
    }

    // Getters
    public String getName() {
        return name;
    }

    public CharacterGroup getParent() {
        return parent;
    }

    public List<Character> getMembers() {
        return new ArrayList<>(members);
    }

    public List<CharacterGroup> getSubGroups() {
        return new ArrayList<>(subGroups);
    }
}