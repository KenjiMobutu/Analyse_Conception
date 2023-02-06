package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.FermeFacade;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class MenuViewModel {
    private final FermeFacade ferme;

    public MenuViewModel(FermeFacade ferme){
        this.ferme = ferme;
        configNameHandlers();
        //configLogicStatus();
    }

    private void configNameHandlers() {

    }

    public ReadOnlyStringProperty startLabelProperty() {
        return new SimpleStringProperty("Démarrer");
    }

    public ReadOnlyStringProperty plantLabelProperty() {
        return new SimpleStringProperty("Planter du gazon");
    }
    public ReadOnlyStringProperty unPlantLabelProperty() {
        return new SimpleStringProperty("Tondre du gazon");
    }

    public void start() {
        ferme.start();

    }
    public void newGame() {
        //ferme.start();
        ferme.newGame();
    }
    public ReadOnlyBooleanProperty isFermeStartableProperty() {
        return ferme.isStartableProperty();
    }
    public ReadOnlyBooleanProperty isFermeInProgressProperty() {
        return ferme.isInProgressProperty();
    }


}
