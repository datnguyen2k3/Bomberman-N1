package uet.oop.bomberman.UI.Menu.animationMenu;

import javafx.scene.canvas.GraphicsContext;

public class Background extends AnimatedGraphic {
//    private Image img2; //use for background
//    private int x1;
//    private int x2;
    private int screenWidth;
    private int screenHeight;

    public Background(String filepath, int x, int y, int renderSpeed, int screenWidth, int screenHeight) {
        super(filepath, x, y);
//        img2 = img;
        this.renderSpeed = renderSpeed;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        width = (int) img.getWidth();
        height = (int) img.getHeight();
//        x1 = 0;
//        x2 = width;
    }


    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
        gc.drawImage(img, x + width, y);
    }

    @Override
    public void update() {
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
