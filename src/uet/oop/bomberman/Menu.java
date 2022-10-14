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
import uet.oop.bomberman.graphics.Sprite;

public class Menu extends Application {
    public static void main(String[] args) {
        Application.launch(Menu.class);
    }

    private Canvas canvas;
    private GraphicsContext gc;
    private static final int WIDTH = 31;
    private static final int HEIGHT = 13;

    private AnimatedGraphic animatedGraphic;
//    private List<AnimatedGraphic> animatedGraphics;

    private TextGraphics startText;
    private TextGraphics exitText;

    @Override
    public void start(Stage stage) {
        int screenWidth = Sprite.SCALED_SIZE * WIDTH;
        int screenHeight = Sprite.SCALED_SIZE * HEIGHT;
//        String filepath = "https://www.spriters-resource.com/resources/sheets/107/110015.png?updated=1539611896";
        String filepath = "C:/Users/Tung/Downloads/Work/oop/Bomberman-N1/menu_logo.png";
        animatedGraphic = new AnimatedGraphic(filepath,
                screenWidth / 2 - 280 / 2, screenHeight / 2 - 146 / 2 - 100, 1288, 1683, 280, 146);

        startText = new TextGraphics("START");
        startText.setPos(screenWidth / 2 - startText.getWidth() / 2, screenHeight / 2 - startText.getHeight() / 2);
        startText.setColor(Color.WHITE);
        startText.setOpacity(1);

        exitText = new TextGraphics("END");
        exitText.setPos(screenWidth / 2 - exitText.getWidth() / 2, screenHeight / 2 - startText.getHeight() / 2 + 40);
        exitText.setColor(Color.WHITE);
        exitText.setOpacity(0.5);

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

        startText.create(root);
        exitText.create(root);

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
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        animatedGraphic.render(gc);
    }
}
