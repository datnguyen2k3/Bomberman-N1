package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import uet.oop.bomberman.UI.LevelGameUI;
import uet.oop.bomberman.graphics.Sprite;

public class Game extends Application {

    private Canvas canvas;
    private GraphicsContext gc;

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
        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);


        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        BombermanGame bombermanGame = new BombermanGame();
        LevelGameUI levelGameUI = new LevelGameUI(1);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (levelGameUI.isRun()) {
                    levelGameUI.run(root);
                } else {
                    bombermanGame.run(canvas, gc, scene);
                }
            }
        };
        timer.start();
    }

    public static void main(String[] args) {
        launch(Game.class);
    }
}
