package uet.oop.bomberman.entities.Character.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.State;

public class Minvo extends Enemy {
    public int currentTimeDead = 0;
    public Minvo(int xUnit, int yUnit, Image img, BombermanGame game) {
        super(xUnit, yUnit, img, game);
        this.speed = 2;
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
    }

    @Override
    public void update() {
        if (isDead) {
            currentTimeDead++;
            if (currentTimeDead == TIME_DEAD - 5) {
                this.game.getEnemyManagement().add(get_xUnit(), get_yUnit(), '1', game);
                this.game.getEnemyManagement().add(get_xUnit(), get_yUnit(), '2', game);
            }
        }

        super.update();
    }
}
