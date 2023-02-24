package eu.epfc.anc3.view;
import eu.epfc.anc3.model.Element;
import eu.epfc.anc3.model.FermeStatus;
import eu.epfc.anc3.model.ParcelleValue;
import eu.epfc.anc3.vm.ParcelleViewModel;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
public class ParcelleView extends StackPane {
    private static final Image FARMER = new Image("farmer.png");
    private static final Image DIRT = new Image("dirt.png");
    private static final Image GRASS = new Image("grass.png");

    private final ImageView imageView = new ImageView();


    public ParcelleView(ParcelleViewModel parcelleViewModel, DoubleBinding parcelleWidthProperty) {
        imageView.setPreserveRatio(false); // permet de garder son aspect meme en étant redimensionnée
        imageView.fitWidthProperty().bind(parcelleWidthProperty);//resize image to cellWidthpropery
        imageView.fitHeightProperty().bind(parcelleWidthProperty);
        getChildren().add(imageView);
        setParcelleImage(imageView,parcelleViewModel.valueProperty().getValue());


        imageView.autosize();
        ReadOnlyObjectProperty<ParcelleValue> valueProp = parcelleViewModel.valueProperty();
        valueProp.addListener((obs, old, newVal) -> setParcelleImage(imageView,newVal));
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
                if (imageView.getImage() == null || imageView.getImage() == DIRT){
                    imageView.setImage(GRASS);
//                    imageView = new ImageView(GRASS);
                    imageView.setPreserveRatio(false);
//                    imageView.setFitHeight(50);
//                    imageView.setFitWidth(50);
                }
                children.add(imageView);
                break;
            case FARMER:
                ImageView farmer = new ImageView(FARMER);
                farmer.setPreserveRatio(false);
                farmer.setFitHeight(20);
                farmer.setFitWidth(20);
                System.out.println(imageView.getImage() + " <-- IMAAAAAAAAAAAAAAAAAAAAAAAAAGE");
                if (imageView.getImage() != GRASS){
                    ImageView dirt = new ImageView(DIRT);
                    dirt.setPreserveRatio(false);
                    dirt.setFitHeight(25);
                    dirt.setFitWidth(25);
                    children.addAll(dirt, farmer);
                    break;
                }else{
                    ImageView grass = new ImageView(GRASS);
                    grass.setPreserveRatio(false);
                    grass.setFitHeight(20);
                    grass.setFitWidth(20);
                    children.addAll(grass, farmer);
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




}
