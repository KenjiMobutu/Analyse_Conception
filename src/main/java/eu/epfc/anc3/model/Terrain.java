package eu.epfc.anc3.model;

import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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

    ObservableSet<Element> getElem(int line, int col){return matrix[line][col].getElements();}

    ObservableSet<ParcelleValue> getElemType(int line, int col){return matrix[line][col].getElementsType();}

    boolean containsElementType(ParcelleValue pv, int line, int col){
        return  matrix[line][col].getElements().stream().map(e -> e.getType()).anyMatch(x -> x == pv);
    }

    public void updateAllParcelleViews() {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                notifyParcelleView(new Position(i, j));

            }
        }
    }
    public boolean containsRottenElement() {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                ObservableSet<Element> elements = matrix[i][j].getElements();
                for (Element e : elements) {
                    if (e.isRotten()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }




    public void notifyParcelleView(Position pos) {
        // récupérer la liste d'éléments dans la position donnée
        ObservableSet<Element> elements = getElem(pos.getX(), pos.getY());

        // mettre à jour la vue pour cette position en fonction des éléments
        // par exemple :
        // - actualiser les images représentant les éléments dans la position
        // - actualiser les informations de chaque élément affiché dans la position
        // - etc.
    }
}
