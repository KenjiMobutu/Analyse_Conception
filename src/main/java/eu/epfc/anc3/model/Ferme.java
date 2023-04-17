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
    /*void addElementToCell(Element p, int line, int col){
        // check si la cellule a deja un element de type vegetable
        if (!cellContainsElementType(p.getType(),line, col) &&
                (p.getType() != ParcelleValue.CARROT1 || !cellContainsElementType(ParcelleValue.CABBAGE1 ,line, col)) &&
                (p.getType() != ParcelleValue.CABBAGE1 || !cellContainsElementType(ParcelleValue.CARROT1 ,line, col)))
        {
            terrain.addElementToCell(p, line, col);
        }
    }*/
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

    void removeRotten(){
        for (int i = 0; i < terrain.GRID_HEIGHT; i++) {
            for (int j = 0; j < terrain.GRID_WIDTH; j++) {
                ObservableSet<Element> elem = getAllElem(i, j);
                for (Element e : elem) {
                    System.out.println("ROTTEN VEGETABLES (cabbage) --> " +elem  + e.getType() + " " + e.isRotten());
                    terrain.notifyParcelleView(new Position(i, j));
                    if (e.getType() == ParcelleValue.ROTTEN_CABBAGE || e.getType() == ParcelleValue.ROTTEN_CARROT || e.getType() == ParcelleValue.GRASS) {
                        e.setStateChanged(true); // met à jour l'état changé de l'élément
                        System.out.println(e.getStateChanged());
                        // Supprimer le légume ou l'herbe pourri de la cellule
                        if (e.isRotten()) {
                            removeVegetables( i, j);
                            terrain.notifyParcelleView(new Position(i, j));
                        }
                    }
                }
            }
        }
    }
    //permet de déplacer le joueur dans le grid
    void spawnFarmer(Farmer farmer, int line, int col){
        terrain.addElementToCell(farmer, line, col);
    }

    Terrain getTerrain(){
        return terrain;
    }

}
