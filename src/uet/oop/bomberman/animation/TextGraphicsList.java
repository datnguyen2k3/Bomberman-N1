package uet.oop.bomberman.animation;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
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
    protected List<String> listTexts;
    protected String listType;
    protected static final double yPos = 210;
    protected List<TextGraphics> textGraphicsList;
    protected int mainIndex;
    protected int scrollSpeed = 10;
    protected int speedCounter = 0;
    protected Scene scene;
    protected double screenWidth;
    protected double screenHeight;
    protected String exitTo = "FALSE";
    protected boolean isOverScreen = false; //Use when the list is rendered outside the screen
    protected int maxSize; //Maximum number of texts that can fit the screen
    protected int currentTopIndex = 0; //Used for handling outside the screen case, otherwise it always equals to 0

    public TextGraphicsList(String[] textList, int screenWidth, int screenHeight, Scene scene) {
        this.scene = scene;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        mainIndex = 0;

        this.textGraphicsList = new ArrayList<>();

        for (String text: textList) {
            TextGraphics textGraphics = new TextGraphics(text);
            textGraphicsList.add(textGraphics);
        }

        setText();

        //Check if the list is over screen
        maxSize = calculateMaxSize(screenHeight);
        if (maxSize != textGraphicsList.size()) {
            isOverScreen = true;
        }
    }

    public void setListType(String listType) {
        this.listType = listType;
    }
    public void setText() {
        if (isOverScreen) {
            //Set the opacity of texts outside the current range to 0
            for (int i = 0; i < currentTopIndex; i++) {
                textGraphicsList.get(i).setOpacity(0);
            }

            for (int i = currentTopIndex + maxSize; i < textGraphicsList.size(); i++) {
                textGraphicsList.get(i).setOpacity(0);
            }
        }

        for (int i = currentTopIndex; i < currentTopIndex + maxSize; i++) {
            TextGraphics textGraphics = textGraphicsList.get(i);
            if (i != mainIndex) {
                textGraphics.setOpacity(0.5);
                textGraphics.setSize(smallSize);
            } else {
                textGraphics.setOpacity(1);
                textGraphics.setSize(defaultSize);
            }

            this.textGraphicsList.get(i).setColor(defaultColor);

            //Set x position at center
            this.textGraphicsList.get(i).setCenterHorizontal(this.screenWidth);

            //Set y position
            if (i == currentTopIndex) {
                this.textGraphicsList.get(i).setY(yPos);
            } else {
                double y = this.textGraphicsList.get(i - 1).getY()
                        + this.textGraphicsList.get(i - 1).getHeight() + spaceBetweenLines;
                this.textGraphicsList.get(i).setY(y);
            }
        }
    }

    public void render(GraphicsContext gc) {
        for (TextGraphics textGraphics: textGraphicsList) {
            textGraphics.render(gc);
        }
    }

    public void update() {
        if (speedCounter == scrollSpeed) {
            updateInput(scene);
            speedCounter = 0;
        } else {
            speedCounter++;
        }

        currentTopIndex = Math.max(mainIndex + 1 - maxSize, 0);

        setText();
    }

    protected void updateInput(Scene scene) {
        scene.setOnKeyPressed(this::eventHandler);
    }

    public void exit() {
        exitTo = "FALSE";
        mainIndex = 0;
    }

    public String isExiting() {
        return exitTo;
    }

    //Calculate the total height of the list
    public int calculateMaxSize(double screenHeight) {
        double height = 0;

        height += yPos;
        int spaceWithScreen = 10;

        for (int i = 0; i < textGraphicsList.size(); i++) {
            height += textGraphicsList.get(i).getHeight() + spaceBetweenLines;

            if (height + spaceBetweenLines + spaceWithScreen > screenHeight) {
                return i;
            }
        }

        return textGraphicsList.size();
    }

    //Use when the player moving up and down
    private void eventHandler(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W:
            case UP:
                mainIndex = (mainIndex == 0) ? textGraphicsList.size() - 1 : mainIndex - 1;
                break;
            case S:
            case DOWN:
                mainIndex = (mainIndex == textGraphicsList.size() - 1) ? 0 : mainIndex + 1;
                break;
        }

        addEventHandlers(keyEvent);
    }

    protected void addEventHandlers(KeyEvent keyEvent) {}

    protected void addText(String text, int index) {
        textGraphicsList.add(index, new TextGraphics(text));
        restartList();
    }

    protected void addTextAtEnd(String text) {
        textGraphicsList.add(new TextGraphics(text));
        restartList();
    }

    protected void removeText(String text) {
        for (TextGraphics textGraphics: textGraphicsList) {
            if (textGraphics.getText() == text) {
                textGraphicsList.remove(textGraphics);
                break;
            }
        }

        restartList();
    }

    protected void removeText(int i) {
        textGraphicsList.remove(i);
        restartList();
    }

    private void restartList() {
        //Check if the list is over screen
        maxSize = calculateMaxSize(screenHeight);
        if (maxSize != textGraphicsList.size()) {
            isOverScreen = true;
        }

        mainIndex = 0;

        setText();
    }
    protected String getText(int mainIndex) {
        return textGraphicsList.get(mainIndex).getText();
    }
}
