package Plateau;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Plateau {
    static public ArrayList<AgentTaquin> listeAgents = new ArrayList<>();
    public static int TAILLE = 5;
    public static int NB_AGENTS = 3;
    final public static int VIDE = 0;
    final public static int AGENT = 1;
    final public static int DESTINATION = 2;
    final public static int AGENT_DESTINATION = 3;
    final public static int AGENT_SLEEP_MOVE_TIME = 400;
    final public static int AGENT_SLEEP_TIME = 200;

    public static int[][][] getPlateauMatrix() {
        int[][][] matrix = new int[TAILLE][TAILLE][4];
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                matrix[i][j][0] = VIDE;
                matrix[i][j][2] = VIDE;
            }
        }
        for (AgentTaquin agent : listeAgents) {
            matrix[agent.getPosition().getX()][agent.getPosition().getY()][0] = AGENT;
            matrix[agent.getPosition().getX()][agent.getPosition().getY()][1] = Integer.parseInt(agent.setName());
            matrix[agent.getDestination().getPosition().getX()][agent.getDestination().getPosition().getY()][2] = DESTINATION;
            matrix[agent.getDestination().getPosition().getX()][agent.getDestination().getPosition().getY()][3] = Integer.parseInt(agent.setName());
        }
        return matrix;
    }

    public static boolean isBoardValid() {
        AtomicBoolean isValid = new AtomicBoolean(true);
        Plateau.listeAgents.forEach(agent -> {
            if (!agent.getPosition().equals(agent.getDestination().getPosition())) {
                isValid.set(false);
            }
        });
        return isValid.get();
    }

    public static AgentTaquin getAgentAtPosition(Position position) {
        for (AgentTaquin agent : listeAgents) {
            if (agent.getPosition().equals(position)) {
                return agent;
            }
        }
        return null;
    }
}

