package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.FermeFacade;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;

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
    public void plantCabbageMode() {
        fermeFacade.plantCabbageMode();
    }
    public void plantCarottMode() {
        fermeFacade.plantCarrotMode();
    }
    public void fertilizerMode() {
        fermeFacade.fertilizerMode();
    }
    public void recoltMode() {
        fermeFacade.recoltMode();
    }
    public void unplantMode(){fermeFacade.unplantMode();}
    public void newGame() {
        fermeFacade.newGame();
    }







    /*public void saveGame() {
        fermeFacade.saveGame();
    }*/

}
