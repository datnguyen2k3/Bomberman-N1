package uet.oop.bomberman.entities.Character.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character.Enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Balloom extends Enemy {


    public Balloom(int xUnit, int yUnit, Image img, BombermanGame game) {
        this(xUnit, yUnit, img);
        initSolidArea();
        this.game = game;
        type = "Balloom";
    }

    @Override
    public void initSolidArea() {
        solidArea = new Rectangle(0 * Sprite.SCALE, 0 * Sprite.SCALE, 15 * Sprite.SCALE , 15 * Sprite.SCALE);
    }

    public Balloom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        initSprite();
        initState();
    }


    @Override
    protected void initState() {
        setRandomState();
    }

    @Override
    protected void initSprite() {

        this._sprite = Sprite.balloom_right1;

        this.sprite_character_left = Sprite.balloom_left2;
        this.sprite_character_right = Sprite.balloom_right2;

        this.last_sprite_left = Sprite.balloom_left3;
        this.last_sprite_right = Sprite.balloom_right3;


        this.sprite_character_right_1 = Sprite.balloom_right1;
        this.sprite_character_right_2 = Sprite.balloom_right2;
        this.sprite_character_right_3 = Sprite.balloom_right3;

        this.sprite_character_left_1 = Sprite.balloom_left1;
        this.sprite_character_left_2 = Sprite.balloom_left2;
        this.sprite_character_left_3 = Sprite.balloom_left3;

        this.sprite_character_dead = Sprite.balloom_dead;
        this.sprite_character_dead_1 = Sprite.balloom_dead;
        this.sprite_character_dead_2 = Sprite.balloom_dead;

        this.sprite_character_up = Sprite.balloom_right1;
        this.sprite_character_up_1 = Sprite.balloom_right1;
        this.sprite_character_up_2 = Sprite.balloom_right1;

        this.sprite_character_down = Sprite.balloom_left1;
        this.sprite_character_down_1 = Sprite.balloom_left1;
        this.sprite_character_down_2 = Sprite.balloom_left2;

    }


    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }


    @Override
    public int getVal() {
        return 0;
    }
}
