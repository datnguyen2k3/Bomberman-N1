package uet.oop.bomberman.UI.Menu.animationMenu;

import javafx.scene.canvas.GraphicsContext;

public class Background extends AnimatedGraphic {
    private boolean moving = true;
    private int screenWidth;
    private int screenHeight;

    public Background(String filepath, int x, int y, int renderSpeed, int screenWidth, int screenHeight) {
        super(filepath, x, y);
        this.renderSpeed = renderSpeed;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        width = (int) img.getWidth();
        height = (int) img.getHeight();
    }

    public void setMove(boolean moving) {
        this.moving = moving;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
        if (moving) {
            gc.drawImage(img, x + width, y);
        }
    }

    @Override
    public void update() {
        if (!moving) {
            return;
        }
        if (renderSpeedCount == renderSpeed) {
            x--;
            if (x + 2 * width <= screenWidth) {
                x = x + width;
            }
            renderSpeedCount = 0;
        }
        renderSpeedCount++;
    }
}
