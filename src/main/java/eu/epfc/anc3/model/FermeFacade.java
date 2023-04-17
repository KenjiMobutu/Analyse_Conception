package eu.epfc.anc3.model;

import javafx.beans.property.*;
import javafx.collections.ObservableSet;

import static eu.epfc.anc3.model.Terrain.GRID_HEIGHT;
import static eu.epfc.anc3.model.Terrain.GRID_WIDTH;

public class FermeFacade {

    private final Ferme ferme = new Ferme();
    private final Farmer farmer = new Farmer();
    private boolean action = false;

    //check si jeu est démarrable :
    private final BooleanProperty isStartable = new SimpleBooleanProperty(false);
    //check si le jeu est démarré :
    private final BooleanProperty isStarted = new SimpleBooleanProperty(false);
    //check si le jeu est en cours :
    private final BooleanProperty isInProgress = new SimpleBooleanProperty(false);
    //check si le jeu est arrêté :
    private final BooleanProperty isStopped = new SimpleBooleanProperty(false);


    // boolean property :

    public ReadOnlyBooleanProperty isStartedProperty (){return isStarted;}
    public boolean actionProperty() {return action;}

    // mettre le actionProperty a true :
    public void setActionProperty(boolean b){action = b;}
    // les actions possible :
    private final BooleanProperty plantGrass = new SimpleBooleanProperty(false);
    private final BooleanProperty deplantGrass = new SimpleBooleanProperty(false);
    private final BooleanProperty plantCarrot = new SimpleBooleanProperty(false);
    private final BooleanProperty plantCabbage = new SimpleBooleanProperty(false);
    private final BooleanProperty useFertilizer = new SimpleBooleanProperty(false);
    private final BooleanProperty recolt = new SimpleBooleanProperty(false);


    private IntegerProperty nbJours = new SimpleIntegerProperty(0);
    private IntegerProperty score = new SimpleIntegerProperty(0);


    // retourne les éléments d'une cellule :
    public ObservableSet<ParcelleValue> getElementsType(int line, int col){ //BV : à enlevefr
        return ferme.getAllElemType(line,col);
    }

    public ObservableSet<Element> getElements(int line, int col){
        return ferme.getAllElem(line,col);
    }
    public FermeFacade(){
        //si jeu peut être lancé
        isStartable.bind(fermeStatusProperty().isEqualTo(FermeStatus.START));
        // pour vérifier tous les états que peut avoir le jeu
        isInProgress.bind(fermeStatusProperty().isEqualTo(FermeStatus.STARTED)
                .or(ferme.fermeStatusProperty().isEqualTo(FermeStatus.PLANT_GRASS))
                .or(ferme.fermeStatusProperty().isEqualTo(FermeStatus.DEPLANT_GRASS))
                .or(ferme.fermeStatusProperty().isEqualTo(FermeStatus.PLANT_CABBAGE))
                .or(ferme.fermeStatusProperty().isEqualTo(FermeStatus.PLANT_CARROT))
                .or(ferme.fermeStatusProperty().isEqualTo(FermeStatus.FERTILIZER))
                .or(ferme.fermeStatusProperty().isEqualTo(FermeStatus.RECOLT)));

        isStarted.bind(ferme.fermeStatusProperty().isNotEqualTo(FermeStatus.START));
        //check si jeu est arrêté
        isStopped.bind(ferme.fermeStatusProperty().isEqualTo(FermeStatus.STOP));
        //les différentes actions possible dans le jeu
        plantGrass.bind(fermeStatusProperty().isEqualTo(FermeStatus.PLANT_GRASS));
        deplantGrass.bind(fermeStatusProperty().isEqualTo(FermeStatus.DEPLANT_GRASS));
        plantCabbage.bind(fermeStatusProperty().isEqualTo(FermeStatus.PLANT_CABBAGE));
        plantCarrot.bind(fermeStatusProperty().isEqualTo(FermeStatus.PLANT_CARROT));
        recolt.bind(fermeStatusProperty().isEqualTo(FermeStatus.RECOLT));
        useFertilizer.bind(fermeStatusProperty().isEqualTo(FermeStatus.FERTILIZER));
        score.bind(ferme.getPoint());

    }

    //permet de déplacer le fermier dans le jeu
    void spawnFarmerInFarm(){ferme.spawnFarmer(farmer, farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY());}

    public void start(){
        if (isStartable.get()){
            System.out.println(" -> lancement du jeu :) ");
            ferme.start();
            spawnFarmerInFarm();
        }
    }

