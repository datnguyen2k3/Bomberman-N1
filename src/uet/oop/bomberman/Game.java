package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import uet.oop.bomberman.UI.GameOver;
import uet.oop.bomberman.UI.LevelGameUI;
import uet.oop.bomberman.graphics.Sprite;

public class Game extends Application {

    public static final int HEIGHT = Sprite.SCALED_SIZE * BombermanGame.HEIGHT;
    public static final int WIDTH = Sprite.SCALED_SIZE * (BombermanGame.WIDTH + 3);

    private Canvas canvas;
    private GraphicsContext gc;
    private BombermanGame bombermanGame = new BombermanGame();
    private LevelGameUI levelGameUI = new LevelGameUI(1);
    private GameOver gameOver = new GameOver();
    @Override
    public void start(Stage stage) throws Exception {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * BombermanGame.WIDTH, Sprite.SCALED_SIZE * BombermanGame.HEIGHT);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (levelGameUI.isRun()) {
                    levelGameUI.run(root);
                    return;
                }

                if (bombermanGame.isRun()) {
                    bombermanGame.run(canvas, gc, scene, root);
                    return;
                }

                if (bombermanGame.getBomberman().getHP() > 0) {
                    setNewGame(root);
                } else if (gameOver.isRun()) {
                    setEndGame(root);
                    gameOver.run(root);
                } else {
                    stage.close();
                }
            }
        };
        timer.start();
    }

    private void setCanvas() {
        canvas = new Canvas(Sprite.SCALED_SIZE * BombermanGame.WIDTH, Sprite.SCALED_SIZE * BombermanGame.HEIGHT);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
    }

    private void setNewGame(Group root) {
        BombermanGame newBombermanGame = new BombermanGame();
        newBombermanGame.getBomberman().setHP(bombermanGame.getBomberman().getHP());

        bombermanGame = newBombermanGame;
        levelGameUI = new LevelGameUI(1);
        root.getChildren().remove(canvas);
        setCanvas();
        root.getChildren().add(canvas);
    }

    private void setEndGame(Group root) {
        root.getChildren().remove(canvas);
        setCanvas();
        root.getChildren().add(canvas);
    }

    public static void main(String[] args) {
        launch(Game.class);
    }
}
