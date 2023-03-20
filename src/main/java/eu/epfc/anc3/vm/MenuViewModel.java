package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.FermeFacade;
import javafx.beans.property.*;

public class MenuViewModel {
    private final FermeFacade fermeFacade;

    public MenuViewModel(FermeFacade ferme){
        this.fermeFacade = ferme;
    }

    public ReadOnlyStringProperty startLabelProperty() {
        return new SimpleStringProperty("Démarrer");
    }
    public ReadOnlyStringProperty stopLabelProperty() {return new SimpleStringProperty("Stop");}
    public ReadOnlyStringProperty plantLabelProperty() {
        return new SimpleStringProperty("Planter du gazon");
    }
    public ReadOnlyStringProperty unPlantLabelProperty() {
        return new SimpleStringProperty("Tondre du gazon");
    }
    public void start() {fermeFacade.start();}
    public void stop() {fermeFacade.stop();}
    public void plantMode(){fermeFacade.plantActivation();}
    public void unplantMode(){fermeFacade.unplantMode();}
    public ReadOnlyIntegerProperty nbGrass(){return fermeFacade.getNbGrass();}
    public void newGame() {
        fermeFacade.newGame();
    }

}
