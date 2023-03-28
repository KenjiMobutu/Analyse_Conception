package eu.epfc.anc3.model;

class Cabbage extends Vegetable implements Element {
    public Cabbage() {
        super();
    }

    @Override
    public ParcelleValue getType(){return ParcelleValue.CABBAGE;}
}
