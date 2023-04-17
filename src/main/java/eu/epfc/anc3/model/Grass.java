package eu.epfc.anc3.model;

class Grass extends Vegetable implements Element{ //BV : pas public
    // ajouter method auto recolt après 12 jours
    int maxGrowthDays = 12;
    int nbJours;
    private Parcelle parcelle;
    Grass(Parcelle parcelle){
        nbJours = 0;
        setParcelle(parcelle);

    } //BV : rien de public mis à part getType

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
        if(nbJours == maxGrowthDays)
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
