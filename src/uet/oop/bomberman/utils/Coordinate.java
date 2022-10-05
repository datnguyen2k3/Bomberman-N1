package uet.oop.bomberman.utils;

public class Coordinate {
    private int X;
    private int Y;

    public Coordinate(int x, int y) {
        X = x;
        Y = y;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Coordinate c = (Coordinate) obj;
        return this.getX() == c.getX() && this.getY() == c.getY();
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }
}