    public void stop(){
        if (isStarted.getValue() ||isInProgress.getValue()){
            System.out.println(" -> arrêt du jeu :) ");
            ferme.stop();
        }
    }
    public void plantGrassMode(){
        if (isStarted.getValue()){
            System.out.println("  -> Planter de l'herbe est possible :) ");
            ferme.plantGrassMode();
        }
    }
    public void plantCabbageMode() {
        if (isStarted.getValue()){
            System.out.println("  -> Planter Cabbage est possible :) ");
            ferme.plantCabbageMode();
        }
    }
    public void plantCarrotMode() {
        if (isStarted.getValue()){
            System.out.println("  -> Planter Carrot est possible :) ");
            ferme.plantCarrotMode();
        }
    }
    public void fertilizerMode() {
        if (isStarted.getValue()){
            System.out.println("  -> Fertilizer est possible :) ");
            ferme.fertilizerMode();
        }
    }
    public void recoltMode() {
        if (isStarted.getValue()){
            System.out.println("  -> Recolt est possible :) ");
            ferme.recoltMode();
        }
    }

    public void saveGame(){
        System.out.println("sauvegarde du jeu ");
        ferme.saveGame(nbJours.get());
    }
    public void loadGame(){
        if (ferme.saveGameDidWell()) { // check si y a une save
            System.out.println("Loading game...");
            ferme.loadGame(); // load the game
            nbJours.set(ferme.MementoNbDayProperty());
            //score.set(ferme.getMementoScoreProperty());

        } else {
            System.out.println("No saved game to load.");
        }
    }

    public void unplantMode(){
        if (isStarted.getValue()){
            System.out.println("  -> Déplanter de l'herbe est possible :) ");
            ferme.unplantMode();
        }
    }
    public void newGame() {
        System.out.println("Le joueur souhaite rejouer : ");
        if (isStopped.getValue()){
            ferme.newGame();
            farmer.setPosFarmer(0,0);
            spawnFarmerInFarm();
            ferme.setPoint(0);
            System.out.println("le jeu est relancé :D ");
        }
    }


    void addElementToCell(int line, int col, Element element) {
        ferme.addElementToCell(element,line, col);
    }
    void showElementsInCell(int line, int col){
        ferme.getAllElem(line,col);
    }

    boolean containsElementType(ParcelleValue pv,int line, int col){
        return ferme.cellContainsElementType(pv,line,col);
    }


    void removeElemFromCell(int line, int col, ParcelleValue p){
        ferme.removeElementFromCell(p,line,col);
    }
    ReadOnlyObjectProperty<FermeStatus> fermeStatusProperty(){
        return ferme.fermeStatusProperty();
    }
    FermeStatus getStatus(){return fermeStatusProperty().get();}


    public void teleport(int line, int col) { //BV : à revoir, ceci ajoute le fermier en line/col, c'est la parcelle qui décidera où il doit se trouver dans la liste
        System.out.println("CLICK" + line+ "<--> "+col);
        removeElemFromCell(farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY(), ParcelleValue.FARMER);
        Position newPosFarmer = new Position(line,col);
        farmer.setPosFarmer(newPosFarmer.getX(),newPosFarmer.getY()); //BV dans spawn
        //ferme.setFarmerInFarm(farmer);
        ferme.spawnFarmer(farmer, line, col);
    }
    public void moveFarmer(Move move) {
        System.out.println(getStatus() );

        if (isInProgress.getValue()){
            removeElemFromCell(farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY(), ParcelleValue.FARMER);
            switch (move){
                case UP:
                    goUp();
                    System.out.println("here farmer pos : -->" + farmer.getPosFarmer());
                    break;
                case DOWN:
                    goDown();
                    System.out.println("here farmer pos : -->" + farmer.getPosFarmer());
                    break;
                case LEFT:
                    goLeft();
                    System.out.println("here farmer pos : -->" + farmer.getPosFarmer());
                    break;
                case RIGHT:
                    goRight();
                    System.out.println("here farmer pos : -->" + farmer.getPosFarmer());
                    break;
                case SPACE:
                    System.out.println("plant grass val :" + plantGrass.getValue());
                    System.out.println("un plant grass val :" + deplantGrass.getValue());
            }
            if (actionProperty())
                handleAction();
        }
        System.out.println(ferme.getAllElem(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY()) + " <--- affichage des elements d'une cellule");

    }
    void handleAction(){
        if (plantGrass.getValue()){
            dropGrass();
        }else if (deplantGrass.getValue())
            removeGrass();
        else if (plantCabbage.getValue())
            plantCabbage();
        else if (plantCarrot.getValue())
            PlantCarrot();
        else if (useFertilizer.getValue())
            dropFertilizer();
        else {
            recoltVegetals();
        }
        displayTerrain(farmer.getPosFarmer());
        spawnFarmerInFarm();
    }

