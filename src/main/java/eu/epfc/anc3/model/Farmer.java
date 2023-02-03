package eu.epfc.anc3.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Farmer {

    private final StringProperty fermier = new SimpleStringProperty("");

    private final Position posFarmer ;

    //devrait aller chercher l'image du fermier.
    private final Image farmerImage = new Image(getClass().getResource("/ressources/farmer.png").toExternalForm());

    Farmer() {
        posFarmer = new Position(0,0);


       // farmerImageView.xProperty().bind(player.xProperty());
       // farmerImageView.yProperty().bind(player.yProperty());
    }

    public Position getPosFarmer(){return posFarmer;}

    public void setPosPlayer(int x, int y){
        posFarmer.setPosX(x);
        posFarmer.setPosY(y);
    }

//    scene.setOnKeyPressed(event -> {
//        switch (event.getCode()) {
//            case UP:
//                // Code pour déplacer le joueur vers le haut
//                break;
//            case DOWN:
//                // Code pour déplacer le joueur vers le bas
//                break;
//            case LEFT:
//                // Code pour déplacer le joueur vers la gauche
//                break;
//            case RIGHT:
//                // Code le joueur vers la droite
//                break;
//            default:
//                break;
//        }
//    });

    /**
     *      pour ajuster le skin du user ?
     * farmerImageView.setFitWidth(100);
     * farmerImageView.setFitHeight(100);
     * farmerImageView.setX(10);
     * farmerImageView.setY(10);
     */
}
