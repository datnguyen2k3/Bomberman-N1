package uet.oop.bomberman.UI.Menu;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import uet.oop.bomberman.Game;
import uet.oop.bomberman.UI.Menu.animationMenu.AnimatedGraphic;
import uet.oop.bomberman.UI.Menu.animationMenu.MenuList.MenuLists;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.UI.Menu.animationMenu.Background;

import uet.oop.bomberman.sound.SoundManager;

public class Menu {
//    public static void main(String[] args) {
//        Application.launch(Menu.class);
//    }
    private boolean isRun = true;
    private Game game;
    private static final int WIDTH = 31;
    private static final int HEIGHT = 13;

    private AnimatedGraphic animatedGraphic;
    private AnimatedGraphic background;
    private MenuLists menuLists;


    public Menu(Scene scene, Game game) {
        int screenWidth = Game.WIDTH;
        int screenHeight = Game.HEIGHT;

        //Add background and animation
        background = new Background("file:res/Background/mountain.png",
                0, 0, 2, screenWidth, screenHeight);
        animatedGraphic = new AnimatedGraphic("file:res/textures/menu_logo.png", 0, 50);
        animatedGraphic.resize(0.25);
        animatedGraphic.setCenterHorizontal(screenWidth);

        //Add music
        SoundManager.getSoundManager().addMusicInfinite(SoundManager.mainMusicFilepath);

        menuLists = new MenuLists(screenWidth, screenHeight, scene, this);
        this.game = game;

    }

    public boolean isRun() {
        return isRun;
    }

//    @Override
//    public void start(Stage stage) {
//        int screenWidth = Sprite.SCALED_SIZE * WIDTH;
//        int screenHeight = Sprite.SCALED_SIZE * HEIGHT;
//
//        // Tao Canvas
//        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
//        gc = canvas.getGraphicsContext2D();
//
//        // Tao root container
//        Group root = new Group();
//        root.getChildren().add(canvas);
//
//        // Tao scene
//        Scene scene = new Scene(root);
//
//        // Them scene vao stage
//        stage.setScene(scene);
//        stage.show();
//
//        //Add background and animation
//        background = new Background("file:res/Background/mountain.png",
//                0, 0, 2, screenWidth, screenHeight);
//        animatedGraphic = new AnimatedGraphic("file:res/textures/menu_logo.png", 0, 50);
//        animatedGraphic.resize(0.25);
//        animatedGraphic.setCenterHorizontal(screenWidth);
//
//        //Add music
//        SoundManager.getSoundManager().addMusicInfinite(SoundManager.mainMusicFilepath);
//
//        menuLists = new MenuLists(screenWidth, screenHeight, scene, this);
//
//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long l) {
//                run(canvas, gc, stage);
//            }
//        };
//        timer.start();
//    }

    public void update(Stage stage) {
        SoundManager.getSoundManager().update();
        animatedGraphic.update();
        menuLists.update(stage);
        background.update();
    }

    public void stop(Stage stage) {
        SoundManager.getSoundManager().stop();
    }

    public void render(Canvas canvas, GraphicsContext gc) {
        SoundManager.getSoundManager().play();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        background.render(gc);
        animatedGraphic.render(gc);
        menuLists.render(gc);
    }

    public void run(Canvas canvas, GraphicsContext gc, Stage stage) {
        if (!isRun) {
            return;
        }

        update(stage);
        render(canvas, gc);
    }

    public void setEnd() {
        isRun = false;
        game.restartCanvas();
    }

    public void setStart() {
        isRun = true;
    }
}
