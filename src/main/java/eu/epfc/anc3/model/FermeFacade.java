package eu.epfc.anc3.model;

import eu.epfc.anc3.vm.ParcelleViewModel;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


import java.util.List;
import java.util.Set;

import static eu.epfc.anc3.model.Terrain.GRID_WIDTH;
import static eu.epfc.anc3.model.Terrain.GRID_HEIGHT;
public class FermeFacade {

    private final Ferme ferme = new Ferme();
    private final Farmer farmer = new Farmer();
    Terrain field = new Terrain();
    private boolean spacePress = false;

    //jeu démarrable
    private final BooleanProperty isStartable = new SimpleBooleanProperty(false);
    //jeu démarré
    private final BooleanProperty isStarted = new SimpleBooleanProperty(false);
    //jeu en cours
    private final BooleanProperty isInProgress = new SimpleBooleanProperty(false);
    private final BooleanProperty isStopped = new SimpleBooleanProperty(false);

    //faire boolean pour les actions ?
    private final BooleanProperty plantGrass = new SimpleBooleanProperty(false);
    private final BooleanProperty deplantGrass = new SimpleBooleanProperty(false);
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
    public ReadOnlyBooleanProperty isStartedProperty() {return isStarted;}
    public ReadOnlyBooleanProperty isInProgressProperty() {return isInProgress;}
    public ReadOnlyBooleanProperty isStoppedProperty() {return isStopped;}
    public ReadOnlyBooleanProperty isSpacePressed() {return isInProgress;}
    public ReadOnlyBooleanProperty isStartableProperty() {return spacePressed;}
    public ReadOnlyBooleanProperty isPlantProperty() {return plantGrass;}
    public ReadOnlyBooleanProperty isDeplantProperty() {return deplantGrass;}


    private void putFarmerInFarm(){ferme.farmerInFarm(farmer);}

    public void start(){
        System.out.println(isStartable.get());
        if (isStartable.get()){
            ferme.start();
            putFarmerInFarm();
            System.out.println("START");
            System.out.println("le jeu est lancé (fonction start) ");
        }
    }
    public void stop(){
        if (isStarted.getValue() ||isInProgress.getValue()){
            ferme.stop();
            System.out.println("jeu arrêté");
        }
    }
    public void plantMode(){
        if (isStarted.getValue()){
            ferme.plantMode();
            System.out.println("Peut Plant Grass normalement.");
        }
    }
    public void unplantMode(){
        if (isStarted.getValue()){
            ferme.unplantMode();
            System.out.println("Peut unPlant Grass normalement.");
        }
    }
    public void newGame() {
        System.out.println(isStopped.get() +"LE JEU EST STOP");
        if (isStopped.getValue()){
            farmer.resetGrass();
            ferme.newGame();
            farmer.setPosFarmer(0,0);
            putFarmerInFarm();
            System.out.println("RESTARTED");
            System.out.println("le jeu est lancé (fonction RESTARTED) ");
        }
    }

    public ReadOnlyObjectProperty<ParcelleValue> valueProperty(int line, int col) {
        return ferme.valueProperty(line, col);
    }
    public Set<Element> valuePropertyFromSet(int line, int col) {
        return ferme.valuePropertyFromSet(line, col);
    }
    public void addValuePropertyToSet(int line, int col, ParcelleValue parcelleValue) {
        ferme.addValuePropertyToCell(parcelleValue,line, col);
    }
    public void removeElemFromSet(int line, int col, ParcelleValue p){
        ferme.removeValueFromPropertyCell(line,col,p);
    }

    public ReadOnlyObjectProperty<FermeStatus> fermeStatusProperty(){
        return ferme.fermeStatusProperty();
    }

    public ReadOnlyIntegerProperty getNbGrass(){return ferme.nbGrassPlant();}


    public FermeStatus getStatus(){return fermeStatusProperty().get();}

    public void play(int line, int col) {
        System.out.println("CLICK" + line+ "<--> "+col);
        Position tpPlayer = new Position(line,col);
        if (valuePropertyFromSet(farmer.getPosFarmer().getPosX(),farmer.getPosFarmer().getPosY()).contains(ParcelleValue.GRASS))
            displayGrass(farmer.getPosFarmer());
        else {
            displayTerrain(farmer.getPosFarmer());
        }
        farmer.setPosFarmer(tpPlayer.getPosX(),tpPlayer.getPosY());
        ferme.farmerInFarm(farmer);
    }

