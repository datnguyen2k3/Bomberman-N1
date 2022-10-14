package uet.oop.bomberman.map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MapParser {
    public static char[][] diagramMapParser(String filepath, int mapWidth, int mapHeight) {
        char[][] diagramMap = new char[mapWidth][mapHeight];

        try {
            BufferedReader bufferReader = new BufferedReader(new FileReader(filepath));
            String line;
            int indexLine = 0;

            while ((line = bufferReader.readLine()) != null) {
                diagramMap[indexLine] = line.toCharArray();
                indexLine++;
            }

            bufferReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return diagramMap;
    }
}
