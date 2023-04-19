package eu.epfc.anc3.model;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

class Farmer implements Element{

    private final Position posFarmer;

    public Farmer(){
        this.posFarmer = new Position(0,0);
    }
    public Farmer(Farmer farmer){
        this.posFarmer = farmer.posFarmer;
    }

    public Position getPosFarmer() {
        return posFarmer;
    }

    public void setPosFarmer(int x, int y){
        posFarmer.setX(x); posFarmer.setY(y);
    }

    public ParcelleValue getType(){return ParcelleValue.FARMER;}

    @Override
    public boolean getStateChanged() {
        return false;
    }

    @Override
    public void setStateChanged(boolean stateChanged) {

    }

    @Override
    public boolean isRotten() {
        return false;
    }

    @Override
    public boolean isVegetable() {
        return false;
    }

    @Override
    public boolean canBeFetilize() {
        return false;
    }

    @Override
    public Parcelle getParcelle() {
        return null;
    }

    @Override
    public boolean isGrass() {
        return false;
    }

    @Override
    public String toString() {return "Farmer Position : " + getPosFarmer();}

    /*-----------------POUR DEBUG-----------------------------------------------*/
    public static final SimpleListProperty<Grass> listOfPlantedGrass = new SimpleListProperty<>(FXCollections.observableArrayList());
    public ReadOnlyIntegerProperty nbgrass() {return listOfPlantedGrass.sizeProperty();
    }
    public void plantGrass(Position p ){//K:pour debug
        listOfPlantedGrass.add(new Grass(p));
    }

    public void removeGrassAtPos(Position posFarmer) {//K:pour debug
        if (hasPlantedGrass())
            listOfPlantedGrass.remove(listOfPlantedGrass.get(listOfPlantedGrass.getSize()-1));
    }

    private boolean hasPlantedGrass() {
        return !listOfPlantedGrass.isEmpty();
    }
}
