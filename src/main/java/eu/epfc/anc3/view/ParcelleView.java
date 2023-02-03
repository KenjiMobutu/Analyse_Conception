package eu.epfc.anc3.view;

import eu.epfc.anc3.model.ParcelleValue;
import eu.epfc.anc3.vm.ParcelleViewModel;
import eu.epfc.anc3.vm.TerrainViewModel;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class ParcelleView extends StackPane {
    //cell

    private final static Image FARMER = new Image("farmer.png");
    private final static Image DIRT = new Image("dirt.png");
    private final static Image GRASS = new Image("grass.png");
    // pourra ajouter plant etc
    private final ImageView imageView = new ImageView();


    public ParcelleView(ParcelleViewModel parcelleViewModel, DoubleBinding parcelleWidthProperty){
        imageView.setPreserveRatio(true); // permet de garder son aspect meme en étant redimensionnée
        imageView.fitWidthProperty().bind(parcelleWidthProperty);

        imageView.setImage(setImage(parcelleViewModel.valueProperty().get()));
        getChildren().add(imageView);

    }


    private Image setImage(ParcelleValue parcelleValue){
        switch (parcelleValue){
            case EMPTY:
                return DIRT;
            case GRASS:
                return GRASS;
            case FARMER:
                return FARMER;
            default:
                return null; //pas sûr de celui la
        }
    }
}
