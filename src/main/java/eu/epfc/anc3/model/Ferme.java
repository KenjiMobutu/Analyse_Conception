package eu.epfc.anc3.model;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableSet;

import java.util.List;
import java.util.Set;

class Ferme {

    private Terrain terrain = new Terrain();
    private final ObjectProperty<FermeStatus> fermeStatus = new SimpleObjectProperty<>(FermeStatus.START);
    public Ferme(){}

    void start(){
        terrain = new Terrain();
        fermeStatus.set(FermeStatus.STARTED);
    }
    void newGame() {
        terrain.resetTerrain();
        fermeStatus.set(FermeStatus.STARTED);
    }
    void stop(){
        fermeStatus.setValue(FermeStatus.STOP);
    }
    void plantMode(){
        fermeStatus.setValue(FermeStatus.PLANT_GRASS);
    }
    public void plantCabbageMode() {
        fermeStatus.setValue(FermeStatus.PLANT_CABBAGE);
    }

    public void plantCarrotMode() {
        fermeStatus.setValue(FermeStatus.PLANT_CARROT);
    }

    public void fertilizerMode() {
        fermeStatus.setValue(FermeStatus.FERTILIZER);
    }

    public void recoltMode() {
        fermeStatus.setValue(FermeStatus.RECOLT);
    }
    void unplantMode(){
        fermeStatus.set(FermeStatus.DEPLANT_GRASS);
    }

    boolean cellContainsElementType(ParcelleValue pv, int line, int col){
        return terrain.containsElementType(pv, line, col);
    }

    //ajout un element a une cellule
    void addElementToCell(Element p, int line, int col){
        terrain.addElementToCell(p, line,col);
    }

    //supprime un element
    void removeElementFromCell(ParcelleValue p, int line, int col){terrain.removeElement(p,line,col);}

    ObservableSet<Element> getAllElem(int line, int col){ return terrain.getElem(line, col);}

    ObservableSet<ParcelleValue> getAllElemType(int line, int col){return terrain.getElemType(line,col);}
    //retourne le status du jeu
    ReadOnlyObjectProperty<FermeStatus> fermeStatusProperty(){return fermeStatus;}

    void spawnFarmer(Farmer farmer, int line, int col){
        terrain.addElementToCell(farmer, line, col);
    }
    //permet de déplacer le joueur dans le grid

    Terrain getTerrain(){
        return terrain;
    }


}
