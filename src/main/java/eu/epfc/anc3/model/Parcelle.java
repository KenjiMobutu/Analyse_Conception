package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

public class Parcelle {
    //cellule
    private final ObjectProperty<ParcelleValue> value = new SimpleObjectProperty<>(ParcelleValue.EMPTY); //BV :enlever ça, c'est doublon
    private final ObservableSet<Element> elements = FXCollections.observableSet();

    ParcelleValue getValue() {
        return value.getValue();
    }

    void setValue(ParcelleValue value){
        this.value.setValue(value);
    }

    ObjectProperty<ParcelleValue> valueProperty() {
        return value;
    }

    boolean isEmpty() {
        return value.get() == ParcelleValue.EMPTY;
    }

    ObservableSet<Element> getElements() {
        return elements;
    }

    void addElement(Element e) {
        elements.add(e);
    }

    void removeElement(Element e) {
        elements.remove(e);
    }

    void clear(){elements.clear();}
}
