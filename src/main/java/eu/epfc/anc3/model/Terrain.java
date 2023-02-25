package eu.epfc.anc3.model;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Terrain {
    //grid

    static final int GRID_HEIGHT = 15;
    static final int GRID_WIDTH = 25;

    private final Parcelle[][] matrix;

    private final Set<Element>[][] elementsInCell;

    Terrain() {
        matrix = new Parcelle[GRID_HEIGHT][];
        elementsInCell = new TreeSet[GRID_HEIGHT][];
        for (int i = 0; i < GRID_HEIGHT; ++i) {
            matrix[i] = new Parcelle[GRID_WIDTH];
            elementsInCell[i] = new TreeSet[GRID_WIDTH];
            for (int j = 0; j < GRID_WIDTH; ++j) {
                matrix[i][j] = new Parcelle();
                elementsInCell[i][j] = new TreeSet<>();
            }
        }
    }

    void resetTerrain() {
        for (int i = 0; i < GRID_HEIGHT; ++i) {
            for (int j = 0; j < GRID_WIDTH; ++j) {
                matrix[i][j].setValue(ParcelleValue.DIRT);
                elementsInCell[i][j].clear();
            }
        }
    }



    ReadOnlyObjectProperty<ParcelleValue> valueProperty(int line, int col) {
        return matrix[line][col].valueProperty();
    }


    Set<Element> getAllElementsInCellFromSet(int line, int col) {
        return elementsInCell[line][col];
    }

    void addElementToCell(Element e, int line, int col) {
        if (!elementsInCell[line][col].contains(e))
            elementsInCell[line][col].add(e);
    }

    void removeElemFromCell(int l, int c, Element e){
        elementsInCell[l][c].remove(e);
        System.out.println(elementsInCell[l][c]);
    }

    ParcelleValue getValue(int line, int col) {
        return matrix[line][col].getValue();
    }

    //etait boolean avant
    void play(int line, int col, ParcelleValue playerValue) {
        matrix[line][col].setValue(playerValue);
    }

    private ParcelleValue checkCell(int line, int column){
        ParcelleValue res = matrix[line][column].getValue();
        if (res == ParcelleValue.EMPTY)return res;
        return ParcelleValue.GRASS;
    }



    boolean isFull() {
        for(int i = 0; i < GRID_WIDTH; ++i)
            for(int j = 0; j < GRID_WIDTH; ++j)
                if(matrix[i][j].isEmpty())
                    return false;
        return true;
    }

    public void setValueOnFarm(int x, int y, ParcelleValue value) {
        matrix[x][y].setValue(value);
    }


    public void setGrassOnFarm(int x, int y, ParcelleValue value) {
        matrix[x][y].setValue(value);

    }
}
