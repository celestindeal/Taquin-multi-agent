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
        int[][][] plateauMatrix = Plateau.getPlateauMatrix();
        System.out.print("╔");
        for (int i = 0; i < Plateau.TAILLE; i++) {
            System.out.print("═══");
        }
        System.out.println("╗");
        for (int i = 0; i < Plateau.TAILLE; i++) {
            System.out.print("║");
            for (int j = 0; j < Plateau.TAILLE; j++) {
                switch (plateauMatrix[j][i][0]) {
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

//    Création de la page d'affichage
    public void buildPanelColors() {

        int Taille_Case = 100;
        int tailleTableau = Plateau.TAILLE;
        int[][][] plateauMatrix = Plateau.getPlateauMatrix();
// demander la page et mettre une grille à l'interieur
        setLayout(new GridLayout(tailleTableau, tailleTableau)); // Utilisation de GridLayout

        for (int j = 0; j < tailleTableau; j++) {
            for (int i = 0; i < tailleTableau; i++) {

                Color couleur = Color.BLACK;
                Color couleurBordu = Color.BLACK;

                if(plateauMatrix[i][j][0] == Plateau.VIDE){
                    couleur = Color.WHITE;
                } else if (plateauMatrix[i][j][0] == Plateau.AGENT) {
                    couleur = getColorFromHashCode(plateauMatrix[i][j][1]);
                }

                if(plateauMatrix[i][j][2] == Plateau.VIDE){
                    couleurBordu = Color.WHITE;
                } else if (plateauMatrix[i][j][2] == Plateau.DESTINATION) {
                    couleurBordu = getColorFromHashCode(plateauMatrix[i][j][3]);
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
        int[][][] plateauMatrix = Plateau.getPlateauMatrix();

        for (int j = 0; j < tailleTableau; j++) {
            for (int i = 0; i < tailleTableau; i++) {
                int index = i + j * tailleTableau;
                RoundColorPanel roundColorPanel = roundColorPanels.get(index);

                // Mettez à jour la couleur du panneau en fonction de la nouvelle matrice du plateau
                Color couleur = Color.BLACK;
                Color couleurBordu = Color.BLACK;


                if(plateauMatrix[i][j][0] == Plateau.VIDE){
                    couleur = Color.WHITE;
                } else if (plateauMatrix[i][j][0] == Plateau.AGENT) {
                    couleur = getColorFromHashCode(plateauMatrix[i][j][1]);
                }

                if(plateauMatrix[i][j][2] == Plateau.VIDE){
                    couleurBordu = Color.WHITE;
                } else if (plateauMatrix[i][j][2] == Plateau.DESTINATION) {
                    couleurBordu = getColorFromHashCode(plateauMatrix[i][j][3]);
                }

                roundColorPanel.setColor(couleur); // Mettez à jour la couleur du panneau
                roundColorPanel.setBorderColor(couleurBordu); // Mettez à jour la couleur de la bordure
                roundColorPanel.repaint(); // Redessinez le panneau avec les nouvelles couleurs
            }
        }
    }

    public static Color getColorFromHashCode(int hashCode) {
        // Convertissez le hashcode en une valeur de couleur en utilisant les opérations bit à bit
        int red = (hashCode & 0xFF0000) >> 16;
        int green = (hashCode & 0x00FF00) >> 8;
        int blue = hashCode & 0x0000FF;

        // Créez une nouvelle couleur en utilisant les valeurs de rouge, vert et bleu
        Color color = new Color(red, green, blue);

        return color;
    }
}


