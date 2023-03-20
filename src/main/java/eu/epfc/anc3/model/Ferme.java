package eu.epfc.anc3.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;

import java.util.Set;

public class Ferme {
    private Terrain terrain = new Terrain();
    private final ObjectProperty<FermeStatus> fermeStatus = new SimpleObjectProperty<>(FermeStatus.START);
    public static final SimpleListProperty<Grass> listOfPlantedGrass = new SimpleListProperty<>(FXCollections.observableArrayList());
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
    public ReadOnlyIntegerProperty nbGrassPlant(){ return listOfPlantedGrass.sizeProperty();}

    public Terrain getTerrain(){
        return terrain;
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

    public void removeGrassAtPos(Position p ){
        if (hasPlantedGrass())
            listOfPlantedGrass.remove(listOfPlantedGrass.getSize()-1);
    }
    public ReadOnlyIntegerProperty nbgrass(){return listOfPlantedGrass.sizeProperty();}

}
