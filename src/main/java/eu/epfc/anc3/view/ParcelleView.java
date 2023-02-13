package eu.epfc.anc3.view;

import eu.epfc.anc3.model.ParcelleValue;
import eu.epfc.anc3.vm.ParcelleViewModel;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import eu.epfc.anc3.vm.TerrainViewModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class ParcelleView extends StackPane {
private static final Image FARMER = new Image("farmer.png");
private static final Image DIRT = new Image("dirt.png");
private static final Image GRASS = new Image("grass.png");
private final ImageView imageView = new ImageView();
//cell
// pourra ajouter plant etc


    public ParcelleView(ParcelleViewModel parcelleViewModel, DoubleBinding parcelleWidthProperty) {
        imageView.setPreserveRatio(true); // permet de garder son aspect meme en étant redimensionnée
        imageView.fitWidthProperty().bind(parcelleWidthProperty);
        Image img = new Image("dirt.png");
        BackgroundImage backgroundImage = new BackgroundImage(
                img,
                BackgroundRepeat.SPACE,
                BackgroundRepeat.SPACE,
                BackgroundPosition.CENTER,
                //BackgroundSize.DEFAULT);
                new BackgroundSize(100, 100, true, true, true, true));
        Background background = new Background(backgroundImage);
        this.setBackground(background);
        getChildren().add(imageView);
        imageView.setImage(DIRT);
        ReadOnlyObjectProperty<ParcelleValue> valueProp = parcelleViewModel.valueProperty();
        valueProp.addListener((obs, old, newVal) ->imageView.setImage(setFermeImg(newVal)));

        this.setOnMouseClicked(e -> parcelleViewModel.play());
    }

    private Image setFermeImage (ImageView imageView, ParcelleValue parcelleValue){
        imageView.setImage(parcelleValue == ParcelleValue.EMPTY
                ? DIRT
                : GRASS);
        return null;
    }

    private Image setFermeImg (ParcelleValue parcelleValue){
        switch (parcelleValue) {
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

