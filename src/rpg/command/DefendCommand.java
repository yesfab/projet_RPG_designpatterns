package rpg.command;

import rpg.core.Character;

public class DefendCommand implements Command {
    private Character defender;
    private int defenseBonus;
    private boolean executed;

    public DefendCommand(Character defender) {
        this.defender = defender;
        this.defenseBonus = 0;
        this.executed = false;
    }

    @Override
    public void execute() {
        defenseBonus = defender.getAgility() / 2 + 10;

        System.out.println(defender.getRole() + " " + defender.getName() +
                " se met en position défensive (+" + defenseBonus + " défense)");

        executed = true;
    }

    @Override
    public void undo() {
        if (executed) {
            System.out.println(defender.getName() + " abandonne sa position défensive");
            executed = false;
        }
    }

    @Override
    public String getDescription() {
        return defender.getName() + " se défend";
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }
}