package eu.epfc.anc3.model;

// Classe concrète représentant l'état "germination"
public class SeedlingState extends VegetableState {
    public SeedlingState(Vegetable vegetable) {
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

    public void grow() {
        vegetable.setState(new VegetativeState(vegetable));
    }

    public void harvest() {
        // Impossible de récolter une plante en germination
    }
}
