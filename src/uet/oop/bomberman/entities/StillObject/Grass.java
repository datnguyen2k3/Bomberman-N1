package uet.oop.bomberman.entities.StillObject;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Grass extends Entity {
    public static final char diagramGrass = ' ';

    @Override
    public void initSolidArea() {

    }

    public Grass(int x, int y, BombermanGame game) {
        super(x, y, game);
        initSolidArea();
        isEnd = false;
        BombermanGame.diagramMap[get_yUnit()][get_xUnit()] = ' ';
    }

    @Override
    protected void initSprite() {
        img = Sprite.grass.getFxImage();
    }

    @Override
    public void update() {

    }

    public static boolean isGrass(char diagram) {
        return diagram != Wall.diagramWall && diagram != Brick.diagramBrick;
    }
    public static boolean isGrass(int x, int y) {
        char currentDiagram = BombermanGame.diagramMap[get_yUnit(y)][get_xUnit(x)];
        return isGrass(currentDiagram);
    }

    public static boolean isGrass(int x, int y, boolean checkBrick) {
        if (!checkBrick) {
            return true;
        }

        return isGrass(x, y);
    }


}
