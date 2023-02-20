package eu.epfc.anc3.view;

import eu.epfc.anc3.model.*;

import eu.epfc.anc3.model.Controls;
import eu.epfc.anc3.model.FermeFacade;
import eu.epfc.anc3.model.Move;
import eu.epfc.anc3.model.Parcelle;
import eu.epfc.anc3.vm.FermeViewModel;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class FermeView extends BorderPane {
    static final int PADDING = 20;
    static final int MENU_WIDTH = 150;
    private static final int SCENE_MIN_WIDTH = 600;
    private static final int SCENE_MIN_HEIGHT = 500;

    static final int FERME_WIDTH = 15;

    // Contrainte de mise en page
    private final DoubleProperty gridWidthProperty = new SimpleDoubleProperty(350);
    private final FermeViewModel fermeViewModel = new FermeViewModel();
    // Composants principaux
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
        configKeyPressed(scene);
        stage.setScene(scene);
        stage.show();
        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());

        setOnMouseClicked(e -> requestFocus());
        menuView.manageNbHerb();


    }

    private void configMainComponents(Stage stage){
        stage.titleProperty().bind(fermeViewModel.titleProperty());
        Image icon = new Image("farmer.png");
        stage.getIcons().add(icon);
        setPadding(new Insets(PADDING));
        // Mise en place des composants du menu
        configMenu();
        //Mise en place du Field du jeu
        configTerrainView();

    }

    private void configTerrainView() {
        createTerrain();
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
       getChildren().remove(terrainView);
        terrainView  = null;
    }

    private void createTerrain() {
        terrainView = new TerrainView(fermeViewModel.getTerrainViewModel(), gridWidthProperty);
        System.out.println(terrainView);

        gridWidthProperty.bind(Bindings.min(widthProperty().subtract(MENU_WIDTH + 2 * PADDING), heightProperty()));
        setCenter(terrainView);
    }

    private void configMenu() {
        menuView = new MenuView(fermeViewModel.getMenuViewModel());
        setTop(menuView.nbHerb);
        setBottom(menuView.buttons);


    }
    public void configKeyPressed(Scene scene){
        //doit focus la scene
        scene.setOnKeyPressed(keyEvent -> {
            switch(keyEvent.getCode()) {
                case RIGHT:
                    System.out.println(keyEvent.getCode());
                    fermeViewModel.keyPressed(Move.RIGHT);
                    menuView.manageNbHerb();

                    break;
                case LEFT:
                    System.out.println(keyEvent.getCode());
                    fermeViewModel.keyPressed(Move.LEFT);
                    menuView.manageNbHerb();

                    break;
                case UP:
                    System.out.println(keyEvent.getCode());
                    fermeViewModel.keyPressed(Move.UP);
                    menuView.manageNbHerb();

                    break;
                case DOWN:
                    System.out.println(keyEvent.getCode());
                    fermeViewModel.keyPressed(Move.DOWN);
                    menuView.manageNbHerb();

                    break;
                case SPACE:
                    System.out.println(keyEvent.getCode());
                    fermeViewModel.keyPressed(Move.SPACE);
                    menuView.manageNbHerb();

                    break;
            }
        });
    }

}
