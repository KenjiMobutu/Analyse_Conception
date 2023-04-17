package eu.epfc.anc3.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

class Grass extends Vegetable implements Element{ //BV : pas public
    // ajouter method auto recolt après 12 jours
    int maxGrowthDays = 12;
    IntegerProperty nbJours = new SimpleIntegerProperty(0);
    private Parcelle parcelle;
    Grass(Parcelle parcelle){
        nbJours.set(0);
        nbJours.addListener((obs, oldVal, newVal) -> {
            System.out.println("++day");
            this.nextDay();
        });
        setParcelle(parcelle);

    } //BV : rien de public mis à part getType

    IntegerProperty nbJoursProperty(){return nbJours;}
    void nextDay(){
        isRotten();
    }
    void setParcelle(Parcelle parcelle){
        this.parcelle = parcelle;
    }
    public ParcelleValue getType(){return ParcelleValue.GRASS;}

    @Override
    public boolean getStateChanged() {
        return false;
    }

    @Override
    public void setStateChanged(boolean stateChanged) {

    }

    @Override
    public boolean isRotten() {
        if(nbJours.getValue().equals(maxGrowthDays))
            return true;
        else
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
    public String toString() {
        return "Grass ; ";
    }

    Grass(Position p){} //K:pour debug
    /*public VegetableState getCurrentState() {
        return getState();
    }*/
}
