package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import uet.oop.bomberman.UI.GameUI.Board;

import uet.oop.bomberman.UI.GameUI.GameOver;
import uet.oop.bomberman.UI.GameUI.GameWin;
import uet.oop.bomberman.UI.GameUI.LevelGameUI;

import uet.oop.bomberman.UI.Menu.Menu;
import uet.oop.bomberman.UI.Menu.PauseMenu;
import uet.oop.bomberman.entities.Score.HighScore;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;

public class Game extends Application {
    public static final int WIDTH_CAMERA = Sprite.SCALED_SIZE * 20;
    public static final int HEIGHT = Sprite.SCALED_SIZE * BombermanGame.HEIGHT + Board.HEIGHT;
    public static final int WIDTH = WIDTH_CAMERA;

    private int maxLevel = 5;
    private Canvas boardCanvas;
    private Canvas canvas;
    private GraphicsContext gc;

    public Group getRoot() {
        return root;
    }

    private Group root;
    Scene scene;

    public BombermanGame getBombermanGame() {
        return bombermanGame;
    }

    private BombermanGame bombermanGame = new BombermanGame(4, this);
    private LevelGameUI levelGameUI = new LevelGameUI(1);
    private Menu menu;

    public PauseMenu getPauseMenu() {
        return pauseMenu;
    }

    private PauseMenu pauseMenu;
    private GameOver gameOver = new GameOver();
    private GameWin gameWin = new GameWin();
    private boolean isWin = false;

    @Override
    public void start(Stage stage) {
        // Tao Canvas

        boardCanvas = new Canvas(Game.WIDTH, HEIGHT);
        canvas = new Canvas(Game.WIDTH, HEIGHT);
        canvas.setTranslateY(Board.HEIGHT);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);

        // Tao root container
        root = new Group();
        root.getChildren().add(canvas);
        root.getChildren().add(boardCanvas);

        // Tao scene
        scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);
        stage.getIcons().add(
                new Image(
                        getClass().getResourceAsStream( "/textures/icon.png" )));
        menu = new Menu(scene, this);
        pauseMenu = new PauseMenu(scene, this);

        // Them scene vao stage
        stage.setTitle("Bomberman");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        scene.setCursor(new ImageCursor(new Image(new File("res/sprites/cursor1.png").toURI().toString())));
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (pauseMenu.isRun()) {
                    pauseMenu.run(canvas, gc, stage);
                    return;
                }

                if (menu.isRun()) {
                    menu.run(canvas, boardCanvas.getGraphicsContext2D(), stage);
                    return;
                }

                if (levelGameUI.isRun()) {
                    menu.stop(stage);
                    restartCanvas();
                    bombermanGame.getSoundTrack().playStageStart();
                    levelGameUI.run(root);
                    return;
                }

                if (bombermanGame.isRun()) {
                    bombermanGame.getSoundTrack().playLevelThemeAt(bombermanGame.getLevel());
                    bombermanGame.run(canvas, gc, boardCanvas.getGraphicsContext2D(), scene, root);
                    return;
                }

                if (bombermanGame.isWin()) {


                    if (bombermanGame.getLevel() == maxLevel) {
                        if (!isWin) {
                            isWin = true;
                            restartCanvas();
                            HighScore.addScore(bombermanGame.getBomberScore().getCurrentScore());
                        }
                        if (gameWin.isRun()) {
                            gameWin.run(root);
                            return;
                        }

                        setNewGame();
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
        boardCanvas = new Canvas(WIDTH, HEIGHT);
        boardCanvas.getGraphicsContext2D().setFill(Color.BLACK);
        canvas = new Canvas(WIDTH, HEIGHT);
        canvas.setTranslateY(Board.HEIGHT);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
    }

    private void setRestartGame(Group root) {
        BombermanGame newBombermanGame = new BombermanGame(bombermanGame.getLevel(), this);
        newBombermanGame.setBomber(bombermanGame.getBomberman());
        newBombermanGame.getBomberScore().setScore(
                Math.max(bombermanGame.getBomberScore().getCurrentScore() - 1000, 0));
        newBombermanGame.getBomberman().setPassFlame(false);
        newBombermanGame.getBomberman().setPassBomb(false);
        newBombermanGame.getBomberman().setPassBrick(false);

        bombermanGame = newBombermanGame;
        levelGameUI = new LevelGameUI(bombermanGame.getLevel());

        restartCanvas();
    }

    private void setNextLevel() {
        bombermanGame = bombermanGame.newLevel(bombermanGame.getLevel() + 1);
        levelGameUI = new LevelGameUI(bombermanGame.getLevel());
        restartCanvas();
    }

    public void setNewGame() {
        menu = new Menu(scene, this);
        pauseMenu = new PauseMenu(scene, this);
        bombermanGame = new BombermanGame(1, this);
        levelGameUI = new LevelGameUI(1);
        gameOver = new GameOver();
        gameWin = new GameWin();
        isWin = false;
        restartCanvas();
    }

    public void restartCanvas() {
        root.getChildren().remove(canvas);
        root.getChildren().remove(boardCanvas);
        setCanvas();
        root.getChildren().add(boardCanvas);
        root.getChildren().add(canvas);
    }

    public static void main(String[] args) {
        launch(Game.class);
    }
}
