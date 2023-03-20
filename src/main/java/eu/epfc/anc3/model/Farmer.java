package eu.epfc.anc3.model;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;

class Farmer implements Element{

    private final Position posFarmer;

    public Farmer(){
        this.posFarmer = new Position(0,0);
    }

    public Position getPosFarmer() {
        return posFarmer;
    }

    public void setPosFarmer(int x, int y){
        posFarmer.setX(x); posFarmer.setY(y);
    }

    public ParcelleValue getType(){return ParcelleValue.FARMER;}

    @Override
    public String toString() {return "Farmer Position : " + getPosFarmer();}


}
