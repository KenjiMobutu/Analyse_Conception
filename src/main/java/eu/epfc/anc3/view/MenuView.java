package eu.epfc.anc3.view;


import eu.epfc.anc3.vm.MenuViewModel;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static eu.epfc.anc3.view.FermeView.MENU_WIDTH;
import static eu.epfc.anc3.view.FermeView.PADDING;

public class MenuView extends VBox {
    // Composants du "menu"
    private final Label nbGrassTxt = new Label("Nombre de parcelles de gazon : ");
    private final TextField nbGrass= new TextField();
    private final Button startButton = new Button();
    private final Button plantButton = new Button();
    private final Button unPlantButton = new Button();
    HBox nbHerb = new HBox(nbGrassTxt,nbGrass);
    HBox buttons = new HBox(startButton,plantButton,unPlantButton);
    private final MenuViewModel menuViewModel;
    public MenuView(MenuViewModel menuViewModel) {
        this.menuViewModel = menuViewModel;
        configMenu();
        configButtons();
        manageStartButton();
    }

    private void configButtons() {
        getChildren().addAll(buttons);
        startButton.setDisable(false);
        unPlantButton.setDisable(true);
        configLabels();
    }

    private void manageStartButton() {
        startButton.setOnAction(e -> menuViewModel.start());
    }

    private void configMenu() {
        setPadding(new Insets(PADDING));
        setMinWidth(MENU_WIDTH);
        getChildren().addAll(nbHerb);
    }

    private void configLabels() {
        startButton.textProperty().bind(menuViewModel.startLabelProperty());
        plantButton.textProperty().bind(menuViewModel.plantLabelProperty());
        unPlantButton.textProperty().bind(menuViewModel.unPlantLabelProperty());
    }
}
