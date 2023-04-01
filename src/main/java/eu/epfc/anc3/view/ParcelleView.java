package eu.epfc.anc3.view;

import eu.epfc.anc3.model.Cabbage;
import eu.epfc.anc3.model.Carrot;
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
    private static final Image CABBAGE2 = new Image("cabbage2.png");
    private static final Image CABBAGE3 = new Image("cabbage3.png");
    private static final Image CABBAGE4 = new Image("cabbage4.png");
    private static final Image CABBAGEROTTEN = new Image("rotten_cabbage.png");
    private static final Image CARROT = new Image("carrot1.png");
    private static final Image CARROT2 = new Image("carrot2.png");
    private static final Image CARROT3 = new Image("carrot3.png");
    private static final Image CARROT4 = new Image("carrot4.png");
    private static final Image CARROTROTTEN = new Image("rotten_carrot.png");



    private ImageView imageView = new ImageView();

    private static final Map<ParcelleValue, Image> images = new HashMap<>();
    static {
        images.put(ParcelleValue.EMPTY, DIRT);
        images.put(ParcelleValue.DIRT, DIRT);
        images.put(ParcelleValue.GRASS, GRASS);
        images.put(ParcelleValue.FARMER, FARMER);
        images.put(ParcelleValue.CABBAGE1, CABBAGE);
        images.put(ParcelleValue.CABBAGE2, CABBAGE2);
        images.put(ParcelleValue.CABBAGE3, CABBAGE3);
        images.put(ParcelleValue.CABBAGE4, CABBAGE4);
        images.put(ParcelleValue.ROTTEN_CABBAGE, CABBAGEROTTEN);
        images.put(ParcelleValue.CARROT1, CARROT);
        images.put(ParcelleValue.CARROT2, CARROT2);
        images.put(ParcelleValue.CARROT3, CARROT3);
        images.put(ParcelleValue.CARROT4, CARROT4);
        images.put(ParcelleValue.ROTTEN_CARROT, CARROTROTTEN);
    }



    public ParcelleView(ParcelleViewModel parcelleViewModel, DoubleBinding parcelleWidthProperty) {
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(parcelleWidthProperty);
        imageView.fitHeightProperty().bind(parcelleWidthProperty);
        imageView.setImage(DIRT);
        getChildren().add(imageView);

        ObservableSet<Element> valueProp = parcelleViewModel.getElementsInCell();

        // Ajouter un écouteur de changement d'état à chaque CarrotViewModel
       valueProp.addListener((SetChangeListener<Element>) change -> {
            ParcelleView.this.getChildren().clear();
            addParcelleImage(ParcelleValue.EMPTY);
            for(Element e : valueProp) {
                if (e.getType() != ParcelleValue.CABBAGE1  && e.getType() != ParcelleValue.CARROT1)
                    addParcelleImage(e.getType());
                if (e instanceof Carrot) {
                    Carrot c = (Carrot) e;
                    c.addStateListener((obs, oldState, newState) -> {
                        ParcelleValue pv = newState.getType();
                        Node child = ParcelleView.this.getChildren().get(1);
                        if (getParcelleValueFromImage(((ImageView) child).getImage()) != ParcelleValue.GRASS) {
                            ParcelleView.this.getChildren().remove(1);
                        }else {
                            ParcelleView.this.getChildren().remove(2);
                        }
                        addParcelleImage(pv);
                    });
                }
                if (e instanceof Cabbage) {
                    Cabbage c = (Cabbage) e;
                    c.addStateListener((obs, oldState, newState) -> {
                        ParcelleValue pv = newState.getType();
                        Node child = ParcelleView.this.getChildren().get(1);
                        if (getParcelleValueFromImage(((ImageView) child).getImage()) != ParcelleValue.GRASS) {
                            ParcelleView.this.getChildren().remove(1);
                        }else {
                            ParcelleView.this.getChildren().remove(2);
                        }
                        addParcelleImage(pv);
                    });
                }
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
    public static ParcelleValue getParcelleValueFromImage(Image image) {
        String imageName = image.getUrl().substring(image.getUrl().lastIndexOf("/") + 1);
        imageName = imageName.substring(0, imageName.indexOf("."));
        return ParcelleValue.valueOf(imageName.toUpperCase());
    }

}
