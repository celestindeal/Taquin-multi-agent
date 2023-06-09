package Plateau;

public class AgentTaquin extends PlateauBaseObject {
    private DestinationTaquin destination;

    public AgentTaquin(int position_X, int position_Y, int objectif_X, int objectif_Y) {
        super(new Position(position_X, position_Y), PlateauEnum.AGENT);
        this.destination = new DestinationTaquin(objectif_X, objectif_Y, this);
    }

    public AgentTaquin(Position position, Position destination) {
        super(position, PlateauEnum.AGENT);
        this.destination = new DestinationTaquin(destination, this);
    }

    public void deplacer(DirectionEnum directionEnum) {
        Position newPosition = Position.enumToPosition(this.getPosition(), directionEnum);
        this.setPosition(newPosition);
    }

    public DestinationTaquin getDestination() {
        return this.destination;
    }
}
