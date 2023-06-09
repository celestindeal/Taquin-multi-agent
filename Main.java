import Plateau.Plateau;
import Plateau.DirectionEnum;
import Renderer.PlateauRenderer;
import Solver.TaquinSolver;

public class Main {
    public static void main(String[] args) {
        TaquinGame game = new TaquinGame();
        game.generatePlateau();
        int[][] plateauMatrix = Plateau.getPlateauMatrix();
        int[][] costs = TaquinSolver.getCosts(plateauMatrix, Plateau.listeAgents.get(0));
        for (int i = 0; i < Plateau.TAILLE; i++) {
            for (int j = 0; j < Plateau.TAILLE; j++) {
                System.out.print(costs[j][i] + " ");
            }
            System.out.println();
        }

    }
}
