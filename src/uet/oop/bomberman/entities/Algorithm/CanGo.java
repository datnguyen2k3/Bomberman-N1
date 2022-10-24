package uet.oop.bomberman.entities.Algorithm;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character.Character;
import uet.oop.bomberman.graphics.Sprite;

public class CanGo {
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int UP = 3;
    public static final int DOWN = 4;
    public static final int CANT_MOVE = -1;

    public static boolean isCanGoUp(Character character, char[][] map) {
        int xUnit = character.get_xUnit();
        int yUnit = character.get_yUnit();

        return xUnit * Sprite.SCALED_SIZE < character.getX()
                && (xUnit + 1) * Sprite.SCALED_SIZE > character.getX() + character.getSolidArea().width
                && MinPath.isCanMove(xUnit, yUnit - 1, BombermanGame.diagramMap, character);
    }

    public static boolean isCanGoDown(Character character, char[][] map) {
        int xUnit = character.get_xUnit();
        int yUnit = character.get_yUnit();

        //System.out.println(character.getX() + " " + character.getY());

        return xUnit * Sprite.SCALED_SIZE < character.getX()
                && (xUnit + 1) * Sprite.SCALED_SIZE > character.getX() + character.getSolidArea().width
                && MinPath.isCanMove(xUnit, yUnit + 1, BombermanGame.diagramMap, character);
    }

    public static boolean isCanGoLeft(Character character, char[][] map) {
        int xUnit = character.get_xUnit();
        int yUnit = character.get_yUnit();

        return yUnit * Sprite.SCALED_SIZE < character.getY()
                && (yUnit + 1) * Sprite.SCALED_SIZE > character.getY() + character.getSolidArea().height
                && MinPath.isCanMove(xUnit - 1, yUnit, BombermanGame.diagramMap, character);
    }

    public static boolean isCanGoRight(Character character, char[][] map) {
        int xUnit = character.get_xUnit();
        int yUnit = character.get_yUnit();

        return yUnit * Sprite.SCALED_SIZE < character.getY()
                && (yUnit + 1) * Sprite.SCALED_SIZE > character.getY() + character.getSolidArea().height
                && MinPath.isCanMove(xUnit + 1, yUnit, BombermanGame.diagramMap, character);
    }



}
