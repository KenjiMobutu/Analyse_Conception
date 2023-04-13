package eu.epfc.anc3.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.util.*;

class Parcelle {
    //cellule

    private final Map<ParcelleValue, Integer> elementPriorityMap = new HashMap<>();

    {
        elementPriorityMap.put(ParcelleValue.DIRT, 1);
        elementPriorityMap.put(ParcelleValue.GRASS, 2);
        elementPriorityMap.put(ParcelleValue.CARROT1, 3);
        elementPriorityMap.put(ParcelleValue.CARROT2, 3);
        elementPriorityMap.put(ParcelleValue.CARROT3, 3);
        elementPriorityMap.put(ParcelleValue.CARROT4, 3);
        elementPriorityMap.put(ParcelleValue.ROTTEN_CARROT, 3);

        elementPriorityMap.put(ParcelleValue.CABBAGE1, 3);
        elementPriorityMap.put(ParcelleValue.CABBAGE2, 3);
        elementPriorityMap.put(ParcelleValue.CABBAGE3, 3);
        elementPriorityMap.put(ParcelleValue.CABBAGE4, 3);
        elementPriorityMap.put(ParcelleValue.ROTTEN_CABBAGE, 3);
        elementPriorityMap.put(ParcelleValue.FARMER, 4);

    }

    private BooleanProperty refresh = new SimpleBooleanProperty(false);

    private final Comparator<Element> elementComparator = new Comparator<>() {
        @Override
        public int compare(Element e1, Element e2) {


            int priority1 = elementPriorityMap.getOrDefault(e1.getType(), 0);
            int priority2 = elementPriorityMap.getOrDefault(e2.getType(), 0);
            return Integer.compare(priority1, priority2);
        }
    };


    private final ObservableSet<Element> elements = FXCollections.observableSet(
            new TreeSet<>(elementComparator)
    );

    BooleanProperty refreshProperty(){return refresh;}

    void setRefresh(Boolean b){refresh.set(b);}


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
//
//    void removeElement(Element e) {
//        elements.remove(e);
//    }
//
//    void clear(){elements.clear();}
}
