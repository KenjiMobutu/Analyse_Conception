package eu.epfc.anc3.model;

import javafx.beans.property.*;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class Ferme {
    private static final Image GRASS = new Image("grass.png");
    private final ImageView imageView = new ImageView();
    private Position posFarmer = new Position() ;
    private Terrain terrain= new Terrain();
    private Parcelle parcelle = new Parcelle();

    private Farmer farmer= new Farmer() ;
    private final ObjectProperty<FermeStatus> fermeStatus = new SimpleObjectProperty<>(FermeStatus.START);

    void start(){
        terrain = new Terrain();
        fermeStatus.set(FermeStatus.STARTED);
        //newGame();
    }

    private FermeStatus status(){return this.fermeStatus.get();}
    ParcelleValue play(int line, int col){

        //Farmer farmer = new Farmer();
        farmer.setPosFarmer(line,col);
        posFarmer.setPos(line,col);
        this.farmerInFarm(farmer);

        switch (status()){
            case STARTED:
            case PLANT_GRASS: return ParcelleValue.GRASS;
            case DEPLANT_GRASS: return ParcelleValue.EMPTY; // a vérifier s'il faudrait pas faire une value deplant grass
        }
        if (terrain.play(line, col, getCurrentFarmerValue())) {
            System.out.println("CLICK LEVEL 2");
            //derniere position du farmer
            System.out.println(posFarmer.getPosX() + " : "+ posFarmer.getPosY());

            System.out.println(parcelle.getValue());
            //update nouvelle pos du farmer
            posFarmer.setPos(line,col);
            this.farmerInFarm(farmer);
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

    private ParcelleValue getCurrentFarmerValue() {
        if (status() == FermeStatus.STARTED) {
            return farmer.hasPlantedGrass() ? ParcelleValue.GRASS : ParcelleValue.FARMER;
        } else {
            return ParcelleValue.EMPTY;
        }
    }

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
//    public void setGrassInFarm(Farmer farmer) {
//        //trouver la parcelle où se trouve le fermier --> parcelle
//        //parcelle.addGrass();
//        Grass grass = new Grass(farmer.getPosFarmer().getPosX(), farmer.getPosFarmer().getPosY());
//        //plantedGrass.add(grass);
//        ImageView grassImage = new ImageView("grass.png");
//        grassImage.setPreserveRatio(true);
//        grassImage.setImage(GRASS);
//        parcelle.setValue(ParcelleValue.GRASS);
//
//       // for (Grass e : plantedGrass) {
//       //     System.out.println(e.getX() + "   " + e.getY());
//
////            parcelle.addChildren(grassImage);
////        test.setPreserveRatio(true);
////        test.setImage(FARMER);
////        getChildren().add(test);
//
//        //terrain.setValueOnFarm(farmer.getPosFarmer().getPosX(), farmer.getPosFarmer().getPosY(), ParcelleValue.GRASS);
//    }

    public void setGrassInFarm(Position position){
        System.out.println("Grass here");
        plantGrass(position);
       // int x = position.getPosX();
       // int y = position.getPosY();
        //farmerInFarm(farmer);
        //farmer.hasPlantedGrass();
    }
    public void plantGrass(Position position){
        System.out.println("PG -> : "+ "X :"+position.getPosX()+ " Y :"+position.getPosY());
        //terrain.setGrassOnFarm(grassPos.getPosX(),grassPos.getPosY(),ParcelleValue.GRASS);
//        terrain.setGrassOnFarm(position.getPosX(),position.getPosY(),ParcelleValue.GRASS);

        //je rajoute chacune des grass dans la liste
        Farmer.listOfPlantedGrass.add(new Grass(position));

        System.out.println(farmer.listOfPlantedGrass);

        for(int i = 0; i < farmer.listOfPlantedGrass.size(); ++i ){
            //
            terrain.setGrassOnFarm(farmer.listOfPlantedGrass.get(i).getPosition().getPosX(),farmer.listOfPlantedGrass.get(i).getPosition().getPosY(),ParcelleValue.GRASS);
            System.out.println("Nb Grass :" + i);
        }
        //farmerInFarm(farmer);
        farmer.hasPlantedGrass();

    }
    public Terrain getTerrain(){
        return terrain;
    }
    public ReadOnlyIntegerProperty nbGrassPlant(){return farmer.nbgrass();}

    public void setGrass(int posX, int posY, boolean b) {
        System.out.println("X :" + posX+" - "+" Y :"+posY + " ICI de L'herbe");
        //imageView.setImage(GRASS);
    }
}
