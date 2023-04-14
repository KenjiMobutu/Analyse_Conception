package eu.epfc.anc3.model;

class Grass extends Vegetable implements Element{ //BV : pas public
    // ajouter method auto recolt après 12 jours
    Grass(){} //BV : rien de public mis à part getType

    public ParcelleValue getType(){return ParcelleValue.GRASS;}

    @Override
    public boolean isRotten() {
        return false;
    }

    @Override
    public String toString() {
        return "Grass ; ";
    }

    Grass(Position p){} //K:pour debug
    /*public VegetableState getCurrentState() {
        return getState();
    }*/
}
