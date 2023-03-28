package eu.epfc.anc3.model;

public class State1 extends VegetableState {
    public State1(Vegetable vegetable) {
        super(vegetable);
    }

    @Override
    public void nextState() {

    }

    @Override
    public boolean isRipe() {
        return false;
    }

    @Override
    public boolean isRotten() {
        return false;
    }

    @Override
    public int getHarvestPoints() {
        return 0;
    }

    @Override
    public void nextDay() {

    }
}
