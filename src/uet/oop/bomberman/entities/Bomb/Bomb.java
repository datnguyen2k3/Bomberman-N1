package uet.oop.bomberman.entities.Bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;
import uet.oop.bomberman.entities.StillObject.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.StillObject.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Coordinate;
import uet.oop.bomberman.utils.State;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Character.Character;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends Entity {

    public static final char bombDiagram = '@';
    List<Coordinate> explodedBrick = new ArrayList<>();

    private int animateFlameRight = 0;
    private int animateFlameLeft = 0;
    private int animateFlameTop = 0;
    private int animateFlameDown = 0;

    private int explodingCounter = 0;
    private int brickDestroyCounterRight = 0;
    private int brickDestroyCounterDown = 0;
    private int brickDestroyCounterTop = 0;
    private int brickDestroyCounterLeft = 0;
    private int canDestroyBrick = 0;
    private BombManagement bombManagement;
    private boolean isWaitedToExploding = false;
    private boolean isExploded = false;
    public int TIME_WAIT_TO_EXPLODING = 60 * 2;
    public int TIME_EXPLODING = 30;
    public int timeRefresh = TIME_EXPLODING + TIME_WAIT_TO_EXPLODING;
    private int currentTimeWaitToExploding = TIME_WAIT_TO_EXPLODING;
    private int currentTimeExploding = TIME_EXPLODING;
    private int timeBrickCollapse = 100;
    private List<Character> charactersInBomb = new ArrayList<>();
    private List<Bomb> bombList = new ArrayList<>();
    private int explodedLength;


    public List<Pair<Integer, Integer>> explodedCells = new ArrayList<>();
    public List<Pair<Integer, Integer>> destroyedBricks = new ArrayList<>();

    private Sprite currentSprite = Sprite.bomb;
    private Sprite curFlameSprite = Sprite.explosion_vertical;

    private Sprite bomb = Sprite.bomb;
    private Sprite bomb1 = Sprite.bomb_1;
    private Sprite bomb2 = Sprite.bomb_2;

    private Sprite bombExploding = Sprite.bomb_exploded;
    private Sprite bombExploding1 = Sprite.bomb_exploded1;
    private Sprite bombExploding2 = Sprite.bomb_exploded2;

    private int animateWaitingToExplode = 0;
    private int animateExploding = 0;

    Coordinate firstBrickRight;
    Coordinate firstBrickLeft;
    Coordinate firstBrickTop;
    Coordinate firstBrickDown;
    private boolean isPlayExplosionSound = false;

    public void setCurrentTimeWaitToExploding(int currentTimeWaitToExploding) {
        this.currentTimeWaitToExploding = currentTimeWaitToExploding;
    }

    public Bomb(int xUnit, int yUnit, BombManagement bombManagement, BombermanGame game) {
        super(xUnit, yUnit, game);
        _state = State.WAITING_EXPLODING;
        initSolidArea();
        this.bombManagement = bombManagement;
        this.game = game;
        findFirstBrickAt4Side();
        initCharacterInBomb();
        explodedLength = bombManagement.getExplodedLength();
        BombermanGame.diagramMap[get_yUnit()][get_xUnit()] = Bomb.bombDiagram;
    }

    private void initCharacterInBomb() {
        if (game.getBomberman().isImpact(get_xUnit(), get_yUnit())) {
            charactersInBomb.add(game.getBomberman());
        }

        for (Entity e : game.getEnemyManagement().getList()) {
            Character c = (Character) e;
            if (c.isImpact(get_xUnit(), get_yUnit())) {
                charactersInBomb.add(c);
            }
        }
    }

    private void updateCharacterInBomb() {
        List<Character> newCharactersInBomb = new ArrayList<>();
        for (Character c : charactersInBomb) {
            if (c.isImpact(get_xUnit(), get_yUnit())) {
                newCharactersInBomb.add(c);
            }
        }
        charactersInBomb = newCharactersInBomb;
    }

    public boolean isCharacterInBomb(Character other) {
        for (Character c : charactersInBomb) {
            if (c == other)
                return true;
        }
        return false;
    }

    private void findFirstBrickAt4Side() {
        firstBrickRight = firstBrickToBeDestroyed(get_xUnit(), get_yUnit(), "right");
        firstBrickLeft = firstBrickToBeDestroyed(get_xUnit(), get_yUnit(), "left");
        firstBrickTop = firstBrickToBeDestroyed(get_xUnit(), get_yUnit(), "top");
        firstBrickDown = firstBrickToBeDestroyed(get_xUnit(), get_yUnit(), "down");
    }

    @Override
    public void initSolidArea() {

    }

    @Override
    protected void initSprite() {
        img = Sprite.bomb.getFxImage();
    }

    public void activeExploding() {
        this._state = State.EXPLODING;
        isWaitedToExploding = true;
    }

    public boolean isWaitedToExploding() {
        return isWaitedToExploding;
    }

    public boolean isExploded() {
        return isExploded;
    }

    private void waitToExploding() {
        if (!isWaitedToExploding) {
            img = Sprite.bomb.getFxImage();
            isWaitedToExploding = true;
            _state = State.WAITING_EXPLODING;
        }
        currentTimeWaitToExploding--;
    }

    public boolean isOnCurrentAreaExploding(Bomb b) {
        System.out.println(explodedLength);
        if (b.get_yUnit() == get_yUnit() && b.get_xUnit() > get_xUnit()) {
            if (firstBrickRight.get_xUnit() == get_xUnit() + explodedLength) {
                if ((b.get_xUnit() <= get_xUnit() + explodedLength && b.get_xUnit() > get_xUnit())) {
                    return true;
                }
            } else {
                if (b.get_xUnit() != get_xUnit()
                        && get_yUnit() == b.get_yUnit()
                        && (b.get_xUnit() <= firstBrickRight.getX() && b.get_xUnit() >= get_xUnit())) {
                    return true;
                }
            }
            return false;
        }

        if (b.get_yUnit() == get_yUnit() && b.get_xUnit() < get_xUnit()) {
            if (firstBrickLeft.get_xUnit() == get_xUnit() - explodedLength) {
                if ((b.get_xUnit() < get_xUnit() && b.get_xUnit() >= get_xUnit() - explodedLength)) {
                    return true;
                }
            } else {
                if (b.get_xUnit() != get_xUnit()
                        && get_yUnit() == b.get_yUnit()
                        && (b.get_xUnit() >= firstBrickLeft.getX()  && b.get_xUnit() < get_xUnit())) {
                    return true;
                }
            }
            return false;
        }

        if (b.get_xUnit() == get_xUnit() && b.get_yUnit() < get_yUnit()) {

            if (firstBrickTop.get_yUnit() == get_yUnit() - explodedLength) {
                if ((b.get_yUnit() < get_yUnit() && b.get_yUnit() >= get_yUnit() - explodedLength)) {
                    return true;
                }
            } else {
                if (b.get_yUnit() != get_yUnit()
                        && get_xUnit() == b.get_xUnit()
                        && (b.get_yUnit() >= firstBrickTop.get_yUnit()  && b.get_yUnit() < get_yUnit())) {
                    System.out.println("Bomb on top");
                    return true;
                }
            }
            return false;
        }

        if (b.get_xUnit() == get_xUnit() && b.get_yUnit() > get_yUnit()) {
            if (firstBrickDown.get_yUnit() == get_yUnit() + explodedLength) {
                if ((b.get_yUnit() <= get_yUnit() + explodedLength && b.get_yUnit() >= get_yUnit())) {
                    return true;
                }
            } else {
                if (b.get_yUnit() != get_yUnit()
                        && get_xUnit() == b.get_xUnit()
                        && (b.get_yUnit() <= firstBrickDown.get_yUnit() && b.get_yUnit() > get_yUnit())) {
                    return true;
                }
            }
        }

//        if (b.get_yUnit() != get_yUnit()
//                && get_xUnit() == b.get_xUnit()
//                && (b.get_yUnit() >= get_yUnit() - explodedLength && b.get_yUnit() <= get_yUnit() + explodedLength)) {
//            return true;
//        }
        return false;

    }

    public void setAdjacentBombExplode() {
        for (Entity e : bombList) {
            if (e instanceof Bomb) {
                if (_state == State.EXPLODING
                        && e.get_state() == State.WAITING_EXPLODING
                        && this.isOnCurrentAreaExploding((Bomb) e)) {
                    System.out.println(true);
                    ((Bomb) e).activeExploding();
                    ((Bomb) e).explode();
                }
            }
        }
    }

    private void explode() {
        if (!isExploded) {
            img = Sprite.bomb_exploded.getFxImage();
            _state = State.EXPLODING;
            isExploded = true;
            setExplodedCells();
        }
        currentTimeExploding--;
    }

    protected void setEnd() {
        isEnd = true;
        BombermanGame.diagramMap[get_yUnit()][get_xUnit()] = ' ';
    }

    private void running() {
        waitToExploding();
        if (currentTimeWaitToExploding > 0)
            return;

        explode();
        if (currentTimeExploding > 0)
            return;


        if (explodedBrick.isEmpty()) {
            setEnd();
        }

    }

    private boolean setExplodedCell(int xUnit, int yUnit) {
        if (Wall.isWall(xUnit, yUnit))
            return false;
        if (Brick.isBrick(xUnit, yUnit)) {
            destroyedBricks.add(new Pair<>(xUnit, yUnit));
            return false;
        }
        explodedCells.add(new Pair<>(xUnit, yUnit));
        return true;
    }


    // get the first brick to be destroyed, we need render from bomb location to this brick location, not render over.
    private Coordinate firstBrickToBeDestroyed(int startXUnit, int startYUnit, String direction) {

        Coordinate res = new Coordinate(0, 0);
        if (direction.equals("right")) {
            res.setY(get_yUnit());
            if (startXUnit + bombManagement.getExplodedLength() >= BombermanGame.WIDTH) {
                res.setX(BombermanGame.WIDTH - 1);
            } else {
                res.setX(startXUnit + bombManagement.getExplodedLength());
            }
            for (int i = startXUnit + 1; i <= startXUnit + bombManagement.getExplodedLength(); i++) {
                if (i >= BombermanGame.WIDTH) {
                    i = BombermanGame.WIDTH - 1;
                    res.setX(i);
                    break;
                }
                if (Wall.isWall(i, startYUnit)) {
                    res.setX(i - 1);
                    res.setY(startYUnit);
                    return res;
                }
                if (Brick.isBrick(i, startYUnit)) {
                    res.setX(i - 1);
                    res.setY(startYUnit);
                    Coordinate realBrick = new Coordinate(i, startYUnit);
                    explodedBrick.add(realBrick);
                    return res;
                }
            }
            return res;
        } else if (direction.equals("left")) {
            res.setY(get_yUnit());
            if (startXUnit - bombManagement.getExplodedLength() <= 0) {
                res.setX(0);
            } else {
                res.setX(startXUnit - bombManagement.getExplodedLength());
            }
            for (int i = startXUnit - 1; i >= startXUnit - bombManagement.getExplodedLength(); i--) {
                if (i <= 0) {
                    i = 0;
                    res.setX(i);
                    break;
                }

                if (Wall.isWall(i, startYUnit)) {
                    res.setX(i + 1);
                    res.setY(startYUnit);
                    return res;
                }
                if (Brick.isBrick(i, startYUnit)) {
                    res.setX(i + 1);
                    res.setY(startYUnit);
                    Coordinate realBrick = new Coordinate(i, startYUnit);
                    explodedBrick.add(realBrick);
                    return res;
                }
            }
            return res;
        } else if (direction.equals("top")) {
            res.setX(get_xUnit());
            if (startYUnit - bombManagement.getExplodedLength() <= 0) {
                res.setY(0);
            } else {
                res.setY(startYUnit - bombManagement.getExplodedLength());
            }
            for (int i = startYUnit - 1; i >= startYUnit - bombManagement.getExplodedLength(); i--) {
                if (i <= 0) {
                    i = 0;
                    res.setY(i);
                    break;
                }
                if (Wall.isWall(get_xUnit(), i)) {
                    res.setX(get_xUnit());
                    res.setY(i + 1);
                    return res;
                }
                if (Brick.isBrick(get_xUnit(), i)) {
                    res.setX(get_xUnit());
                    res.setY(i + 1);
                    Coordinate realBrick = new Coordinate(get_xUnit(), i);
                    explodedBrick.add(realBrick);
                    return res;
                }
            }
            return res;
        } else {
            res.setX(get_xUnit());
            if (startYUnit + bombManagement.getExplodedLength() >= BombermanGame.HEIGHT) {
                res.setY(BombermanGame.HEIGHT - 1);
            } else {
                res.setY(startYUnit + bombManagement.getExplodedLength());
            }
            for (int i = startYUnit + 1; i <= startYUnit + bombManagement.getExplodedLength(); i++) {
                if (i >= BombermanGame.HEIGHT) {
                    i = BombermanGame.HEIGHT - 1;
                    res.setY(i);
                    break;
                }
                if (Wall.isWall(get_xUnit(), i)) {
                    res.setX(get_xUnit());
                    res.setY(i - 1);
                    return res;
                }
                if (Brick.isBrick(get_xUnit(), i)) {
                    res.setX(get_xUnit());
                    res.setY(i - 1);
                    Coordinate realBrick = new Coordinate(get_xUnit(), i);
                    explodedBrick.add(realBrick);
                    return res;
                }
            }
            return res;
        }


    }


    private void setExplodedCells() {
        setExplodedCell(get_xUnit(), get_yUnit());
        for (int j = get_xUnit() + 1; j <= get_xUnit() + bombManagement.getExplodedLength(); j++) {
            if (!setExplodedCell(j, get_yUnit()))
                break;
        }

        for (int j = get_xUnit() - 1; j >= get_xUnit() - bombManagement.getExplodedLength(); j--) {
            if (!setExplodedCell(j, get_yUnit()))
                break;
        }

        for (int i = get_yUnit() + 1; i <= get_yUnit() + bombManagement.getExplodedLength(); i++) {
            if (!setExplodedCell(get_xUnit(), i))
                break;
        }

        for (int i = get_yUnit() - 1; i >= get_yUnit() - bombManagement.getExplodedLength(); i--) {
            if (!setExplodedCell(get_xUnit(), i))
                break;
        }
    }


    public void choosingSprite() {

        switch (_state) {
            case WAITING_EXPLODING: {
                currentSprite = Sprite.movingSprite(bomb, bomb1, bomb2, animateWaitingToExplode, TIME_WAIT_TO_EXPLODING / 2);
                break;
            }
            case EXPLODING: {
                if (isPlayExplosionSound == false) {
                    game.getSoundTrack().playExplosion();
                    isPlayExplosionSound = true;
                }
                currentSprite = Sprite.movingSprite(bombExploding, bombExploding1, bombExploding2, animateExploding, TIME_EXPLODING / 2);
                break;
            }
        }
    }

    private void renderFlameRight(GraphicsContext gc, int xUnit, boolean isLast) {
        if (isLast) {
            curFlameSprite = Sprite.movingSprite(Sprite.explosion_horizontal_right_last
                    , Sprite.explosion_horizontal_right_last1
                    , Sprite.explosion_horizontal_right_last2, animateFlameRight, TIME_EXPLODING / 2);
            super.render(gc, curFlameSprite.getFxImage()
                    , xUnit * Sprite.SCALED_SIZE
                    , get_yUnit() * Sprite.SCALED_SIZE);
        } else {
            curFlameSprite = Sprite.movingSprite(Sprite.explosion_horizontal
                    , Sprite.explosion_horizontal1
                    , Sprite.explosion_horizontal2, animateFlameRight, TIME_EXPLODING / 2);
            super.render(gc, curFlameSprite.getFxImage()
                    , xUnit * Sprite.SCALED_SIZE
                    , get_yUnit() * Sprite.SCALED_SIZE);
        }
    }

    private void renderFlameLeft(GraphicsContext gc, int xUnit, boolean isLast) {
        if (isLast) {
            curFlameSprite = Sprite.movingSprite(Sprite.explosion_horizontal_left_last
                    , Sprite.explosion_horizontal_left_last1
                    , Sprite.explosion_horizontal_left_last2, animateFlameLeft, TIME_EXPLODING / 2);
            super.render(gc, curFlameSprite.getFxImage()
                    , xUnit * Sprite.SCALED_SIZE
                    , get_yUnit() * Sprite.SCALED_SIZE);

        } else {
            curFlameSprite = Sprite.movingSprite(Sprite.explosion_horizontal
                    , Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, animateFlameLeft, TIME_EXPLODING / 2);
            super.render(gc, curFlameSprite.getFxImage()
                    , xUnit * Sprite.SCALED_SIZE
                    , get_yUnit() * Sprite.SCALED_SIZE);
        }
    }

    private void renderFlameTop(GraphicsContext gc, int yUnit, boolean isLast) {
        if (isLast) {
            curFlameSprite = Sprite.movingSprite(Sprite.explosion_vertical_top_last
                    , Sprite.explosion_vertical_top_last1
                    , Sprite.explosion_vertical_top_last2, animateFlameTop, TIME_EXPLODING / 2);
            super.render(gc, curFlameSprite.getFxImage()
                    , get_xUnit() * Sprite.SCALED_SIZE
                    , yUnit * Sprite.SCALED_SIZE);
        } else {
            curFlameSprite = Sprite.movingSprite(Sprite.explosion_vertical
                    , Sprite.explosion_vertical1
                    , Sprite.explosion_vertical2, animateFlameTop, TIME_EXPLODING / 2);
            super.render(gc, curFlameSprite.getFxImage()
                    , get_xUnit() * Sprite.SCALED_SIZE
                    , yUnit * Sprite.SCALED_SIZE);
        }
    }


    private void renderFlameDown(GraphicsContext gc, int yUnit, boolean isLast) {

        if (isLast) {
            curFlameSprite = Sprite.movingSprite(Sprite.explosion_vertical_down_last
                    , Sprite.explosion_vertical_down_last1
                    , Sprite.explosion_vertical_down_last2, animateFlameDown, TIME_EXPLODING / 2);
            super.render(gc, curFlameSprite.getFxImage()
                    , get_xUnit() * Sprite.SCALED_SIZE
                    , yUnit * Sprite.SCALED_SIZE);
        } else {
            curFlameSprite = Sprite.movingSprite(Sprite.explosion_vertical
                    , Sprite.explosion_vertical1
                    , Sprite.explosion_vertical2, animateFlameDown, TIME_EXPLODING / 2);
            super.render(gc, curFlameSprite.getFxImage()
                    , get_xUnit() * Sprite.SCALED_SIZE
                    , yUnit * Sprite.SCALED_SIZE);
        }
    }

    private void log(Coordinate c) {
        // System.out.println(c.toString());
    }

    public void draw4SideFlame(GraphicsContext gc) {
        // check if bomb is exploding, find all grass tiles at 4 side within this exploded length and render
        // flame on it.

        if (_state == State.EXPLODING) {
            //right flame.
            for (int j = get_xUnit() + 1; j <= firstBrickRight.getX(); j++) {
                if (j != firstBrickRight.getX()) {
                    if (Wall.isWall(j, get_yUnit())) {
                        break;
                    } else {
                        if (Brick.isBrick(j, get_yUnit())) {
                            renderFlameRight(gc, j, false);
                            break;
                        } else {
                            renderFlameRight(gc, j, false);

                        }
                    }
                } else {
                    if (Wall.isWall(j, get_yUnit())) break;
                    else {
                        if (Brick.isBrick(j, get_yUnit())) {
                            renderFlameRight(gc, j, true);
                            break;
                        } else {
                            renderFlameRight(gc, j, true);
                        }
                    }
                }
            }


            //left flame
            for (int j = get_xUnit() - 1; j >= firstBrickLeft.getX(); j--) {
                if (j != firstBrickLeft.getX()) {
                    if (Wall.isWall(j, get_yUnit())) break;
                    else {
                        if (Brick.isBrick(j, get_yUnit())) {
                            renderFlameLeft(gc, j, false);
                            break;
                        } else {
                            renderFlameLeft(gc, j, false);

                        }
                    }
                } else {

                    if (Wall.isWall(j, get_yUnit())) break;
                    else {
                        if (Brick.isBrick(j, get_yUnit())) {

                            renderFlameLeft(gc, j, true);
                            break;
                        } else {
                            renderFlameLeft(gc, j, true);

                        }
                    }
                }
            }

            // top flame
            for (int j = get_yUnit() - 1; j >= firstBrickTop.getY(); j--) {
                if (j != firstBrickTop.getY()) {
                    if (Wall.isWall(get_xUnit(), j)) break;
                    else {
                        if (Brick.isBrick(get_xUnit(), j)) {
                            renderFlameTop(gc, j, false);
                            break;
                        } else {
                            renderFlameTop(gc, j, false);

                        }
                    }
                } else {

                    if (Wall.isWall(get_xUnit(), j)) break;
                    else {
                        if (Brick.isBrick(get_xUnit(), j)) {
                            renderFlameTop(gc, j, true);
                            break;
                        } else {
                            renderFlameTop(gc, j, true);
                        }
                    }
                }
            }

            // down flame
            for (int j = get_yUnit() + 1; j <= firstBrickDown.getY(); j++) {
                if (j != firstBrickDown.getY()) {
                    if (Wall.isWall(get_xUnit(), j)) break;
                    else {
                        if (Brick.isBrick(get_xUnit(), j)) {
                            renderFlameDown(gc, j, false);
                            break;
                        } else {
                            renderFlameDown(gc, j, false);
                        }
                    }
                } else {

                    if (Wall.isWall(get_xUnit(), j)) break;
                    else {
                        if (Brick.isBrick(get_xUnit(), j)) {
                            renderFlameDown(gc, j, true);
                            break;
                        } else {
                            renderFlameDown(gc, j, true);
                        }
                    }
                }
            }


        }

    }

    @Override
    public void update() {
        animateFlameRight = animate(animateFlameRight);
        animateFlameLeft = animate(animateFlameLeft);
        animateFlameTop = animate(animateFlameTop);
        animateFlameDown = animate(animateFlameDown);

        bombList = bombManagement.getList();
        animateExploding = animate(animateExploding);
        animateWaitingToExplode = animate(animateWaitingToExplode);
        animate();
        running();
        updateCharacterInBomb();
        setAdjacentBombExplode();
    }

    @Override
    public void render(GraphicsContext gc) {
        explodedLength = bombManagement.getExplodedLength();
        choosingSprite();
        if (isEnd)
            return;
        img = currentSprite.getFxImage();
        super.render(gc);
        draw4SideFlame(gc);
        // _animate=0;
        renderExplodeBrick(gc);


    }

    public void renderExplodeBrick(GraphicsContext gc) {

        if (_state == State.EXPLODING) {
            Sprite curBrickSprite;
            log(firstBrickDown);
            // System.out.println(get_yUnit());
            if (firstBrickDown.getY() + 1 < BombermanGame.HEIGHT
                    && Brick.isBrick(firstBrickDown.getX(), firstBrickDown.getY() + 1)
                    && firstBrickDown.getY() + 1 <= get_yUnit() + bombManagement.getExplodedLength()) {

                curBrickSprite = Sprite.movingSprite(Sprite.brick_exploded,
                        Sprite.brick_exploded1,
                        Sprite.brick_exploded2, _animate, timeBrickCollapse);

                super.render(gc, curBrickSprite.getFxImage(),
                        firstBrickDown.getX() * Sprite.SCALED_SIZE,
                        (firstBrickDown.getY() + 1) * Sprite.SCALED_SIZE);

                brickDestroyCounterDown++;

                if (brickDestroyCounterDown == 50) {
                    brickDestroyCounterDown = 0;
                    BombermanGame.diagramMap[firstBrickDown.getY() + 1][firstBrickDown.getX()] = ' ';
                    game.findBrickAndDelete(firstBrickDown.getX(), firstBrickDown.getY() + 1);
                    if (!explodedBrick.isEmpty()) explodedBrick.remove(0);
                    canDestroyBrick = 1;
                }
            }

            if (firstBrickRight.getX() + 1 < BombermanGame.WIDTH
                    && Brick.isBrick(firstBrickRight.getX() + 1, firstBrickRight.getY())
                    && firstBrickRight.getX() + 1 <= get_xUnit() + bombManagement.getExplodedLength()) {

                curBrickSprite = Sprite.movingSprite(Sprite.brick_exploded,
                        Sprite.brick_exploded1,
                        Sprite.brick_exploded2, _animate, timeBrickCollapse);

                super.render(gc, curBrickSprite.getFxImage(),
                        (firstBrickRight.getX() + 1) * Sprite.SCALED_SIZE,
                        (firstBrickRight.getY()) * Sprite.SCALED_SIZE);

                brickDestroyCounterRight++;

                if (brickDestroyCounterRight == 50) {
                    brickDestroyCounterRight = 0;
                    BombermanGame.diagramMap[firstBrickRight.getY()][firstBrickRight.getX() + 1] = ' ';
                    game.findBrickAndDelete(firstBrickRight.getX() + 1, firstBrickRight.getY());
                    if (!explodedBrick.isEmpty()) explodedBrick.remove(0);
                    canDestroyBrick = 1;
                }

            }

            if (firstBrickLeft.getX() - 1 >= 0
                    && Brick.isBrick(firstBrickLeft.getX() - 1, firstBrickLeft.getY())
                    && firstBrickLeft.getX() - 1 >= get_xUnit() - bombManagement.getExplodedLength()) {

                curBrickSprite = Sprite.movingSprite(Sprite.brick_exploded,
                        Sprite.brick_exploded1,
                        Sprite.brick_exploded2, _animate, timeBrickCollapse);

                super.render(gc, curBrickSprite.getFxImage(),
                        (firstBrickLeft.getX() - 1) * Sprite.SCALED_SIZE,
                        (firstBrickLeft.getY()) * Sprite.SCALED_SIZE);

                brickDestroyCounterLeft++;

                if (brickDestroyCounterLeft == 50) {
                    brickDestroyCounterLeft = 0;
                    BombermanGame.diagramMap[firstBrickLeft.getY()][firstBrickLeft.getX() - 1] = ' ';
                    game.findBrickAndDelete(firstBrickLeft.getX() - 1, firstBrickLeft.getY());
                    if (!explodedBrick.isEmpty()) explodedBrick.remove(0);
                    canDestroyBrick = 1;
                }
            }

            if (firstBrickTop.getY() - 1 >= 0
                    && Brick.isBrick(firstBrickTop.getX(), firstBrickTop.getY() - 1)
                    && firstBrickTop.getY() - 1 >= get_yUnit() - bombManagement.getExplodedLength()) {

                curBrickSprite = Sprite.movingSprite(Sprite.brick_exploded,
                        Sprite.brick_exploded1,
                        Sprite.brick_exploded2,
                        _animate, timeBrickCollapse);

                super.render(gc, curBrickSprite.getFxImage(),
                        (firstBrickTop.getX()) * Sprite.SCALED_SIZE,
                        (firstBrickTop.getY() - 1) * Sprite.SCALED_SIZE);

                brickDestroyCounterTop++;

                if (brickDestroyCounterTop == 50) {
                    brickDestroyCounterTop = 0;
                    BombermanGame.diagramMap[firstBrickTop.getY() - 1][firstBrickTop.getX()] = ' ';
                    game.findBrickAndDelete(firstBrickTop.getX(), firstBrickTop.getY() - 1);
                    if (!explodedBrick.isEmpty()) explodedBrick.remove(0);
                    canDestroyBrick = 1;
                }
            }
        }
        if (explodedBrick.size() == 0 && canDestroyBrick == 1) {
            setEnd();
        }

        if (explodedBrick.size() != 0) {
            explodedBrick.clear();
            findFirstBrickAt4Side();
        }

    }

}
