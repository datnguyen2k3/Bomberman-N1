package uet.oop.bomberman.entities.Item;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class Item extends Entity {
    public static final char flameItemDiagram = 'f';
    public static final char bombItemDiagram = 'b';
    public static final char speedItemDiagram = 's';
    public static final char portalItemDiagram = 'x';
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
        return diagram == flameItemDiagram
                || diagram == bombItemDiagram
                || diagram == speedItemDiagram
                || diagram == portalItemDiagram;
    }

    public Item(int xUnit, int yUnit) {
        super(xUnit, yUnit);
        initItemDiagram();
        initSolidArea();
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
}
