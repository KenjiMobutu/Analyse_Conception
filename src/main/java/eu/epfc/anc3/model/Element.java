package eu.epfc.anc3.model;

public interface Element {
    ParcelleValue getType();

    boolean isRotten();

    // pour pouvoir itérer plus facilement les elements d'une cell
}
