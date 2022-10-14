package uet.oop.bomberman.entities.Character.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Algorithm.CanGo;
import uet.oop.bomberman.entities.Algorithm.MinPath;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.State;

import java.util.PriorityQueue;

public class Doll extends Enemy{
    public Doll(int xUnit, int yUnit, Image img, BombermanGame game) {
        super(xUnit, yUnit, img, game);
        this.x = xUnit * Sprite.SCALED_SIZE + 1;
        this.y = yUnit * Sprite.SCALED_SIZE + 1;
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
        if (isEnd)
            return;

        if (x % Sprite.SCALED_SIZE != 1 || y % Sprite.SCALED_SIZE != 1)
            return;

        int direction = MinPath.findDirection(this, bomber.get_xUnitCenter(), bomber.get_yUnitCenter());

        if (direction == CanGo.CANT_MOVE) {
            return;
        }

        switch (direction) {
            case 3: _state = State.GO_NORTH; break;
            case 4: _state = State.GO_SOUTH; break;
            case 1: _state = State.GO_WEST; break;
            case 2: _state = State.GO_EAST; break;
        }

        //System.out.println(direction);

    }

    @Override
    public void update() {
        updateCurrentState();
        super.update();

    }
}
