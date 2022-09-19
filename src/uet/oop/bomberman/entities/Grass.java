package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Grass extends Entity {
    private static char[] diagramGrasses = new char[]{'p', '1', '2', ' '};
    public Grass(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    public static boolean isGrass(char diagram) {
        for (char d : diagramGrasses) {
            if (diagram == d)
                return true;
        }

        return false;
    }
    public static boolean isGrass(int x, int y) {
        char currentDiagram = BombermanGame.diagramMap[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE];
        return isGrass(currentDiagram);
    }
}
