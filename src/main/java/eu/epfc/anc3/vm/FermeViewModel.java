package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.FermeFacade;
import eu.epfc.anc3.model.Move;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.input.KeyEvent;

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
    public boolean isSpacePressed() {
        return fermeFacade.isSpacePressed();
    }
    public void setSpacePressed(boolean b){fermeFacade.setSpacePressedProperty(b);}
    public FermeViewModel(){
        menuViewModel = new MenuViewModel(fermeFacade);
        terrainViewModel = new TerrainViewModel(fermeFacade);
    }

    public void keyPressed(Move move) {
        fermeFacade.moveFarmer(move);
    }

    public void keyReleased(KeyEvent event) {
        fermeFacade.handleKeyReleased(event);
    }
}
