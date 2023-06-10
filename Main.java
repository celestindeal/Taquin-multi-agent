import Plateau.Plateau;
import Renderer.PlateauRenderer;

public class Main {
    public static void main(String[] args) {
        TaquinGame game = new TaquinGame();
        game.generatePlateau();
        PlateauRenderer.renderPlateauConsole();
        System.out.println(Plateau.listeAgents);
        Plateau.listeAgents.forEach(agent -> {
            Thread thread = new Thread(agent);
            thread.start();
        });
    }
}
