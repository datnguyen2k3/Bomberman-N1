package uet.oop.bomberman;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import uet.oop.bomberman.UI.GameUI.Board;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Character.Enemy.Cat;
import uet.oop.bomberman.entities.Character.Enemy.Enemy;
import uet.oop.bomberman.entities.Character.Enemy.EnemyManagement;
import uet.oop.bomberman.entities.Item.Item;
import uet.oop.bomberman.entities.Item.ItemManagement;
import uet.oop.bomberman.entities.StillObject.Brick;
import uet.oop.bomberman.entities.StillObject.Grass;
import uet.oop.bomberman.entities.StillObject.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.sound.Soundtrack;
import uet.oop.bomberman.utils.CollisionChecker;
import uet.oop.bomberman.entities.Bomb.BombManagement;

public class BombermanGame {
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static final int TIME_WIN = 180;
    public static final int TIME_LOSE = 150;
    public static final int TIME_GAME = 60 * 500;
    public static final int TIME_ADD_ENEMY = 30;
    private int currentTimeWin = 0;
    private int currentTimeLose = 0;
    private int currentTimeGame = TIME_GAME;
    private int currentTimeAddEnemy = 0;
    private static final int NUM_ENEMIES_IS_ADDED = 15;
    private int currentNumEnemiesIsAdded = 0;
    private Canvas canvas;
    private GraphicsContext gc;
    private Soundtrack soundTrack = new Soundtrack();

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
    private boolean isLose = false;
    private int levelDone = 0;
    private int justDie = 0;

    public Soundtrack getSoundTrack() {
        return soundTrack;
    }

    public List<Entity> getStillObjects() {
        return stillObjects;
    }

    public void findBrickAndDelete(int xUnit, int yUnit) {
        for (Entity e : stillObjects) {
            if (e instanceof Brick) {
                if (e.get_xUnit() == xUnit && e.get_yUnit() == yUnit) {
                    e.setImg(Sprite.grass.getFxImage());
                }
            }
        }
    }

    public BombManagement getBombManagement() {
        return this.bombManagement;
    }

    private Board board = new Board();
    int level = 1;

    public boolean isRun() {
        return isRun;
    }

    private void setWin() {
        if (isWin)
            return;

        isWin = true;
        soundTrack.stopLevelThemeAt(level);
        soundTrack.playLevelDone();
    }

    public boolean isWin() {
        return isWin;
    }

    public int getLevel() {
        return level;
    }
    public int getCurrentTimeGame() {
        return currentTimeGame / 60;
    }
    private void updateCurrentTimeGame() {
        if (currentTimeGame <= 0)
            return;

        currentTimeGame--;
    }

    public EnemyManagement getEnemyManagement() {
        return enemyManagement;
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
        createMap(level);

    }

    public Bomber getBomberman() {
        return bomberman;
    }

    public void setBomberman(int hp) {
        this.bomberman.setHP(hp);
    }

    public static final char[][] diagramMap = new char[HEIGHT][WIDTH];

    private void createDiagramMap(int level) {
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader("res/Map/map" + level + ".txt"));
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

    private void createMap(int level) {
        createDiagramMap(level);

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object = null;
                char currentDiagramObject = diagramMap[j][i];

                if (Wall.isWall(currentDiagramObject)) {
                    object = new Wall(i, j, this);
                } else if (Brick.isBrick(currentDiagramObject)) {
                    object = new Brick(i, j, this);
                } else if (Item.isItem(currentDiagramObject)) {
                    object = new Brick(i, j, this);
                    itemManagement.add(i, j, currentDiagramObject, this);
                } else if (Enemy.isEnemy(currentDiagramObject)) {
                    object = new Grass(i, j, this);
                    enemyManagement.add(i, j, currentDiagramObject, this);
                } else {
                    object = new Grass(i, j, this);
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
            entity.update();
        }
        itemManagement.update();
        enemyManagement.update();
        board.update(bomberman.getHP(), enemyManagement.getNumEnemies(),
                bombManagement.getLeftBomb(), bombManagement.getFlame(),
                bomberman.getSpeed(), getCurrentTimeGame());
        updateCurrentTimeGame();
    }

    public void updateInput(Scene scene) {
        bomberman.updateInput(scene);
    }

    public void updateCombat(Scene scene) {

        // destroy brick
        for (Entity e : stillObjects) {
            if (e instanceof Brick) {
                if (bomberman.getBombManagement().isDestroyBrick((Brick) e)) {
                    ((Brick) e).setDestroyed();
                    itemManagement.setItemIfBrickIsDestroyed((Brick) e);
                }
            }
        }

        // Bomber take item
        itemManagement.updateBomberTakeItem(bomberman);

        // Bomb kill enemy
        enemyManagement.updateEnemyIsKilledByBomb(bomberman.getBombManagement());

        // Bomb kill bomber
//        if (bomberman.getBombManagement().isDestroyEnemy(bomberman)) {
//            bomberman.setDead();
//        }

        // Enemy kill bomber
        if (enemyManagement.isEnemyKillCharacter(bomberman)) {
            bomberman.setDead();
        }

        // Bomber kill all enemies
        if (enemyManagement.getNumEnemies() == 0) {
            bomberman.setBombermanKillAllEnemies();
        }

        if (currentTimeGame <= 0) {
            updateAddEnemyWhenEndTimeGame();
        }

        // Bomber win
        if (bomberman.isWin()) {
            setWin();
        }

        // Bomber lose
        if (bomberman.isDead()) {
            setLose();
        }

    }

    private void updateAddEnemyWhenEndTimeGame() {
        currentTimeAddEnemy++;
        if (currentTimeAddEnemy == TIME_ADD_ENEMY && currentNumEnemiesIsAdded < NUM_ENEMIES_IS_ADDED) {
            enemyManagement.add(1, 1, '1', this);
            currentTimeAddEnemy = 0;
            currentNumEnemiesIsAdded++;
        }
    }

    public void render(Canvas canvas, GraphicsContext gc) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        itemManagement.render(gc);
        bomberman.render(gc);
        enemyManagement.render(gc);

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

        if (isLose) {
            currentTimeLose++;
            if (currentTimeLose > TIME_LOSE) {
                setEnd(root);
            }
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

    private void setLose() {
        if (isLose)
            return;
        isLose = true;
        soundTrack.stopLevelThemeAt(level);
        soundTrack.playJustDie();
    }
}