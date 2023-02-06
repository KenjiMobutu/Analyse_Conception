package eu.epfc.anc3.model;

import java.util.ArrayList;
import java.util.List;

public class Farmer {
    private final Position posFarmer;
    public static Grass plantedGrass;
    public final List<Grass> listOfPlantedGrass = new ArrayList<>();

    public Farmer(){
        posFarmer = new Position(0,0);
    }
    public List<Grass> getListOfPlantedGrass(){
        return listOfPlantedGrass;
    }
    public void setPosFarmer(int x, int y){
        posFarmer.setPosX(x);
        posFarmer.setPosY(y);
    }

    public Position getPosFarmer() {
        return posFarmer;
    }

    public static Grass getPlantedGrass() {
        return plantedGrass;
    }
    @Override
    public String toString(){
        System.out.println(posFarmer);
        return posFarmer.toString();
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

}
