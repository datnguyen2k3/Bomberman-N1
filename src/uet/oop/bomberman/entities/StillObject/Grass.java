package uet.oop.bomberman.entities.StillObject;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Grass extends Entity {
    public static final char diagramGrass = ' ';

    @Override
    public void initSolidArea() {

    }

    public Grass(int x, int y) {
        super(x, y);
        initSolidArea();
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


}
