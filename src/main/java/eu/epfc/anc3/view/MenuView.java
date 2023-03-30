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
    private final TextField nbJour = new TextField("0"); // <------------- a binder

    private final Label nbGrassTxt = new Label("Nombre de parcelles de gazon : ");
    private final TextField nbGrass= new TextField();

    private final Button startButton = new Button();
    private final Button sleepButton = new Button("sleep");
    private final Button stopButton = new Button();
    private final ToggleButton plantButtonGrass = new ToggleButton();
    private final ToggleButton unPlantButton = new ToggleButton();

    private final ToggleGroup toggleGroup = new ToggleGroup();
    private final ToggleButton plantCarotteButton = new ToggleButton();
    private final ToggleButton plantCabbageButton = new ToggleButton();
    private final ToggleButton fertilizerButton = new ToggleButton();
    private final ToggleButton recoltButton = new ToggleButton();

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
        setUpButtonPlantGrass();
        setUpButtonPlantCabbage();
        setUpButtonPlantCarott();
        setUpButtonFertilizer();
        setUpButtonRecolt();
        setUpButtonUnplant();
        setUpButtonStop();
        setUpSleepAction();

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

        setUpButtonMode();
        setUpImages();
       // nbGrassTextField.setDisable(true);

        addToToggleGroup();
        bindLabelsToViewModel();
        manageNbGrass();//K:pour DEBUG
    }

    private void setUpButtonMode(){
        // Enable the start button initially
        startButton.setDisable(false);
        startButton.setFocusTraversable(false);
        stopButton.setFocusTraversable(false);
        nbHbox.setFocusTraversable(false);
        plantButtonGrass.setFocusTraversable(false);
        unPlantButton.setFocusTraversable(false);
        nbGrass.setFocusTraversable(false);
        nbGrassTxt.setFocusTraversable(false);
        nbGrass.setDisable(true);
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
    }
    private void setUpImages(){
        Image img1 = new Image("cabbage4.png");
        ImageView view1 = new ImageView(img1);
        plantCabbageButton.setGraphic(view1);
        plantCabbageButton.setFocusTraversable(false);

        Image img2 = new Image("carrot4.png");
        ImageView view2 = new ImageView(img2);
        plantCarotteButton.setGraphic(view2);
        plantCarotteButton.setFocusTraversable(false);

        Image img3 = new Image("watering_can.png");
        ImageView view3 = new ImageView(img3);
        fertilizerButton.setGraphic(view3);
        fertilizerButton.setFocusTraversable(false);

        Image img4 = new Image("shovel.png");
        ImageView view4 = new ImageView(img4);
        recoltButton.setGraphic(view4);
        recoltButton.setFocusTraversable(false);

        Image img5 = new Image("moon.png");
        ImageView view5 = new ImageView(img5);
        sleepButton.setGraphic(view5);
        sleepButton.setFocusTraversable(false);
    }
    private void manageNbGrass() {//K:pour DEBUG
        System.out.println(menuViewModel.nbGrass().toString());
        nbGrass.textProperty().bind(menuViewModel.nbGrass().asString());
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
        plantCabbageButton.textProperty().bind(menuViewModel.plantCabbageLabelProperty());
        plantCarotteButton.textProperty().bind(menuViewModel.plantCarrotLabelProperty());
        fertilizerButton.textProperty().bind(menuViewModel.fertilizerLabelProperty());
        recoltButton.textProperty().bind(menuViewModel.recoltLabelProperty());
    }

    HBox createNewHobx() {
        return new HBox(scoreLabel,nbScore ,jourLabel,nbJour, nbGrassTxt,nbGrass);//K:pour DEBUG
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

    private void setUpSleepAction(){
    sleepButton.setOnAction(event -> {
        int nbJours = Integer.parseInt(nbJour.getText());
        nbJour.setText(Integer.toString(nbJours + 1));
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
//    private void updateNbDaysLabel(){
//        nbJour.textProperty().bind(menuViewModel.getNbDay().asString());
//    }
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
            plantCabbageButton.setDisable(false);
            plantCarotteButton.setDisable(false);
            fertilizerButton.setDisable(false);
            recoltButton.setDisable(false);
            menuViewModel.newGame();
        });
    }

}
