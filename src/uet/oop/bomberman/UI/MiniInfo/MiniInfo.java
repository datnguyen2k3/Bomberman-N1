package uet.oop.bomberman.UI.MiniInfo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;

public abstract class MiniInfo extends Entity {
    protected String title;
    public static final int TIME_RUN = 60 * 3;
    protected int TIME_CHANGE_COORDINATE = 2;
    protected int speed = 1;
    protected int currentTime = 0;
    protected Text text = new Text();

    boolean isEnd = false;

    public boolean isEnd() {
        return isEnd;
    }

    public MiniInfo(int xUnit, int yUnit, BombermanGame game) {
        super(xUnit, yUnit, game);
        this.x += rand.nextInt(10);
        TIME_CHANGE_COORDINATE = rand.nextInt(2) + 2;
        speed = rand.nextInt(2) + 1;
        text.setX(get_xRender(x));
        text.setY(y);
        //text.setFont(Font.loadFont("file:res/Font/game_font.ttf", 30));
        text.setScaleX(2);
        text.setScaleY(2);
        text.setFill(Color.WHITE);
        game.getGame().getRoot().getChildren().add(text);
        System.out.println("Start");
    }


    @Override
    public void initSolidArea() {

    }

    @Override
    protected void initSprite() {

    }

    @Override
    public void update() {
        if (currentTime % TIME_CHANGE_COORDINATE == 0) {
            y -= speed;
        }
        currentTime++;
        if (currentTime > TIME_RUN) {
            setEnd();
        }
        text.setX(get_xRender(x));
        text.setY(y);
    }

    @Override
    public void render(GraphicsContext gc) {

    }

    protected void setEnd() {
        isEnd = true;
        game.getGame().getRoot().getChildren().remove(text);
        System.out.println("End");
    }

}
