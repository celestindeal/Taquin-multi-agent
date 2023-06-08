import Renderer.PlateauRenderer;

public class Main {
    public static void main(String[] args) {
        TaquinGame game = new TaquinGame();
        game.generatePlateau();
        PlateauRenderer.renderPlateauConsole();
    }
}
