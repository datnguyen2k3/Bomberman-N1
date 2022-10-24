package uet.oop.bomberman.entities.Character.Enemy.YellowEnemy;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character.Enemy.Enemy;
import uet.oop.bomberman.entities.Score.Score;
import uet.oop.bomberman.graphics.Sprite;

public class Minvo extends Enemy {
    public int currentTimeMinvoDead = 0;

    public Minvo(int xUnit, int yUnit, Image img, BombermanGame game) {
        super(xUnit, yUnit, img, game);
        this.speed = 3;

    }

    @Override
    protected void initSprite() {
        this._sprite = Sprite.minvo_left1;

        this.sprite_character_left = Sprite.minvo_left1;
        this.sprite_character_right = Sprite.minvo_right1;

        this.last_sprite_left = Sprite.minvo_left3;
        this.last_sprite_right = Sprite.minvo_right3;


        this.sprite_character_right_1 = Sprite.minvo_right1;
        this.sprite_character_right_2 = Sprite.minvo_right2;
        this.sprite_character_right_3 = Sprite.minvo_right3;

        this.sprite_character_up = Sprite.minvo_right1;
        this.sprite_character_up_1 = Sprite.minvo_right2;
        this.sprite_character_up_2 = Sprite.minvo_right3;

        this.sprite_character_left_1 = Sprite.minvo_left1;
        this.sprite_character_left_2 = Sprite.minvo_left2;
        this.sprite_character_left_3 = Sprite.minvo_left3;

        this.sprite_character_down = Sprite.minvo_left1;
        this.sprite_character_down_1 = Sprite.minvo_left2;
        this.sprite_character_down_2 = Sprite.minvo_left3;

        this.sprite_character_dead = Sprite.minvo_dead;
        this.sprite_character_dead_1 = Sprite.mob_dead1;
        this.sprite_character_dead_2 = Sprite.mob_dead2;
        this.sprite_character_dead_3 = Sprite.mob_dead3;
    }

    @Override
    public void update() {
        if (isDead) {
            currentTimeMinvoDead++;
            if (currentTimeMinvoDead == TIME_DEAD - 5) {
                this.game.getEnemyManagement().addEnemyLater(get_xUnit(), get_yUnit(), '1', game);
                this.game.getEnemyManagement().addEnemyLater(get_xUnit(), get_yUnit(), '2', game);
            }
        }

        super.update();
    }
}
