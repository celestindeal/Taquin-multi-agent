package Plateau;

public enum DirectionEnum {
    HAUT, BAS, GAUCHE, DROITE;

    public static DirectionEnum getOpposite(DirectionEnum directionEnum) {
        return switch (directionEnum) {
            case HAUT -> BAS;
            case BAS -> HAUT;
            case GAUCHE -> DROITE;
            case DROITE -> GAUCHE;
        };
    }
}
