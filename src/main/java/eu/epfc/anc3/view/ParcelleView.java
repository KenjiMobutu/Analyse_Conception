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
//cell
public class ParcelleView extends StackPane {
    private static final Image DIRT = new Image("dirt.png");
    private static final Image GRASS = new Image("grass.png");
    private ImageView imageView = new ImageView();

    // constantes pour stocker les noms des fichiers d'image
    private static final String GRASS_IMAGE = "grass.png";
    private static final String FARMER_IMAGE = "farmer.png";
    private final ImageView backgroundImageView;
    private final ImageView foregroundImageView;
    private final SnapshotParameters snapshotParameters;

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

    private void setParcelleImage1(ImageView imageView, ParcelleValue parcelleValue) {
        ObservableList<Node> children = getChildren();
        children.clear();
        switch (parcelleValue) {
            case EMPTY:
                setEmpty();
                break;
            case DIRT:
                setDirt(imageView);
                break;
            case FARMER_GRASS:
                setFarmerGrassImage();
                break;
            case GRASS:
                setGrass(imageView, GRASS);
                break;
            case FARMER:
                setFarmerImage(imageView);
                break;
            default:
                throw new IllegalArgumentException("Unknown parcelle value: " + parcelleValue);
        }
    }
    private void setFarmerGrassImage() {
        backgroundImageView.setImage(new Image(GRASS_IMAGE));
        foregroundImageView.setImage(new Image(FARMER_IMAGE));
        ObservableList<Node> children = this.getChildren();
        if (!children.contains(backgroundImageView)) {
            children.add(backgroundImageView);
        }
        if (!children.contains(foregroundImageView)) {
            children.add(foregroundImageView);
        }
    }
    private void setEmpty() {
        ImageView emptyCase = new ImageView(DIRT);
        emptyCase.autosize();
        emptyCase.setPreserveRatio(false);
        emptyCase.setFitHeight(8);
        emptyCase.setFitWidth(8);
        if (!getChildren().contains(emptyCase))
            getChildren().add(0,emptyCase);
    }
    private void setDirt(ImageView imageView) {
        if (imageView.getImage() == null || imageView.getImage() == GRASS){
            imageView.setImage(DIRT);
            imageView = new ImageView(DIRT);
            imageView.setPreserveRatio(false);
            imageView.setFitHeight(20);
            imageView.setFitWidth(20);
        }
    }
    private void setGrass(ImageView imageView, Image image) {
        ImageView grass = new ImageView(GRASS);
        ObservableList<Node> children = this.getChildren();
        imageView.setImage(image);
        grass.setPreserveRatio(false);
        grass.setFitHeight(25);
        grass.setFitWidth(25);

        if (!children.contains(imageView)) {
            children.add(0,grass);
        }
    }
    private void setFarmerImage(ImageView imageView) {
        ImageView farmer = new ImageView(FARMER_IMAGE);
        farmer.setPreserveRatio(false);
        farmer.setFitHeight(20);
        farmer.setFitWidth(20);

        ImageView background = new ImageView(GRASS_IMAGE);
        background.setPreserveRatio(false);
        background.setFitHeight(25);
        background.setFitWidth(25);

        ImageView foreground = new ImageView(FARMER_IMAGE);
        foreground.setPreserveRatio(false);
        foreground.setFitHeight(20);
        foreground.setFitWidth(20);

        ObservableList<Node> children = this.getChildren();
        if (!children.contains(background)) {
            children.add(background);
        }
        if (!children.contains(farmer)) {
            children.add(farmer);
        }
        if (!children.contains(foreground)) {
            children.add(foreground);
        }

        if (imageView.getImage() == GRASS) {
            foreground.setVisible(false);
        } else {
            background.setVisible(false);
        }
    }


}
