package uet.oop.bomberman.entities.Item;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class Item extends Entity {
    public static final char flameItemDiagram = 'f';
    public static final char bombItemDiagram = 'b';
    public static final char speedItemDiagram = 's';
    public static final char portalItemDiagram = 'x';
    public static final char hpItemDiagram = 'h';
    public static final char passBrickDiagram = 'w';
    public static final char[] items = {'f', 'b', 's', 'x', 'h', 'w'};
    protected char itemDiagram;
    protected boolean isActivate = false;
    protected boolean isTaken = false;

    public boolean isActivate() {
        return isActivate;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public static boolean isItem(char diagram) {
        for (char itemDiagram : items) {
            if (itemDiagram == diagram) {
                return true;
            }
        }

        return false;
    }

    public Item(int xUnit, int yUnit) {
        super(xUnit, yUnit);
        initItemDiagram();
        initSolidArea();
    }

    @Override
    public void initSolidArea() {

    }

    public void setActivate() {
        isActivate = true;
    }

    public void setTaken() {
        isActivate = false;
        isTaken = true;
    }

    public char getDiagramItem() {
        return itemDiagram;
    }

    protected abstract void initItemDiagram();

    @Override
    public void render(GraphicsContext gc) {
        if (!isActivate)
            return;
        super.render(gc);
    }

    @Override
    public void update() {

    }
}
