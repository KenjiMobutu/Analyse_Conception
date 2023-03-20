package eu.epfc.anc3.model;

class Grass implements Element{ //BV : pas public

    public Grass(){} //BV : rien de public mis à part getType

    public ParcelleValue getType(){return ParcelleValue.GRASS;}

}
