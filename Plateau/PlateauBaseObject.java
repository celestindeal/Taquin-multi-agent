package Plateau;

public class PlateauBaseObject {
    private Position position;
    private PlateauEnum type;

    PlateauBaseObject(Position position, PlateauEnum type) {
        this.position = position;
        this.type = type;
        Plateau.plateau[position.getX()][position.getY()] = this;
    }

    public Position getPosition() {
        return this.position;
    }

    public PlateauEnum getType() {
        return this.type;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setType(PlateauEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type.toString() + " " + this.position.toString();
    }
}
