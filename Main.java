import Renderer.PlateauRenderer;

public class Main {
    public static void main(String[] args) {
        PlateauRenderer plateauRenderer = new PlateauRenderer();

        TaquinGame game = new TaquinGame();
        game.generatePlateau();
        PlateauRenderer.renderPlateauConsole();

        plateauRenderer.swing1();
    }
}
