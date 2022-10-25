package uet.oop.bomberman.UI.Menu;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import uet.oop.bomberman.Game;
import uet.oop.bomberman.UI.Menu.animationMenu.AnimatedGraphic;
import uet.oop.bomberman.UI.Menu.animationMenu.Background;

import uet.oop.bomberman.UI.Menu.animationMenu.PauseList.PauseLists;
import uet.oop.bomberman.sound.SoundManager;

public class PauseMenu {
    private boolean isRun = false;

    public Game getGame() {
        return game;
    }

    private Game game;
    private static final int WIDTH = 31;
    private static final int HEIGHT = 13;
    private Background background;
    private PauseLists pauseLists;
    //RESUME GAME, OPTIONS, MAIN MENU

    public PauseMenu(Scene scene, Game game) {
        pauseLists = new PauseLists(Game.WIDTH, Game.HEIGHT, scene, this);

        //Add background and animation
        background = new Background("file:res/Background/mountain.png",
                0, 0, 2, Game.WIDTH, Game.HEIGHT);
        this.game = game;
        //Add music
        SoundManager.getSoundManager().addMusicInfinite(SoundManager.mainMusicFilepath);
        isRun = false;
    }

    public boolean isRun() {
        return isRun;
    }

    public void update(Stage stage) {
        SoundManager.getSoundManager().update();
        pauseLists.update(stage);
        background.update();
    }

    public void stop(Stage stage) {
        SoundManager.getSoundManager().stop();
    }

    public void render(Canvas canvas, GraphicsContext gc) {
        //SoundManager.getSoundManager().play();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        background.render(gc);
        pauseLists.render(gc);
    }

    public void run(Canvas canvas, GraphicsContext gc, Stage stage) {
        if (!isRun) {
            return;
        }

        update(stage);
        render(canvas, gc);
    }

    public void setEnd() {
        isRun = false;
    }

    public void setStart() {
        isRun = true;
    }
}
