package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Wall extends Entity {
    private static final char[] diagramWalls = new char[]{'#'};
    public Wall(int x, int y, Image img) {
        super(x, y, img);
    }

    public static boolean isWall(char diagram) {
        for (char d : diagramWalls) {
            if (d == diagram)
                return true;
        }
        return false;
    }

    @Override
    public void update() {

    }
}
