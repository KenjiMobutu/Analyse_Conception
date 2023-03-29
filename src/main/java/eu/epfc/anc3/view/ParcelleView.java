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

import java.util.Set;

public class ParcelleView extends StackPane {
    private static final Image FARMER = new Image("farmer.png");
    private static final Image DIRT = new Image("dirt.png");
    private static final Image GRASS = new Image("grass.png");
    private static final Image CABBAGE = new Image("cabbage1.png");
    private static final Image CARROT = new Image("carrot1.png");



    private ImageView imageView = new ImageView();



    public ParcelleView(ParcelleViewModel parcelleViewModel, DoubleBinding parcelleWidthProperty) {
        //imageView = new ImageView();

        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(parcelleWidthProperty);
        imageView.fitHeightProperty().bind(parcelleWidthProperty);
        imageView.setImage(DIRT);
        getChildren().add(imageView);

        //un listener sur l'ObservableSet<Element> et dedans parcourir ce truc en appelant getType() sur chaque element
        ObservableSet<Element> valueProp = parcelleViewModel.getElementsInCell();

        valueProp.addListener((SetChangeListener<Element>) change -> {
            //vider tous les children
            ParcelleView.this.getChildren().clear();
            addParcelleImage(ParcelleValue.EMPTY);
            for(Element e  : valueProp){
                addParcelleImage(e.getType());
                //retrouver l'image de l'élement
                //creer un nouvel imageView avec cette image
                //ajouter cette imageView aux children
            }
            //System.out.println(parcelleViewModel.line + " " + parcelleViewModel.col + " " +valueProp);
            //System.out.println(parcelleViewModel.line + " " + parcelleViewModel.col + " " +ParcelleView.this.getChildren());
            //pour chaque element de valueProp, creer une imageview et l'ajouter aux childrens'

        });
        this.setOnMouseClicked(e -> parcelleViewModel.play());
    }

    void addParcelleImage(ParcelleValue pv){
        switch (pv){
            case EMPTY :
            case DIRT:
                addDirt();
                break;
            case GRASS:
                addGrass();
                break;
            case FARMER:
                addFarmer();
                break;
            case CABBAGE:
                addCabbage();
                break;
            case CARROT:
                addCarrot();
                break;
        }
    }

    private void addCarrot() {
        ImageView carrot = new ImageView(CARROT);
        carrot.setFitWidth(imageView.getFitWidth());
        carrot.setFitHeight(imageView.getFitHeight());
        getChildren().add(carrot);
    }

    private void addCabbage() {
        ImageView cabbage = new ImageView(CABBAGE);
        cabbage.setFitWidth(imageView.getFitWidth());
        cabbage.setFitHeight(imageView.getFitHeight());
        getChildren().add(cabbage);
    }

    void addDirt(){
        ImageView dirt = new ImageView(DIRT);
        dirt.setFitWidth(imageView.getFitWidth());
        dirt.setFitHeight(imageView.getFitHeight());
        getChildren().add(dirt);
    }
    void addGrass(){
        ImageView grass = new ImageView(GRASS);
        grass.setFitWidth(imageView.getFitWidth());
        grass.setFitHeight(imageView.getFitHeight());
        getChildren().add(grass);
    }

    void addFarmer(){
        ImageView farmer = new ImageView(FARMER);
        farmer.setFitWidth(imageView.getFitWidth());
        farmer.setFitHeight(imageView.getFitHeight());
        getChildren().add(farmer);
    }
}
