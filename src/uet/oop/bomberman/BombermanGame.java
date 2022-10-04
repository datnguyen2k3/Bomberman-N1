package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Character.Enemy.Balloom;
import uet.oop.bomberman.entities.Character.Enemy.Enemy;
import uet.oop.bomberman.entities.Character.Enemy.EnemyManagement;
import uet.oop.bomberman.entities.Character.Enemy.Oneal;
import uet.oop.bomberman.entities.Item.Item;
import uet.oop.bomberman.entities.Item.ItemManagement;
import uet.oop.bomberman.entities.StillObject.Brick;
import uet.oop.bomberman.entities.StillObject.Grass;
import uet.oop.bomberman.entities.StillObject.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.utils.CollisionChecker;

public class BombermanGame extends Application {
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    public CollisionChecker collisionChecker = new CollisionChecker(this);
    private GraphicsContext gc; // window
    private Canvas canvas;
    private Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage(), this);
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private ItemManagement itemManagement = new ItemManagement();
    private EnemyManagement enemyManagement = new EnemyManagement();


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
                updateCombat(scene);
            }
        };
        timer.start();

        createMap();


    }

    public static final char[][] diagramMap = new char[HEIGHT][WIDTH];

    private void createDiagramMap() {
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader("res\\map.txt"));
            String line;
            int indexLine = 0;

            while ((line = bufferreader.readLine()) != null) {
                diagramMap[indexLine] = line.toCharArray();
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
                char currentDiagramObject = diagramMap[j][i];

                if (Wall.isWall(currentDiagramObject)) {
                    object = new Wall(i, j);
                } else if (Brick.isBrick(currentDiagramObject)) {
                    object = new Brick(i, j);
                } else if (Item.isItem(currentDiagramObject)) {
                    object = new Brick(i, j);
                    itemManagement.add(i, j, currentDiagramObject);
                } else if (Enemy.isEnemy(currentDiagramObject)) {
                    object = new Brick(i, j);
                    enemyManagement.add(i, j, currentDiagramObject, this);
                } else {
                    object = new Grass(i, j);
                }

                stillObjects.add(object);
            }
        }
    }

    public void update() {
        entities.forEach(Entity::update);
        bomberman.update();
        for (Entity entity : stillObjects) {
            if (entity instanceof Brick) {
                ((Brick) entity).update(bomberman);
            }
        }
        itemManagement.update();
        enemyManagement.update();
    }

    public void updateInput(Scene scene) {
        bomberman.updateInput(scene);
    }

    public void updateCombat(Scene scene) {

        // destroy brick
        for (Entity e : stillObjects) {
            if (e instanceof Brick) {
                Brick brick = (Brick) e;
                if (bomberman.getBombManagement().isDestroyBrick(brick)) {
                    brick.setDestroyed();
                    itemManagement.setItemIfBrickIsDestroyed(brick);
                }
            }
        }

        // Bomber take item
        itemManagement.updateBomberTakeItem(bomberman);

        // Bomb kill enemy
        enemyManagement.updateEnemyIsKilledByBomb(bomberman.getBombManagement());

        
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        itemManagement.render(gc);
        bomberman.render(gc);
        enemyManagement.render(gc);

    }
}
