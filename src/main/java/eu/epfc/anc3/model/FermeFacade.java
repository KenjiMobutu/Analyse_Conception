package eu.epfc.anc3.model;

import javafx.beans.property.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Set;

import static eu.epfc.anc3.model.Terrain.GRID_HEIGHT;
import static eu.epfc.anc3.model.Terrain.GRID_WIDTH;

public class FermeFacade {

    private final Ferme ferme = new Ferme();
    private final Farmer farmer = new Farmer();

    private final Grass grass = new Grass();
    Terrain field = new Terrain();

    private boolean isPressingSpace = false;

    //check si jeu est démarrable :
    private final BooleanProperty isStartable = new SimpleBooleanProperty(false);
    //check si le jeu est démarré :
    private final BooleanProperty isStarted = new SimpleBooleanProperty(false);
    //check si le jeu est en cours :
    private final BooleanProperty isInProgress = new SimpleBooleanProperty(false);
    //check si le jeu est arrêté :
    private final BooleanProperty isStopped = new SimpleBooleanProperty(false);

    // les actions possible :
    private final BooleanProperty plantGrass = new SimpleBooleanProperty(false);
    private final BooleanProperty deplantGrass = new SimpleBooleanProperty(false);
    private final BooleanProperty plantCarrot = new SimpleBooleanProperty(false);
    private final BooleanProperty plantCabbage = new SimpleBooleanProperty(false);
    private final BooleanProperty useFertilizer = new SimpleBooleanProperty(false);
    private final BooleanProperty spacePressed = new SimpleBooleanProperty(false);

    public FermeFacade(){
        isStartable.bind(fermeStatusProperty().isEqualTo(FermeStatus.START));
        isInProgress.bind(fermeStatusProperty().isEqualTo(FermeStatus.STARTED)
                .or(ferme.fermeStatusProperty().isEqualTo(FermeStatus.PLANT_GRASS))
                .or(ferme.fermeStatusProperty().isEqualTo(FermeStatus.DEPLANT_GRASS)));

        isStarted.bind(ferme.fermeStatusProperty().isNotEqualTo(FermeStatus.START));


        isStopped.bind(ferme.fermeStatusProperty().isEqualTo(FermeStatus.STOP));

        plantGrass.bind(fermeStatusProperty().isEqualTo(FermeStatus.PLANT_GRASS));
        deplantGrass.bind(fermeStatusProperty().isEqualTo(FermeStatus.DEPLANT_GRASS));

        spacePressed.bind(fermeStatusProperty().isEqualTo(KeyCode.SPACE));
    }

    public ReadOnlyBooleanProperty isSpacePressed() {return spacePressed;}

    void putFarmerInFarm(){ferme.setFarmerInFarm(farmer);}

    public void start(){
        if (isStartable.get()){
            System.out.println(" -> lancement du jeu :) ");
            ferme.start();
            putFarmerInFarm();
        }
    }

    public void stop(){
        if (isStarted.getValue() ||isInProgress.getValue()){
            System.out.println(" -> arrêt du jeu :) ");
            ferme.stop();
        }
    }
    public void plantActivation(){
        if (isStarted.getValue()){
            System.out.println("  -> Planter de l'herbe est possible :) ");
            ferme.plantMode();
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
            putFarmerInFarm();
            System.out.println("le jeu est relancé :D ");
        }
    }

