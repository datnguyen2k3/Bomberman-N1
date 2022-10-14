package uet.oop.bomberman.animation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelBuffer;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class AnimatedGraphic {
    private int x;
    private int y;

    private int width;
    private int height;

    private int spriteWidth = 0;
    private int spriteHeight = 0;

    private int spriteXPos = 0;
    private int spriteYPos = 0;
    private int renderSpeed = 1;
    private int renderSpeedCount = 0;
    private Image img;

    public AnimatedGraphic(String filepath, int x, int y, int width, int height, int spriteWidth, int spriteHeight) {
        if (filepath != null) {
            this.img = new Image(filepath);
        }

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }

    public void setRenderSpeed(int speed) {
        renderSpeed = speed;
    }

    //280 x 146 || 1288x1683
    private WritableImage cropImage() {
        PixelReader pixelReader = img.getPixelReader();
//        return new WritableImage(pixelReader, spriteXPos, spriteYPos, spriteWidth, spriteHeight);
        return new WritableImage(pixelReader, 0, 1683 - 150, 280, 146);
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(cropImage(), x, y);
    }

    public void update() {
        if (renderSpeedCount < renderSpeed) {
            renderSpeedCount++;
        } else {
            renderSpeedCount = 0;

            if (spriteXPos < width - spriteWidth) {
                spriteXPos += spriteWidth;
            } else {
                //get to end of a line
                spriteXPos = 0;
                if (spriteYPos < height - spriteHeight) {
                    spriteYPos += spriteHeight;
                } else {
                    spriteYPos = 0;
                }
            }
        }

    }

}
