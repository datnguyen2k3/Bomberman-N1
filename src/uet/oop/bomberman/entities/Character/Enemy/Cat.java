package uet.oop.bomberman.entities.Character.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;

public class Cat extends Enemy {
    protected boolean isExplodeBomb = false;
    protected boolean removeBomb = false;
    private Bomb b;

    public Cat(int xUnit, int yUnit, Image img, BombermanGame game) {
        super(xUnit, yUnit, img, game);
    }

    private void updateExplodingBombAfterDead() {
        if (isDead && !isExplodeBomb) {
            isExplodeBomb = true;
            //game.getBombManagement().setMaxBomb(game.getBombManagement().getMaxBomb() + 1);
            Bomb b = new Bomb(get_xUnitCenter(), get_yUnitCenter(), game.getEnemyBombManagement(), game);
            b.setCurrentTimeWaitToExploding(1);
            game.getEnemyBombManagement().add(b);
        }

//        if (b != null && b.isEnd() && !removeBomb) {
//            game.getBombManagement().setMaxBomb(game.getBombManagement().getMaxBomb() - 1);
//            removeBomb = true;
//        }
    }

    @Override
    protected void initSprite() {
        this._sprite = Sprite.cat_left1;

        this.sprite_character_left = Sprite.cat_left1;
        this.sprite_character_right = Sprite.cat_right1;

        this.last_sprite_left = Sprite.cat_left3;
        this.last_sprite_right = Sprite.cat_right3;


        this.sprite_character_right_1 = Sprite.cat_right1;
        this.sprite_character_right_2 = Sprite.cat_right2;
        this.sprite_character_right_3 = Sprite.cat_right3;

        this.sprite_character_up = Sprite.cat_right1;
        this.sprite_character_up_1 = Sprite.cat_right2;
        this.sprite_character_up_2 = Sprite.cat_right3;

        this.sprite_character_left_1 = Sprite.cat_left1;
        this.sprite_character_left_2 = Sprite.cat_left2;
        this.sprite_character_left_3 = Sprite.cat_left3;

        this.sprite_character_down = Sprite.cat_left1;
        this.sprite_character_down_1 = Sprite.cat_left2;
        this.sprite_character_down_2 = Sprite.cat_left3;

        this.sprite_character_dead = Sprite.cat_dead;
        this.sprite_character_dead_1 = Sprite.mob_dead1;
        this.sprite_character_dead_2 = Sprite.mob_dead2;
        this.sprite_character_dead_3 = Sprite.mob_dead3;
    }

    @Override
    public void update() {
        updateExplodingBombAfterDead();
        super.update();
    }
}
