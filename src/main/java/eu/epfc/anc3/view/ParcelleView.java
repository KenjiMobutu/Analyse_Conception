package eu.epfc.anc3.view;

import eu.epfc.anc3.model.Parcelle;
import eu.epfc.anc3.model.ParcelleValue;
import eu.epfc.anc3.vm.ParcelleViewModel;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import eu.epfc.anc3.vm.TerrainViewModel;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class ParcelleView extends StackPane {
private static final Image FARMER = new Image("farmer.png");
private static final Image DIRT = new Image("dirt.png");
private static final Image GRASS = new Image("grass.png");
private final ImageView imageView = new ImageView();
//cell

    public ParcelleView(ParcelleViewModel parcelleViewModel, DoubleBinding parcelleWidthProperty) {
        imageView.setPreserveRatio(false); // permet de garder son aspect meme en étant redimensionnée
        imageView.fitWidthProperty().bind(parcelleWidthProperty);//resize image to cellWidthpropery
        imageView.fitHeightProperty().bind(parcelleWidthProperty);
        getChildren().add(imageView);
        setParcelleImage(imageView,parcelleViewModel.valueProperty().getValue());
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
        valueProp.addListener((obs, old, newVal) -> setParcelleImage(imageView,newVal));
        this.setOnMouseClicked(e -> parcelleViewModel.play());
    }
    private void setParcelleImage1(ImageView imageView1) {
        imageView1.setImage(FARMER);
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
                imageView.setImage(GRASS);
                break;
            case FARMER:
                imageView.setImage(FARMER);
                break;
        }
    }

}

