package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.util.*;

class Parcelle {
    //cellule


    private final Comparator<Element> elementComparator = new Comparator<>() {
        @Override
        public int compare(Element e1, Element e2) {
            if (e1.getType() == ParcelleValue.FARMER && e2.getType() == ParcelleValue.GRASS) {
                return -1; // farmer always first
            } else if (e2.getType() == ParcelleValue.FARMER) {
                return 1; // farmer always first
            } else if (e1.getType() == ParcelleValue.DIRT) {
                return 1; // dirt always last
            } else if (e2.getType() == ParcelleValue.DIRT) {
                return -1; // dirt always last
            } else if (e1.getType() == ParcelleValue.GRASS) {
                return -1; // grass before vegetables
            } else if (e2.getType() == ParcelleValue.GRASS) {
                return 1; // grass before vegetables
            } /*else if (e1.getType() == ParcelleValue.VEGETABLE) {
                return 1; // vegetables cannot be stacked, last before dirt
            } else if (e2.getType() == ParcelleValue.VEGETABLE) {
                return -1; // vegetables cannot be stacked, last before dirt
            }*/ else {
                return 0; // same element type
            }
        }
    };

    private final ObservableSet<Element> elements = FXCollections.observableSet(
            new TreeSet<>(elementComparator)
    );



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
