package eu.epfc.anc3.model;

class Dirt implements Element{
    @Override
    public ParcelleValue getType() {
        return ParcelleValue.DIRT;
    }

    @Override
    public boolean isRotten() {
        return false;
    }

}
