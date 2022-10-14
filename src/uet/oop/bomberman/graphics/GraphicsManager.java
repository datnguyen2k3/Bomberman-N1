package uet.oop.bomberman.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.utils.State;

import static uet.oop.bomberman.BombermanGame.HEIGHT;
import static uet.oop.bomberman.BombermanGame.WIDTH;

public class GraphicsManager {
    private static GraphicsManager graphicsManager = null;

    //Direction of bomberman
    private State dir = null;

    //Speed of bomberman
    private int speed = 0;
    private Canvas canvas;
    private GraphicsContext gc;
    private int blockSize;

    //Current x-position of viewport
    private int curX = 0;

    //Current y-position of viewport
    private int curY = 0;

    private int screenWidth;
    private int screenHeight;

    private int mapWidth;
    private int mapHeight;

    private GraphicsManager() {
        blockSize = Sprite.SCALED_SIZE;
        canvas = new Canvas(blockSize * WIDTH, blockSize * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        speed = 0;
        dir = State.IDLE;

        screenHeight = HEIGHT * blockSize;
        screenWidth = WIDTH * blockSize;
    }

    public void setMapSize(int mapWidth, int mapHeight) {
        this.mapWidth = mapWidth * blockSize;
        this.mapHeight = mapHeight * blockSize;
    }

    public static GraphicsManager getGraphicsManager() {
        if (graphicsManager == null) {
            graphicsManager = new GraphicsManager();
        }
        return graphicsManager;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public int getCurX() {
        return curX;
    }

    public int getCurY() {
        return curY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public State getDir() {
        return dir;
    }

    public void setDir(State dir) {
        this.dir = dir;
    }

    public void render(Entity entity) {
        gc.drawImage(entity.getImage(), entity.getX() -  curX, entity.getY() -  curY);
    }

    public void update(boolean isColliding, State state, int speed) {
//        if (true) {
//            return;
//        }

        this.speed = speed;

        if (state == State.DEAD || isColliding) {
            dir = State.IDLE;
            return;
        }

        this.dir = state;

        if (dir == State.GO_WEST && curX - speed >= 0) {
            curX -= speed;
        }

        if (dir == State.GO_EAST && curX + speed + screenWidth <= mapWidth) {
            curX += speed;
        }

        if (dir == State.GO_NORTH && curY - speed >= 0) {
            curY -= speed;
        }

        if (dir == State.GO_SOUTH && curY + speed + screenHeight <= mapHeight) {
            curY += speed;
        }
    }
}
