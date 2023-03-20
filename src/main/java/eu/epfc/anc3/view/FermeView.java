package eu.epfc.anc3.view;

import eu.epfc.anc3.model.Move;
import eu.epfc.anc3.vm.FermeViewModel;
import eu.epfc.anc3.vm.MenuViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FermeView extends BorderPane {
    static final int PADDING = 20;
    static final int MENU_WIDTH = 150;
    private static final int SCENE_MIN_WIDTH = 800;
    private static final int SCENE_MIN_HEIGHT = 500;
    static final int FERME_WIDTH = 25;
    static final int FERME_HEIGHT = 15;
    //private boolean spacePressed = false;
    // Contrainte de mise en page
    private final DoubleProperty gridWidthProperty = new SimpleDoubleProperty(350);
    private final FermeViewModel fermeViewModel = new FermeViewModel();
    // Composants principaux
    private TerrainView terrainView;
    public FermeView(Stage primaryStage ){
        start(primaryStage);
    }

    public void start(Stage stage) {
        // Mise en place des composants principaux
        configMainComponents(stage);

        // Mise en place de la scène et affichage de la fenêtre
        Scene scene = new Scene(this,SCENE_MIN_WIDTH,SCENE_MIN_HEIGHT);
        configKeyPressed(scene);
        configKeyRealeased(scene);
        stage.setScene(scene);
        stage.show();
        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
    }

    private void configMainComponents(Stage stage){
        // Configuration de la fenêtre
        stage.titleProperty().bind(fermeViewModel.titleProperty());
        stage.getIcons().add(new Image("farmer.png"));
        setPadding(new Insets(PADDING));

        // Configuration du menu
        configMenu();

        // Configuration de la vue de la ferme
        configTerrainView();
    }

    private void configTerrainView() {
        fermeViewModel.isFermeStartedProperty().addListener(
                (obs, oldval, newval) -> configTerrainPane(newval));
    }
    private void configTerrainPane(Boolean gameStarted) {
        if (gameStarted) {
            createTerrain();
            if (fermeViewModel.isFermeStartedProperty().getValue()){
                this.requestFocus();
            }
        } else {
            removeTerrain();
        }
    }

    private void removeTerrain() {
        if (terrainView != null) {
            getChildren().remove(terrainView);
            terrainView = null;
        }
    }

    private void createTerrain() {
        terrainView = new TerrainView(fermeViewModel.getTerrainViewModel(), gridWidthProperty);
        System.out.println("Terrain :"+terrainView);
        setCenter(terrainView);
    }

    private void configMenu() {
        MenuViewModel menuViewModel = fermeViewModel.getMenuViewModel();
        MenuView menuView = new MenuView(menuViewModel);
        setTop(menuView.createNewHobx());
        setBottom(menuView.buttons);
        setLeft(menuView.actionVbox);
    }
    void configKeyRealeased(Scene scene){
        scene.setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()){
                case SPACE :
                    System.out.println(keyEvent.getCode() + " test key released");
                    spacePressed(false);
            }
        });
    }
    public void configKeyPressed(Scene scene){
        //doit focus la scene
        scene.setOnKeyPressed(keyEvent -> {
            switch(keyEvent.getCode()) {
                case RIGHT:
                    System.out.println(keyEvent.getCode());
                    fermeViewModel.keyPressed(Move.RIGHT);
                    break;
                case LEFT:
                    System.out.println(keyEvent.getCode());
                    fermeViewModel.keyPressed(Move.LEFT);
                    break;
                case UP:
                    System.out.println(keyEvent.getCode());
                    fermeViewModel.keyPressed(Move.UP);
                    break;
                case DOWN:
                    System.out.println(keyEvent.getCode());
                    fermeViewModel.keyPressed(Move.DOWN);
                    break;
                case SPACE:
                    System.out.println(keyEvent.getCode()+ " est pressé -----------------");
                    //scene.setOnKeyReleased(e -> handleKeyReleased());
                    spacePressed(true);
                    fermeViewModel.keyPressed(Move.SPACE);
                    break;
            }
        });
        // Ajout de l'écouteur d'événements pour capturer les événements clavier
    }

    private void spacePressed(boolean b) { fermeViewModel.setSpacePressed(b);}
}
