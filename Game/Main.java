package Game;

import Plateau.Plateau;
import Renderer.PlateauRenderer;

public class Main {
    public static PlateauRenderer plateauRenderer = new PlateauRenderer();
    public static void main(String[] args) {
        TaquinGame game = new TaquinGame();

        Plateau.TAILLE =5;
        Plateau.NB_AGENTS= 3;

        game.generatePlateau();
        PlateauRenderer.renderPlateauConsole();

        plateauRenderer.buildPanelColors();
        System.out.println(Plateau.listeAgents);
        Plateau.listeAgents.forEach(agent -> {
            Thread thread = new Thread(agent);
            thread.start();
        });
    }
}
