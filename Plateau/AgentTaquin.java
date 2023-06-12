package Plateau;

import Communication.Letterbox;
import Communication.MoveAgentOrder;
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

    public boolean deplacer(DirectionEnum directionEnum) {
        Position newPosition = Position.enumToPosition(this.getPosition(), directionEnum);
        try {
            this.setPosition(newPosition);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public DestinationTaquin getDestination() {
        return this.destination;
    }

    private DirectionEnum getPossibleDirectionFromPosition() {
        int[][] matrix = Plateau.getPlateauMatrix();
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        if (x > 0 && matrix[x - 1][y] == Plateau.VIDE) {
            return DirectionEnum.HAUT;
        }
        if (x < Plateau.TAILLE - 1 && matrix[x + 1][y] == Plateau.VIDE) {
            return DirectionEnum.BAS;
        }
        if (y > 0 && matrix[x][y - 1] == Plateau.VIDE) {
            return DirectionEnum.GAUCHE;
        }
        if (y < Plateau.TAILLE - 1 && matrix[x][y + 1] == Plateau.VIDE) {
            return DirectionEnum.DROITE;
        }
        return null;
    }

    @Override
    public void run() {
        ArrayList<DirectionEnum> directions = TaquinSolver.getDirectionsToDestination(this);
        boolean hasReachedDestination = false;
        while (true) {
            while (directions.size() > 0) {
                if (!this.deplacer(directions.get(0))) {
                    AgentTaquin blockingAgent = Plateau.getAgentAtPosition(Position.enumToPosition(this.getPosition(), directions.get(0)));
                    if (blockingAgent != null) {
                        blockingAgent.letterbox.setOrder(new MoveAgentOrder(this));
                    }
                }
                System.out.println("Agent " + this.name + " moved to " + this.getPosition());
                directions.remove(0);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (this.getPosition().equals(this.destination.getPosition()) && !hasReachedDestination) {
                hasReachedDestination = true;
                System.out.println("Agent " + this.name + " reached destination");
                if (Plateau.isBoardValid()) {
                    System.out.println("Board is valid");
                    break;
                }
            }
            if (this.letterbox.hasOrder()) {
                DirectionEnum possibleDirection = this.getPossibleDirectionFromPosition();
                if (possibleDirection != null) {
                    this.deplacer(possibleDirection);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Agent " + this.name + " moved to " + this.getPosition());
                    hasReachedDestination = false;
                    directions = TaquinSolver.getDirectionsToDestination(this);
                }
            }
        }
    }
}
