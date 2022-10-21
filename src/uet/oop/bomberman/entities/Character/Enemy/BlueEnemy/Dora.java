package uet.oop.bomberman.entities.Character.Enemy.BlueEnemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character.Enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.State;

public class Dora extends Oneal{
    public Dora(int xUnit, int yUnit, Image img, BombermanGame game) {
        super(xUnit, yUnit, img, game);
        setPassBrick();
    }



    @Override
    protected void initSprite() {
        this._sprite = Sprite.kondoria_left1;

        this.sprite_character_left = Sprite.kondoria_left1;
        this.sprite_character_right = Sprite.kondoria_right1;

        this.last_sprite_left = Sprite.kondoria_left3;
        this.last_sprite_right = Sprite.kondoria_right3;


        this.sprite_character_right_1 = Sprite.kondoria_right1;
        this.sprite_character_right_2 = Sprite.kondoria_right2;
        this.sprite_character_right_3 = Sprite.kondoria_right3;

        this.sprite_character_up = Sprite.kondoria_right1;
        this.sprite_character_up_1 = Sprite.kondoria_right2;
        this.sprite_character_up_2 = Sprite.kondoria_right3;

        this.sprite_character_left_1 = Sprite.kondoria_left1;
        this.sprite_character_left_2 = Sprite.kondoria_left2;
        this.sprite_character_left_3 = Sprite.kondoria_left3;

        this.sprite_character_down = Sprite.kondoria_left1;
        this.sprite_character_down_1 = Sprite.kondoria_left2;
        this.sprite_character_down_2 = Sprite.kondoria_left3;

        this.sprite_character_dead = Sprite.kondoria_dead;
        this.sprite_character_dead_1 = Sprite.mob_dead1;
        this.sprite_character_dead_2 = Sprite.mob_dead2;
        this.sprite_character_dead_3 = Sprite.mob_dead3;
    }
}
