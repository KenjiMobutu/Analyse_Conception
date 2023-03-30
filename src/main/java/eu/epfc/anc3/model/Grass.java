package eu.epfc.anc3.model;

class Grass implements Element{ //BV : pas public

    Grass(){} //BV : rien de public mis à part getType

    public ParcelleValue getType(){return ParcelleValue.GRASS;}

    @Override
    public String toString() {
        return "Grass ; ";
    }

    Grass(Position p){} //K:pour debug
}
