package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import uet.oop.bomberman.UI.GameUI.Board;

import uet.oop.bomberman.UI.GameUI.GameOver;
import uet.oop.bomberman.UI.GameUI.GameWin;
import uet.oop.bomberman.UI.GameUI.LevelGameUI;

import uet.oop.bomberman.UI.Menu.Menu;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.SpriteSheet;
import uet.oop.bomberman.sound.SoundManager;
import uet.oop.bomberman.sound.Soundtrack;

public class Game extends Application {

    public static final int WIDTH_CAMERA = Sprite.SCALED_SIZE * 16;
    public static final int HEIGHT = Sprite.SCALED_SIZE * BombermanGame.HEIGHT;
    public static final int WIDTH = WIDTH_CAMERA + Board.WIDTH;
    private int maxLevel = 3;
    private Canvas canvas;
    private GraphicsContext gc;
    Group root;
    private BombermanGame bombermanGame = new BombermanGame(1);
    private LevelGameUI levelGameUI = new LevelGameUI(1);
    private Menu menu;
    private GameOver gameOver = new GameOver();
    private GameWin gameWin = new GameWin();
    private boolean isWin = false;




    @Override
    public void start(Stage stage) throws Exception {
        // Tao Canvas
        canvas = new Canvas(BombermanGame.WIDTH * Sprite.SCALED_SIZE, BombermanGame.HEIGHT * Sprite.SCALED_SIZE);

        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);

        // Tao root container
        root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);

        menu = new Menu(scene, this);

        // Them scene vao stage
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (menu.isRun()) {
                    menu.run(canvas, gc, stage);
                    return;
                }

                if (levelGameUI.isRun()) {
                    menu.stop(stage);
                    bombermanGame.getSoundTrack().playStageStart();
                    levelGameUI.run(root);
                    return;
                }

                if (bombermanGame.isRun()) {
                    bombermanGame.getSoundTrack().playLevelThemeAt(bombermanGame.getLevel());
                    bombermanGame.run(canvas, gc, scene, root);
                    return;
                }

                if (bombermanGame.isWin()) {
                    if (bombermanGame.getLevel() == maxLevel) {
                        if (!isWin) {
                            isWin = true;
                            restartCanvas();
                        }
                        if (gameWin.isRun()) {
                            gameWin.run(root);
                            return;
                        }

                        setNewGame();
                        menu.setStart();
                        return;
                        //stage.close();
                    }

                    setNextLevel();
                    return;
                }

                if (bombermanGame.getBomberman().getHP() > 0) {
                    System.out.println("Die");
                    bombermanGame.getSoundTrack().stopLevelThemeAt(bombermanGame.getLevel());
                    setRestartGame(root);
                    return;
                }

                if (gameOver.isRun()) {
                    restartCanvas();
                    gameOver.run(root);
                    return;
                }
                menu.setStart();
                setNewGame();
                //stage.close();
            }
        };
        timer.start();
    }

    private void setCanvas() {
        canvas = new Canvas(Sprite.SCALED_SIZE * BombermanGame.WIDTH, Sprite.SCALED_SIZE * BombermanGame.HEIGHT);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
    }

    private void setRestartGame(Group root) {
        BombermanGame newBombermanGame = new BombermanGame(bombermanGame.getLevel());
        newBombermanGame.getBomberman().setHP(bombermanGame.getBomberman().getHP());

        bombermanGame = newBombermanGame;
        levelGameUI = new LevelGameUI(bombermanGame.getLevel());

        restartCanvas();
    }

    private void setNextLevel() {
        BombermanGame newBombermanGame = new BombermanGame(bombermanGame.getLevel() + 1);
        newBombermanGame.setBomber(bombermanGame.getBomberman());

        bombermanGame = newBombermanGame;
        levelGameUI = new LevelGameUI(bombermanGame.getLevel());

        restartCanvas();
    }

    private void setNewGame() {
        bombermanGame = new BombermanGame(1);
        levelGameUI = new LevelGameUI(1);
        gameOver = new GameOver();
        gameWin = new GameWin();
        isWin = false;
        restartCanvas();
    }

    public void restartCanvas() {
        root.getChildren().remove(canvas);
        setCanvas();
        root.getChildren().add(canvas);
    }

    public static void main(String[] args) {
        launch(Game.class);
    }
}
