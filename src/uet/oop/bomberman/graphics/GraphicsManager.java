package uet.oop.bomberman.graphics;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.map.MapParser;
import uet.oop.bomberman.utils.State;

import static javafx.scene.paint.Color.BLACK;
import static uet.oop.bomberman.BombermanGame.HEIGHT;
import static uet.oop.bomberman.BombermanGame.WIDTH;

class Viewport {
    private State dir = null;
    private int speed = 0;
    private int x;
    private int y;
    private int screenWidth;
    private int screenHeight;
    private int mapWidth;
    private int mapHeight;

    public Viewport(int x, int y, int screenWidth, int screenHeight, int mapWidth, int mapHeight) {
        this.x = x;
        this.y = y;

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;

        speed = 0;
        dir = State.IDLE;
    }

    public void update() {
        if (dir == State.GO_WEST && x - speed >= 0) {
            x -= speed;
        }

        if (dir == State.GO_EAST && x + speed + screenWidth <= mapWidth) {
           x += speed;
        }

        if (dir == State.GO_NORTH && y - speed >= 0) {
            y -= speed;
        }

        if (dir == State.GO_SOUTH && y + speed + screenHeight <= mapHeight) {
            y += speed;
        }

    }
    public int getX() {return x;}
    public int getY() {return y;}
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setDir(State dir) {
        this.dir = dir;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class GraphicsManager {
    private static GraphicsManager graphicsManager = null;
    private Canvas canvas;
    private GraphicsContext gc;
    private int blockSize;
    private int screenWidth;
    private int screenHeight;
    private int mapWidth;
    private int mapHeight;
    private Viewport viewport;

    private GraphicsManager() {
        blockSize = Sprite.SCALED_SIZE;
        screenWidth = WIDTH * blockSize;
        screenHeight = HEIGHT * blockSize;

        mapWidth = MapParser.getMapWidth(BombermanGame.diagramMap) * blockSize;
        mapHeight = MapParser.getMapHeight(BombermanGame.diagramMap) * blockSize;

        //Init canvas and graphics context
        setCanvas();

        //Init the viewport
        viewport = new Viewport(0, 0, screenWidth, screenHeight, mapWidth, mapHeight);
    }

    public void render(Entity entity) {
        renderRaw(entity.getX(), entity.getY(), entity.getImage());
    }

    public void renderWithImage(Entity entity, Image image) {
        renderRaw(entity.getX(), entity.getY(), image);
    }

    public void renderRaw(int x, int y, Image image) {

        gc.drawImage(image, x - viewport.getX(), y - viewport.getY());
    }

    public void update(boolean isColliding, State state, int speed) {
        if (state == State.DEAD || isColliding) {
            viewport.setDir(State.IDLE);
            return;
        }

        //Update the viewport
        viewport.setDir(state);
        viewport.setSpeed(speed);
        viewport.update();
    }

    public void restart(Group root) {
        restartCanvas(root);
        viewport.setPos(0, 0);
    }

    public static GraphicsManager getGraphicsManager() {
        if (graphicsManager == null) {
            graphicsManager = new GraphicsManager();
        }
        return graphicsManager;
    }

    public void setCanvas() {
        canvas = new Canvas(blockSize * WIDTH, blockSize * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(BLACK);
    }

    public void restartCanvas(Group root) {
        root.getChildren().remove(canvas);
        setCanvas();
        root.getChildren().add(canvas);
    }

    public Canvas getCanvas() {
        return canvas;
    }
    public GraphicsContext getGraphicsContext() {
        return gc;
    }
}
