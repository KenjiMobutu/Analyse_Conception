package eu.epfc.anc3.view;


import eu.epfc.anc3.vm.MenuViewModel;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static eu.epfc.anc3.view.FermeView.MENU_WIDTH;
import static eu.epfc.anc3.view.FermeView.PADDING;

public class MenuView extends VBox {
    // Composants du "menu"
    private final Label nbGrassLabel = new Label("Nombre de parcelles de gazon : ");
    private final TextField nbGrassTextField = new TextField();
    private final ToggleButton startButton = new ToggleButton();
    private final ToggleButton stopButton = new ToggleButton();
    private final ToggleButton plantButton = new ToggleButton();
    private final ToggleButton unPlantButton = new ToggleButton();

    private final ToggleGroup toggleGroup = new ToggleGroup();

    private final MenuViewModel menuViewModel;

    private final HBox nbGrassHBox;
    HBox buttonsHBox = new HBox(startButton,plantButton,unPlantButton) ;

    HBox buttons = new HBox(startButton,plantButton,unPlantButton);


    public MenuView(MenuViewModel menuViewModel) {
        this.menuViewModel = menuViewModel;
        this.nbGrassHBox = createNbGrassHBox();
        this.buttonsHBox = buttons;
        configureMenu();
        bindLabelsToViewModel();
        setUpButtonStart();
        setUpButtonPlant();
        setUpButtonUnplant();
        setUpButtonStop();
    }

    private void configureMenu(){
        setPadding(new Insets(FermeView.PADDING));
        setMinWidth(FermeView.MENU_WIDTH);
        getChildren().addAll(nbGrassHBox, buttonsHBox);

        // Make sure the user can't change the number of grass parcels manually
        nbGrassTextField.setEditable(false);

        // Disable the plant and unplant buttons initially
        plantButton.setDisable(true);
        unPlantButton.setDisable(true);

        // Enable the start button initially
        startButton.setDisable(false);

        // Bind the number of grass parcels to the view model
        nbGrassTextField.textProperty().bind(menuViewModel.nbGrass().asString());

        startButton.setFocusTraversable(false);
        stopButton.setFocusTraversable(false);
        nbGrassHBox.setFocusTraversable(false);
        plantButton.setFocusTraversable(false);
        unPlantButton.setFocusTraversable(false);

        nbGrassTextField.setDisable(true);
        plantButton.setDisable(true);
        unPlantButton.setDisable(true);
        startButton.setDisable(false);
        manageNbHerb();
        addToToggleGroup();
        bindLabelsToViewModel();
    }

    private void addToToggleGroup(){

        plantButton.setToggleGroup(toggleGroup);
        unPlantButton.setToggleGroup(toggleGroup);
    }

    private void bindLabelsToViewModel() {
        startButton.textProperty().bind(menuViewModel.startLabelProperty());
        stopButton.textProperty().bind(menuViewModel.stopLabelProperty());
        plantButton.textProperty().bind(menuViewModel.plantLabelProperty());
        unPlantButton.textProperty().bind(menuViewModel.unPlantLabelProperty());
    }

    HBox createNbGrassHBox() {
        return new HBox(nbGrassLabel, nbGrassTextField);
    }
    public void setUpButtonStart() {
        startButton.setOnAction(e -> handleStartButtonAction());
    }
    private void setUpButtonStop() {
        stopButton.setOnAction(e -> handleStopButtonAction());
    }
    private void setUpButtonPlant() {
        plantButton.setOnAction(e -> handlePlantButtonAction());
    }
    private void setUpButtonUnplant() {
        unPlantButton.setOnAction(e -> handleUnPlantButtonAction());
    }


     public void handleStartButtonAction() {
         System.out.println("Handelleelel Start");
        buttons.getChildren().remove(startButton);
         System.out.println("Retire start");
        buttons.getChildren().add(0, stopButton);
        plantButton.setDisable(false);
        unPlantButton.setDisable(false);
        menuViewModel.start();
    }

    private void handleStopButtonAction() {
        buttonsHBox.getChildren().remove(stopButton);
        plantButton.setDisable(true);
        unPlantButton.setDisable(true);
        menuViewModel.stop();
        manageNewGameButton();
    }

    private void handlePlantButtonAction() {
        menuViewModel.plantMode();
    }

    private void handleUnPlantButtonAction() {
        menuViewModel.unplantMode();
    }
    public void manageNbHerb(){
        System.out.println(menuViewModel.nbGrass().toString());
        nbGrassTextField.textProperty().bind(menuViewModel.nbGrass().asString());
    }

    private void manageNewGameButton() {
        buttonsHBox.getChildren().add(0, startButton);
        startButton.setOnAction(e -> {
            buttonsHBox.getChildren().remove(startButton);
            buttonsHBox.getChildren().add(0, stopButton);
            plantButton.setDisable(false);
            unPlantButton.setDisable(false);
            menuViewModel.newGame();
        });
    }

}