    void goUp(){ //BV : voir "play" mais qui devrait se nommer "teleport"
        Position up = new Position(farmer.getPosFarmer().getX()-1, farmer.getPosFarmer().getY());
        if (up.getX() >= 0){
            System.out.println("x : " + up.getX() + "  y :" + up.getY());
            removeElemFromCell(farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY(), ParcelleValue.FARMER);
            farmer.setPosFarmer(up.getX(),up.getY());
        }
        spawnFarmerInFarm();

//        ferme.spawnFarmer(farmer, farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY());

    }
    void goDown(){

        Position down = new Position(farmer.getPosFarmer().getX()+1, farmer.getPosFarmer().getY());
        if (down.getX() < GRID_HEIGHT){
            System.out.println("x : " + down.getX() + "  y :" + down.getY());
            removeElemFromCell(farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY(), ParcelleValue.FARMER);
            farmer.setPosFarmer(down.getX(),down.getY()); // a mettre dans spawnFarmer
        }
        spawnFarmerInFarm();

//        ferme.spawnFarmer(farmer, farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY());
    }

    void goRight(){
        Position right = new Position(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY()+1);
        if (right.getY() < GRID_WIDTH){
            System.out.println("x : " + right.getX() + "  y :" + right.getY());
            removeElemFromCell(farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY(), ParcelleValue.FARMER);
            farmer.setPosFarmer(right.getX(),right.getY());
        }
        spawnFarmerInFarm();

//        ferme.spawnFarmer(farmer, farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY());
    }

    void goLeft(){

        Position left = new Position(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY()-1);
        if (left.getY() >= 0){
            System.out.println("x : " + left.getX() + "  y :" + left.getY());

            removeElemFromCell(farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY(), ParcelleValue.FARMER);

            farmer.setPosFarmer(left.getX(),left.getY());
        }
        spawnFarmerInFarm();

//        ferme.spawnFarmer(farmer, farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY());
    }

    void dropGrass(){ //BV : voir plus haut
        Position posGrass = new Position(farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY());
        farmer.plantGrass(posGrass);//K:Pour DEBUG
        System.out.println(!containsElementType(ParcelleValue.GRASS,farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY()) );
        //addElementToCell(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY(),new Grass());
        ferme.addElementToCell(new Grass(),farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY());
        addListenerToVegetables(posGrass);

        System.out.println(ferme.getAllElem(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY()) + "qsldh");
    }

    void plantCabbage(){
        Position cabbagePos = new Position(farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY());
        Cabbage cabbageState1 = new Cabbage(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY(),containsElementType(ParcelleValue.GRASS,farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY()));
        //Position posCabbage = new Position(farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY());
        addListenerToVegetables(cabbagePos);
        System.out.println(!containsElementType(ParcelleValue.CABBAGE1,farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY()) + "ICI Cabbage");
        addElementToCell(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY(),cabbageState1);
        //      <------------ mettre ça dans Cabbage ------------>

        System.out.println(ferme.getAllElem(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY()) + "ICI Cabbage");
    }
    void PlantCarrot(){
        Position carrotPos = new Position(farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY());
        Carrot carrot = new Carrot(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY());
        addElementToCell(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY(), carrot);
        addListenerToVegetables(carrotPos);
        System.out.println(ferme.getAllElem(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY()) + "ICI Carrot");
    }

    void addListenerToVegetables(Position vegetablePos){
        ferme.addListener(vegetablePos);
    }



    void removeGrass(){
        farmer.removeGrassAtPos(farmer.getPosFarmer());//K:Pour DEBUG

        removeElemFromCell(farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY(), ParcelleValue.GRASS);

        System.out.println(ferme.getAllElem(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY()) + "   qsldh");

        displayTerrain(farmer.getPosFarmer());

    }
    private void recoltVegetals() {
        //faire fonction addPoint qui récupère le state du légume et les points lié a celui-ci
        ferme.removeVegetables(farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY());
    }
    public ReadOnlyIntegerProperty scoreProperty(){
        return score;
    }

    private void dropFertilizer() {
        ferme.fetilize(farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY());
    }
    void displayTerrain(Position pos){
        showElementsInCell(pos.getX(), pos.getY());
    }

    public ReadOnlyIntegerProperty getNbGrass() {return ferme.nbGrassPlant();}//K:Pour DEBUG

    public ReadOnlyIntegerProperty getNbJour() {return ferme.nbDays();
    }

    public ReadOnlyIntegerProperty nextDay() { return this.nextDayProperty();
    }

    public boolean containsVegetable(int line, int col){
        return ferme.cellContainsCarrotOrCabbage(line,col);
    }

    public IntegerProperty nextDayProperty() {
        nbJours.set(nbJours.get() + 1);
        System.out.println("nombre de jour dans fichier Ferme : ->>> "  + ferme.nbDaysInFarm());
        ferme.setDays(nbJours.get()+1);
        System.out.println(nbJours + "nbJours");
        return nbJours;
    }
}
