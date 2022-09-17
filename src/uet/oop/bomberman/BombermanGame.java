package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    private GraphicsContext gc; // window
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();

    private List<Entity> stillObjects = new ArrayList<>();
    private String[] diagramMap = new String[HEIGHT];

    @Override
    public void start(Stage stage) {
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


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
                updateInput(scene);
            }
        };
        timer.start();

        createMap();

        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
    }

    private void createDiagramMap() {
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader("res\\map.txt"));
            String line;
            int indexLine = 0;

            while ((line = bufferreader.readLine()) != null) {
                diagramMap[indexLine] = line;
                indexLine++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void createMap() {
        createDiagramMap();

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                char currentDiagramObject = diagramMap[j].charAt(i);

                if (currentDiagramObject == '#' ) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                } else if (currentDiagramObject == '*'|| currentDiagramObject == 'x' || currentDiagramObject == 'f'
                        || currentDiagramObject == 'b' || currentDiagramObject == 's') {
                    object = new Wall(i, j, Sprite.brick.getFxImage());
                } else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }

                stillObjects.add(object);
            }
        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void updateInput(Scene scene) {
        for (Entity entity : entities) {
            if (entity instanceof Bomber) {
                ((Bomber) entity).updateCoordinate(scene);
            }
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
