package uet.oop.bomberman.entities.Algorithm;

import javafx.util.Pair;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Character.Character;
import uet.oop.bomberman.entities.StillObject.Brick;
import uet.oop.bomberman.entities.StillObject.Wall;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinPath {
    public static int[] moveX = {0, 0, 1, -1};
    public static int[] moveY = {1, -1, 0, 0};

    public static boolean isCanMove(int xUnit, int yUnit, char[][] map, Character character) {
        switch (map[yUnit][xUnit]) {
            case Wall.diagramWall:
                return false;
            case Brick.diagramBrick:
                return character.getPassBrick();
            case Bomb.bombDiagram:
                return character.getPassBomb();
        }

        return true;
    }

    public static int findMinPath(int startX, int startY, int endX, int endY, char[][] map, Character character) {
        int m = map.length;
        int n = map[0].length;

        if (!isCanMove(startX, startY, BombermanGame.diagramMap, character)) {
            return -1;
        }

        int[][] d = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(startX, startY));
        visited[startY][startX] = true;

        while (!queue.isEmpty()) {
            int currX = queue.peek().getKey();
            int currY = queue.peek().getValue();
            queue.remove();

            if (currX == endX && currY == endY) {
                if (d[currY][currX] > 15)
                    return -1;
                return d[currY][currX];
            }

            for (int i = 0; i < 4; i++) {
                int nextX = currX + moveX[i];
                int nextY = currY + moveY[i];

                if (!isCanMove(nextX, nextY, BombermanGame.diagramMap, character)) {
                    continue;
                }

                if (visited[nextY][nextX]) {
                    continue;
                }

                d[nextY][nextX] = d[currY][currX] + 1;
                visited[nextY][nextX] = true;
                queue.add(new Pair<>(nextX, nextY));
            }

        }

        return -1;
    }

    public static int findDirection(Character character, int endX, int endY) {
        int startX = character.get_xUnit();
        int startY = character.get_yUnit();
        int direction = CanGo.CANT_MOVE;
        int currMinPath = 10000;

        if (CanGo.isCanGoUp(character, BombermanGame.diagramMap)) {
            int minPath = findMinPath(startX, startY - 1, endX, endY, BombermanGame.diagramMap, character);
            //System.out.println(CanGo.UP + " " + minPath);
            if (minPath != -1 && minPath < currMinPath) {
                currMinPath = minPath;
                direction = CanGo.UP;
            }
        }

        if (CanGo.isCanGoDown(character, BombermanGame.diagramMap)) {
            int minPath = findMinPath(startX, startY + 1, endX, endY, BombermanGame.diagramMap, character);
            //System.out.println(CanGo.DOWN + " " + minPath);

            if (minPath != -1 && minPath < currMinPath) {
                currMinPath = minPath;
                direction = CanGo.DOWN;
            }
        }

        if (CanGo.isCanGoLeft(character, BombermanGame.diagramMap)) {
            int minPath = findMinPath(startX - 1, startY, endX, endY, BombermanGame.diagramMap, character);
            //System.out.println(CanGo.LEFT + " " + minPath);

            if (minPath != -1 && minPath < currMinPath) {
                currMinPath = minPath;
                direction = CanGo.LEFT;
            }
        }

        if (CanGo.isCanGoRight(character, BombermanGame.diagramMap)) {
            int minPath = findMinPath(startX + 1, startY, endX, endY, BombermanGame.diagramMap, character);
            //System.out.println(CanGo.RIGHT + " " + minPath);

            if (minPath != -1 && minPath < currMinPath) {
                currMinPath = minPath;
                direction = CanGo.RIGHT;
            }
        }

        return direction;
    }
}
