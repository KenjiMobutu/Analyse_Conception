package eu.epfc.anc3.model;

class Carrot extends Vegetable implements Element {
    public Carrot() {
        super();
    }

    @Override
    public ParcelleValue getType() {
        return ParcelleValue.CARROT;
    }

    @Override
    public String toString() {
        return "Je Suis une Carotte";
    }
}


