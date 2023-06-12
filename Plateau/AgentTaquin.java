package Plateau;

import Communication.Letterbox;
import Communication.MoveAgentOrder;
import Game.Main;
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

    private DirectionEnum getPossibleDirectionFromPosition(DirectionEnum directionFromTaquinAsker) {
        ArrayList<DirectionEnum> possibleDirections = new ArrayList<DirectionEnum>();
        // move left or right
        if (this.getPosition().getX() > 0 && Plateau.getAgentAtPosition(new Position(this.getPosition().getX() - 1, this.getPosition().getY())) == null) {
            possibleDirections.add(DirectionEnum.GAUCHE);
        } else if (this.getPosition().getX() < Plateau.TAILLE - 1 && Plateau.getAgentAtPosition(new Position(this.getPosition().getX() + 1, this.getPosition().getY())) == null) {
            possibleDirections.add(DirectionEnum.DROITE);
        }
        // move up or down
        if (this.getPosition().getY() > 0 && Plateau.getAgentAtPosition(new Position(this.getPosition().getX(), this.getPosition().getY() - 1)) == null) {
            possibleDirections.add(DirectionEnum.HAUT);
        } else if (this.getPosition().getY() < Plateau.TAILLE - 1 && Plateau.getAgentAtPosition(new Position(this.getPosition().getX(), this.getPosition().getY() + 1)) == null) {
            possibleDirections.add(DirectionEnum.BAS);
        }
        if (possibleDirections.size() == 0) {
            return null;
        }
        return possibleDirections.get((int) (Math.random() * possibleDirections.size()));
    }

    @Override
    public void run() {
        boolean isAgentOnDestination = false;
        while (true) {
            // Check if the agent needs to move
            if (this.letterbox.hasOrder()) {
                DirectionEnum direction = this.getPossibleDirectionFromPosition(this.letterbox.getOrder().getDirection());
                if (direction != null) {
                    this.deplacer(direction);
                    System.out.println("Agent " + this.name + " has moved to " + this.getPosition());
                    isAgentOnDestination = false;
                    try {
                        Thread.sleep(Plateau.AGENT_SLEEP_MOVE_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Agent " + this.name + " has moved to " + this.getPosition());
                } else {
                    System.out.println("Agent " + this.name + " can't move");
                }
                this.letterbox.setOrder(null);
            }

            // Check if the agent is on the destination
            if (this.getPosition().equals(this.destination.getPosition()) && !isAgentOnDestination) {
                isAgentOnDestination = true;
                System.out.println("Agent " + this.name + " is on destination");
                if (Plateau.isBoardValid()) {
                    System.out.println("Board is valid");
                    Main.plateauRenderer.updatePanelColors();
                }
            }

            // Move the agent to the destination
            while (!this.getPosition().equals(this.getDestination().getPosition())) {
                System.out.println(this.letterbox.getOrder());
                ArrayList<DirectionEnum> direction = TaquinSolver.getDirectionsToDestination(this);
                System.out.println(direction);
                for (DirectionEnum directionEnum : direction) {
                    if (this.deplacer(directionEnum)) {
                        System.out.println("Agent " + this.name + " has moved to " + this.getPosition());
                        try {
                            Thread.sleep(Plateau.AGENT_SLEEP_TIME);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        AgentTaquin agent = Plateau.getAgentAtPosition(Position.enumToPosition(this.getPosition(), directionEnum));
                        if (agent != null) {
                            agent.letterbox.setOrder(new MoveAgentOrder(this, directionEnum));
                        }
                        break;
                    }
                }
                break;
            }
        }
    }
}
