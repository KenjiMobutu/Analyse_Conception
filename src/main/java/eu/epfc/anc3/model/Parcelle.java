package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.util.*;

class Parcelle {
    //cellule
    private final ObservableSet<Element> elements = FXCollections.observableSet();


    ObservableSet<Element> getElements() {
        List<Element> sortedElements = new ArrayList<>(elements);
        Collections.sort(sortedElements, new Comparator<Element>() {
            @Override
            public int compare(Element e1, Element e2) {
                if (e1.getType() == ParcelleValue.FARMER) {
                    return -1; // e1 comes first
                } else if (e2.getType() == ParcelleValue.FARMER) {
                    return 1; // e2 comes first
                } else {
                    return 0; // no preference
                }
            }
        });
        return FXCollections.observableSet(new LinkedHashSet<>(sortedElements));
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
