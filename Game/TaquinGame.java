package Game;

import Plateau.*;

import java.util.ArrayList;

public class TaquinGame {

    public void generatePlateau() {
        // Generate the positions of the agents
        ArrayList<Position> agentList = getRandomPositions();
        ArrayList<Position> destinationList = getRandomPositions();

        // Interpolate agent positions to destination positions
        ArrayList<Position> finalList = new ArrayList<>();
        for (int i = 0; i < agentList.size(); i++) {
            finalList.add(agentList.get(i));
            finalList.add(destinationList.get(i));
        }

        // Declare a list of agents
        for (int i = 0; i < finalList.size(); i += 2) {
            AgentTaquin newAgent = new AgentTaquin(finalList.get(i), finalList.get(i + 1));
            if (!Plateau.listeAgents.contains(newAgent)) {
                Plateau.listeAgents.add(newAgent);
            }
        }
    }

    private ArrayList<Position> getRandomPositions() {
        ArrayList<Position> positionsList = new ArrayList<>();
        for (int i = 0; i < Plateau.NB_AGENTS; i++) {
            Position position = new Position((int) (Math.random() * Plateau.TAILLE), (int) (Math.random() * Plateau.TAILLE));
            if (!positionsList.contains(position)) {
                positionsList.add(position);
            } else {
                i--;
            }
        }
        return positionsList;
    }
}
