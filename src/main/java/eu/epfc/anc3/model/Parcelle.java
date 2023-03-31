package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.util.*;

class Parcelle {
    //cellule

    private final Map<ParcelleValue, Integer> elementPriorityMap = new HashMap<>();{
        elementPriorityMap.put(ParcelleValue.DIRT, 1);
        elementPriorityMap.put(ParcelleValue.GRASS, 2);
        elementPriorityMap.put(ParcelleValue.CARROT, 3);
        elementPriorityMap.put(ParcelleValue.CABBAGE, 4);
        elementPriorityMap.put(ParcelleValue.FARMER, 5);
    }
    private final Comparator<Element> elementComparator = new Comparator<>() {
        @Override
        public int compare(Element e1, Element e2) {
            if (e1.getType() == ParcelleValue.FARMER) {
                return 1; // farmer devant tous les éléments
            } else if (e2.getType() == ParcelleValue.FARMER) {
                return -1;
            } else if (e1.getType() == ParcelleValue.DIRT) {
                return 1; // la dirt devrait se trouver en dernier dans la liste
            } else if (e2.getType() == ParcelleValue.DIRT) {
                return -1;
            } else if (e1.getType() == ParcelleValue.GRASS) { // si dans la cellule il y a de l'herbe
                if (e2.getType() == ParcelleValue.CARROT || e2.getType() == ParcelleValue.CABBAGE) {
                    return -1; // je retourne les 'vegetables' après la grass
                } else {
                    return 1;
                }
            } else if (e2.getType() == ParcelleValue.GRASS) { // si on dépose de l'herbe après avoir déposer un 'vegetables' l'herbe doit se trouver derrière.
                if (e1.getType() == ParcelleValue.CARROT || e1.getType() == ParcelleValue.CABBAGE) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (e1.getType() == ParcelleValue.CARROT || e1.getType() == ParcelleValue.CABBAGE) { // fais en sorte que les légumes > dirt/grass
                if (e2.getType() == ParcelleValue.GRASS) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                return 0; // same element
            }
            /*int priority1 = elementPriorityMap.getOrDefault(e1.getType(), 0);
            int priority2 = elementPriorityMap.getOrDefault(e2.getType(), 0);
            return Integer.compare(priority1, priority2);*/
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
//
//    void removeElement(Element e) {
//        elements.remove(e);
//    }
//
//    void clear(){elements.clear();}
}
