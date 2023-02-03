package eu.epfc.anc3.model;

import eu.epfc.anc3.view.FermeView;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

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
            farmer.validateWithException();
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
        farmer.setPosPlayer(up.getPosX(),up.getPosY());
        ferme.setPosFarmerInGame(farmer);
    }

    private void goDown(){

    }
    private void goRight(){

    }
    private void goLeft(){

    }

}
