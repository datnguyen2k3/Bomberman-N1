package uet.oop.bomberman.entities.Character.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Coin extends Enemy{
    public Coin(int xUnit, int yUnit, Image img, BombermanGame game) {
        super(xUnit, yUnit, img, game);
        setPassBrick();
        this.speed = 4;
        this.TIME_RANDOM_STATE = 120;
    }

    protected void updateCurrentStateByCell() {
        if (isInCell(get_xUnit(), get_yUnit())) {
            setRandomState();
        }
    }

    @Override
    protected void initSprite() {
        this._sprite = Sprite.orange_segment;

        this.sprite_character_left = Sprite.orange_coin_left;
        this.sprite_character_right = Sprite.orange_coin_right;

        this.last_sprite_left = Sprite.orange_coin_left;
        this.last_sprite_right = Sprite.orange_coin_right;


        this.sprite_character_right_1 = Sprite.orange_coin_left;
        this.sprite_character_right_2 = Sprite.orange_segment;
        this.sprite_character_right_3 = Sprite.orange_coin_right;

        this.sprite_character_up = Sprite.orange_segment;
        this.sprite_character_up_1 = Sprite.orange_coin_right;
        this.sprite_character_up_2 = Sprite.orange_segment;

        this.sprite_character_left_1 = Sprite.orange_segment;
        this.sprite_character_left_2 = Sprite.orange_coin_left;
        this.sprite_character_left_3 = Sprite.orange_segment;

        this.sprite_character_down = Sprite.orange_segment;
        this.sprite_character_down_1 = Sprite.orange_coin_left;
        this.sprite_character_down_2 = Sprite.orange_segment;

        this.sprite_character_dead = Sprite.minvo_dead;
        this.sprite_character_dead_1 = Sprite.mob_dead1;
        this.sprite_character_dead_2 = Sprite.mob_dead2;
        this.sprite_character_dead_3 = Sprite.mob_dead3;
    }

    @Override
    public void update() {
        super.update();
        updateCurrentStateByCell();
    }
}
