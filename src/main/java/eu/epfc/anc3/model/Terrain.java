package eu.epfc.anc3.model;

import javafx.beans.property.BooleanProperty;
import javafx.collections.ObservableSet;

class Terrain {
    //grid

    static final int GRID_HEIGHT = 15;
    static final int GRID_WIDTH = 25;

    private final Parcelle[][] matrix;


    Terrain() {
        matrix = new Parcelle[GRID_HEIGHT][];
        for (int i = 0; i < GRID_HEIGHT; ++i) {
            matrix[i] = new Parcelle[GRID_WIDTH];
            for (int j = 0; j < GRID_WIDTH; ++j) {
                matrix[i][j] = new Parcelle();
            }
        }
    }

    void resetTerrain() {
        for (int i = 0; i < GRID_HEIGHT; ++i) {
            for (int j = 0; j < GRID_WIDTH; ++j) {
                matrix[i][j].getElements().clear();
            }
        }
    }

    void addElementToCell(Element e, int line, int col) {
        matrix[line][col].addElement(e);
    }
    void removeElement(ParcelleValue pv , int line, int col){
         matrix[line][col].getElements().removeIf(e -> e.getType() == pv);
    }
    void removeVegetables(Element e, int line, int col){

        matrix[line][col].getElements().remove(e);
    }

    Parcelle getParcelle(int line, int col) {
        return matrix[line][col];
    }
    ObservableSet<Element> getElem(int line, int col){return matrix[line][col].getElements();}

    ObservableSet<ParcelleValue> getElemType(int line, int col){return matrix[line][col].getElementsType();}

    boolean containsElementType(ParcelleValue pv, int line, int col){
        return  matrix[line][col].getElements().stream().map(e -> e.getType()).anyMatch(x -> x == pv);
    }

    BooleanProperty getElementsStateProperty(int line,int col){
        return matrix[line][col].stateChangeProperty();
    }

}
