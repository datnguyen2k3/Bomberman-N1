package uet.oop.bomberman.map;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Item.Item;
import uet.oop.bomberman.entities.Item.ItemManagement;
import uet.oop.bomberman.entities.StillObject.Brick;
import uet.oop.bomberman.entities.StillObject.Grass;
import uet.oop.bomberman.entities.StillObject.Wall;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Map {
    private int fullHeight;
    private int fullWidth;
    private int screenHeight;
    private int screenWidth;
    private int curX = 0;
    private int curY = 0;
    private static char[][] diagramMap;

    public Map(int fullHeight, int fullWidth, int screenHeight, int screenWidth) {
        this.fullHeight = fullHeight;
        this.fullWidth = fullWidth;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;

        diagramMap = new char[fullHeight][fullWidth];
    }

    public char[][] createDiagramMap() {
        try {
            BufferedReader bufferReader = new BufferedReader(new FileReader("res\\map.txt"));
            String line;
            int indexLine = 0;

            while ((line = bufferReader.readLine()) != null) {
                diagramMap[indexLine] = line.toCharArray();
                indexLine++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return diagramMap;
    }

    public void createMap(ItemManagement itemManagement, List<Entity> stillObjects) {
        createDiagramMap();

        for (int i = 0; i < fullWidth; i++) {
            for (int j = 0; j < fullHeight; j++) {
                Entity object;
                char currentDiagramObject = diagramMap[j][i];

                if (Wall.isWall(currentDiagramObject)) {
                    object = new Wall(i, j);
                } else if (Brick.isBrick(currentDiagramObject)) {
                    object = new Brick(i, j);
                } else if (Item.isItem(currentDiagramObject)) {
                    object = new Brick(i, j);
                    itemManagement.add(i, j, currentDiagramObject);
                } else {
                    object = new Grass(i, j);
                }

                stillObjects.add(object);
            }
        }
    }
}
