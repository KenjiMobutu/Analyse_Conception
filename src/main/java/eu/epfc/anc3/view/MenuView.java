package eu.epfc.anc3.view;


import eu.epfc.anc3.vm.MenuViewModel;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private final ToggleButton plantGrassButton = new ToggleButton();
    private final ToggleButton unPlantButton = new ToggleButton();
    private final Label scoreLabel = new Label("Score : ");
    private final TextField score = new TextField();
    private final Label jourLabel = new Label("Jour : ");
    private final TextField nbJour = new TextField();
    private final ToggleButton plantCarottButton = new ToggleButton("plant carrot");

    private final ToggleButton plantCabbageButton = new ToggleButton("plant cabbage");
    private final ToggleButton fertilizeButton = new ToggleButton("fertilize");
    private final ToggleButton recoltButton = new ToggleButton();
    private final Button sleepButton = new Button("Dormir");

    private final ToggleGroup toggleGroup = new ToggleGroup();


    private final MenuViewModel menuViewModel;

    private final HBox nbGrassHBox;
    HBox buttonsHBox = new HBox(startButton,sleepButton) ;
    HBox buttons = new HBox(startButton,sleepButton);
    VBox actionVbox = new VBox(plantGrassButton,unPlantButton,plantCarottButton,plantCabbageButton);

    public MenuView(MenuViewModel menuViewModel) {
        this.menuViewModel = menuViewModel;
        this.nbGrassHBox = createNbGrassHBox();
        this.buttonsHBox = buttons;
        unPlantButton.setPrefWidth(130);
        Image img = new Image("carrot4.png");
        ImageView carrot = new ImageView(img);
        this.plantCarottButton.setGraphic(carrot);
        plantCarottButton.setPrefWidth(130);

        Image img2 = new Image("cabbage4.png");
        ImageView cabbage = new ImageView(img2);
        this.plantCabbageButton.setGraphic(cabbage);
        plantCabbageButton.setPrefWidth(130);

        Image img3 = new Image("grass.png");
        ImageView grass = new ImageView(img3);
        this.plantGrassButton.setGraphic(grass);
        plantGrassButton.setPrefWidth(130);
        score.setPrefWidth(50);
        nbJour.setPrefWidth(50);

        plantCarottButton.setTranslateY(30);
        plantGrassButton.setTranslateY(30);
        unPlantButton.setTranslateY(30);
        plantCabbageButton.setTranslateY(30);
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
        plantGrassButton.setDisable(true);
        unPlantButton.setDisable(true);

        // Enable the start button initially
        startButton.setDisable(false);

        // Bind the number of grass parcels to the view model
        nbGrassTextField.textProperty().bind(menuViewModel.nbGrass().asString());

        startButton.setFocusTraversable(false);
        stopButton.setFocusTraversable(false);
        nbGrassHBox.setFocusTraversable(false);
        plantGrassButton.setFocusTraversable(false);
        unPlantButton.setFocusTraversable(false);
        plantCabbageButton.setFocusTraversable(false);
        plantCarottButton.setFocusTraversable(false);
        sleepButton.setFocusTraversable(false);

        nbJour.setDisable(true);
        nbJour.setTranslateX(150);
        jourLabel.setTranslateX(150);
        score.setTranslateX(100);
        scoreLabel.setTranslateX(100);
        score.setDisable(true);
        nbGrassTextField.setDisable(true);
        plantGrassButton.setDisable(true);
        unPlantButton.setDisable(true);
        startButton.setDisable(false);
        manageNbHerb();
        bindLabelsToViewModel();
        addToToggleGroup();
    }

    private void addToToggleGroup() {
        plantGrassButton.setToggleGroup(toggleGroup);
        plantCabbageButton.setToggleGroup(toggleGroup);
        plantCarottButton.setToggleGroup(toggleGroup);
        fertilizeButton.setToggleGroup(toggleGroup);
        unPlantButton.setToggleGroup(toggleGroup);
    }

    private void bindLabelsToViewModel() {
        startButton.textProperty().bind(menuViewModel.startLabelProperty());
        stopButton.textProperty().bind(menuViewModel.stopLabelProperty());
        plantGrassButton.textProperty().bind(menuViewModel.plantLabelProperty());
        unPlantButton.textProperty().bind(menuViewModel.unPlantLabelProperty());
    }

    HBox createNbGrassHBox() {
        return new HBox(nbGrassLabel, nbGrassTextField,scoreLabel, score,jourLabel,nbJour);
    }
    HBox scoreAndDay(){
        return new HBox(scoreLabel, score,jourLabel,nbJour);
    }

    public void setUpButtonStart() {
        startButton.setOnAction(e -> handleStartButtonAction());
    }
    private void setUpButtonStop() {
        stopButton.setOnAction(e -> handleStopButtonAction());
    }
    private void setUpButtonPlant() {
        plantGrassButton.setOnAction(e -> handlePlantButtonAction());
    }
    private void setUpButtonUnplant() {
        unPlantButton.setOnAction(e -> handleUnPlantButtonAction());
    }


     public void handleStartButtonAction() {
         System.out.println("Handelleelel Start");
        buttons.getChildren().remove(startButton);
         System.out.println("Retire start");
        buttons.getChildren().add(0, stopButton);
        plantGrassButton.setDisable(false);
        unPlantButton.setDisable(false);
        menuViewModel.start();
    }

    private void handleStopButtonAction() {
        buttonsHBox.getChildren().remove(stopButton);
        plantGrassButton.setDisable(true);
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
            plantGrassButton.setDisable(false);
            unPlantButton.setDisable(false);
            menuViewModel.newGame();
        });
    }

}
