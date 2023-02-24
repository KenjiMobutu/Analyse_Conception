package eu.epfc.anc3.view;


import eu.epfc.anc3.vm.MenuViewModel;
import javafx.beans.property.SimpleBooleanProperty;
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
    private  Button startButton = new Button();
    private final Button stopButton = new Button();
    private final Button plantButton = new Button();
    private final Button unPlantButton = new Button();

    HBox nbHerb = new HBox(nbGrassTxt,nbGrass);
    HBox buttons = new HBox(plantButton,unPlantButton,startButton);

    private final MenuViewModel menuViewModel;

    private FermeView fermeView;

    public MenuView(MenuViewModel menuViewModel) {
        this.menuViewModel = menuViewModel;
        configMenu();
        // Gestion du click sur le bouton "Start"
        manageStartButton();
        // Gestion du click sur le bouton "plant"

        managePlantButton();
        // Gestion du click sur le bouton "unplant"

        manageUnPlantButton();

        //gestion click bouton "stop"
        manageStopButton();

    }

    private void configMenu(){
        setPadding(new Insets(PADDING));
        setMinWidth(MENU_WIDTH);
        getChildren().addAll(nbHerb,buttons);
        startButton.setFocusTraversable(false);
        stopButton.setFocusTraversable(false);
        nbHerb.setFocusTraversable(false);
        plantButton.setFocusTraversable(false);
        unPlantButton.setFocusTraversable(false);

        nbHerb.setDisable(true);
        plantButton.setDisable(true);
        unPlantButton.setDisable(true);
        startButton.setDisable(false);
        manageNbHerb();
        configLabels();

    }
    private void configLabels() {
//        nbGrass.textProperty().bind(); doit etre bind avec son compteur
        startButton.textProperty().bind(menuViewModel.startLabelProperty());
        stopButton.textProperty().bind(menuViewModel.stopLabelProperty());
        plantButton.textProperty().bind(menuViewModel.plantLabelProperty());
        unPlantButton.textProperty().bind(menuViewModel.unPlantLabelProperty());
    }

    public void manageStartButton() {
        startButton.setOnAction(e -> {
                buttons.getChildren().remove(startButton);
                buttons.getChildren().add(stopButton);
                plantButton.setDisable(false);
                unPlantButton.setDisable(false);
                // Si le texte du bouton est "Start", on le change en "Stop"
                menuViewModel.start();
                
            });
    }
    public void manageStopButton(){
        stopButton.setOnAction(e -> {
            buttons.getChildren().remove(stopButton);
            plantButton.setDisable(true);
            unPlantButton.setDisable(true);
            menuViewModel.stop();
            manageNewGameButton();
        });
//        stopButton.setOnAction(event -> menuViewModel.stop());

    }
    public void managePlantButton(){
        plantButton.setOnAction(e -> menuViewModel.plantMode());
    }
    public void manageUnPlantButton() {
        unPlantButton.setOnAction(e -> menuViewModel.unplantMode());
    }

    public void manageNbHerb(){
        System.out.println(menuViewModel.nbGrass().toString());
        nbGrass.textProperty().bind(menuViewModel.nbGrass().asString());
    }

    private void manageNewGameButton() {
        buttons.getChildren().add(startButton);
        startButton.setOnAction(e -> {
            buttons.getChildren().remove(startButton);
            buttons.getChildren().add(stopButton);
            plantButton.setDisable(false);
            unPlantButton.setDisable(false);

            menuViewModel.newGame();
        });
    }



}
