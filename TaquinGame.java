import Plateau.*;

import java.util.ArrayList;

public class TaquinGame {
    private ArrayList<AgentTaquin> listAgent = new ArrayList<AgentTaquin>();

    public void generatePlateau() {
        Plateau.plateau = new PlateauBaseObject[Plateau.TAILLE][Plateau.TAILLE];

        // Generate an empty plateau
        for (int i = 0; i < Plateau.TAILLE; i++) {
            for (int j = 0; j < Plateau.TAILLE; j++) {
                new CaseVideTaquin(i, j);
            }
        }

        ArrayList<Position> listPosition = new ArrayList<Position>();
        while (listPosition.size() < Plateau.NB_AGENTS * 2) {
            int posx = (int) (Math.random() * (Plateau.TAILLE - 1));
            int posy = (int) (Math.random() * (Plateau.TAILLE - 1));
            Position newPosition = new Position(posx, posy);
            if (!listPosition.contains(newPosition)) {
                listPosition.add(newPosition);
            }
        }

        // Generate the positions of the agents
        for (int i = 0; i < listPosition.size(); i += 2) {
            AgentTaquin newAgent = new AgentTaquin(listPosition.get(i), listPosition.get(i + 1));
            if (!Plateau.listeAgents.contains(newAgent)) {
                Plateau.listeAgents.add(newAgent);
            }
            listAgent.add(newAgent);
        }

        System.out.println(listAgent);
        Plateau.listeAgents.forEach(agent -> System.out.println(agent.getDestination()));
    }

    public boolean validateSolution() {

        for (AgentTaquin agent : listAgent) {
            if (agent.getPosition().getX() != agent.getDestination().getPosition().getX() || agent.getPosition().getY() != agent.getDestination().getPosition().getY()) {
                return false;
            }
        }
        return true;
    }
}
