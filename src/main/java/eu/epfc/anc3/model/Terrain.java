package eu.epfc.anc3.model;

import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
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
    Terrain(Terrain terrain){
        this.matrix = terrain.matrix;
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

    public void updateAllParcelleViews() {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                //notifyParcelleView(new Position(i, j));

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
        System.out.println("notifyParcelleView 1 --> " + elements.size());
        // mettre à jour la vue pour cette position en fonction des éléments
        for(Element e : elements) {
            System.out.println("notifyParcelleView 2  --> " + e.getType());
            e.getType();
            if (e.getStateChanged()) { // vérifie si l'élément a changé d'état
                // mettre à jour la vue pour cet élément en fonction de son nouvel état
                e.setStateChanged(false); // remet l'état changé de l'élément à false
            }
        }
    }

    BooleanProperty getElementsStateProperty(int line,int col){
        return matrix[line][col].stateChangeProperty();
    }
}
