package eu.epfc.anc3.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

class Grass extends Vegetable implements Element{ //BV : pas public
    // ajouter method auto recolt après 12 jours
    IntegerProperty maxGrowthDays = new SimpleIntegerProperty(0);
    IntegerProperty nbJours = new SimpleIntegerProperty(0);
    int nbJoursSinceStart ;
    private Parcelle parcelle;
    Grass(Parcelle parcelle){
        nbJoursSinceStart = 0;
        maxGrowthDays.set(nbJours.getValue() + 12);
        nbJours.addListener((obs, oldVal, newVal) -> {
            System.out.println("++day ------|>" +  nbJoursSinceStart);
            this.nextDay();
        });
        setParcelle(parcelle);

    } //BV : rien de public mis à part getType


    void nextDay(){
        ++nbJoursSinceStart;
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
        return nbJoursSinceStart == maxGrowthDays.getValue() + 1;
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
        return true;
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
