package eu.epfc.anc3.model;

import eu.epfc.anc3.view.ParcelleView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Ferme {
    private Position posFarmer = new Position();
    private Terrain terrain= new Terrain();
    private Parcelle parcelle = new Parcelle();
    private Grass plantedGrass;
    private Farmer farmer ;
    private final ObjectProperty<FermeStatus> fermeStatus = new SimpleObjectProperty<>(FermeStatus.START);

    void start(){
        terrain = new Terrain();
        fermeStatus.set(FermeStatus.STARTED);
        //newGame();
    }

    private FermeStatus status(){return this.fermeStatus.get();}
    ParcelleValue play(int line, int col){
        switch (status()){
            case START :
            case PLANT_GRASS: return ParcelleValue.GRASS;
            case DEPLANT_GRASS: return ParcelleValue.EMPTY;

        }

        if (terrain.play(line, col, getCurrentFarmerValue())) {
            //derniere position du farmer
            System.out.println(posFarmer.getPosX() + " : "+ posFarmer.getPosY());

            System.out.println(parcelle.getValue());
            //update nouvelle pos du farmer
            posFarmer.setPos(line,col);
            System.out.println(parcelle.getValue());
            //updateStatusAfterMove();
            //verification parcelle cliqué
            System.out.println(line+" / "+col);

            //verification update
            System.out.println(posFarmer.getPosX() + " : "+ posFarmer.getPosY());
            // terrain.getValue(line, col);

        }
        /**
         * ajout de la fonction d'action du joueur (Terrain.play)
         */
        return ParcelleValue.EMPTY;
    }

    private void updateStatusAfterMove() {

        //System.out.println(line+" / "+col);
        //posFarmer.setPos(line,col);

        //récupérer la derniere pos du farmer puis mettre cette cellule en empty
        //mettre à jour la nouvelle pos du farmer
        //posFarmer.setPos(line,col);
        //System.out.println(posFarmer.getPosX() + " : "+ posFarmer.getPosY());

    }

    private ParcelleValue getCurrentFarmerValue() {
        return status() ==  FermeStatus.STARTED ? ParcelleValue.FARMER : ParcelleValue.EMPTY;
    }

    /*private ParcelleValue getCurrentFarmerValue() {
        //return STARTED;
    }*/

    ReadOnlyObjectProperty<ParcelleValue> valueProperty(int line, int col) {
        return terrain.valueProperty(line, col);
    }
    ReadOnlyObjectProperty<FermeStatus> fermeStatusProperty() {
        return fermeStatus;
    }


    public void newGame() {
        fermeStatus.set(FermeStatus.STARTED);
    }

    public void farmerInFarm(Farmer farmer) {
        System.out.println("Farmer");
        terrain.setValueOnFarm(farmer.getPosFarmer().getPosX(),farmer.getPosFarmer().getPosY(),ParcelleValue.FARMER);
    }
    public Terrain getTerrain(){
        return terrain;
    }

    public void setGrass(int posX, int posY, boolean b) {
        setGrass(posX,posY,b);
    }
}
