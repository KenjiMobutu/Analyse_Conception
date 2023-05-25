package eu.epfc.anc3.model;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.ObservableSet;
import javafx.scene.input.KeyCode;

import java.util.Random;

import static eu.epfc.anc3.model.Terrain.GRID_HEIGHT;
import static eu.epfc.anc3.model.Terrain.GRID_WIDTH;

public class FermeFacade {
 // TODO : Memento : doit faire une copie profonde de chaque fichier

    private final Ferme ferme = new Ferme();
    private final Terrain terrain = new Terrain();
    private final Farmer farmer = new Farmer();
    //private final Vegetable vegetable = new Vegetable();
    private boolean isPressingAction = false;

    //check si jeu est démarrable :
    private final BooleanProperty isStartable = new SimpleBooleanProperty(false);
    //check si le jeu est démarré :
    private final BooleanProperty isStarted = new SimpleBooleanProperty(false);
    //check si le jeu est en cours :
    private final BooleanProperty isInProgress = new SimpleBooleanProperty(false);
    //check si le jeu est arrêté :
    private final BooleanProperty isStopped = new SimpleBooleanProperty(false);


    // boolean property :

    //public ReadOnlyBooleanProperty isStartableProperty (){return isStartable;}
    public ReadOnlyBooleanProperty isStartedProperty (){return isStarted;}
    public boolean actionPressedProperty() {return isPressingAction;} //BV mettre juste action property

    // mettre le spacePressed a true :
    public void setActionProperty(boolean b){isPressingAction = b;} //BV
    // les actions possible :
    private final BooleanProperty plantGrass = new SimpleBooleanProperty(false);
    private final BooleanProperty deplantGrass = new SimpleBooleanProperty(false);
    private final BooleanProperty plantCarrot = new SimpleBooleanProperty(false);
    private final BooleanProperty plantCabbage = new SimpleBooleanProperty(false);
    private final BooleanProperty useFertilizer = new SimpleBooleanProperty(false);
    private final BooleanProperty recolt = new SimpleBooleanProperty(false);
    private final BooleanProperty putCow = new SimpleBooleanProperty(false);
    private final BooleanProperty putSheep = new SimpleBooleanProperty(false);
    private final BooleanProperty plantCorn = new SimpleBooleanProperty(false);
    private final BooleanProperty plantStrawberry = new SimpleBooleanProperty(false);

