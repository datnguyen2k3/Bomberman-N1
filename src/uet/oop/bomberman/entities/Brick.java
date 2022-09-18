package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Brick extends Entity{
    private static final char[] diagramWalls = new char[]{'*', 'x', 'b', 'f', 's'};
    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public static boolean isBrick(char diagram) {
        for (char d : diagramWalls) {
            if (diagram == d)
                return true;
        }
        return false;
    }

    @Override
    public void update() {

    }
}
