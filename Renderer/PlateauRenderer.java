package Renderer;

import Plateau.Plateau;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import static Plateau.PlateauEnum.VIDE;

public class PlateauRenderer extends JFrame {
    private static  ArrayList<RoundColorPanel> roundColorPanels = new ArrayList<>();
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

    public void swing1() {

        int Taille_Case = 100;
        int tailleTableau = Plateau.TAILLE;
        int[][] plateauMatrix = Plateau.getPlateauMatrix();

        setLayout(new GridLayout(tailleTableau, tailleTableau)); // Utilisation de GridLayout

        for (int j = 0; j < tailleTableau; j++) {
            for (int i = 0; i < tailleTableau; i++) {
                // Code existant pour déterminer la couleur du panneau
                Color couleur;
                Color couleurBordu;
                switch (plateauMatrix[i][j]) {
                    case Plateau.VIDE:
                        couleur = Color.WHITE;
                        couleurBordu = Color.WHITE;
                        break;
                    case Plateau.AGENT:
                        couleur = Color.RED;
                        couleurBordu = Color.WHITE;
                        break;
                    case Plateau.DESTINATION:
                        couleur = Color.WHITE;
                        couleurBordu = Color.GREEN;
                        break;
                    default:
                        couleur = Color.BLACK;
                        couleurBordu = Color.BLACK;
                }

                RoundColorPanel roundColorPanel = new RoundColorPanel(couleur, couleurBordu);
                roundColorPanels.add(roundColorPanel); // Ajoutez le panneau à la liste

                add(roundColorPanel); // Ajout du panneau au conteneur
            }
        }

        setSize(Taille_Case * tailleTableau, Taille_Case * tailleTableau);
        setVisible(true);

    }

    public void updatePanelColors() {
        int tailleTableau = Plateau.TAILLE;
        int[][] plateauMatrix = Plateau.getPlateauMatrix();

        for (int j = 0; j < tailleTableau; j++) {
            for (int i = 0; i < tailleTableau; i++) {
                int index = i + j * tailleTableau;
                RoundColorPanel roundColorPanel = roundColorPanels.get(index);

                // Mettez à jour la couleur du panneau en fonction de la nouvelle matrice du plateau
                Color couleur;
                Color couleurBordu;
                switch (plateauMatrix[i][j]) {
                    case Plateau.VIDE:
                        couleur = Color.WHITE;
                        couleurBordu = Color.WHITE;
                        break;
                    case Plateau.AGENT:
                        couleur = Color.RED;
                        couleurBordu = Color.WHITE;
                        break;
                    case Plateau.DESTINATION:
                        couleur = Color.WHITE;
                        couleurBordu = Color.GREEN;
                        break;
                    case Plateau.AGENT_DESTINATION:
                        couleur = Color.RED;
                        couleurBordu = Color.GREEN;
                        break;
                    default:
                        couleur = Color.BLACK;
                        couleurBordu = Color.BLACK;
                }

                roundColorPanel.setColor(couleur); // Mettez à jour la couleur du panneau
                roundColorPanel.setBorderColor(couleurBordu); // Mettez à jour la couleur de la bordure
                roundColorPanel.repaint(); // Redessinez le panneau avec les nouvelles couleurs
            }
        }
    }
}


