package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Ferme {


    private Terrain terrain = new Terrain();
    private Grass plantedGrass;
    private final ObjectProperty<FermeStatus> fermeStatus = new SimpleObjectProperty<>(FermeStatus.START);

    void start(){
        newGame();
    }

    private FermeStatus status(){return this.fermeStatus.get();}

    ParcelleValue play(int x, int y){
        switch (status()){
            case START :
            case PLANT_GRASS: return ParcelleValue.GRASS;
            case DEPLANT_GRASS: return ParcelleValue.EMPTY;
        }
        /**
         * ajout de la fonction d'action du joueur (Terrain.play)
         */
        return ParcelleValue.EMPTY;
    }
    ReadOnlyObjectProperty<ParcelleValue> valueProperty(int line, int col) {
        return terrain.valueProperty(line, col);
    }
    ReadOnlyObjectProperty<FermeStatus> fermeStatusProperty() {
        return fermeStatus;
    }


    public void newGame() {
        fermeStatus.set(FermeStatus.START);
    }

    public void farmerInFarm(Farmer farmer) {
        terrain.setValueOnField(farmer.getPosFarmer().getPosX(),farmer.getPosFarmer().getPosY(),ParcelleValue.FARMER);
    }
    public Terrain getTerrain(){
        return terrain;
    }
}
