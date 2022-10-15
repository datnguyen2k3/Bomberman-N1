package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import uet.oop.bomberman.UI.Board;
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
import uet.oop.bomberman.graphics.GraphicsManager;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.map.MapParser;
import uet.oop.bomberman.utils.CollisionChecker;
import uet.oop.bomberman.entities.Bomb.BombManagement;

public class BombermanGame {
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static final int TIME_WIN = 180;
    private int currentTimeWin = 0;
    private Canvas canvas;
    private GraphicsContext gc;

    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage(), this);
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private ItemManagement itemManagement = new ItemManagement();
    private EnemyManagement enemyManagement = new EnemyManagement();
    private BombManagement bombManagement = bomberman.getBombManagement();
    private boolean isRun = true;
    private boolean isAdd = false;
    private boolean isWin = false;

    private Board board = new Board();
    int level = 1;
    public static char[][] diagramMap;

    public boolean isRun() {
        return isRun;
    }

    private void setWin() {
        isWin = true;
    }

    public boolean isWin() {
        return isWin;
    }

    public int getLevel() {
        return level;
    }

    public void setBomber(Bomber bomberman) {
        this.bomberman.setHP(bomberman.getHP());
        this.bomberman.setSpeed(bomberman.getSpeed());
        this.bomberman.getBombManagement().setMaxBomb(bomberman.getBombManagement().getMaxBomb());
        this.bomberman.getBombManagement().setFlame(bomberman.getBombManagement().getFlame());
    }

    public BombermanGame(int level) {
        canvas = new Canvas(Sprite.SCALED_SIZE * BombermanGame.WIDTH, Sprite.SCALED_SIZE * BombermanGame.HEIGHT);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        this.level = level;

        //Create map
        diagramMap = MapParser.diagramMapParser(level);
        MapParser.load(diagramMap, itemManagement, stillObjects);
    }

    public Bomber getBomberman() {
        return bomberman;
    }

    public void setBomberman(int hp) {
        this.bomberman.setHP(hp);
    }


//    private void createDiagramMap(int level) {
//        try {
//            BufferedReader bufferreader = new BufferedReader(new FileReader("res/Map/map" + level + ".txt"));
//            String line;
//            int indexLine = 0;
//
//            while ((line = bufferreader.readLine()) != null) {
//                diagramMap[indexLine] = line.toCharArray();
//                indexLine++;
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    private void createMap(int level) {
//        createDiagramMap(level);
//
//        for (int i = 0; i < WIDTH; i++) {
//            for (int j = 0; j < HEIGHT; j++) {
//                Entity object = null;
//                char currentDiagramObject = diagramMap[j][i];
//
//                if (Wall.isWall(currentDiagramObject)) {
//                    object = new Wall(i, j);
//                } else if (Brick.isBrick(currentDiagramObject)) {
//                    object = new Brick(i, j);
//                } else if (Item.isItem(currentDiagramObject)) {
//                    object = new Brick(i, j);
//                    itemManagement.add(i, j, currentDiagramObject);
//                } else if (Enemy.isEnemy(currentDiagramObject)) {
//                    object = new Grass(i, j);
//                    enemyManagement.add(i, j, currentDiagramObject, this);
//                } else {
//                    object = new Grass(i, j);
//                }
//
//                stillObjects.add(object);
//            }
//        }
//    }

    public void update() {
        entities.forEach(Entity::update);
        bomberman.update();
        for (Entity entity : stillObjects) {
            if (entity instanceof Brick) {
                ((Brick) entity).update(bomberman);
            }
            entity.update();
        }
        itemManagement.update();
        enemyManagement.update();
        GraphicsManager.getGraphicsManager().update(bomberman.isCollisionOn, bomberman.get_state(), bomberman.getSpeed());

        board.update(bomberman.getHP(), enemyManagement.getNumEnemies(),
                    bombManagement.getMaxBomb(), bombManagement.getFlame(),
                    bomberman.getSpeed());
    }

    public void updateInput(Scene scene) {
        bomberman.updateInput(scene);
    }

    public void updateCombat(Scene scene) {
        // destroy brick
        for (Entity e : stillObjects) {
            if (e instanceof Brick) {
                if (bomberman.getBombManagement().isDestroyBrick((Brick)e)) {
                    ((Brick)e).setDestroyed();
                    itemManagement.setItemIfBrickIsDestroyed((Brick) e);
                }
            }
        }

        // Bomber take item
        itemManagement.updateBomberTakeItem(bomberman);

        // Bomb kill enemy
        enemyManagement.updateEnemyIsKilledByBomb(bomberman.getBombManagement());

        // Bomb kill bomber
        if (bomberman.getBombManagement().isDestroyEnemy(bomberman)) {
            bomberman.setDead();
        }

        // Enemy kill bomber
        if (enemyManagement.isEnemyKillCharacter(bomberman)) {
            bomberman.setDead();
        }

        // Bomber kill all enemies
        if (enemyManagement.getNumEnemies() == 0) {
            bomberman.setBombermanKillAllEnemies();
        }

        // Bomber win
        if (bomberman.isWin()) {
            setWin();
        }

    }

    public void render(Canvas canvas, GraphicsContext gc) {
//        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//        stillObjects.forEach(g -> g.render(gc));
//        entities.forEach(g -> g.render(gc));
//        itemManagement.render(gc);
//        bomberman.render(gc);
//        enemyManagement.render(gc);

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render());
        entities.forEach(g -> g.render());
        itemManagement.render();
        bomberman.render();
        enemyManagement.render();
    }

    public void run(Canvas canvas, GraphicsContext gc, Scene scene, Group root) {
        if (!isRun) {
            return;
        }

        if (isWin) {
            currentTimeWin++;
            if (currentTimeWin > TIME_WIN) {
                setEnd(root);
            }

            return;
        }

        if (!isAdd) {
            setAdd(root);
        }

        render(canvas, gc);
        update();
        updateInput(scene);
        updateCombat(scene);

        if (bomberman.isEnd()) {
            setEnd(root);
        }
    }

    private void setAdd(Group root) {
        isAdd = true;
        board.pushInRoot(root);
    }

    private void setEnd(Group root) {
        board.popInRoot(root);
        isRun = false;
    }
}