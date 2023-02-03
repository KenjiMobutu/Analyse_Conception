package eu.epfc.anc3.view;

import eu.epfc.anc3.model.FermeFacade;
import eu.epfc.anc3.vm.FermeViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FermeView extends BorderPane {
    static final int PADDING = 20;
    static final int MENU_WIDTH = 160;
    private static final int SCENE_MIN_WIDTH = 600;
    private static final int SCENE_MIN_HEIGHT = 400;
    static final int GRID_WIDTH = FermeFacade.gridWidth();

    // Contrainte de mise en page
    private final DoubleProperty gridWidthProperty = new SimpleDoubleProperty(250);
    private final FermeViewModel fermeViewModel = new FermeViewModel();
    // Composants principaux
    private FermeView fermeView;
    private TerrainView terrainView;
    private MenuView menuView;

    public FermeView(Stage primaryStage ){
        start(primaryStage);
    }

    public void start(Stage stage) {
        // Mise en place des composants principaux
        configMainComponents(stage);

        // Mise en place de la scène et affichage de la fenêtre
        Scene scene = new Scene(this,SCENE_MIN_WIDTH,SCENE_MIN_HEIGHT);

        //background music


        //configKeyPressed(scene);
        stage.setScene(scene);
        stage.show();
        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
    }
    private void configMainComponents(Stage stage){
        stage.titleProperty().bind(fermeViewModel.titleProperty());
        setPadding(new Insets(PADDING));

        // Mise en place des composants du menu
        configMenu();

        //Mise en place du Field du jeu
        configTerrainView();

        //Mise en place du bouton start
        //configPlayButton();
    }

    private void configTerrainView() {
        fermeViewModel.isFermeStartedProperty().addListener(
                (obs, oldval, newval) -> configTerrainPane(newval));
    }

    private void configTerrainPane(Boolean gameStarted) {
        if (gameStarted) {
            createTerrain();
        } else {
            removeTerrain();
        }
    }

    private void removeTerrain() {
        this.getChildren().remove(terrainView);
        terrainView  = null;
    }

    private void createTerrain() {
        terrainView = new TerrainView(fermeViewModel.getTerrainViewModel(), gridWidthProperty);
        // Grille carrée
        // Utilise des bindings (que nous verrons plus tard) : prenez ce code tel quel sans
        // nécessairement le comprendre.
        terrainView.minHeightProperty().bind(gridWidthProperty);
        terrainView.minWidthProperty().bind(gridWidthProperty);
        terrainView.maxHeightProperty().bind(gridWidthProperty);
        terrainView.maxWidthProperty().bind(gridWidthProperty);
        gridWidthProperty.bind(Bindings.min(widthProperty().subtract(MENU_WIDTH + 2 * PADDING), heightProperty()));

        setCenter(terrainView);
    }

    private void configMenu() {
        menuView = new MenuView(fermeViewModel.getMenuViewModel());
        setBottom(menuView);
    }

}
