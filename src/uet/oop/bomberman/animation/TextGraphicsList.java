package uet.oop.bomberman.animation;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.paint.Color.WHITE;

public class TextGraphicsList {
    public static final Color defaultColor = WHITE;
    public static final double defaultSize = 20;
    public static final double smallSize = 15;
    public static final double spaceBetweenLines = 15;
    public static final double yPos = 210;

    private List<TextGraphics> list;
    private int mainIndex = 0;

    private int scrollSpeed = 10;
    private int speedCounter = 0;
    private Scene scene;
    private double screenWidth;

    //Starting y position: 200
    //4 lines: START, EXIT, HIGHSCORE, OPTIONS
    //Space between each line: 10px
    //2 sizes: 15 (default) and 13 (small)
    //Total height: 15-height + 3 * 13-height + 3 * space (10px)

    public TextGraphicsList(String[] textList, int screenWidth, Scene scene) {
        this.list = new ArrayList<>();
        for (String text: textList) {
            this.list.add(new TextGraphics(text));
        }

        this.scene = scene;
        this.screenWidth = screenWidth;

        setText();
    }

    public void setText() {
        for (int i = 0; i < list.size(); i++) {
            if (i != mainIndex) {
                this.list.get(i).setOpacity(0.5);
                this.list.get(i).setSize(smallSize);
            } else {
                this.list.get(i).setOpacity(1);
                this.list.get(i).setSize(defaultSize);
            }

            //Set color
            this.list.get(i).setColor(defaultColor);

            //Set x position at center
            this.list.get(i).setCenterHorizontal(this.screenWidth);

            //Set y position
            if (i == 0) {
                this.list.get(i).setY(yPos);
            } else {
                double y = this.list.get(i - 1).getY() + this.list.get(i - 1).getHeight() + spaceBetweenLines;
                this.list.get(i).setY(y);
            }
        }
    }

    public void render(GraphicsContext gc) {
        for (TextGraphics tg: list) {
            tg.render(gc);
        }
    }

    public void update() {
        if (speedCounter == scrollSpeed) {
            updateInput(scene);
            speedCounter = 0;
        } else {
            speedCounter++;
        }

        setText();
    }

    private void updateInput(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case W:
                    case UP:
                        mainIndex = (mainIndex == 0) ? list.size() - 1 : mainIndex - 1;
                        break;
                    case S:
                    case DOWN:
                        mainIndex = (mainIndex == list.size() - 1)  ? 0 : mainIndex + 1;
                        break;
                }
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    //handle state changes
                }
            }
        });
    }
}
