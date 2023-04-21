package eu.epfc.anc3.model;

public interface Element {
    ParcelleValue getType();
    boolean getStateChanged();
    void setStateChanged(boolean stateChanged);
    boolean isRotten();
    boolean isVegetable();
    boolean canBeFetilize();
    Parcelle getParcelle();
    boolean isGrass();

    // pour pouvoir itérer plus facilement les elements d'une cell
}
