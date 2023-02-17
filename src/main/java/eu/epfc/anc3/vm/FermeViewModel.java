package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.Controls;
import eu.epfc.anc3.model.FermeFacade;
import eu.epfc.anc3.model.Move;
import eu.epfc.anc3.view.TerrainView;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;

public class FermeViewModel {
    private final MenuViewModel menuViewModel;
    private final TerrainViewModel terrainViewModel;
    private final FermeFacade ferme = new FermeFacade();

    public MenuViewModel getMenuViewModel() {
        return menuViewModel;
    }
    public TerrainViewModel getTerrainViewModel() {
        return terrainViewModel;
    }

    public ReadOnlyStringProperty titleProperty() {
        return new SimpleStringProperty("Farm");
    }

    public ReadOnlyBooleanProperty isFermeStartedProperty() {
        return ferme.isStartedProperty();
    }

    public FermeViewModel(){
        menuViewModel = new MenuViewModel(ferme);
        terrainViewModel = new TerrainViewModel(ferme);
    }

    public void action(Controls control){
        ferme.moveFarmer(control);
    }

    public void keyPressed(Move move) {
        ferme.moveFarmer(move);

    }
}
