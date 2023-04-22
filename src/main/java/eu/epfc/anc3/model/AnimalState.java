package eu.epfc.anc3.model;

abstract class AnimalState {
    protected Animal animal;

    public AnimalState(Animal animal) {
        this.animal = animal;
    }
    public abstract void nextState();

    public abstract void nextDay();

    public abstract ParcelleValue getType();
}
