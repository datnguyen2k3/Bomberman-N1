package uet.oop.bomberman.entities.StillObject;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Wall extends Entity {
    public static final char diagramWall = '#';

    @Override
    public void initSolidArea() {
    }

    public Wall(int xUnit, int yUnit, BombermanGame game) {
        super(xUnit, yUnit, game);
        initSolidArea();
        isEnd = false;
    }

    @Override
    protected void initSprite() {
        img = Sprite.wall.getFxImage();
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
}