    ReadOnlyObjectProperty<ParcelleValue> valueProperty(int line, int col) {
        return ferme.valueProperty(line, col);
    }
    void addElementToCell(int line, int col, Element element) {
        ferme.addElementToCell(element,line, col);
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


    public void play(int line, int col) {
        System.out.println("CLICK" + line+ "<--> "+col);
        Position newPosFarmer = new Position(line,col);
        if (containsElementType(ParcelleValue.GRASS, farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY()))
            displayGrass(farmer.getPosFarmer());
        else {
            displayTerrain(farmer.getPosFarmer());
        }
        farmer.setPosFarmer(newPosFarmer.getX(),newPosFarmer.getY());
        ferme.setFarmerInFarm(farmer);
    }
    public void moveFarmer(Move move) {
        System.out.println(getStatus() );
        if (isInProgress.getValue()){
            switch (move){
                case UP:
                    goUp();
                    if (isSpacePressed().getValue())
                        handleAction();
                    System.out.println("here farmer pos : -->" + farmer.getPosFarmer());
                    break;
                case DOWN:
                    goDown();
                    if (isSpacePressed().getValue())
                        handleAction();
                    System.out.println("here farmer pos : -->" + farmer.getPosFarmer());
                    break;
                case LEFT:
                    goLeft();
                    if (isSpacePressed().getValue())
                        handleAction();
                    System.out.println("here farmer pos : -->" + farmer.getPosFarmer());
                    break;
                case RIGHT:
                    goRight();
                    if (isSpacePressed().getValue())
                        handleAction();
                    System.out.println("here farmer pos : -->" + farmer.getPosFarmer());
                    break;
                case SPACE:
                    System.out.println("plant grass val :" + plantGrass.getValue());
                    System.out.println("un plant grass val :" + deplantGrass.getValue());
                    if (isSpacePressed().getValue())
                        handleAction();
            }
        }
    }
    void handleAction(){
        if (plantGrass.getValue()){
            dropGrass();
        }else if (deplantGrass.getValue())
            removeGrass();
    }
    public void handleKeyReleased(KeyEvent event) {
        if (isInProgress.getValue()) {
            if (event.getCode() == KeyCode.SPACE) {
                spacePressed.set(false); // la barre d'espace a été relâchée
                if (plantGrass.getValue()) {
                    dropGrass();
                } else if (deplantGrass.getValue()) {
                    removeGrass();
                }
            }
        }
    }
    void goUp(){
        Position up = new Position(farmer.getPosFarmer().getX()-1, farmer.getPosFarmer().getY());

        if (up.getX() >= 0){
            System.out.println("x : " + up.getX() + "  y :" + up.getY());
            if (containsElementType(ParcelleValue.GRASS, farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY()))
                displayGrass(farmer.getPosFarmer());
            else{
                displayTerrain(farmer.getPosFarmer());
            }
            farmer.setPosFarmer(up.getX(),up.getY());
            ferme.setFarmerInFarm(farmer);
        }
    }
    void goDown(){

        Position down = new Position(farmer.getPosFarmer().getX()+1, farmer.getPosFarmer().getY());
        if (down.getX() < GRID_HEIGHT){
            System.out.println("x : " + down.getX() + "  y :" + down.getY());
            if (containsElementType(ParcelleValue.GRASS, farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY()))
                displayGrass(farmer.getPosFarmer());
            else{
                displayTerrain(farmer.getPosFarmer());
            }
            farmer.setPosFarmer(down.getX(),down.getY());
            ferme.setFarmerInFarm(farmer);
        }

    }

    void goRight(){
        Position right = new Position(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY()+1);
        if (right.getY() < GRID_WIDTH){
            System.out.println("x : " + right.getX() + "  y :" + right.getY());
            if (containsElementType(ParcelleValue.GRASS, farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY()))
                displayGrass(farmer.getPosFarmer());
            else{
                displayTerrain(farmer.getPosFarmer());
            }
            farmer.setPosFarmer(right.getX(),right.getY());
            ferme.setFarmerInFarm(farmer);
        }
    }
    void goLeft(){

        Position left = new Position(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY()-1);
        if (left.getY() >= 0){
            System.out.println("x : " + left.getX() + "  y :" + left.getY());
            if (containsElementType(ParcelleValue.GRASS, farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY()))
                displayGrass(farmer.getPosFarmer());
            else{
                displayTerrain(farmer.getPosFarmer());
            }
            farmer.setPosFarmer(left.getX(),left.getY());
            ferme.setFarmerInFarm(farmer);
        }
    }

    void dropGrass(){
        //addValuePropertyToSet(farmer.getPosFarmer().getPosX(), farmer.getPosFarmer().getPosY(),ParcelleValue.FARMER);
        System.out.println(valueProperty(farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY()));
        if (plantGrass.getValue()){
            Position posGrass = new Position(farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY());
            if (!containsElementType(ParcelleValue.GRASS,farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY())) {
                addElementToCell(farmer.getPosFarmer().getX(), farmer.getPosFarmer().getY(),new Grass());
                displayGrass(posGrass);
            }
        }
        putFarmerInFarm();
    }
    void displayGrass(Position pos) {
        ferme.getTerrain().setValueOnFarm(pos.getX(), pos.getY(), ParcelleValue.GRASS);
    }
    void displayDirt(Position pos){
        ferme.getTerrain().setValueOnFarm(pos.getX(), pos.getY(), ParcelleValue.DIRT);
    }
    void removeGrass(){

        if (containsElementType(ParcelleValue.GRASS, farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY())){
            removeElemFromCell(farmer.getPosFarmer().getX(),farmer.getPosFarmer().getY(), ParcelleValue.GRASS);
        }
        displayTerrain(farmer.getPosFarmer());
        putFarmerInFarm();
    }
    void displayTerrain(Position pos){
        displayDirt(pos);
    }

   /* public void setSpacePressedProperty(boolean b) {
        return spacePressed = b;
    }*/

    public ReadOnlyIntegerProperty getNbGrass() {
        return getNbGrass();
    }
}
