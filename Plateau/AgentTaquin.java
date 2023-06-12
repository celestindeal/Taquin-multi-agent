package Plateau;

import Communication.Letterbox;
import Communication.MoveAgentOrder;
import Game.Main;
import Renderer.PlateauRenderer;
import Solver.TaquinSolver;

import java.util.ArrayList;

public class AgentTaquin extends PlateauBaseObject implements Runnable {
    private DestinationTaquin destination;
    private String name;
    public Letterbox letterbox = new Letterbox();

    public AgentTaquin(int position_X, int position_Y, int objectif_X, int objectif_Y) {
        super(new Position(position_X, position_Y), PlateauEnum.AGENT);
        this.destination = new DestinationTaquin(objectif_X, objectif_Y, this);
        this.name = hashCode() + "";
    }

    public AgentTaquin(Position position, Position destination) {
        super(position, PlateauEnum.AGENT);
        this.destination = new DestinationTaquin(destination, this);
        this.name = hashCode() + "";
    }

    public String setName(){
        return name;
    }

    public boolean deplacer(DirectionEnum directionEnum) {
        Position newPosition = Position.enumToPosition(this.getPosition(), directionEnum);
        try {
            this.setPosition(newPosition);
        } catch (IllegalArgumentException e) {
            return false;
        }
        Main.plateauRenderer.updatePanelColors();
        return true;
    }

    public DestinationTaquin getDestination() {
        return this.destination;
    }

    private DirectionEnum getPossibleDirectionFromPosition() {
        int[][][] matrix = Plateau.getPlateauMatrix();
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        if (x > 0 && matrix[x - 1][y][0] == Plateau.VIDE) {
            return DirectionEnum.HAUT;
        }
        if (x < Plateau.TAILLE - 1 && matrix[x + 1][y][0] == Plateau.VIDE) {
            return DirectionEnum.BAS;
        }
        if (y > 0 && matrix[x][y - 1][0] == Plateau.VIDE) {
            return DirectionEnum.GAUCHE;
        }
        if (y < Plateau.TAILLE - 1 && matrix[x][y + 1][0] == Plateau.VIDE) {
            return DirectionEnum.DROITE;
        }
        return null;
    }

    @Override
    public void run() {
        boolean isAgentOnDestination = false;
        while (true) {
            // Check if the agent needs to move
            if (this.letterbox.hasOrder()) {
                DirectionEnum direction = this.getPossibleDirectionFromPosition();
                if (direction != null) {
                    this.deplacer(direction);
                    System.out.println("Agent " + this.name + " has moved to " + direction);
                    isAgentOnDestination = false;
                    try {
                        Thread.sleep(Plateau.AGENT_SLEEP_MOVE_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.deplacer(DirectionEnum.getOpposite(direction));
                    System.out.println("Agent " + this.name + " has moved to " + DirectionEnum.getOpposite(direction));
                } else {
                    System.out.println("Agent " + this.name + " can't move");
                }
            }

            // Check if the agent is on the destination
            if (this.getPosition().equals(this.destination.getPosition()) && !isAgentOnDestination) {
                isAgentOnDestination = true;
                System.out.println("Agent " + this.name + " is on destination");
                if (Plateau.isBoardValid()) {
                    System.out.println("Board is valid");
                    break;
                }

            }

            // Move the agent to the destination
            while (!this.getPosition().equals(this.getDestination().getPosition())) {
                ArrayList<DirectionEnum> direction = TaquinSolver.getDirectionsToDestination(this);
                for (DirectionEnum directionEnum : direction) {
                    if (this.deplacer(directionEnum)) {
                        System.out.println("Agent " + this.name + " has moved to " + directionEnum);
                        try {
                            Thread.sleep(Plateau.AGENT_SLEEP_TIME);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        AgentTaquin agent = Plateau.getAgentAtPosition(Position.enumToPosition(this.getPosition(), directionEnum));
                        if (agent != null) {
                            agent.letterbox.setOrder(new MoveAgentOrder(this));
                        }
                        break;
                    }
                }
            }
        }
        Plateau.listeAgents.remove(this);
    }
}
