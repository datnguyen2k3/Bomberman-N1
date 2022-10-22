package uet.oop.bomberman.entities.Character.Enemy.BlueEnemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character.Enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import uet.oop.bomberman.utils.Badge;
import uet.oop.bomberman.utils.State;

import java.awt.*;
import java.util.Random;

import uet.oop.bomberman.BombermanGame;
public class Oneal extends Enemy {
    protected int TimeChangeSpeed = 240;
    protected int currentTimeChangeSpeed = 0;

    public Oneal(int xUnit, int yUnit, Image img, BombermanGame game) {
        super(xUnit, yUnit, img, game);
        deadPoint = Badge.onealPoint;
    }

    @Override
    protected void initSprite() {
        this._sprite = Sprite.oneal_right1;

        this.sprite_character_left = Sprite.oneal_left2;
        this.sprite_character_right = Sprite.oneal_right2;

        this.last_sprite_left = Sprite.oneal_left3;
        this.last_sprite_right = Sprite.oneal_right3;


        this.sprite_character_right_1 = Sprite.oneal_right1;
        this.sprite_character_right_2 = Sprite.oneal_right2;
        this.sprite_character_right_3 = Sprite.oneal_right3;

        this.sprite_character_up = Sprite.oneal_right1;
        this.sprite_character_up_1 = Sprite.oneal_right2;
        this.sprite_character_up_2 = Sprite.oneal_right3;

        this.sprite_character_left_1 = Sprite.oneal_left1;
        this.sprite_character_left_2 = Sprite.oneal_left2;
        this.sprite_character_left_3 = Sprite.oneal_left3;

        this.sprite_character_down = Sprite.oneal_left1;
        this.sprite_character_down_1 = Sprite.oneal_left2;
        this.sprite_character_down_2 = Sprite.oneal_left3;

        this.sprite_character_dead = Sprite.oneal_dead;
        this.sprite_character_dead_1 = Sprite.mob_dead1;
        this.sprite_character_dead_2 = Sprite.mob_dead2;
        this.sprite_character_dead_3 = Sprite.mob_dead3;
    }

    public void setRandomSpeed() {
        Random random = new Random();
        speed = random.nextInt(3) + 1;
    }

    @Override
    public void update() {
        super.update();
        updateSpeed();
    }

    protected void updateSpeed() {
        if (isImpactWall()) {
            setRandomSpeed();
        }
        currentTimeChangeSpeed++;
        if (currentTimeChangeSpeed > TimeChangeSpeed) {
            currentTimeChangeSpeed = 0;
            setRandomSpeed();
        }

    }


}
