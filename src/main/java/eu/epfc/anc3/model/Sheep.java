package eu.epfc.anc3.model;

public class Sheep implements Element{
    private Parcelle parcelle ;

    public Sheep(Parcelle parcelle) {
        setParcelle(parcelle);
    }

    private void setParcelle(Parcelle parcelle) {
        this.parcelle = parcelle;
    }

    @Override
    public ParcelleValue getType() {
        return ParcelleValue.SHEEP;
    }

    @Override
    public boolean getStateChanged() {
        return false;
    }

    @Override
    public void setStateChanged(boolean stateChanged) {

    }

    @Override
    public boolean isRotten() {return false;}

    @Override
    public boolean isVegetable() {return false;}

    @Override
    public boolean canBeFetilize() {return false;}

    @Override
    public Parcelle getParcelle() {
        return parcelle;
    }

    @Override
    public boolean isGrass() {return false;}

    @Override
    public String toString() {
        return "Mouton";
    }
}
