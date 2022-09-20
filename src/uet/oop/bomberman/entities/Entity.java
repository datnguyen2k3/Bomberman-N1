package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {

    protected int _animate = 0;
    public static final int MAX_ANIMATION = 7500;

    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    public int get_xUnit() {
        return x / Sprite.SCALED_SIZE;
    }
    public int get_yUnit() {
        return y / Sprite.SCALED_SIZE;
    }
    public static int get_xUnit(int x) {
        return x / Sprite.SCALED_SIZE;
    }
    public static int get_yUnit(int y) {
        return y / Sprite.SCALED_SIZE;
    }
    protected Image img;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public void setCoordinate(int xUnit, int yUnit) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
    }

    public void animate() {
        if (_animate < MAX_ANIMATION) {
            _animate++;
        } else {
            _animate = 0;
        }
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update();
}
