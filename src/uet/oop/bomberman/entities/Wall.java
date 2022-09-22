package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class Wall extends Entity {
    public static final char diagramWall = '#';
    public Wall(int x, int y, Image img) {
        super(x, y, img);
    }

    public static boolean isWall(char diagram) {
        return diagram == diagramWall;
    }
    public static boolean isWall(int xUnit, int yUnit) {
        return isWall(BombermanGame.diagramMap[yUnit][xUnit]);
    }
    @Override
    public void update() {

    }

    @Override
    public boolean pointIsOnEntityArea(Point p){
        return false;
    }
}
