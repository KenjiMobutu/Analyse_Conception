package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Set;

public class Ferme {
    private Terrain terrain = new Terrain();
    private final Farmer farmer= new Farmer() ;
    private Position posFarmer = new Position() ;


    private final ObjectProperty<FermeStatus> fermeStatus = new SimpleObjectProperty<>(FermeStatus.START);
    private final ObjectProperty<ParcelleValue> parcelleStatus = new SimpleObjectProperty<>(ParcelleValue.EMPTY);

    public Ferme(){}

    void start(){
        terrain = new Terrain();
        fermeStatus.set(FermeStatus.STARTED);
    }
    public void newGame() {
        terrain.resetTerrain();
        fermeStatus.set(FermeStatus.STARTED);
    }

    void stop(){
        fermeStatus.setValue(FermeStatus.STOP);
    }
    void plantMode(){
        fermeStatus.setValue(FermeStatus.PLANT_GRASS);
    }
    void unplantMode(){
        fermeStatus.set(FermeStatus.DEPLANT_GRASS);
    }

    public Terrain getTerrain() {
        return terrain;
    }

    private FermeStatus status(){return this.fermeStatus.get();}
    private ParcelleValue parcelleStatus(){return this.parcelleStatus.get();}

    ParcelleValue play (int line, int col){
        farmer.setPosFarmer(line,col);
        posFarmer.setPosX(line);
        posFarmer.setPosY(col);
        this.farmerInFarm(farmer);

        switch (status()){
            case STOP:
                break;
            case STARTED:

            case PLANT_GRASS:
                return ParcelleValue.DIRT;
            case DEPLANT_GRASS:
                return ParcelleValue.GRASS;
        }

        return ParcelleValue.EMPTY;
    }

    private ParcelleValue getCurrentFarmerValue() {
        if (status() == FermeStatus.STARTED) {
            return farmer.hasPlantedGrass() ? ParcelleValue.GRASS : ParcelleValue.FARMER;
        } else {
            return ParcelleValue.EMPTY;
        }
    }

    ReadOnlyObjectProperty<ParcelleValue> valueProperty(int line, int col){
        return terrain.valueProperty(line,col);
    }
    Set<Element> valuePropertyFromSet(int line, int col){
        return terrain.getAllElementsInCellFromSet(line,col);
    }
    void addValuePropertyToCell(ParcelleValue p, int line, int col){
        terrain.addElementToCell(p, line,col);
    }
    void removeValueFromPropertyCell(int l, int c, Element e){
        terrain.removeElemFromCell(l,c,e);
    }
    ReadOnlyObjectProperty<FermeStatus> fermeStatusProperty() {
        return fermeStatus;
    }


    public void farmerInFarm(Farmer farmer) {
        terrain.setValueOnFarm(farmer.getPosFarmer().getPosX(),farmer.getPosFarmer().getPosY(),ParcelleValue.FARMER);

    }
    public ReadOnlyIntegerProperty nbGrassPlant(){return farmer.nbgrass();}

}
