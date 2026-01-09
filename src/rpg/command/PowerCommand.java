package rpg.command;

import rpg.core.Character;

public class PowerCommand implements Command {
    private Character caster;
    private String powerName;
    private int manaCost;
    private boolean executed;

    public PowerCommand(Character caster, String powerName) {
        this.caster = caster;
        this.powerName = powerName;
        this.manaCost = 0;
        this.executed = false;
    }

    @Override
    public void execute() {
        manaCost = 20 + (caster.getIntelligence() / 5);

        System.out.println(caster.getRole() + " " + caster.getName() +
                " utilise le pouvoir [" + powerName + "] (Coût: " + manaCost + " mana)");

        executed = true;
    }

    @Override
    public void undo() {
        if (executed) {
            System.out.println("Annulation du pouvoir " + powerName + " de " + caster.getName());
            executed = false;
        }
    }

    @Override
    public String getDescription() {
        return caster.getName() + " utilise " + powerName;
    }

    public int getManaCost() {
        return manaCost;
    }
}