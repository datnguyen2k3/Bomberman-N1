package uet.oop.bomberman.utils;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.StillObject.Brick;
import uet.oop.bomberman.entities.StillObject.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character.Character;

public class CollisionChecker {
    BombermanGame game;

    public CollisionChecker(BombermanGame game) {
        this.game = game;
    }

    private boolean isBomb(int xUnit, int yUnit, Entity e) {
        return !game.getBombManagement().isCanMoveThroughBomb(xUnit, yUnit, (Character) e);
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

        boolean checkBrick = true;
        if (e instanceof Character) {
            checkBrick = !((Character) e).getPassBrick();
        }


        switch (e.get_state()) {
            case GO_NORTH: {
                entityTopRow = (entityTopY - e.getSpeed()) / Sprite.SCALED_SIZE;
                typeTileLeft = BombermanGame.diagramMap[entityTopRow][entityLeftCol];
                typeTileRight = BombermanGame.diagramMap[entityTopRow][entityRightCol];
                if (isBomb(entityLeftCol, entityTopRow, e) || isBomb(entityRightCol, entityTopRow,e)
                        || Brick.isBrick(typeTileLeft, checkBrick) || Wall.isWall(typeTileRight)
                        || Brick.isBrick(typeTileRight, checkBrick) || Wall.isWall(typeTileLeft)) {
                    e.isCollisionOn = true;
                }
                break;
            }
            case GO_SOUTH: {
                entityBottomRow = (entityBottomY + e.getSpeed()) / Sprite.SCALED_SIZE;
                typeTileLeft = BombermanGame.diagramMap[entityBottomRow][entityLeftCol];
                typeTileRight = BombermanGame.diagramMap[entityBottomRow][entityRightCol];
                if (isBomb(entityLeftCol, entityBottomRow, e) || isBomb(entityRightCol, entityBottomRow, e)
                        || Brick.isBrick(typeTileLeft, checkBrick) || Wall.isWall(typeTileRight)
                        || Brick.isBrick(typeTileRight, checkBrick) || Wall.isWall(typeTileLeft)) {
                    e.isCollisionOn = true;
                }
                break;
            }
            case GO_EAST: {
                entityRightCol = (entityRightSideX + e.getSpeed()) / Sprite.SCALED_SIZE;
                typeTileUp = BombermanGame.diagramMap[entityTopRow][entityRightCol];
                typeTileDown = BombermanGame.diagramMap[entityBottomRow][entityRightCol];
                if (isBomb(entityRightCol, entityBottomRow, e) || isBomb(entityRightCol, entityTopRow, e)
                        || Brick.isBrick(typeTileUp, checkBrick) || Wall.isWall(typeTileDown)
                        || Brick.isBrick(typeTileDown, checkBrick) || Wall.isWall(typeTileUp)) {
                    e.isCollisionOn = true;
                }
                break;
            }
            case GO_WEST: {
                entityLeftCol = (entityLeftSideX - e.getSpeed()) / Sprite.SCALED_SIZE;
                typeTileUp = BombermanGame.diagramMap[entityTopRow][entityLeftCol];
                typeTileDown = BombermanGame.diagramMap[entityBottomRow][entityLeftCol];
                if (isBomb(entityLeftCol, entityBottomRow, e) || isBomb(entityLeftCol, entityTopRow, e)
                        || Brick.isBrick(typeTileUp, checkBrick) || Wall.isWall(typeTileDown)
                        || Brick.isBrick(typeTileDown, checkBrick) || Wall.isWall(typeTileUp)) {
                    e.isCollisionOn = true;
                }
                break;

            }
        }
    }
}
