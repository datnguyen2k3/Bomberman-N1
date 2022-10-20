package uet.oop.bomberman.entities.Character.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Cat extends  Enemy {
    public Cat(int xUnit, int yUnit, Image img, BombermanGame game) {
        super(xUnit, yUnit, img, game);
    }

    @Override
    protected void initSprite() {
        sprite_character_right = Sprite.cat_right1;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(sprite_character_right.getFxImage(),x,y);
    }
}
