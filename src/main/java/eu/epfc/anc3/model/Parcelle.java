package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.ImageView;

import java.util.*;

public class Parcelle {
    //cellule
    private final ObjectProperty<ParcelleValue> value = new SimpleObjectProperty<>(ParcelleValue.EMPTY);

    private final Set<ParcelleValue> cellValue = new TreeSet<>();

    public Parcelle(){}

    ParcelleValue getValue() {
        return value.getValue();
    }

    public Set<ParcelleValue> getCellValue() {
        return cellValue;
    }

    //était boolean
    void setValue(ParcelleValue value){
        this.value.setValue(value);
        //cellValue.add(value);
    }

    ReadOnlyObjectProperty<ParcelleValue> valueProperty() {
        return value;
    }

    boolean isEmpty() {
        return value.get() == ParcelleValue.EMPTY;
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

}
