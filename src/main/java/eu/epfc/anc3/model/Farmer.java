package eu.epfc.anc3.model;
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;

public class Farmer {

    private final Position posFarmer;
    public static Grass plantedGrass;

//    public final List<Grass> listOfPlantedGrass1 = new ArrayList<>();

    public static final SimpleListProperty<Grass> listOfPlantedGrass = new SimpleListProperty<>(FXCollections.observableArrayList());

    //doit etre observable ou listProperty (SimpleListProperty)
    //listProperty a une sizeProperty

    private final StringProperty fermier = new SimpleStringProperty("");
    public Farmer() {
        posFarmer = new Position(0, 0);
    }
    public void setPosFarmer(int x, int y){
        posFarmer.setPosX(x);
        posFarmer.setPosY(y);
    }
    public Position getPosFarmer () {
        return posFarmer;
    }

        public static Grass getPlantedGrass () {
            return plantedGrass;
        }

    public ReadOnlyIntegerProperty nbgrass(){return listOfPlantedGrass.sizeProperty();}
    @Override
    public String toString() {
        return "Farmer{" +
                "posFarmer=" + posFarmer +
                ", listOfPlantedGrass=" + listOfPlantedGrass +
                ", fermier=" + fermier +
                '}';
    }

    public void plantGrass() {
        listOfPlantedGrass.add(new Grass(posFarmer));
    }


    public boolean hasPlantedGrass() {
        return !listOfPlantedGrass.isEmpty();
    }
}

