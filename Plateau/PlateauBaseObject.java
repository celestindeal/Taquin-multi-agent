package Plateau;

public class PlateauBaseObject {
    private Position position;
    private PlateauEnum type;

    PlateauBaseObject(Position position, PlateauEnum type) {
        Plateau.listeAgents.forEach(agent -> {
            if (agent.getPosition().equals(position)) {
                throw new IllegalArgumentException("Position already taken by another agent");
            }
        });
        this.position = position;
        this.type = type;
    }

    public Position getPosition() {
        return this.position;
    }

    public PlateauEnum getType() {
        return this.type;
    }

    public void setPosition(Position position) {
        Plateau.listeAgents.forEach(agent -> {
            if (agent.getPosition().equals(position)) {
                throw new IllegalArgumentException("Position already taken by another agent");
            }
        });
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
