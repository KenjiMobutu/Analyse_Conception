package eu.epfc.anc3.view;

import eu.epfc.anc3.model.Move;
import eu.epfc.anc3.vm.FermeViewModel;
import eu.epfc.anc3.vm.MenuViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FermeView extends BorderPane {
    static final int PADDING = 10;
    static final int MENU_WIDTH = 20;
    private static final int SCENE_MIN_WIDTH = 900;
    private static final int SCENE_MIN_HEIGHT = 900;
    static final int FERME_WIDTH = 25;//25
    static final int FERME_HEIGHT = 15;//15
    // Contrainte de mise en page
    private final DoubleProperty gridWidthProperty = new SimpleDoubleProperty(150);

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
        //scene.widthProperty().addListener((obs, oldval, newval) -> resizeTerrain(newval.doubleValue(), scene.getHeight()));
        //scene.heightProperty().addListener((obs, oldval, newval) -> resizeTerrain(scene.getWidth(), newval.doubleValue()));
    }
    /*private void resizeTerrain(double width, double height) {
        double gridWidth = Math.min(width - MENU_WIDTH - 2 * PADDING, height - 2 * PADDING);
        gridWidthProperty.set(gridWidth);
        if (terrainView != null) {
            terrainView.resize(gridWidth, height - 2 * PADDING);
        }
    }*/

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

        terrainView.minHeightProperty().bind(gridWidthProperty);
        terrainView.minWidthProperty().bind(gridWidthProperty);
        terrainView.maxHeightProperty().bind(gridWidthProperty);
        terrainView.maxWidthProperty().bind(gridWidthProperty);
        gridWidthProperty.bind(Bindings.min(widthProperty().subtract(MENU_WIDTH - 2 * PADDING), heightProperty()));
        double terrainWidth = gridWidthProperty.get();
        double padding = (getWidth() - MENU_WIDTH - terrainWidth) / 2;
        setMargin(terrainView, new Insets(0, padding, 0, padding));
        setBottom(terrainView);
    }

    private void configMenu() {
        MenuViewModel menuViewModel = fermeViewModel.getMenuViewModel();
        MenuView menuView = new MenuView(menuViewModel);
        setTop(menuView.createNewHobx());
        setLeft(menuView.buttons);
        setRight(menuView.actionVbox);
    }
    void configKeyRealeased(Scene scene){
        scene.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.SPACE) {
                fermeViewModel.setSpacePressed(false);
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
                    spacePressed(true);
                    fermeViewModel.keyPressed(Move.SPACE);
                    break;
            }
        });
    }

    private void spacePressed(boolean b) { fermeViewModel.setSpacePressed(b);}
}
