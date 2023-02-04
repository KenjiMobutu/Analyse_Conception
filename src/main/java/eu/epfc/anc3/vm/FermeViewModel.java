package eu.epfc.anc3.vm;

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
    public ReadOnlyStringProperty titleProperty() {
        return new SimpleStringProperty("Farm");
    }

    public ReadOnlyBooleanProperty isFermeStartedProperty() {
        return ferme.isStartedProperty();
    }

    public TerrainViewModel getTerrainViewModel() {
        return terrainViewModel;
    }
    public FermeViewModel(){
        menuViewModel = new MenuViewModel(ferme);
        terrainViewModel = new TerrainViewModel(ferme);

    }
    public void keyPressed(Move move) {
        //ferme.moveFarmer(move);
    }
}
