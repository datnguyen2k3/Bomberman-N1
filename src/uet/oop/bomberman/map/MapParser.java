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
import java.util.ArrayList;
import java.util.List;

public class MapParser {
    public static int getMapHeight(char[][] diagramMap) {
        return diagramMap.length;
    }

    public static int getMapWidth(char[][] diagramMap) {
        return diagramMap[0].length;
    }

    public static char[][] diagramMapParser(int level) {
        ArrayList<ArrayList<Character>> listDiagramMap = new ArrayList<>();

        try {
            BufferedReader bufferReader = new BufferedReader(new FileReader("res/Map/map" + level + ".txt"));
            String line;

            while ((line = bufferReader.readLine()) != null) {
                ArrayList<Character> l = new ArrayList<>();
                for (Character x: line.toCharArray()) {
                    l.add(x);
                }
                listDiagramMap.add(l);

            }

            bufferReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (listDiagramMap.get(0) == null) {
            System.out.println("Error when reading map");
            return null;
        }

        char[][] diagramMap = new char[listDiagramMap.size()][listDiagramMap.get(0).size()];

        for (int i = 0; i < listDiagramMap.size() - 1; i++) {
            for (int j = 0; j < listDiagramMap.get(0).size() - 1; j++) {
                diagramMap[i][j] = listDiagramMap.get(i).get(j);
            }
        }

        return diagramMap;
    }

    public static void load(char[][] diagramMap, ItemManagement itemManagement, List<Entity> stillObjects) {
        for (int i = 0; i < getMapWidth(diagramMap) ; i++) {
            for (int j = 0; j < getMapHeight(diagramMap); j++) {
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
