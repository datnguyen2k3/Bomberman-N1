package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import uet.oop.bomberman.animation.AnimatedGraphic;
import uet.oop.bomberman.animation.TextGraphics;
import uet.oop.bomberman.animation.TextGraphicsList;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Menu extends Application {
    public static void main(String[] args) {
        Application.launch(Menu.class);
    }

    private Canvas canvas;
    private GraphicsContext gc;
    private static final int WIDTH = 31;
    private static final int HEIGHT = 13;

    private AnimatedGraphic animatedGraphic;
    private TextGraphicsList textGraphicsList;

    @Override
    public void start(Stage stage) {
        int screenWidth = Sprite.SCALED_SIZE * WIDTH;
        int screenHeight = Sprite.SCALED_SIZE * HEIGHT;

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        //Add animation
        String filepath = "file:res/textures/menu_logo.png";
        animatedGraphic = new AnimatedGraphic(filepath,
                screenWidth / 2 - 280 / 2, screenHeight / 2 - 146 / 2 - 100,
                1288, 1683, 280, 146);

        String[] textList = {"START", "OPTIONS", "HIGHSCORE", "EXIT"};
        textGraphicsList = new TextGraphicsList(textList, screenWidth, scene);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

    }

    public void update() {
        animatedGraphic.update();
        textGraphicsList.update();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        animatedGraphic.render(gc);
        textGraphicsList.render(gc);
    }
}
