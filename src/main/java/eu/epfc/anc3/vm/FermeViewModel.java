package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.Controls;
import eu.epfc.anc3.model.FermeFacade;
import eu.epfc.anc3.model.Grass;
import eu.epfc.anc3.model.Move;
import eu.epfc.anc3.view.TerrainView;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class FermeViewModel {
    private final MenuViewModel menuViewModel;
    private final TerrainViewModel terrainViewModel;
    private final FermeFacade fermeFacade = new FermeFacade();

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
        return fermeFacade.isStartedProperty();
    }

    public FermeViewModel(){
        menuViewModel = new MenuViewModel(fermeFacade);
        terrainViewModel = new TerrainViewModel(fermeFacade);
    }


    public void action(Controls control){
        fermeFacade.moveFarmer(control);
    }


    public void keyPressed(Move move) {
        fermeFacade.moveFarmer(move);
    }
}
