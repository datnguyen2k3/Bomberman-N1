package uet.oop.bomberman.utils;

import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.StillObject.Brick;
import uet.oop.bomberman.entities.StillObject.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.map.MapParser;

public class CollisionChecker {
    BombermanGame game;

    public CollisionChecker(BombermanGame game) {
        this.game = game;
    }

    public void checkTile(Entity e) {
        // get 4 point at x-axis, y-axis represent entity's rectangle.
        int entityLeftSideX = e.getX() + e.solidArea.x;
        int entityRightSideX = entityLeftSideX + e.solidArea.width;
        int entityTopY = e.getY() + e.solidArea.y;
        int entityBottomY = entityTopY + e.solidArea.height;

        // get col and row of each side by divide coordinate by sprite scaled size.
        int entityLeftCol = entityLeftSideX / Sprite.SCALED_SIZE;
        int entityRightCol = entityRightSideX / Sprite.SCALED_SIZE;
        int entityTopRow = entityTopY / Sprite.SCALED_SIZE;
        int entityBottomRow = entityBottomY / Sprite.SCALED_SIZE;

        char typeTileLeft, typeTileRight;
        char typeTileUp, typeTileDown;

        switch (e.get_state()) {
            case GO_NORTH: {
                entityTopRow = (entityTopY - e.getSpeed()) / Sprite.SCALED_SIZE;
                if (!isOutOfMap(BombermanGame.diagramMap, 0, entityTopRow)) {
                    typeTileLeft = BombermanGame.diagramMap[entityTopRow][entityLeftCol];
                    typeTileRight = BombermanGame.diagramMap[entityTopRow][entityRightCol];
                    if (Brick.isBrick(typeTileLeft) || Wall.isWall(typeTileRight) || Brick.isBrick(typeTileRight) || Wall.isWall(typeTileLeft)) {
                        e.isCollisionOn = true;
                    }
                } else {
                    e.isCollisionOn = true;
                }
                break;
            }
            case GO_SOUTH: {
                entityBottomRow = (entityBottomY + e.getSpeed()) / Sprite.SCALED_SIZE;
                if (!isOutOfMap(BombermanGame.diagramMap, 0, entityBottomRow)) {
                    typeTileLeft = BombermanGame.diagramMap[entityBottomRow][entityLeftCol];
                    typeTileRight = BombermanGame.diagramMap[entityBottomRow][entityRightCol];
                    if (Brick.isBrick(typeTileLeft) || Wall.isWall(typeTileRight) || Brick.isBrick(typeTileRight) || Wall.isWall(typeTileLeft)) {
                        e.isCollisionOn = true;
                    }
                } else {
                    e.isCollisionOn = true;
                }
                break;
            }
            case GO_EAST: {
                entityRightCol = (entityRightSideX + e.getSpeed()) / Sprite.SCALED_SIZE;
                if (!isOutOfMap(BombermanGame.diagramMap, entityRightCol, 0)) {
                    typeTileUp = BombermanGame.diagramMap[entityTopRow][entityRightCol];
                    typeTileDown = BombermanGame.diagramMap[entityBottomRow][entityRightCol];
                    if (Brick.isBrick(typeTileUp) || Wall.isWall(typeTileDown) || Brick.isBrick(typeTileDown) || Wall.isWall(typeTileUp)) {
                        e.isCollisionOn = true;
                    }
                } else {
                    e.isCollisionOn = true;
                }

                break;
            }
            case GO_WEST: {
                entityLeftCol = (entityLeftSideX - e.getSpeed()) / Sprite.SCALED_SIZE;
                if (!isOutOfMap(BombermanGame.diagramMap, entityLeftCol, 0)) {
                    typeTileUp = BombermanGame.diagramMap[entityTopRow][entityLeftCol];
                    typeTileDown = BombermanGame.diagramMap[entityBottomRow][entityLeftCol];
                    if (Brick.isBrick(typeTileUp) || Wall.isWall(typeTileDown) || Brick.isBrick(typeTileDown) || Wall.isWall(typeTileUp)) {
                        e.isCollisionOn = true;
                    }
                } else {
                    e.isCollisionOn = true;
                }
                break;

            }
        }
    }

    public static boolean isOutOfMap(char[][] diagramMap, int x, int y) {
        if (x < 0 || x > MapParser.getMapWidth(diagramMap) || y < 0 || y > MapParser.getMapHeight(diagramMap)) {
            return true;
        }
        return false;
    }
}
