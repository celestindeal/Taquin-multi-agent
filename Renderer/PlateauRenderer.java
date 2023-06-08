package Renderer;

import Plateau.Plateau;

public class PlateauRenderer {
    public static void renderPlateauConsole() {
        for (int i = 0; i < Plateau.TAILLE; i++) {
            for (int j = 0; j < Plateau.TAILLE; j++) {
                switch (Plateau.plateau[i][j].getType()) {
                    case VIDE:
                        System.out.print("_");
                        break;
                    case AGENT:
                        System.out.print("A");
                        break;
                    case DESTINATION:
                        System.out.print("D");
                        break;
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
