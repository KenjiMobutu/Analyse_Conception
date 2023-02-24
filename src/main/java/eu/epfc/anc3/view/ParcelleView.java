package eu.epfc.anc3.view;

import eu.epfc.anc3.model.Parcelle;
import eu.epfc.anc3.model.ParcelleValue;
import eu.epfc.anc3.vm.ParcelleViewModel;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import eu.epfc.anc3.vm.TerrainViewModel;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ParcelleView extends StackPane {
private static final Image FARMER = new Image("farmer.png");
private static final Image DIRT = new Image("dirt.png");
private static final Image GRASS = new Image("grass.png");
private ImageView imageView = new ImageView();
//cell
// constantes pour stocker les noms des fichiers d'image
private static final String DIRT_IMAGE = "dirt.png";
private static final String GRASS_IMAGE = "grass.png";
private static final String FARMER_IMAGE = "farmer.png";
    private final ImageView backgroundImageView;
    private final ImageView foregroundImageView;
    //private final ImageView imageView;

    private final SnapshotParameters snapshotParameters;
    /*public ParcelleView(ParcelleViewModel parcelleViewModel, DoubleBinding parcelleWidthProperty) {
        backgroundImageView = new ImageView();
        foregroundImageView = new ImageView();
        //imageView = new ImageView();
        snapshotParameters = new SnapshotParameters();
        imageView.setPreserveRatio(false); // permet de garder son aspect meme en étant redimensionnée
        imageView.fitWidthProperty().bind(parcelleWidthProperty);//resize image to cellWidthpropery
        imageView.fitHeightProperty().bind(parcelleWidthProperty);
        getChildren().add(imageView);
        setParcelleImage(imageView,parcelleViewModel.valueProperty().getValue());
        //createParcelleNode(parcelleViewModel.valueProperty().getValue());
        Image img = new Image("dirt.png");
        BackgroundImage backgroundImage = new BackgroundImage(
                img,
                BackgroundRepeat.SPACE,
                BackgroundRepeat.SPACE,
                BackgroundPosition.CENTER,
                new BackgroundSize(100,100,true,true,true,true)
        );
        Background background = new Background(backgroundImage);
        this.setBackground(background);

        ReadOnlyObjectProperty<ParcelleValue> valueProp = parcelleViewModel.valueProperty();
        //valueProp.addListener((obs, old, newVal) -> createParcelleNode(newVal));
        valueProp.addListener((obs, old, newVal) -> setParcelleImage(imageView,newVal));
       *//* valueProp.addListener((obs, old, newVal) -> {
            Node newParcelleNode = createParcelleNode(newVal);
            imageView.setImage(takeSnapshot(newParcelleNode));
        });*//*
        this.setOnMouseClicked(e -> parcelleViewModel.play());
    }
    private void setParcelleImage(ImageView imageView, ParcelleValue parcelleValue){
        switch (parcelleValue){
            case DIRT:
                imageView.setImage(DIRT);
                break;
            case EMPTY:
                imageView.setImage(DIRT);
                break;
            case GRASS:
                //imageView.setImage(GRASS);
                backgroundImageView.setImage(new Image(GRASS_IMAGE));
                foregroundImageView.setImage(new Image(FARMER_IMAGE));
                this.getChildren().addAll(backgroundImageView, foregroundImageView);
                break;
            case FARMER:
                imageView.setImage(FARMER);
                break;
        }
    }
   *//* public ParcelleView(ParcelleViewModel parcelleViewModel, DoubleBinding parcelleWidthProperty) {
        backgroundImageView = new ImageView();
        foregroundImageView = new ImageView();
        imageView = new ImageView();
        snapshotParameters = new SnapshotParameters();

        imageView.setPreserveRatio(false);
        imageView.fitWidthProperty().bind(parcelleWidthProperty);
        imageView.fitHeightProperty().bind(parcelleWidthProperty);
        getChildren().add(imageView);

        Node parcelleNode = createParcelleNode(parcelleViewModel.valueProperty().getValue());
        imageView.setImage(DIRT);

        ReadOnlyObjectProperty<ParcelleValue> valueProp = parcelleViewModel.valueProperty();
        valueProp.addListener((obs, old, newVal) -> {
            Node newParcelleNode = createParcelleNode(newVal);
            imageView.setImage(takeSnapshot(newParcelleNode));
        });

        this.setOnMouseClicked(e -> parcelleViewModel.play());
    }*//*

    private Node createParcelleNode(ParcelleValue parcelleValue) {
        backgroundImageView.setImage(new Image(DIRT_IMAGE));
        foregroundImageView.setImage(null);

        switch (parcelleValue) {
            case DIRT, EMPTY -> {
                // do nothing
                //backgroundImageView.setImage(new Image(DIRT_IMAGE));
            }
            case FARMER -> {
                foregroundImageView.setImage(new Image(FARMER_IMAGE));
            }
            case GRASS -> {
                backgroundImageView.setImage(new Image(GRASS_IMAGE));
                foregroundImageView.setImage(new Image(FARMER_IMAGE));
                this.getChildren().addAll(backgroundImageView, foregroundImageView);
            }
            default -> throw new IllegalArgumentException("Invalid parcelleValue");
        }

        return backgroundImageView;
    }

    private Image takeSnapshot(Node node) {
        return node.snapshot(snapshotParameters, null);
    }*/


    public ParcelleView(ParcelleViewModel parcelleViewModel, DoubleBinding parcelleWidthProperty) {
        backgroundImageView = new ImageView();
        foregroundImageView = new ImageView();
        imageView = new ImageView();
        snapshotParameters = new SnapshotParameters();

        imageView.setPreserveRatio(false);
        imageView.fitWidthProperty().bind(parcelleWidthProperty);
        imageView.fitHeightProperty().bind(parcelleWidthProperty);
        imageView.setImage(DIRT);
        getChildren().add(imageView);
        Image img = new Image("dirt.png");
        BackgroundImage backgroundImage = new BackgroundImage(
                img,
                BackgroundRepeat.SPACE,
                BackgroundRepeat.SPACE,
                BackgroundPosition.CENTER,
                new BackgroundSize(100,100,true,true,true,true)
        );
        Background background = new Background(backgroundImage);
        this.setBackground(background);

        setParcelleImage1(imageView,parcelleViewModel.valueProperty().getValue());

        ReadOnlyObjectProperty<ParcelleValue> valueProp = parcelleViewModel.valueProperty();
        valueProp.addListener((obs, old, newVal) -> setParcelleImage1(imageView,newVal));

        this.setOnMouseClicked(e -> parcelleViewModel.play());
    }
    private void setParcelleImage1(ImageView imageView, ParcelleValue parcelleValue){
        switch (parcelleValue){
            case DIRT:
                imageView.setImage(DIRT);
                break;
            case FARMER_GRASS:
                backgroundImageView.setImage(new Image(GRASS_IMAGE));
                foregroundImageView.setImage(new Image(FARMER_IMAGE));
                this.getChildren().setAll(backgroundImageView, foregroundImageView);
                break;
            case GRASS:
                imageView.setImage(GRASS);
                break;
            case FARMER:
                imageView.setImage(FARMER);
                break;
        }
    }

    private void setParcelleImage(ParcelleValue parcelleValue) {
        switch (parcelleValue) {
            case DIRT, EMPTY -> imageView.setImage(DIRT);
            case FARMER -> {
                foregroundImageView.setImage(FARMER);
                this.getChildren().setAll(foregroundImageView);
            }
            case GRASS -> {
                backgroundImageView.setImage(new Image(GRASS_IMAGE));
                foregroundImageView.setImage(new Image(FARMER_IMAGE));
                this.getChildren().setAll(backgroundImageView, foregroundImageView);

            }
            default -> throw new IllegalArgumentException("Invalid parcelleValue");
        }
    }

}

