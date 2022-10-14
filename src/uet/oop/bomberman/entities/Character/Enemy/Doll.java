package uet.oop.bomberman.entities.Character.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.State;

public class Doll extends Enemy{
    public Doll(int xUnit, int yUnit, Image img, BombermanGame game) {
        super(xUnit, yUnit, img, game);
    }

    @Override
    protected void initState() {
        _state = State.GO_NORTH;
    }

    @Override
    protected void initSprite() {
        this._sprite = Sprite.doll_left1;

        this.sprite_character_left = Sprite.doll_left1;
        this.sprite_character_right = Sprite.doll_right1;

        this.last_sprite_left = Sprite.doll_left3;
        this.last_sprite_right = Sprite.doll_right3;


        this.sprite_character_right_1 = Sprite.doll_right1;
        this.sprite_character_right_2 = Sprite.doll_right2;
        this.sprite_character_right_3 = Sprite.doll_right3;

        this.sprite_character_up = Sprite.doll_right1;
        this.sprite_character_up_1 = Sprite.doll_right2;
        this.sprite_character_up_2 = Sprite.doll_right3;

        this.sprite_character_left_1 = Sprite.doll_left1;
        this.sprite_character_left_2 = Sprite.doll_left2;
        this.sprite_character_left_3 = Sprite.doll_left3;

        this.sprite_character_down = Sprite.doll_left1;
        this.sprite_character_down_1 = Sprite.doll_left2;
        this.sprite_character_down_2 = Sprite.doll_left3;

        this.sprite_character_dead = Sprite.doll_dead;
        this.sprite_character_dead_1 = Sprite.mob_dead1;
        this.sprite_character_dead_2 = Sprite.mob_dead2;
    }

    @Override
    public void setState() {

    }

    // find min path from doll to bomberman
    @Override
    public void updateCurrentState() {

    }

    @Override
    public void update() {
        updateCurrentState();
        super.update();

    }
}
