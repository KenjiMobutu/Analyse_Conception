package eu.epfc.anc3.view;
import eu.epfc.anc3.model.Parcelle;
import eu.epfc.anc3.model.Element;
import eu.epfc.anc3.model.FermeStatus;
import eu.epfc.anc3.model.ParcelleValue;
import eu.epfc.anc3.vm.ParcelleViewModel;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
        imageView.autosize();
        ReadOnlyObjectProperty<ParcelleValue> valueProp = parcelleViewModel.valueProperty();
        //valueProp.addListener((obs, old, newVal) -> createParcelleNode(newVal));
        valueProp.addListener((obs, old, newVal) -> setParcelleImage(imageView,newVal));
       *//* valueProp.addListener((obs, old, newVal) -> {
            Node newParcelleNode = createParcelleNode(newVal);
            imageView.setImage(takeSnapshot(newParcelleNode));
        });*//*
        this.setOnMouseClicked(e -> parcelleViewModel.play());
        if ((parcelleViewModel.isStarted().getValue() || parcelleViewModel.isInProgress().getValue() )){
            this.setOnMouseClicked(e -> parcelleViewModel.play());
        }
    }
    private void setParcelleImage(ImageView imageView, ParcelleValue parcelleValue){
        ObservableList<Node> children = getChildren();
        children.clear();
        switch (parcelleValue){
            case EMPTY:
                ImageView emptyCase = new ImageView(DIRT);
                emptyCase.autosize();
                emptyCase.setPreserveRatio(false);
                emptyCase.setFitHeight(8);
                emptyCase.setFitWidth(8);
                if (!getChildren().contains(emptyCase))
                   getChildren().add(0,emptyCase);
                break;
            case DIRT:
                if (imageView.getImage() == null || imageView.getImage() == GRASS){
                    imageView.setImage(DIRT);
                    imageView = new ImageView(DIRT);
                    imageView.setPreserveRatio(false);
                    imageView.setFitHeight(20);
                    imageView.setFitWidth(20);
                }
                children.add(imageView);

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
                if (imageView.getImage() == null || imageView.getImage() == DIRT){
                    imageView.setImage(GRASS);
                    imageView.setPreserveRatio(false);
                }
                this.getChildren().add(imageView);
                break;
            case FARMER:
                ImageView farmer = new ImageView(FARMER);
                farmer.setPreserveRatio(false);
                farmer.setFitHeight(20);
                farmer.setFitWidth(20);
                if (imageView.getImage() != GRASS){
                    ImageView dirt = new ImageView(DIRT);
                    dirt.setPreserveRatio(false);
                    dirt.setFitHeight(25);
                    dirt.setFitWidth(25);
                    this.getChildren().addAll(dirt, farmer);
                    break;
                }else{
                    ImageView grass = new ImageView(GRASS);
                    grass.setPreserveRatio(false);
                    grass.setFitHeight(20);
                    grass.setFitWidth(20);
                    this.getChildren().addAll(grass, farmer);
                    break;
                }
        }
    }
        /**
         * ANCIEN VER
         * private void setParcelleImage(ImageView imageView, ParcelleValue parcelleValue){
         *         getChildren().setAll(children);
         *         System.out.println(getChildren().toString() + "HELQSMLKDF");
         *         switch (parcelleValue){
         *             case EMPTY :
         *             case DIRT:
         *                 ImageView emptyCase = new ImageView(DIRT);
         *                 emptyCase.setPreserveRatio(false);
         *                 emptyCase.setFitHeight(50);
         *                 emptyCase.setFitWidth(50);
         *                 if (!getChildren().contains(emptyCase))
         *                     getChildren().add(0,emptyCase);
         *                 break;
         *             case GRASS:
         *                 ImageView grassCase = new ImageView(GRASS);
         *                 grassCase.setPreserveRatio(false);
         *                 grassCase.setFitHeight(50);
         *                 grassCase.setFitWidth(50);
         *                 if (!getChildren().contains(grassCase))
         *                     getChildren().add(0,grassCase);
         *                 break;
         *             case FARMER:
         *                 imageView = new ImageView(FARMER);
         *                 imageView.setPreserveRatio(false);
         *                 imageView.setFitHeight(50);
         *                 imageView.setFitWidth(50);
         *                 if (!getChildren().contains(imageView))
         *                     getChildren().add(0,imageView);
         *                 break;
         *         }
         *     }
         * }
         */




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
