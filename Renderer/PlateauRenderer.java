package Renderer;

import Plateau.Plateau;

public class PlateauRenderer {
    public static void renderPlateauConsole() {
        int[][] plateauMatrix = Plateau.getPlateauMatrix();
        System.out.print("╔");
        for (int i = 0; i < Plateau.TAILLE; i++) {
            System.out.print("═══");
        }
        System.out.println("╗");
        for (int i = 0; i < Plateau.TAILLE; i++) {
            System.out.print("║");
            for (int j = 0; j < Plateau.TAILLE; j++) {
                switch (plateauMatrix[j][i]) {
                    case Plateau.VIDE -> System.out.print(" - ");
                    case Plateau.AGENT -> System.out.print(" A ");
                    case Plateau.DESTINATION -> System.out.print(" D ");
                    case Plateau.AGENT_DESTINATION -> System.out.print(" X ");
                }
            }
            System.out.println("║");
        }
        System.out.print("╚");
        for (int i = 0; i < Plateau.TAILLE; i++) {
            System.out.print("═══");
        }
        System.out.println("╝");
    }
}
