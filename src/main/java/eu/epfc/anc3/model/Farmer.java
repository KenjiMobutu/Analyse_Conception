package eu.epfc.anc3.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

class Farmer {

    private final Position posFarmer;
    private final StringProperty fermier = new SimpleStringProperty("");
    public Farmer(){
        this.posFarmer = new Position(0,0);
    }

    public Position getPosFarmer() {
        return posFarmer;
    }
    @Override
    public String toString() {
        return "Farmer{" +
                "posFarmer=" + posFarmer +
                ", fermier=" + fermier +
                '}';
    }
    public void setPosFarmer(int x, int y){
        posFarmer.setPosX(x); posFarmer.setPosY(y);
    }
}
