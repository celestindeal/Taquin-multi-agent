package Communication;

import Plateau.AgentTaquin;
import Plateau.DirectionEnum;

public class MoveAgentOrder {
    private AgentTaquin asker;
    private DirectionEnum direction;
    public MoveAgentOrder(AgentTaquin asker, DirectionEnum direction) {
        this.asker = asker;
        this.direction = direction;
    }

    public AgentTaquin getAsker() {
        return this.asker;
    }

    public DirectionEnum getDirection() {
        return this.direction;
    }

    @Override
    public String toString() {
        return "MoveAgentOrder [asker=" + asker + ", direction=" + direction + "]";
    }
}
