package rpg.command;

import java.util.ArrayList;
import java.util.List;

public class CommandInvoker {
    private List<Command> commandHistory;

    public CommandInvoker() {
        this.commandHistory = new ArrayList<>();
    }

    public void executeCommand(Command command) {
        command.execute();
        commandHistory.add(command);
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.remove(commandHistory.size() - 1);
            lastCommand.undo();
        } else {
            System.out.println("Aucune commande à annuler");
        }
    }

    public void undoAll() {
        System.out.println("\n--- Annulation de toutes les commandes ---");
        while (!commandHistory.isEmpty()) {
            undoLastCommand();
        }
    }

    public void replayAll() {
        System.out.println("\n--- Rejeu de toutes les commandes ---");
        List<Command> commands = new ArrayList<>(commandHistory);
        commandHistory.clear();

        for (Command command : commands) {
            executeCommand(command);
        }
    }

    public void displayHistory() {
        System.out.println("\n=== Historique des Commandes ===");
        if (commandHistory.isEmpty()) {
            System.out.println("Aucune commande exécutée");
        } else {
            for (int i = 0; i < commandHistory.size(); i++) {
                System.out.println((i + 1) + ". " + commandHistory.get(i).getDescription());
            }
        }
        System.out.println("================================");
    }

    public int getHistorySize() {
        return commandHistory.size();
    }
}