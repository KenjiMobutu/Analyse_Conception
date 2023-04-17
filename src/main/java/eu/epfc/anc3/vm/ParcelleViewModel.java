package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.Element;
import eu.epfc.anc3.model.FermeFacade;
import eu.epfc.anc3.model.ParcelleValue;
import eu.epfc.anc3.model.VegetableState;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableSet;

import java.util.Set;


public class ParcelleViewModel {
    //cell
    // remettre en private quand tout sera correcte (mettre en public si on veut débug les elements dans une cellule
    //puis remettre les lignes nécessaire dans le fichier ParcelleView.
    private final int line, col;
    private final FermeFacade fermeFacade;
    private BooleanProperty isReadyForNextState = new SimpleBooleanProperty(false);
    public ParcelleViewModel(int line, int col, FermeFacade ferme){
        this.col = col;
        this.line = line;
        this.fermeFacade = ferme;
    }
    public BooleanProperty isReadyForNextStateProperty() {
        return isReadyForNextState;
    }
    public void setIsReadyForNextState(Boolean b){isReadyForNextState.set(b);}

    public void listener(){
        if (fermeFacade.containsVegetable(line,col)){
            // doit bind ce bool a la con sur le stateChanged. gl hf pouet pouet
            //isReadyForNextState.bind();
        }
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
