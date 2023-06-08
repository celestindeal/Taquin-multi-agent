package Plateau;

public class DestinationTaquin extends PlateauBaseObject {
    private AgentTaquin agent;

    public DestinationTaquin(int position_X, int position_Y, AgentTaquin agent) {
        super(new Position(position_X, position_Y), PlateauEnum.DESTINATION);
        this.agent = agent;
    }

    public DestinationTaquin(Position position, AgentTaquin agent) {
        super(position, PlateauEnum.DESTINATION);
        this.agent = agent;
    }

    public AgentTaquin getAgent() {
        return this.agent;
    }
}
