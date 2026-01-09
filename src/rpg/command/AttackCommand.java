package rpg.command;

import rpg.core.Character;

public class AttackCommand implements Command {
    private Character attacker;
    private Character target;
    private int damage;
    private boolean executed;

    public AttackCommand(Character attacker, Character target) {
        this.attacker = attacker;
        this.target = target;
        this.damage = 0;
        this.executed = false;
    }

    @Override
    public void execute() {
        damage = attacker.getStrength() + (int)(Math.random() * 20);

        System.out.println(attacker.getRole() + " " + attacker.getName() +
                " attaque " + target.getRole() + " " + target.getName() +
                " et inflige " + damage + " dégâts !");

        executed = true;
    }

    @Override
    public void undo() {
        if (executed) {
            System.out.println("Annulation de l'attaque de " + attacker.getName());
            executed = false;
        }
    }

    @Override
    public String getDescription() {
        return attacker.getName() + " attaque " + target.getName();
    }

    public int getDamage() {
        return damage;
    }
}