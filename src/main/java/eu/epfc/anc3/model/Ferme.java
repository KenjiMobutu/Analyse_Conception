package eu.epfc.anc3.model;

import javafx.beans.property.*;
import javafx.collections.ObservableSet;

class Ferme {

    private Terrain terrain = new Terrain();
    private Memento saveGame = new Memento();
    private boolean isSaved = false;

    private IntegerProperty score = new SimpleIntegerProperty(0);

    private final ObjectProperty<FermeStatus> fermeStatus = new SimpleObjectProperty<>(FermeStatus.START);
    public Ferme(){}

    void start(){
        if (isSaved){
            terrain = saveGame.getFerme().getTerrain();
            fermeStatus.set(FermeStatus.STARTED);
        }else{
            terrain = new Terrain();
            fermeStatus.set(FermeStatus.STARTED);
        }

    }
    void newGame() {
        terrain.resetTerrain();
        fermeStatus.set(FermeStatus.STARTED);
    }
    Memento saveGame(int nbJour){
        saveGame = new Memento(this, score.getValue(), nbJour);
        isSaved = true;
        return saveGame;
    }
    boolean saveGameDidWell(){
        return isSaved;
    }
    void loadGame(){
        if (isSaved)
            start();
    }
    int MementoNbDayProperty(){
        return saveGame.getJour();
    }
    int MementoScoreProperty(){
        return saveGame.getScore();
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
        for (Element e : elem) {
             if (e.getType().toString().contains("CARROT")){
                Carrot currentCarrot = (Carrot) e;
                addPoint(currentCarrot.getCurrentState().getHarvestPoints());
                System.out.println("state cabbage : " + currentCarrot.getCurrentState() + " point a avoir : " + currentCarrot.getCurrentState().getHarvestPoints());
            }else if (e.getType().toString().contains("CABBAGE")){
                Cabbage currentCabbage = (Cabbage) e;
                addPoint(currentCabbage.getCurrentState().getHarvestPoints());
                System.out.println("state cabbage : " + currentCabbage.getCurrentState() + " point a avoir : " + currentCabbage.getCurrentState().getHarvestPoints());
            }
            terrain.removeVegetables(e, line, col);
        }
    }
    void fetilize(int line, int col){
        ObservableSet<Element> elem = getAllElem(line,col);
        for (Element e : elem) {
            if (e instanceof Grass){
                continue;
            }else if (e instanceof Carrot c){
                if (c.getCurrentState().stateProperty() < 3 ){
                    while (c.getCurrentState().stateProperty() != 3){
                        c.getCurrentState().nextState();
                    }
                }
            }
        }
    }

    IntegerProperty getPoint(){
        return score;
    }

    int setPoint(int point){
        score.set(point);
        return point;
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


/*-------------------------------POUR DEBUG------------------------------------*/
    private final Farmer farmer= new Farmer() ;
    public ReadOnlyIntegerProperty nbGrassPlant() {return farmer.nbgrass();
    }
    public ReadOnlyIntegerProperty nbDays() {return farmer.nbgrass();
    }
}
