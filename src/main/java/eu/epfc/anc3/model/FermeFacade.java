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
        }
    }



    public void moveFarmer(Controls controls){
        switch (controls){
            case UP :
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
                break;
        }
    }

    private void goUp(){
        Position up = new Position(farmer.getPosFarmer().getPosX()-1, farmer.getPosFarmer().getPosY());
        // action a verifier genre s'il veut poser de l'herbe ?
        //si l'action est faisable dans la matrice?
        if (up.getPosX() >= 0){
            farmer.setPosPlayer(up.getPosX(),up.getPosY());
            ferme.setPosFarmerInGame(farmer);
        }
    }

    private void goDown(){
        Position down = new Position(farmer.getPosFarmer().getPosX()+1, farmer.getPosFarmer().getPosY());
        //action ?
        if (down.getPosX() < GRID_HEIGHT){
            farmer.setPosPlayer(down.getPosX(),down.getPosY());
            ferme.setPosFarmerInGame(farmer);
        }

    }
    private void goRight(){
        Position right = new Position(farmer.getPosFarmer().getPosX(), farmer.getPosFarmer().getPosY()+1);
        //action ?
        if (right.getPosY() < GRID_WIDTH){
            farmer.setPosPlayer(right.getPosX(),right.getPosY());
            ferme.setPosFarmerInGame(farmer);
        }
    }
    private void goLeft(){
        Position left = new Position(farmer.getPosFarmer().getPosX(), farmer.getPosFarmer().getPosY()-1);
        //action ?
        if (left.getPosY() >= 0){
            farmer.setPosPlayer(left.getPosX(),left.getPosY());
            ferme.setPosFarmerInGame(farmer);
        }
    }

    public ReadOnlyObjectProperty<ParcelleValue> valueProperty(int line,int col){return ferme.valueProperty(line, col);}

}
