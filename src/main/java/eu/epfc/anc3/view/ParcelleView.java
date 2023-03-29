package eu.epfc.anc3.view;

import eu.epfc.anc3.model.Element;
import eu.epfc.anc3.model.ParcelleValue;
import eu.epfc.anc3.vm.ParcelleViewModel;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ParcelleView extends StackPane {
    private static final Image FARMER = new Image("farmer.png");
    private static final Image DIRT = new Image("dirt.png");
    private static final Image GRASS = new Image("grass.png");
    private static final Image CABBAGE = new Image("cabbage1.png");
    private static final Image CARROT = new Image("carrot1.png");



    private ImageView imageView = new ImageView();

    private static final Map<ParcelleValue, Image> images = new HashMap<>();
    static {
        images.put(ParcelleValue.EMPTY, DIRT);
        images.put(ParcelleValue.DIRT, DIRT);
        images.put(ParcelleValue.GRASS, GRASS);
        images.put(ParcelleValue.FARMER, FARMER);
        images.put(ParcelleValue.CABBAGE, CABBAGE);
        images.put(ParcelleValue.CARROT, CARROT);
    }


    public ParcelleView(ParcelleViewModel parcelleViewModel, DoubleBinding parcelleWidthProperty) {
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(parcelleWidthProperty);
        imageView.fitHeightProperty().bind(parcelleWidthProperty);
        imageView.setImage(DIRT);
        getChildren().add(imageView);

        ObservableSet<Element> valueProp = parcelleViewModel.getElementsInCell();

        valueProp.addListener((SetChangeListener<Element>) change -> {
            ParcelleView.this.getChildren().clear();
            addParcelleImage(ParcelleValue.EMPTY);
            for(Element e : valueProp) {
                addParcelleImage(e.getType());
            }
        });

        this.setOnMouseClicked(e -> parcelleViewModel.play());
    }

    void addParcelleImage(ParcelleValue pv) {
        ImageView imageView = new ImageView(images.get(pv));
        imageView.setFitWidth(this.imageView.getFitWidth());
        imageView.setFitHeight(this.imageView.getFitHeight());
        getChildren().add(imageView);
    }

}
