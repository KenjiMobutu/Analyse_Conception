package eu.epfc.anc3.model;

import eu.epfc.anc3.view.FermeView;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;

import static eu.epfc.anc3.model.Terrain.GRID_WIDTH;
import static eu.epfc.anc3.model.Terrain.GRID_HEIGHT;
public class FermeFacade {

    private final Farmer farmer = new Farmer();

    private final Ferme ferme = new Ferme();

    //vérif si le jeu est pas démarré mais peut l'être
    private final BooleanProperty isStartable = new SimpleBooleanProperty(false);

    //jeu démarré est en cours
    private final BooleanProperty isStarted = new SimpleBooleanProperty(false);

    //jeu en cours
    private final BooleanProperty isInProgress = new SimpleBooleanProperty(false);


    public FermeFacade(){
        isStartable.bind(ferme.fermeStatusProperty().isEqualTo(FermeStatus.START));

        isStarted.bind(ferme.fermeStatusProperty().isNotEqualTo(FermeStatus.START));

        isInProgress.bind(ferme.fermeStatusProperty()
                .isEqualTo(FermeStatus.STARTED)
                .or(ferme.fermeStatusProperty().isEqualTo(FermeStatus.PLANT_GRASS))
                .or(ferme.fermeStatusProperty().isEqualTo(FermeStatus.DEPLANT_GRASS)));
    }
    public static int gridWidth() {
        return Terrain.GRID_WIDTH;
    }

    public ReadOnlyBooleanProperty isStartedProperty() {
        return isStarted;
    }

    public void start() {
        if (isStartable.get()) {
            ferme.start();
            farmerInFarm();
            System.out.println("start");

        }
    }



    public void moveFarmer(Controls controls){
        switch (controls){
            case UP :
            //ajouter validation si demandé ultérieurement
            ferme.start();
            farmerInFarm();
            System.out.println("START");
        }
    }

    public ParcelleValue play(int line, int col) {
        return ferme.play(line,col);
    }

    public ReadOnlyObjectProperty<ParcelleValue> valueProperty(int line, int col) {
        return ferme.valueProperty(line, col);
    }
    public void farmerInFarm(){
        ferme.farmerInFarm(farmer);
    }
    public void newGame() {
        ferme.newGame();
    }

    public ReadOnlyBooleanProperty isStartableProperty() {
        return isStartable;
    }

    public ReadOnlyBooleanProperty isInProgressProperty() {
        return isInProgress;
    }

    public void moveFarmer(Move move) {
        switch (move){
            case UP:
                goUp();
                break;
            case DOWN:
                goDown();
                break;
            case LEFT:
                goLeft();
                break;
            case RIGHT:
                goRight();
                break;
            case SPACE:
                //action
                dropGrass();
                break;
        }
    }

    private void goUp(){
        Position up = new Position(farmer.getPosFarmer().getPosX()-1, farmer.getPosFarmer().getPosY());
        // action a verifier genre s'il veut poser de l'herbe ?
        //si l'action est faisable dans la matrice?
        if (up.getPosX() >= 0){
            System.out.println("x : " + up.getPosX() + "  y :" + up.getPosY());
            farmer.setPosFarmer(up.getPosX(),up.getPosY());
            ferme.farmerInFarm(farmer);
        }
    }

    private void goDown(){
        Position down = new Position(farmer.getPosFarmer().getPosX()+1, farmer.getPosFarmer().getPosY());
        //action ?
        if (down.getPosX() < GRID_HEIGHT){
            System.out.println("x : " + down.getPosX() + "  y :" + down.getPosY());
            farmer.setPosFarmer(down.getPosX(),down.getPosY());
            ferme.farmerInFarm(farmer);
        }

    }
    private void goRight(){
        Position right = new Position(farmer.getPosFarmer().getPosX(), farmer.getPosFarmer().getPosY()+1);

        //action ?
        if (right.getPosY() <= GRID_WIDTH){
            System.out.println("x : " + right.getPosX() + "  y :" + right.getPosY());
            farmer.setPosFarmer(right.getPosX(),right.getPosY());
            ferme.farmerInFarm(farmer);
        }
    }
    private void goLeft(){
        Position left = new Position(farmer.getPosFarmer().getPosX(), farmer.getPosFarmer().getPosY()-1);
        //action ?
        if (left.getPosY() >= 0){
            System.out.println("x : " + left.getPosX() + "  y :" + left.getPosY());

            farmer.setPosFarmer(left.getPosX(),left.getPosY());
            ferme.farmerInFarm(farmer);
        }
    }
    private void dropGrass() {
        Position currentPos = farmer.getPosFarmer();
        ferme.setGrass(currentPos.getPosX(), currentPos.getPosY(), true);
    }
}
