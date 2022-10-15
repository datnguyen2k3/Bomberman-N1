//package uet.oop.bomberman.map;
//
//import uet.oop.bomberman.entities.Entity;
//import uet.oop.bomberman.entities.Item.Item;
//import uet.oop.bomberman.entities.Item.ItemManagement;
//import uet.oop.bomberman.entities.StillObject.Brick;
//import uet.oop.bomberman.entities.StillObject.Grass;
//import uet.oop.bomberman.entities.StillObject.Wall;
//
//import java.util.List;
//
//public class Map {
//    private int mapHeight;
//    private int mapWidth;
//    private static char[][] diagramMap;
//
//    public Map(int level) {
//        diagramMap = MapParser.diagramMapParser("res/Map/map" + level + ".txt");
//
//        this.mapWidth = MapParser.getMapWidth(diagramMap);
//        this.mapHeight = MapParser.getMapHeight(diagramMap);
//    }
//
//    public void load(ItemManagement itemManagement, List<Entity> stillObjects) {
//        for (int i = 0; i < mapWidth ; i++) {
//            for (int j = 0; j < mapHeight; j++) {
//                Entity object;
//                char currentDiagramObject = diagramMap[j][i];
//
//                if (Wall.isWall(currentDiagramObject)) {
//                    object = new Wall(i, j);
//                } else if (Brick.isBrick(currentDiagramObject)) {
//                    object = new Brick(i, j);
//                } else if (Item.isItem(currentDiagramObject)) {
//                    object = new Brick(i, j);
//                    itemManagement.add(i, j, currentDiagramObject);
//                } else {
//                    object = new Grass(i, j);
//                }
//
//                stillObjects.add(object);
//            }
//        }
//    }
//
//    public char[][] getDiagramMap() {
//        return diagramMap;
//    }
//}
