package Renderer;

import Plateau.Plateau;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

import static Plateau.PlateauEnum.VIDE;

public class PlateauRenderer extends JFrame {
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

    public void  swing1() {

        int Taille_Case = 100;
        int tailleTableau = Plateau.TAILLE;

        setLayout(new GridLayout(tailleTableau, tailleTableau)); // Utilisation de GridLayout

        for (int i = 0; i < tailleTableau; i++) {
            for (int j = 0; j < tailleTableau; j++) {
                Color couleur;
                Color couleurBordu;
                switch (Plateau.plateau[i][j].getType()) {
                    case VIDE:
                        couleur = Color.WHITE;
                        couleurBordu = Color.WHITE;
                        break;
                    case AGENT:
                        couleur = Color.RED;
                        couleurBordu = Color.WHITE;
                        break;
                    case DESTINATION:
                        couleur = Color.WHITE;
                        couleurBordu = Color.GREEN;
                        break;
                    default:
                        couleur = Color.BLACK;
                        couleurBordu = Color.BLACK;
                }

                RoundColorPanel roundColorPanel = new RoundColorPanel(couleur, couleurBordu); // Création du panel rond de couleur
                //roundColorPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE)); // Ajout d'une bordure de la même couleur
                add(roundColorPanel); // Ajout du panel au conteneur
            }
        }


        setSize(Taille_Case * tailleTableau, Taille_Case * tailleTableau);
        setVisible(true);
    }
}


