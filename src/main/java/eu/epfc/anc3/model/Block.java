package eu.epfc.anc3.model;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

class Block implements Element{

    private Parcelle parcelle ;

    public Block(){
    }

    public ParcelleValue getType(){return ParcelleValue.BLOCK;}

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
    public boolean isBlock() {
        return true;
    }

}
