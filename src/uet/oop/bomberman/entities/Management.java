package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.List;

public abstract class Management {
    protected List<Entity> list = new ArrayList<>();
    public void render(GraphicsContext gc) {
        for(Entity e : list) {
            e.render(gc);
        }
    }
    public void update() {
        for(Entity e : list) {
            e.update();
        }
    }

}
