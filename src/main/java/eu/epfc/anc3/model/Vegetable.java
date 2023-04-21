package eu.epfc.anc3.model;

class Vegetable {
    private VegetableState currentState;
    private boolean hasGrass;

    public Vegetable() {
        this.hasGrass = false;
    }

    public void setState(VegetableState state) {
        this.currentState = state;
    }

    public boolean hasGrass() {
        return hasGrass;
    }

    public void setHasGrass(boolean hasGrass) {
        this.hasGrass = hasGrass;
    }

    public void nextState(VegetableState state) {
        this.currentState = state;
    }

    public VegetableState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(VegetableState currentState) {
        this.currentState = currentState;
    }

}

