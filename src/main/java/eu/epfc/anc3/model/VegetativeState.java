package eu.epfc.anc3.model;

public class VegetativeState extends VegetableState{

    public VegetativeState(Vegetable vegetable) {
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
}
