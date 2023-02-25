package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.FermeFacade;
import javafx.beans.property.*;

public class MenuViewModel {
    private final FermeFacade fermeFacade;

    public MenuViewModel(FermeFacade ferme){
        this.fermeFacade = ferme;
        configNameHandlers();
        //configLogicStatus();

    }

    private void configNameHandlers() {

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
    public void plantMode(){fermeFacade.plantMode();}
    public void unplantMode(){fermeFacade.unplantMode();}
    public ReadOnlyIntegerProperty nbGrass(){return fermeFacade.getNbGrass();}

    public void newGame() {
        //fermeFacade.start();
        fermeFacade.newGame();
    }

//    public ReadOnlyBooleanProperty isFermeStartableProperty() {
//        return fermeFacade.isStartableProperty();
//    }
//    public ReadOnlyBooleanProperty canBeStopProperty() {return fermeFacade.isStartedProperty();}
//    public ReadOnlyBooleanProperty isFermeInProgressProperty() {
//        return fermeFacade.isInProgressProperty();
//    }
//    public ReadOnlyBooleanProperty isPlantedGrassPossibleProperty() {return fermeFacade.isPlantProperty();}
//    public void setPlantGrass(boolean b) {fermeFacade.setPlantGrass(b);}
//    public ReadOnlyBooleanProperty isUnplantedGrassPossibleProperty() {return fermeFacade.isDeplantProperty();}
//    public void setUnplantGrass(boolean b) {fermeFacade.setDeplantGrass(b);}

}
