package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Parcelle {
    //cellule
    private final ObjectProperty<ParcelleValue> value = new SimpleObjectProperty<>(ParcelleValue.EMPTY);

//    private final ImageView imageView;
    public Parcelle(){

//        imageView = new ImageView(new Image(getClass().getResourceAsStream("/ressources/dirt.png")));
    }
    ParcelleValue getValue() {
        return value.getValue();
    }
    /*boolean setValue(ParcelleValue value) {
        if(this.value.get() !=ParcelleValue.EMPTY)
            this.value.setValue(value); //enleve grass
        this.value.setValue(value); //met grass;.
        return true;
    }*/
    boolean setValue(ParcelleValue value){
        this.value.setValue(value);
        return true;
    }

//    void setValuE(ParcelleValue value){
//        this.value.setValue(value);
//    }

    ReadOnlyObjectProperty<ParcelleValue> valueProperty() {
        return value;
    }

    boolean isEmpty() {
        return value.get() == ParcelleValue.EMPTY;
    }



}
