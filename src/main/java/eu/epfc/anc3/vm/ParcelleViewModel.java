package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.Element;
import eu.epfc.anc3.model.FermeFacade;
import eu.epfc.anc3.model.ParcelleValue;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableSet;

import java.util.Set;


public class ParcelleViewModel {
    //cell
    private final int line, col;
    private final FermeFacade fermeFacade;
    public ParcelleViewModel(int line, int col, FermeFacade ferme){
        this.col = col;
        this.line = line;
        this.fermeFacade = ferme;
    }




    public ReadOnlyBooleanProperty isStarted(){return fermeFacade.isStartedProperty();}

    // a refaire : public ReadOnlyObjectProperty<ParcelleValue> elementInCell(){return fermeFacade.valueProperty(line,col);}
    public ObservableSet<ParcelleValue> elementPropertyValue () {return fermeFacade.getElementsType(line,col);}
    public ObservableSet<Element> getElementsInCell(){return fermeFacade.getElements(line,col);}
    public void play() {
        System.out.println(isStarted());
        System.out.println(line +" ----- "+ col);
        fermeFacade.teleport(line, col);
    }
}