    private final IntegerProperty nbJours = new SimpleIntegerProperty(0);
    private final IntegerProperty score = new SimpleIntegerProperty(0);
    private final IntegerProperty graine = new SimpleIntegerProperty(0);///// Exam - déclare une propriété entière nommée graine avec une valeur initiale de 0.

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
                .or(ferme.fermeStatusProperty().isEqualTo(FermeStatus.COW))
                .or(ferme.fermeStatusProperty().isEqualTo(FermeStatus.SHEEP))
                .or(ferme.fermeStatusProperty().isEqualTo(FermeStatus.PLANT_CORN))
                .or(ferme.fermeStatusProperty().isEqualTo(FermeStatus.PLANT_STRAWBERRY))
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
        putCow.bind(fermeStatusProperty().isEqualTo(FermeStatus.COW));
        putSheep.bind(fermeStatusProperty().isEqualTo(FermeStatus.SHEEP));
        plantCorn.bind(fermeStatusProperty().isEqualTo(FermeStatus.PLANT_CORN));
        plantStrawberry.bind(fermeStatusProperty().isEqualTo(FermeStatus.PLANT_STRAWBERRY));
        score.bind(ferme.getPoint());
    }

    //permet de déplacer le fermier dans le jeu
    void spawnFarmerInFarm(){ferme.spawnFarmer(farmer, farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY());}

    public void start(){
        if (isStartable.get()){
            graine.set(5); /// Exam - initialise la valeur de graine à 5
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

    public void unplantMode(){
        if (isStarted.getValue()){
            System.out.println("  -> Déplanter de l'herbe est possible :) ");
            ferme.unplantMode();
        }
    }
    public void cowMode(){
        if (isStarted.getValue()){
            System.out.println("  -> Vache est possible :) ");
            ferme.cowMode();
        }
    }
    public void sheepMode(){
        if (isStarted.getValue()){
            System.out.println("  -> Mouton est possible :) ");
            ferme.sheepMode();
        }
    }
    public void cornMode(){
        if (isStarted.getValue()){
            System.out.println("  -> Maïs est possible :) ");
            ferme.cornMode();
        }
    }
    public void strawberryMode(){
        if (isStarted.getValue()){
            System.out.println("  -> Fraise est possible :) ");
            ferme.strawberryMode();
        }
    }
    public void newGame() {
        System.out.println("Le joueur souhaite rejouer : ");
        if (isStopped.getValue()){
            ferme.newGame();
            farmer.setPosFarmer(0,0);
            spawnFarmerInFarm();
            graine.set(5);//// Exam - initialise la valeur de graine à 5
            ferme.setScore(0);
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
            switch (move) {
                case UP -> {
                    goUp();
                    System.out.println("here farmer pos : -->" + farmer.getPosFarmer());
                }
                case DOWN -> {
                    goDown();
                    System.out.println("here farmer pos : -->" + farmer.getPosFarmer());
                }
                case LEFT -> {
                    goLeft();
                    System.out.println("here farmer pos : -->" + farmer.getPosFarmer());
                }
                case RIGHT -> {
                    goRight();
                    System.out.println("here farmer pos : -->" + farmer.getPosFarmer());
                }
                case SPACE -> System.out.println("pressing space");
            }

            if (actionPressedProperty())
                handleAction();
        }
        System.out.println(ferme.getAllElem(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY()) + " <--- affichage des elements d'une cellule");

    }

    void handleAction(){
        if (plantGrass.getValue())
            dropGrass();
        else if (plantCabbage.getValue())
            plantCabbage();
        else if (plantCarrot.getValue())
            PlantCarrot();
        else if (useFertilizer.getValue())
            dropFertilizer();
        else if (recolt.getValue())
            recoltVegetals();
        else if (putCow.getValue())
            putCow();
        else if (putSheep.getValue())
            putSheep();
        else if (plantCorn.getValue())
            plantCorn();
        else if (plantStrawberry.getValue())
            plantStrawberry();


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
    boolean hasSeed(){ ////Exam - Vérifie si le fermier a des graines
        return graine.get() > 0;
    }

    void dropGrass(){ //BV : voir plus haut
        Position posGrass = new Position(farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY());

        if (hasSeed() && !containsElementType(ParcelleValue.GRASS,posGrass.x,posGrass.y)){ ///Exam - Vérifie si le fermier a des graines et si il n'y a pas déjà de l'herbe
            Grass grass = new Grass(terrain.getParcelle(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY()));

            grass.nbJours.bind(nbJours);

            System.out.println(!containsElementType(ParcelleValue.GRASS,farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY()) );
            //addElementToCell(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY(),new Grass());
            ferme.addElementToCell(grass,farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY());
            graine.set(graine.get()-1);////Exam - Retire une graine
        }


    }

    void plantCabbage(){
        if (hasSeed() && !ferme.cellContainsVegetable(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY())) { ////Exam - Vérifie si le fermier a des graines et si il n'y a pas déjà de chou
            Cabbage cabbage = new Cabbage(terrain.getParcelle(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY()));
            addElementToCell(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY(), cabbage);

            cabbage.nbJoursProperty().bind(nbJours);
            graine.set(graine.get() - 1);////Exam - Retire une graine
            System.out.println(ferme.getAllElem(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY()) + "ICI Cabbage");
        }
    }
    void PlantCarrot(){
        if (hasSeed() && !ferme.cellContainsVegetable(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY())) { ////Exam - Vérifie si le fermier a des graines et si il n'y a pas déjà de carotte
            Carrot carrot = new Carrot(terrain.getParcelle(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY()));
            //Position posCarrot = new Position(farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY());
            addElementToCell(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY(), carrot);
            graine.set(graine.get() - 1); ////Exam - Retire une graine
            carrot.nbJoursProperty().bind(nbJours);
        }
    }
    void plantCorn(){
        // Définir les bornes pour les positions aléatoires
        int minX = 0;
        int maxX = 14;
        int minY = 0;
        int maxY = 24;

        // Générer des coordonnées aléatoires
        Random random = new Random();
        int x = random.nextInt(maxX - minX + 1) + minX;
        int y = random.nextInt(maxY - minY + 1) + minY;

        Corn corn = new Corn(terrain.getParcelle(x, y));
        addElementToCell(x, y, corn);

        corn.nbJoursProperty().bind(nbJours);
    }
    void plantStrawberry(){
        // Définir les bornes pour les positions aléatoires
        int minX = 0;
        int maxX = 14;
        int minY = 0;
        int maxY = 24;

        // Générer des coordonnées aléatoires
        Random random = new Random();
        int x = random.nextInt(maxX - minX + 1) + minX;
        int y = random.nextInt(maxY - minY + 1) + minY;

        Strawberry strawberry = new Strawberry(terrain.getParcelle(x, y));
        addElementToCell(x, y, strawberry);

        strawberry.nbJoursProperty().bind(nbJours);
    }

    private void recoltVegetals() {
        if(ferme.removeVegetables(farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY()))////Exam - Vérifie si il y a des légumes à récolter
            graine.set(graine.get()+1);////Exam - Ajoute une graine
    }
    private void putSheep() {
        Sheep sheep = new Sheep(terrain.getParcelle(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY()));
        addElementToCell(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY(), sheep);
    }

    private void putCow() {
        // Définir les bornes pour les positions aléatoires
        int minX = 0;
        int maxX = 14;
        int minY = 0;
        int maxY = 24;

        // Générer des coordonnées aléatoires
        Random random = new Random();
        int x = random.nextInt(maxX - minX + 1) + minX;
        int y = random.nextInt(maxY - minY + 1) + minY;
        Cow cow = new Cow(terrain.getParcelle(x, y));
        addElementToCell(x, y, cow);
        cow.nbJoursProperty().bind(nbJours);
    }
    public ReadOnlyIntegerProperty scoreProperty(){
        return score;
    }
    public ReadOnlyIntegerProperty graineProperty(){////Exam - Retourne le nombre de graines
        return graine;
    }

    private void dropFertilizer() {
        ferme.fertilize(farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY());
    }
    void displayTerrain(Position pos){
        showElementsInCell(pos.getX(), pos.getY());
    }

    public ReadOnlyIntegerProperty nextDay() { return this.nextDayProperty();}

    public IntegerProperty nextDayProperty() {
        nbJours.set(nbJours.get() + 1);
        graine.set(graine.get() + 1);////Exam - Ajoute une graine quand on passe un jour
        System.out.println("ROTTEN VEGGIEs");
        removeRottenVegetables();
        System.out.println(nbJours + "nbJours");
        return nbJours;
    }

    private void removeRottenVegetables() {
        //ferme.removeRotten...  --> terrain.removeRotten --> à chaque parcelle -> parcelle.removeRotten
        ferme.removeRotten();

    }


    public BooleanProperty getElementsStateProperty(int line, int col){
        return terrain.getElementsStateProperty(line, col);
    }
}
