package eu.epfc.anc3.model;

public class Grass {

    private final Position position;

    public Grass(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Grass at " + "X:"+position.getPosX() +
                "  Y:"+position.getPosY() ;
    }
}
