package uet.oop.bomberman.UI.Menu.animationMenu;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class AnimatedGraphic {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    protected int spriteWidth;
    protected int spriteHeight;
    protected int spriteXPos = 0;
    protected int spriteYPos = 0;
    protected int renderSpeed = 1;
    protected int renderSpeedCount = 0;
    protected Image img;

    public AnimatedGraphic(String filepath, int x, int y) {
        if (filepath != null) {
            this.img = new Image(filepath);
            if (img != null) {
                this.width = this.spriteWidth =  (int) img.getWidth();
                this.height = this.spriteHeight = (int) img.getHeight();
                this.x = x;
                this.y = y;
            }
        }
    }
    public AnimatedGraphic(String filepath, int x, int y, int spriteWidth, int spriteHeight) {
        if (filepath != null) {
            this.img = new Image(filepath);
        }

        this.x = x;
        this.y = y;

        this.width = (int) img.getWidth();
        this.height = (int) img.getHeight();

        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }

    //280 x 146 || 1288x1683
    private WritableImage cropImage() {
        PixelReader pixelReader = img.getPixelReader();
        return new WritableImage(pixelReader, spriteXPos, spriteYPos, spriteWidth, spriteHeight);
    }

    public void resize(int width, int height) {
        img = new Image(img.getUrl(), width, height, false, false);
        this.width = this.spriteWidth = (int) img.getWidth();
        this.height = this.spriteHeight = (int) img.getHeight();
    }

    public void resize(double percentage) {
        resize((int) (img.getWidth() * percentage), (int) (img.getHeight() * percentage));
    }

    public void setCenterHorizontal(int screenWidth) {
        x = screenWidth / 2 - (int) img.getWidth() / 2;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
