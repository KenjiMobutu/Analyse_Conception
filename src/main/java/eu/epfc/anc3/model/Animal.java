package eu.epfc.anc3.model;

public class Animal {
    private AnimalState currentState;


    public Animal() {}
    public void setState(AnimalState state) {
        this.currentState = state;
    }
    public void nextState(AnimalState state) {
        this.currentState = state;
    }
    public AnimalState getCurrentState() {
        return currentState;
    }
    public void setCurrentState(AnimalState currentState) {
        this.currentState = currentState;
    }

}
