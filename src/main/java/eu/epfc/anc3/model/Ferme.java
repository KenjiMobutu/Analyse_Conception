package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Ferme {

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
    void unplantMode(){
        fermeStatus.set(FermeStatus.DEPLANT_GRASS);
    }


    //return les valeurs d'une cellule
    ReadOnlyObjectProperty<ParcelleValue> valueProperty(int line, int col){
        return terrain.valueProperty(line,col);
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

    //retourne le status du jeu
    ReadOnlyObjectProperty<FermeStatus> fermeStatusProperty(){return fermeStatus;}

    //permet de déplacer le joueur dans le grid
    void setFarmerInFarm(Farmer farmer){
        terrain.setValueOnFarm(farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY(),ParcelleValue.FARMER);
    }
    Terrain getTerrain(){
        return terrain;
    }
}
