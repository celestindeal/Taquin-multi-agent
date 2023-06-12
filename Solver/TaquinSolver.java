package Solver;

import Plateau.AgentTaquin;
import Plateau.Plateau;
import Plateau.PlateauBaseObject;
import Plateau.PlateauEnum;
import Plateau.DirectionEnum;

import java.util.ArrayList;
import java.util.List;

public class TaquinSolver {
    public static ArrayList<DirectionEnum> getDirectionsToDestination(AgentTaquin agent, boolean random) {
        int x = agent.getPosition().getX();
        int y = agent.getPosition().getY();
        int xDestination = agent.getDestination().getPosition().getX();
        int yDestination = agent.getDestination().getPosition().getY();
        ArrayList<DirectionEnum> directions = new ArrayList<>();
        while (x != xDestination || y != yDestination) {
            if (x < xDestination) {
                directions.add(DirectionEnum.DROITE);
                x++;
                continue;
            }
            if (y < yDestination) {
                directions.add(DirectionEnum.BAS);
                y++;
                continue;
            }
            if (x > xDestination) {
                directions.add(DirectionEnum.GAUCHE);
                x--;
                continue;
            }
            directions.add(DirectionEnum.HAUT);
            y--;
        }
        // add a random chance to invert the directions
        if (random) {
            if (Math.random() < 0.5) {
                ArrayList<DirectionEnum> newDirections = new ArrayList<>();
                for (int i = directions.size() - 1; i >= 0; i--) {
                    newDirections.add(directions.get(i));
                }
                directions = newDirections;
            }
        }
        return directions;
    }
}
