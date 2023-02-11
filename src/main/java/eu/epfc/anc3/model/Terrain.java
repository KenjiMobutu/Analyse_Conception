package eu.epfc.anc3.model;

import javafx.beans.property.ReadOnlyObjectProperty;

public class Terrain {
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
    ReadOnlyObjectProperty<ParcelleValue> valueProperty(int line, int col) {
        return matrix[line][col].valueProperty();
    }

    ParcelleValue getValue(int line, int col) {
        return matrix[line][col].getValue();
    }

//    boolean play(int line, int col, ParcelleValue playerValue) {
//        return matrix[line][col].setValue(playerValue);
//    }
//    public void setValueOnTerrain(int x, int y, ParcelleValue parcelleValue) {
//        matrix[x][y].setValue(parcelleValue);
//    }
    /*boolean play(int line, int col, ParcelleValue playerValue) {
        return matrix[line][col].setValue(playerValue);
    }*/
    boolean play(int line, int col, ParcelleValue playerValue) {
       return matrix[line][col].setValue(playerValue);
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

    public boolean setValueOnFarm(int x, int y, ParcelleValue value) {
       return matrix[x][y].setValue(value);
    }
}