    public void moveFarmer(Move move) {
        System.out.println(getStatus());
        if (isInProgress.getValue()){
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
                    System.out.println("unplant grass val :" + deplantGrass.getValue());
                    if (spacePress) {
                        return; // la barre d'espace est toujours enfoncée, on ne fait rien
                    }
                    spacePress = true; // la barre d'espace est enfoncée
                    break;

                   /* if (spacePress) {
                        // La barre d'espace est toujours enfoncée, on plante de l'herbe si possible
                        Position farmerPos = farmer.getPosFarmer();
                        List<Position> grassPositions = Terrain.getInstance().getGrassPositions();
                        boolean grassPlanted = false;
                        for (Position grassPos : grassPositions) {
                            if (!grassPos.equals(farmerPos)) {
                                // On plante de l'herbe sur une parcelle différente de celle où se trouve le farmer
                                if (Terrain.getInstance().plantGrass(grassPos)) {
                                    grassPlanted = true;
                                    break;
                                }
                            }
                        }
                        if (grassPlanted) {
                            System.out.println("Grass planted!");
                        } else {
                            System.out.println("No grass to plant!");
                        }
                    }
                    spacePress = true; // La barre d'espace est enfoncée
                    break;*/
            }
        }
    }

    public void handleKeyReleased(KeyEvent event) {
        if (isInProgress.getValue()) {
            if (event.getCode() == KeyCode.SPACE) {
                spacePress = false; // la barre d'espace a été relâchée
                if (plantGrass.getValue()) {
                    dropGrass();
                } else if (deplantGrass.getValue()) {
                    removeGrass();
                }
            }
        }
    }

    private void goUp(){
        Position up = new Position(farmer.getPosFarmer().getPosX()-1, farmer.getPosFarmer().getPosY());
        System.out.println(valuePropertyFromSet(up.getPosX(),up.getPosY()) + " go UP");

        if (up.getPosX() >= 0){
            System.out.println("x : " + up.getPosX() + "  y :" + up.getPosY());
            if (valuePropertyFromSet(farmer.getPosFarmer().getPosX(),farmer.getPosFarmer().getPosY()).contains(ParcelleValue.GRASS))
                displayGrass(farmer.getPosFarmer());
            else{
                displayTerrain(farmer.getPosFarmer());
            }
            farmer.setPosFarmer(up.getPosX(),up.getPosY());
            ferme.farmerInFarm(farmer);
        }
    }

    private void goDown(){

        Position down = new Position(farmer.getPosFarmer().getPosX()+1, farmer.getPosFarmer().getPosY());
        System.out.println(valuePropertyFromSet(down.getPosX(),down.getPosY()) + " go DOWN");

        //action ?
        if (down.getPosX() < GRID_HEIGHT){
            System.out.println("x : " + down.getPosX() + "  y :" + down.getPosY());
            if (valuePropertyFromSet(farmer.getPosFarmer().getPosX(),farmer.getPosFarmer().getPosY()).contains(ParcelleValue.GRASS))
                displayGrass(farmer.getPosFarmer());
            else{
                displayTerrain(farmer.getPosFarmer());
            }
            farmer.setPosFarmer(down.getPosX(),down.getPosY());
            ferme.farmerInFarm(farmer);
        }

    }
    private void goRight(){
        Position right = new Position(farmer.getPosFarmer().getPosX(), farmer.getPosFarmer().getPosY()+1);
        System.out.println(valuePropertyFromSet(right.getPosX(),right.getPosY()) + " GO RIGHT");
        if (right.getPosY() < GRID_WIDTH){
            System.out.println("x : " + right.getPosX() + "  y :" + right.getPosY());
            if (valuePropertyFromSet(farmer.getPosFarmer().getPosX(),farmer.getPosFarmer().getPosY()).contains(ParcelleValue.GRASS))
                displayGrass(farmer.getPosFarmer());
            else{
                displayTerrain(farmer.getPosFarmer());
            }
            farmer.setPosFarmer(right.getPosX(),right.getPosY());
            ferme.farmerInFarm(farmer);
        }
    }
    private void goLeft(){

        Position left = new Position(farmer.getPosFarmer().getPosX(), farmer.getPosFarmer().getPosY()-1);
        System.out.println(valuePropertyFromSet(left.getPosX(),left.getPosY()) + " GO LEFT ");
        if (left.getPosY() >= 0){
            System.out.println("x : " + left.getPosX() + "  y :" + left.getPosY());
            if (valuePropertyFromSet(farmer.getPosFarmer().getPosX(),farmer.getPosFarmer().getPosY()).contains(ParcelleValue.GRASS))
                displayGrass(farmer.getPosFarmer());
            else{
                displayTerrain(farmer.getPosFarmer());
            }
            farmer.setPosFarmer(left.getPosX(),left.getPosY());
            ferme.farmerInFarm(farmer);
        }
    }

    public void dropGrass(){
        //addValuePropertyToSet(farmer.getPosFarmer().getPosX(), farmer.getPosFarmer().getPosY(),ParcelleValue.FARMER);
        System.out.println(valuePropertyFromSet(farmer.getPosFarmer().getPosX(),farmer.getPosFarmer().getPosY()));
        if (plantGrass.getValue()){
            Position posGrass = new Position(farmer.getPosFarmer().getPosX(),farmer.getPosFarmer().getPosY());
            if (!valuePropertyFromSet(farmer.getPosFarmer().getPosX(), farmer.getPosFarmer().getPosY()).contains(ParcelleValue.GRASS)) {
                addValuePropertyToSet(farmer.getPosFarmer().getPosX(), farmer.getPosFarmer().getPosY(),ParcelleValue.GRASS);
                farmer.plantGrass(posGrass);
                displayGrass(posGrass);
            }
        }
        putFarmerInFarm();

    }
    private void displayGrass(Position pos) {
        ferme.getTerrain().setValueOnFarm(pos.getPosX(), pos.getPosY(), ParcelleValue.GRASS);
    }

    private void displayDirt(Position pos){
        ferme.getTerrain().setValueOnFarm(pos.getPosX(), pos.getPosY(), ParcelleValue.DIRT);
    }
    private void removeGrass(){
        System.out.println(valuePropertyFromSet(farmer.getPosFarmer().getPosX(),farmer.getPosFarmer().getPosY()) + " avant REMOVE GRASS");

        if (valuePropertyFromSet(farmer.getPosFarmer().getPosX(),farmer.getPosFarmer().getPosY()).contains(ParcelleValue.GRASS)){
            farmer.removeGrassAtPos(farmer.getPosFarmer());
            removeElemFromSet(farmer.getPosFarmer().getPosX(),farmer.getPosFarmer().getPosY(), ParcelleValue.GRASS);
        }
        System.out.println(valuePropertyFromSet(farmer.getPosFarmer().getPosX(),farmer.getPosFarmer().getPosY()) + " APRES REMOVE GRASS");
        displayTerrain(farmer.getPosFarmer());
        putFarmerInFarm();
    }
    private void displayTerrain(Position pos){
        displayDirt(pos);
    }
}
