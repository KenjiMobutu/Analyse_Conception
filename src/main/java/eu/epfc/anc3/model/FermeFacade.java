package eu.epfc.anc3.model;

import eu.epfc.anc3.view.FermeView;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
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
                dropGrass();
                break;
        }
    }

    private void dropGrass() {
        Position currentPos = farmer.getPosFarmer();
        ferme.setGrass(currentPos.getPosX(), currentPos.getPosY(), true);
    }



    private void goDown() {
        System.out.println("down");
        Position down = new Position(farmer.getPosFarmer().getPosX() + 1, farmer.getPosFarmer().getPosY());
        farmer.setPosFarmer(down.getPosX(), down.getPosY());
        ferme.farmerInFarm(farmer);
    }

    private void goLeft() {
        System.out.println("Left");
        Position left = new Position(farmer.getPosFarmer().getPosX(), farmer.getPosFarmer().getPosY() - 1);
        farmer.setPosFarmer(left.getPosX(), left.getPosY());
        ferme.farmerInFarm(farmer);
    }

    private void goRight() {
        System.out.println("right");
        Position right = new Position(farmer.getPosFarmer().getPosX(), farmer.getPosFarmer().getPosY() + 1);
        farmer.setPosFarmer(right.getPosX(), right.getPosY());
        ferme.farmerInFarm(farmer);
    }


    private void goUp() {
        System.out.println("up");
        Position up = new Position(farmer.getPosFarmer().getPosX()-1,farmer.getPosFarmer().getPosY());
        farmer.setPosFarmer(up.getPosX(),up.getPosY());
        ferme.farmerInFarm(farmer);
    }
}
