package eu.epfc.anc3.view;

import eu.epfc.anc3.vm.MenuViewModel;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuView extends VBox {

    private final Label scoreLabel = new Label("Score : ");
    private final TextField nbScore = new TextField("0");
    private final Label jourLabel = new Label("Jour : ");
    private final TextField nbJour = new TextField("0");
    private final Button startButton = new Button();
    private final Button sleepButton = new Button("sleep");
    private final Button stopButton = new Button();
    private final Button saveButton = new Button("Sauvegarder");
    private final Button loadButton = new Button("Restaurer");
    private final ToggleButton plantButtonGrass = new ToggleButton();
    private final ToggleButton unPlantButton = new ToggleButton();
    private final ToggleGroup toggleGroup = new ToggleGroup();
    private final ToggleButton plantCarotteButton = new ToggleButton();
    private final ToggleButton plantCabbageButton = new ToggleButton();
    private final ToggleButton fertilizerButton = new ToggleButton();
    private final ToggleButton recoltButton = new ToggleButton();
    private final ToggleButton cowButton = new ToggleButton();
    private final ToggleButton sheepButton = new ToggleButton();
    private final ToggleButton cornButton = new ToggleButton();
    private final MenuViewModel menuViewModel;
    private final HBox nbHbox;

    VBox actionVbox = new VBox(plantButtonGrass,plantCarotteButton, plantCabbageButton,cornButton,fertilizerButton,recoltButton,cowButton,sheepButton);
    HBox buttons = new HBox(startButton,sleepButton);

    public MenuView(MenuViewModel menuViewModel) {
        this.menuViewModel = menuViewModel;
        this.nbHbox = createNewHobx();
        configureMenu();
        bindLabelsToViewModel();
        setUpButtonStart();
        setUpButtonPlantGrass();
        setUpButtonPlantCabbage();
        setUpButtonPlantCarott();
        setUpButtonFertilizer();
        setUpButtonRecolt();
        setUpButtonUnplant();
        setUpButtonStop();
        setUpSleepAction();
        setUpCowButton();
        setUpSheepButton();
        setUpCornButton();

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
        plantButtonGrass.setPrefSize(145, 10);

        unPlantButton.setDisable(true);
        unPlantButton.setPrefSize(145, 10);

        cowButton.setPrefSize(145, 10);
        sheepButton.setPrefSize(145, 10);
        cornButton.setPrefSize(145, 10);
        setUpButtonMode();
        setUpImages();
        // nbGrassTextField.setDisable(true);

        manageScore();
        addToToggleGroup();
        bindLabelsToViewModel();
    }

    private void setUpButtonMode(){
        // Enable the start button initially
        startButton.setDisable(false);
        startButton.setFocusTraversable(false);
        stopButton.setFocusTraversable(false);
        saveButton.setFocusTraversable(false);
        loadButton.setFocusTraversable(false);

        nbHbox.setFocusTraversable(false);
        plantButtonGrass.setFocusTraversable(false);
        unPlantButton.setFocusTraversable(false);
        /*---------*/
        nbJour.setDisable(true);
        nbScore.setDisable(true);
        plantButtonGrass.setDisable(true);
        unPlantButton.setDisable(true);
        plantCabbageButton.setDisable(true);
        plantCarotteButton.setDisable(true);
        fertilizerButton.setDisable(true);
        recoltButton.setDisable(true);
        startButton.setDisable(false);
        sleepButton.setDisable(true);
        cowButton.setDisable(true);
        sheepButton.setDisable(true);
        cornButton.setDisable(true);

    }
    private void setUpImages(){
        Image img1 = new Image("cabbage4.png");
        ImageView view1 = new ImageView(img1);
        plantCabbageButton.setGraphic(view1);
        plantCabbageButton.setFocusTraversable(false);
        plantCabbageButton.setPrefSize(145, 10);

        Image img2 = new Image("carrot4.png");
        ImageView view2 = new ImageView(img2);
        plantCarotteButton.setGraphic(view2);
        plantCarotteButton.setFocusTraversable(false);
        plantCarotteButton.setPrefSize(145, 10);

        Image img3 = new Image("watering_can.png");
        ImageView view3 = new ImageView(img3);
        fertilizerButton.setGraphic(view3);
        fertilizerButton.setFocusTraversable(false);
        fertilizerButton.setPrefSize(145, 10);

        Image img4 = new Image("shovel.png");
        ImageView view4 = new ImageView(img4);
        recoltButton.setGraphic(view4);
        recoltButton.setFocusTraversable(false);
        recoltButton.setPrefSize(145, 10);

        Image img5 = new Image("moon.png");
        ImageView view5 = new ImageView(img5);
        sleepButton.setGraphic(view5);
        sleepButton.setFocusTraversable(false);

        Image img6 = new Image("cow.png");
        ImageView view6 = new ImageView(img6);
        cowButton.setGraphic(view6);
        cowButton.setFocusTraversable(false);

        Image img7 = new Image("sheep.png");
        ImageView view7 = new ImageView(img7);
        sheepButton.setGraphic(view7);
        sheepButton.setFocusTraversable(false);

        Image img8 = new Image("corn0.png");
        ImageView view8 = new ImageView(img8);
        cornButton.setGraphic(view8);
        cornButton.setFocusTraversable(false);
    }

    private void manageScore(){
        nbScore.textProperty().bind(menuViewModel.score().asString());
    }


    private void addToToggleGroup(){
        plantButtonGrass.setToggleGroup(toggleGroup);
        plantCabbageButton.setToggleGroup(toggleGroup);
        plantCarotteButton.setToggleGroup(toggleGroup);
        fertilizerButton.setToggleGroup(toggleGroup);
        recoltButton.setToggleGroup(toggleGroup);
        unPlantButton.setToggleGroup(toggleGroup);
        cowButton.setToggleGroup(toggleGroup);
        sheepButton.setToggleGroup(toggleGroup);
        cornButton.setToggleGroup(toggleGroup);
    }

    private void bindLabelsToViewModel() {
        startButton.textProperty().bind(menuViewModel.startLabelProperty());
        stopButton.textProperty().bind(menuViewModel.stopLabelProperty());
        plantButtonGrass.textProperty().bind(menuViewModel.plantLabelProperty());
        unPlantButton.textProperty().bind(menuViewModel.unPlantLabelProperty());
        plantCabbageButton.textProperty().bind(menuViewModel.plantCabbageLabelProperty());
        plantCarotteButton.textProperty().bind(menuViewModel.plantCarrotLabelProperty());
        fertilizerButton.textProperty().bind(menuViewModel.fertilizerLabelProperty());
        recoltButton.textProperty().bind(menuViewModel.recoltLabelProperty());
        cowButton.textProperty().bind(menuViewModel.cowLabelProperty());
        sheepButton.textProperty().bind(menuViewModel.sheepLabelProperty());
        cornButton.textProperty().bind(menuViewModel.cornLabelProperty());
    }

    HBox createNewHobx() {
        return new HBox(scoreLabel,nbScore ,jourLabel,nbJour);//K:pour DEBUG
    }
    public void setUpButtonStart() {
        startButton.setOnAction(e -> handleStartButtonAction());
    }
    private void setUpButtonStop() {
        stopButton.setOnAction(e -> handleStopButtonAction());
    }
    private void setUpButtonPlantGrass() {
        plantButtonGrass.setOnAction(e -> handlePlantGrassButtonAction());
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
    private void setUpSheepButton() {
        sheepButton.setOnAction(e -> handleSheepButtonAction());
    }

    private void setUpCowButton() {
        cowButton.setOnAction(e -> handleCowButtonAction());
    }
    private void setUpCornButton() { cornButton.setOnAction(e -> handleCornButtonAction());
    }

    private void setUpSleepAction(){
        sleepButton.setOnAction(event -> {
            int nbJours = Integer.parseInt(nbJour.getText());
            nbJour.setText(Integer.toString(nbJours + 1));
            handleSleepButtonAction();
        });
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
        fertilizerButton.setDisable(false);
        recoltButton.setDisable(false);
        sleepButton.setDisable(false);
        loadButton.setDisable(false);
        saveButton.setDisable(false);
        cowButton.setDisable(false);
        sheepButton.setDisable(false);
        cornButton.setDisable(false);
        menuViewModel.start();
    }

    private void handleStopButtonAction() {
        buttons.getChildren().remove(stopButton);
        plantButtonGrass.setDisable(true);
        unPlantButton.setDisable(true);
        plantCabbageButton.setDisable(true);
        plantCarotteButton.setDisable(true);
        fertilizerButton.setDisable(true);
        recoltButton.setDisable(true);
        loadButton.setDisable(true);
        saveButton.setDisable(true);
        sleepButton.setDisable(true);
        cowButton.setDisable(true);
        sheepButton.setDisable(true);
        menuViewModel.stop();
        nbJour.setText("0");
        manageNewGameButton();
    }

    private void handlePlantGrassButtonAction() {
        menuViewModel.plantGrassMode();
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
    private void handleSleepButtonAction() { menuViewModel.sleepMode();
    }
    private void handleSheepButtonAction() {
        menuViewModel.sheepMode();
    }
    private void handleCowButtonAction() {menuViewModel.cowMode();}

    private void handleCornButtonAction() {menuViewModel.cornMode();}
    private void manageNewGameButton() {
        buttons.getChildren().add(0, startButton);
        startButton.setOnAction(e -> {
            buttons.getChildren().remove(startButton);
            buttons.getChildren().add(0, stopButton);
            plantButtonGrass.setDisable(false);
            unPlantButton.setDisable(false);
            plantCabbageButton.setDisable(false);
            plantCarotteButton.setDisable(false);
            fertilizerButton.setDisable(false);
            recoltButton.setDisable(false);
            sleepButton.setDisable(false);
            cowButton.setDisable(false);
            sheepButton.setDisable(false);
            menuViewModel.newGame();
            if (toggleGroup.getSelectedToggle() != null) {
                toggleGroup.getSelectedToggle().setSelected(false);
            }
        });
    }


}
