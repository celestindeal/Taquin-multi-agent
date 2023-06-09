package Solver;

import Plateau.AgentTaquin;
import Plateau.Plateau;
import Plateau.PlateauBaseObject;
import Plateau.PlateauEnum;

public class TaquinSolver {
    public static int[][] getCosts(int[][] plateauMatrix, AgentTaquin agent) {
        int x = agent.getPosition().getX();
        int y = agent.getPosition().getY();
        int xDestination = agent.getDestination().getPosition().getX();
        int yDestination = agent.getDestination().getPosition().getY();
        System.out.println("x: " + x + " y: " + y + " xDestination: " + xDestination + " yDestination: " + yDestination);
        int[][] costs = new int[plateauMatrix.length][plateauMatrix.length];
        for (int i = 0; i < plateauMatrix.length; i++) {
            for (int j = 0; j < plateauMatrix[i].length; j++) {
                // if the cell is the current agent, we set the cost to 0
                if (i == x && j == y) {
                    costs[i][j] = 0;
                    continue;
                }
                costs[i][j] = Math.abs(x - i) + Math.abs(y - j);
            }
        }
        return costs;
    }
}
