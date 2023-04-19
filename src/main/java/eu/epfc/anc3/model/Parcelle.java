package eu.epfc.anc3.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.util.*;

class Parcelle {
    //cellule

    BooleanProperty hasVegetable = new SimpleBooleanProperty(false);
    BooleanProperty stateChange = new SimpleBooleanProperty(false);
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

    private final Comparator<Element> elementComparator = (e1, e2) -> {
        int priority1 = elementPriorityMap.getOrDefault(e1.getType(), 0);
        int priority2 = elementPriorityMap.getOrDefault(e2.getType(), 0);
        return Integer.compare(priority1, priority2);
    };


    private final ObservableSet<Element> elements = FXCollections.observableSet(
            new TreeSet<>(elementComparator)
    );

    Parcelle(){}
    Parcelle(Set<Element> elemnt){
        for (Element e : elemnt)
            addElement(e);
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
        if (e.isVegetable())
            hasVegetable.set(true);
        elements.add(e);
    }
    BooleanProperty stateChangeProperty(){
        return stateChange;
    }

    void setStateChange(boolean b){
        stateChange.set(b);
    }

    boolean hasGrass(){
        for (Element e : elements){
            if (e.getType() == ParcelleValue.GRASS)
                return true;
        }
        return false;
    }
//
    void removeElement(Element e) {
        elements.remove(e);
    }


   void removeRottenVegetables() {
        elements.removeIf(Element::isRotten);
    }

}
