package eu.epfc.anc3.view;

import eu.epfc.anc3.vm.MenuViewModel;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuView extends VBox {

    private final Label scoreLabel = new Label("Score : ");
    private final TextField nbScore = new TextField(); //<------------- a binder
    private final Label jourLabel = new Label("Jour : ");
    private final TextField nbJour = new TextField(); // <------------- a binder

    private final Button startButton = new Button();
    private final Button sleepButton = new Button("sleep");
    private final Button stopButton = new Button();
    private final ToggleButton plantButtonGrass = new ToggleButton();
    private final ToggleButton unPlantButton = new ToggleButton();

    private final ToggleGroup toggleGroup = new ToggleGroup();
    private final ToggleButton plantCarotteButton = new ToggleButton("Plant Carrot");
    private final ToggleButton plantCabbageButton = new ToggleButton("Plant Cabbage");
    private final ToggleButton fertilizerButton = new ToggleButton("Fertilizer");
    private final ToggleButton recoltButton = new ToggleButton("Recolt");

    private final MenuViewModel menuViewModel;

    private final HBox nbHbox;
    //HBox buttonsHBox = new HBox(startButton,sleepButton) ;

    VBox actionVbox = new VBox(plantButtonGrass,unPlantButton,plantCarotteButton, plantCabbageButton,fertilizerButton,recoltButton);
    HBox buttons = new HBox(startButton,sleepButton);


    public MenuView(MenuViewModel menuViewModel) {
        this.menuViewModel = menuViewModel;
        this.nbHbox = createNewHobx();
        //this.buttons = buttons;
        // this.actionVbox = actionVbox;
        configureMenu();
        bindLabelsToViewModel();
        setUpButtonStart();
        setUpButtonPlant();
        setUpButtonPlantCabbage();
        setUpButtonPlantCarott();
        setUpButtonFertilizer();
        setUpButtonRecolt();
        setUpButtonUnplant();
        setUpButtonStop();
    }

    private void configureMenu(){
        setPadding(new Insets(FermeView.PADDING));
        setMinWidth(FermeView.MENU_WIDTH);
        getChildren().addAll(nbHbox, buttons);

        // Disable the plant and unplant buttons initially
        Image img = new Image("grass.png");
        ImageView view = new ImageView(img);
        plantButtonGrass.setDisable(true);
        plantButtonGrass.setGraphic(view);

        unPlantButton.setDisable(true);

        // Enable the start button initially
        startButton.setDisable(false);

        startButton.setFocusTraversable(false);
        stopButton.setFocusTraversable(false);
        nbHbox.setFocusTraversable(false);
        plantButtonGrass.setFocusTraversable(false);
        unPlantButton.setFocusTraversable(false);

        Image img1 = new Image("cabbage4.png");
        ImageView view1 = new ImageView(img1);
        plantCabbageButton.setGraphic(view1);
        plantCabbageButton.setFocusTraversable(false);

        Image img2 = new Image("carrot4.png");
        ImageView view2 = new ImageView(img2);
        plantCarotteButton.setGraphic(view2);
        plantCarotteButton.setFocusTraversable(false);

        sleepButton.setFocusTraversable(false);

       // nbGrassTextField.setDisable(true);
        nbJour.setDisable(true);
        nbScore.setDisable(true);
        plantButtonGrass.setDisable(true);
        unPlantButton.setDisable(true);
        plantCabbageButton.setDisable(true);
        plantCarotteButton.setDisable(true);
        startButton.setDisable(false);
        addToToggleGroup();
        bindLabelsToViewModel();
    }
    private void addToToggleGroup(){
        plantButtonGrass.setToggleGroup(toggleGroup);
        plantCabbageButton.setToggleGroup(toggleGroup);
        plantCarotteButton.setToggleGroup(toggleGroup);
        fertilizerButton.setToggleGroup(toggleGroup);
        recoltButton.setToggleGroup(toggleGroup);
        unPlantButton.setToggleGroup(toggleGroup);
    }

    private void bindLabelsToViewModel() {
        startButton.textProperty().bind(menuViewModel.startLabelProperty());
        stopButton.textProperty().bind(menuViewModel.stopLabelProperty());
        plantButtonGrass.textProperty().bind(menuViewModel.plantLabelProperty());
        unPlantButton.textProperty().bind(menuViewModel.unPlantLabelProperty());
        //ajouter les textProperty des autres boutons.
    }

    HBox createNewHobx() {
        return new HBox(scoreLabel,nbScore ,jourLabel,nbJour);
    }
    public void setUpButtonStart() {
        startButton.setOnAction(e -> handleStartButtonAction());
    }
    private void setUpButtonStop() {
        stopButton.setOnAction(e -> handleStopButtonAction());
    }
    private void setUpButtonPlant() {
        plantButtonGrass.setOnAction(e -> handlePlantButtonAction());
    }
    private void setUpButtonUnplant() {
        unPlantButton.setOnAction(e -> handleUnPlantButtonAction());
    }
    private void setUpButtonPlantCabbage() {
        plantCabbageButton.setOnAction(e -> handlePlantCabbageButtonAction());
    }
    private void setUpButtonPlantCarott() {
        plantCarotteButton.setOnAction(e -> handlePlantCarottButtonAction());
    }

    private void setUpButtonFertilizer() {
        fertilizerButton.setOnAction(e -> handleFertilizerButtonAction());
    }
    private void setUpButtonRecolt() {
        recoltButton.setOnAction(e -> handleRecoltButtonAction());
    }

    //Ajouter les setUp des autres boutons.

    public void handleStartButtonAction() {
        System.out.println("-> Handle Start");
        buttons.getChildren().remove(startButton);
        System.out.println("Retire start");
        buttons.getChildren().add(0, stopButton);
        plantButtonGrass.setDisable(false);
        unPlantButton.setDisable(false);
        plantCarotteButton.setDisable(false);
        plantCabbageButton.setDisable(false);
        menuViewModel.start();
    }

    private void handleStopButtonAction() {
        buttons.getChildren().remove(stopButton);
        plantButtonGrass.setDisable(true);
        unPlantButton.setDisable(true);
        menuViewModel.stop();
        manageNewGameButton();
    }

    private void handlePlantButtonAction() {
        menuViewModel.plantMode();
    }
    private void handlePlantCabbageButtonAction() {
        menuViewModel.plantCabbageMode();
    }
    private void handlePlantCarottButtonAction() {
        menuViewModel.plantCarottMode();
    }
    private void handleFertilizerButtonAction() {
        menuViewModel.fertilizerMode();
    }
    private void handleRecoltButtonAction() {
        menuViewModel.recoltMode();
    }

    private void handleUnPlantButtonAction() {
        menuViewModel.unplantMode();
    }

    private void manageNewGameButton() {
        buttons.getChildren().add(0, startButton);
        startButton.setOnAction(e -> {
            buttons.getChildren().remove(startButton);
            buttons.getChildren().add(0, stopButton);
            plantButtonGrass.setDisable(false);
            unPlantButton.setDisable(false);
            menuViewModel.newGame();
        });
    }

}
