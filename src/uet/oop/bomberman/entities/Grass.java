package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Grass extends Entity {

    public static final int GRASS_WIDTH = 14 * (Sprite.SCALED_SIZE / Sprite.DEFAULT_SIZE);
    public static final int GRASS_HEIGHT = 16 * (Sprite.SCALED_SIZE / Sprite.DEFAULT_SIZE);

    public static final char diagramGrass = ' ';

    public Grass() {
        super();

    }

    public Grass(int x, int y, Image img) {
        super(x, y, img);
        BombermanGame.diagramMap[get_yUnit()][get_xUnit()] = ' ';
    }

    @Override
    public void update() {

    }

    @Override
    public boolean pointIsOnEntityArea(Point p) {
        if ((p.getX() >= x && p.getX() <= x + Grass.GRASS_WIDTH) && (p.getY() >= y && p.getY() <= y + GRASS_HEIGHT)) {
            System.out.println("on grass ");
            return true;
        }
        return false;
    }


    public static boolean isGrass(char diagram) {
        return diagram != Wall.diagramWall && diagram != Brick.diagramBrick;
    }

    public static boolean isGrass(int x, int y) {
        char currentDiagram = BombermanGame.diagramMap[get_yUnit(y)][get_xUnit(x)];
        return isGrass(currentDiagram);
    }
}
