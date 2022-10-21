package uet.oop.bomberman.entities.Character.Enemy.YellowEnemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character.Enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import uet.oop.bomberman.BombermanGame;
public class Balloom extends Enemy {
    public Balloom(int xUnit, int yUnit, Image img, BombermanGame game) {
        super(xUnit, yUnit, img, game);
    }

    @Override
    protected void initSprite() {

        this._sprite = Sprite.balloom_right1;

        this.sprite_character_left = Sprite.balloom_left1;
        this.sprite_character_right = Sprite.balloom_right1;

        this.last_sprite_left = Sprite.balloom_left3;
        this.last_sprite_right = Sprite.balloom_right3;


        this.sprite_character_right_1 = Sprite.balloom_right1;
        this.sprite_character_right_2 = Sprite.balloom_right2;
        this.sprite_character_right_3 = Sprite.balloom_right3;

        this.sprite_character_left_1 = Sprite.balloom_left1;
        this.sprite_character_left_2 = Sprite.balloom_left2;
        this.sprite_character_left_3 = Sprite.balloom_left3;

        this.sprite_character_dead = Sprite.balloom_dead;
        this.sprite_character_dead_1 = Sprite.mob_dead1;
        this.sprite_character_dead_2 = Sprite.mob_dead2;
        this.sprite_character_dead_3 = Sprite.mob_dead3;



        this.sprite_character_up = Sprite.balloom_right1;
        this.sprite_character_up_1 = Sprite.balloom_right2;
        this.sprite_character_up_2 = Sprite.balloom_right3;

        this.sprite_character_down = Sprite.balloom_left1;
        this.sprite_character_down_1 = Sprite.balloom_left2;
        this.sprite_character_down_2 = Sprite.balloom_left3;


    }


}
