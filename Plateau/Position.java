package Plateau;

import Plateau.Plateau;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        if (x > Plateau.TAILLE || y > Plateau.TAILLE || x < 0 || y < 0) {
            throw new IllegalArgumentException("Position out of bounds");
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Position)) {
            return false;
        }
        Position position = (Position) o;
        return this.x == position.x && this.y == position.y;
    }

}
