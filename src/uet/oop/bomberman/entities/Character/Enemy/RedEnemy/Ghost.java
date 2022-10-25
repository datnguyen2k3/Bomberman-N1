package uet.oop.bomberman.entities.Character.Enemy.RedEnemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Score.Score;
import uet.oop.bomberman.graphics.Sprite;

public class Ghost extends AI_Enemy {
    public Ghost(int xUnit, int yUnit, Image img, BombermanGame game) {
        super(xUnit, yUnit, img, game);
        this.speed = 2;
        setPassBrick(true);
    }

    @Override
    protected void initSprite() {
        this._sprite = Sprite.doll_left1;

        this.sprite_character_left = Sprite.ghost_left1;
        this.sprite_character_right = Sprite.ghost_right1;

        this.last_sprite_left = Sprite.ghost_left3;
        this.last_sprite_right = Sprite.ghost_right3;


        this.sprite_character_right_1 = Sprite.ghost_right1;
        this.sprite_character_right_2 = Sprite.ghost_right2;
        this.sprite_character_right_3 = Sprite.ghost_right3;

        this.sprite_character_up = Sprite.ghost_right1;
        this.sprite_character_up_1 = Sprite.ghost_right2;
        this.sprite_character_up_2 = Sprite.ghost_right3;

        this.sprite_character_left_1 = Sprite.ghost_left1;
        this.sprite_character_left_2 = Sprite.ghost_left2;
        this.sprite_character_left_3 = Sprite.ghost_left3;

        this.sprite_character_down = Sprite.ghost_left1;
        this.sprite_character_down_1 = Sprite.ghost_left2;
        this.sprite_character_down_2 = Sprite.ghost_left3;

        this.sprite_character_dead = Sprite.ghost_dead;
        this.sprite_character_dead_1 = Sprite.mob_dead1;
        this.sprite_character_dead_2 = Sprite.mob_dead2;
        this.sprite_character_dead_3 = Sprite.mob_dead3;
    }
}
