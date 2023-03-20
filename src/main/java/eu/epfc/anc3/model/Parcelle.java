package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class Parcelle {
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

    ObservableSet<ParcelleValue> getElementsType(){
        ObservableSet<ParcelleValue> pv = FXCollections.observableSet(new TreeSet<>());
        for (Element e : elements){
            pv.add(e.getType());
        }
        return pv;
    }

    void addElement(Element e) {
        elements.add(e);
    }

    void removeElement(Element e) {
        elements.remove(e);
    }

    void clear(){elements.clear();}
}
