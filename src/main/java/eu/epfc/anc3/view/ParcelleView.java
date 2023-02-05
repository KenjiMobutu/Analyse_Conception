package eu.epfc.anc3.view;

import eu.epfc.anc3.model.ParcelleValue;
import eu.epfc.anc3.vm.ParcelleViewModel;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class ParcelleView extends StackPane {
    private static final Image FARMER = new Image("farmer.png");
    private static final Image DIRT= new Image("dirt.png");
    private static final Image GRASS = new Image("grass.png");
    private final ImageView imageView = new ImageView();
    //cell
    public ParcelleView(ParcelleViewModel parcelleViewModel, DoubleBinding parcelleWidthProperty){
        // imageView prend tout l'espace disponible et garde ses proportions
        // Utilise des bindings (que nous verrons plus tard) : prenez ce code tel quel sans
        // nécessairement le comprendre.
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(parcelleWidthProperty);
        getChildren().add(imageView);

        setFermeImage(imageView,parcelleViewModel.valueProperty().getValue());
        ReadOnlyObjectProperty<ParcelleValue> valueProp = parcelleViewModel.valueProperty();
        valueProp.addListener((obs, old, newVal) ->imageView.setImage(setFermeImg(newVal)));

        this.setOnMouseClicked(e -> parcelleViewModel.play());

    }

    private Image setFermeImage(ImageView imageView, ParcelleValue parcelleValue) {
        imageView.setImage(parcelleValue == ParcelleValue.EMPTY
                ? DIRT
                : GRASS);
        return null;
    }
    private Image setFermeImg(ParcelleValue parcelleValue) {
        switch (parcelleValue){
            case DIRT:
                return DIRT;
            case GRASS:
                return GRASS;
            case FARMER:
                return FARMER;
            default:
                return null;
        }
    }
}
