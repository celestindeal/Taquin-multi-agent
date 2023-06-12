package Game;

import Plateau.*;

import java.util.ArrayList;

public class TaquinGame {

    public void generatePlateau() {
        // Generate a list of different random positions
        ArrayList<Position> listPosition = new ArrayList<Position>();
        while (listPosition.size() < Plateau.NB_AGENTS * 2) {
            int posx = (int) (Math.random() * (Plateau.TAILLE - 1));
            int posy = (int) (Math.random() * (Plateau.TAILLE - 1));
            Position newPosition = new Position(posx, posy);
            if (!listPosition.contains(newPosition)) {
                listPosition.add(newPosition);
            }
        }

//        Position p1 = new Position(0, 1);
//        Position p2 = new Position(4, 1);
//        Position p3 = new Position(2, 3);
//        Position p4 = new Position(4, 3);
//        listPosition.add(p1);
//        listPosition.add(p2);
//        listPosition.add(p3);
//        listPosition.add(p4);


        // Declare a list of agents
        for (int i = 0; i < listPosition.size(); i += 2) {
            AgentTaquin newAgent = new AgentTaquin(listPosition.get(i), listPosition.get(i + 1));
            if (!Plateau.listeAgents.contains(newAgent)) {
                Plateau.listeAgents.add(newAgent);
            }
        }
    }
}
