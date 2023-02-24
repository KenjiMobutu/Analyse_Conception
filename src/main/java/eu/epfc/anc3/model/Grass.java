package eu.epfc.anc3.model;

import java.util.Objects;

public class Grass {
    private Position pos;

    public Grass(Position p){
        this.pos = p;
    }

    @Override
    public String toString() {
        return this.pos.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grass grass = (Grass) o;
        return Objects.equals(pos, grass.pos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos);
    }
}
