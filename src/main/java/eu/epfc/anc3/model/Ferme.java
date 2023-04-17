package eu.epfc.anc3.model;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableSet;

class Ferme {

    private Terrain terrain = new Terrain();
    private IntegerProperty score = new SimpleIntegerProperty(0);
    //private final IntegerProperty score = new SimpleIntegerProperty(0);
    private final IntegerProperty nbDays = new SimpleIntegerProperty(0);

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
    void plantGrassMode(){
        fermeStatus.setValue(FermeStatus.PLANT_GRASS);
    }

    void plantCabbageMode() {
        fermeStatus.setValue(FermeStatus.PLANT_CABBAGE);
    }

    void plantCarrotMode() {
        fermeStatus.setValue(FermeStatus.PLANT_CARROT);
    }

    void fertilizerMode() {
        fermeStatus.setValue(FermeStatus.FERTILIZER);
    }

    void recoltMode() {
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
        // check si la cellule a deja un element de type vegetable
        if (!cellContainsElementType(p.getType(),line, col) &&
                (p.getType() != ParcelleValue.CARROT1 || !cellContainsElementType(ParcelleValue.CABBAGE1 ,line, col)) &&
                (p.getType() != ParcelleValue.CABBAGE1 || !cellContainsElementType(ParcelleValue.CARROT1 ,line, col)))
        {
            terrain.addElementToCell(p, line, col);
        }
    }

    //supprime un element
    void removeElementFromCell(ParcelleValue p, int line, int col){
        if (cellContainsElementType(p, line, col))
            terrain.removeElement(p,line,col);
    }
    void removeVegetables( int line, int col){
        ObservableSet<Element> elem = getAllElem(line,col);
        Element lastElement = elem.stream().reduce((a, b) -> b).orElse(null);
        if (lastElement != null){
            if (lastElement.isVegetable()){
                Vegetable v = (Vegetable) lastElement;
                addPoint(v.getCurrentState().getHarvestPoints());
            }
            terrain.removeVegetables(lastElement, line, col);
        }
    }

    void fertilize(int line, int col){
        ObservableSet<Element> elem = getAllElem(line,col);
        for (Element e : elem) {
            if ( e.canBeFetilize() && e.isVegetable()){
                Vegetable vegetable = (Vegetable) e;
                if (vegetable.getCurrentState().stateProperty() < 3 ){
                    while (vegetable.getCurrentState().stateProperty() != 3){
                        vegetable.getCurrentState().nextState();
                    }
                }
            }

        }
    }

    IntegerProperty getPoint(){
        return score;
    }

    void addPoint(int point){
        score.set(score.get() + point);
        //récupérer de removeVegetables les harvestPoint pour ensuite renvoyer les points dans la ferme
    }

    ObservableSet<Element> getAllElem(int line, int col){ return terrain.getElem(line, col);}

    ObservableSet<ParcelleValue> getAllElemType(int line, int col){return terrain.getElemType(line,col);}

    //retourne le status du jeu
    ReadOnlyObjectProperty<FermeStatus> fermeStatusProperty(){return fermeStatus;}

    //permet de déplacer le joueur dans le grid
    void spawnFarmer(Farmer farmer, int line, int col){
        terrain.addElementToCell(farmer, line, col);
    }

    Terrain getTerrain(){
        return terrain;
    }

}
