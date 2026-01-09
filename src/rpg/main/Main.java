package rpg.main;

import rpg.model.GameModel;
import rpg.view.GameView;
import rpg.controller.GameController;

public class Main {
    public static void main(String[] args) {
        GameModel model = new GameModel();
        GameView view = new GameView();
        GameController controller = new GameController(model, view);

        controller.start();
    }
}