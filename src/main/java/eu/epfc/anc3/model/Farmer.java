package eu.epfc.anc3.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

public class Farmer {

    private final Position posFarmer;
    public static Grass plantedGrass;
    private final StringProperty fermier = new SimpleStringProperty("");
    public static final SimpleSetProperty<Grass> listOfPlantedGrass = new SimpleSetProperty<>(FXCollections.observableSet());

    public Farmer(){
        this.posFarmer = new Position(0,0);
    }

    public Position getPosFarmer() {
        return posFarmer;
    }
    public static Grass getPlantedGrass () {
            return plantedGrass;
    }
    @Override
    public String toString() {
        return "Farmer{" +
                "posFarmer=" + posFarmer +
                ", listOfPlantedGrass=" + listOfPlantedGrass +
                ", fermier=" + fermier +
                '}';
    }

    public void setPosFarmer(int x, int y){
        posFarmer.setPosX(x); posFarmer.setPosY(y);
    }
    public boolean hasPlantedGrass(){
        return !listOfPlantedGrass.isEmpty();
    }

    public void plantGrass(Position p ){
        listOfPlantedGrass.add(new Grass(p));
    }
    public void resetGrass(){
        listOfPlantedGrass.clear();
    }

    /*public void removeGrassAtPos(Position p ){
        if (hasPlantedGrass())
            listOfPlantedGrass.remove(listOfPlantedGrass.get());
    }*/
    public ReadOnlyIntegerProperty nbgrass(){return listOfPlantedGrass.sizeProperty();}

    public void removeGrassAtPos(Position p) {
        //K:retire l'herbe en prenant compte de la pos
        listOfPlantedGrass.removeIf(grass -> grass.getPos().equals(p));
    }

}
