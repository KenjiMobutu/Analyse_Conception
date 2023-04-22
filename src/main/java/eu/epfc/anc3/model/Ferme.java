package eu.epfc.anc3.model;

import javafx.beans.property.*;
import javafx.collections.ObservableSet;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class Ferme {
    private Terrain terrain ;
    private final IntegerProperty score = new SimpleIntegerProperty(0);
    private final ObjectProperty<FermeStatus> fermeStatus = new SimpleObjectProperty<>(FermeStatus.START);
    public Ferme(){}

    void start(){
        if (fermeStatus.get() == FermeStatus.START){
            terrain = new Terrain();
            fermeStatus.set(FermeStatus.STARTED);
        }

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

    void unplantMode(){ fermeStatus.set(FermeStatus.DEPLANT_GRASS);}

    void cowMode() {fermeStatus.set(FermeStatus.COW);}
    void sheepMode() {fermeStatus.set(FermeStatus.SHEEP);}

    void cornMode() {fermeStatus.set(FermeStatus.PLANT_CORN);}

    boolean cellContainsElementType(ParcelleValue pv, int line, int col){
        return terrain.containsElementType(pv, line, col);
    }

    //ajout un element a une cellule
    void addElementToCell(Element p, int line, int col){
        // check s'il y a une carrot / cabbage
        if (!cellContainsElementType(p.getType(),line, col) ||
                !cellContainsVegetable(line, col)) {
            terrain.addElementToCell(p, line, col);
            grassOnCell(line,col);
        }
    }


    private void grassOnCell(int line, int col){
        ObservableSet<Element> elem = terrain.getElem(line, col);
        if (cellContainsElementType(ParcelleValue.GRASS, line,col)){
            for (Element e : elem){
                if (e.isVegetable()){
                    Vegetable v = (Vegetable) e;
                    v.setHasGrass(true);
                }
            }
        }

    }

    boolean cellContainsVegetable(int line, int col) {
        ObservableSet<Element> elem = terrain.getElem(line, col);
        for (Element e : elem){
            if (e.isVegetable()){
                return true;
            }
        }
        return false;
    }


    //supprime un element
    void removeElementFromCell(ParcelleValue p, int line, int col){
        if (cellContainsElementType(p, line, col))
            terrain.removeElement(p,line,col);
    }
    void removeRottenVegetables(Element e, int line, int col){
        if (e.isVegetable()){
            Vegetable v = (Vegetable) e;
            addPoint(v.getCurrentState().getHarvestPoints());
        }
        terrain.removeVegetables(e, line, col);
    }
    void removeVegetables( int line, int col){
        ObservableSet<Element> elem = getAllElem(line,col);
        Element lastElement = elem.stream().reduce((a, b) -> b).orElse(null);
        if (lastElement != null ){
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

    void setScore(int i){
        score.set(i);
    }

    void addPoint(int point){
        score.set(score.get() + point);
        //récupérer de removeVegetables les harvestPoint pour ensuite renvoyer les points dans la ferme
    }

    ObservableSet<Element> getAllElem(int line, int col){ return terrain.getElem(line, col);}

    ObservableSet<ParcelleValue> getAllElemType(int line, int col){return terrain.getElemType(line,col);}

    //retourne le status du jeu
    ReadOnlyObjectProperty<FermeStatus> fermeStatusProperty(){return fermeStatus;}

    void removeRotten() {
        for (int i = 0; i < Terrain.GRID_HEIGHT; i++) {
            for (int j = 0; j < Terrain.GRID_WIDTH; j++) {
                ObservableSet<Element> elem = getAllElem(i, j);
                Iterator<Element> iter = elem.iterator();
                while (iter.hasNext()) {
                    Element e = iter.next();
                    if (e.isRotten()) {
                        e.setStateChanged(true);
                        iter.remove();
                        removeRottenVegetables(e,i, j);

                    }
                }
            }
        }
    }

    //permet de déplacer le joueur dans le grid
    void spawnFarmer(Farmer farmer, int line, int col){
        terrain.addElementToCell(farmer, line, col);
    }

}
