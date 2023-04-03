package eu.epfc.anc3.model;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.GridPane;

//k:trouver une solution pour retirer public de la classe
public class Carrot extends Vegetable implements Element {
    //private IntegerProperty nbJours = new SimpleIntegerProperty(0);
    private int line,col;

    private ReadOnlyObjectWrapper<VegetableState> state = new ReadOnlyObjectWrapper<>();
    private final int maxScore = 100;
    public Carrot(int line, int col) {
        super();
        this.line = line; this.col = col;
        this.setState(new CarrotState1(this));
    }
    @Override
    public ParcelleValue getType() {
        return state.get().getType();
    }
    public void addStateListener(ChangeListener<VegetableState> listener) {
        stateProperty().addListener(listener);
    }

    public void setState(VegetableState newState) {
        state.set(newState);
    }
    private ObservableValue<VegetableState> stateProperty() {
        return state.getReadOnlyProperty();
    }
    public VegetableState getState() {
        return state.get();
    }

    @Override
    public String toString() {
        return "Carotte";
    }
    public VegetableState getCurrentState() {
        return getState();
    }

    /*----------------------------------------------------------------------------------------*/

    //Level1
    public class CarrotState1 extends VegetableState {
        private int nbJours;
        private static final int daysToNextState = 3;

        public CarrotState1(Vegetable vegetable) {
            super(vegetable);
            //this.setState(new CarrotState1(this));
            nbJours = 0;
            //growthDays = 0;
            System.out.println("Carrot State 1 created");
        }

        public VegetableState getCurrentState() {
            return getState();
        }
        public int stateProperty(){
            return 1;
        }

        @Override
        public void nextState() {
            vegetable.setCurrentState(new CarrotState2(vegetable));
            System.out.println("Carrot state 1 changed to state 2");
            Carrot.this.getType();
            System.out.println(getType() + " TYPE");
        }

        public ParcelleValue getType() {
            return ParcelleValue.CARROT1;
        }

        @Override
        public void nextDayWithGrass() {

        }

        @Override
        public int getHarvestPoints() {
            //IntergerProperty.get() : pour récuper la valeur de la propriété sous forme d'entier.
            return maxScore / 10;
        }

        @Override
        public void nextDay() {
            nbJours++;
            System.out.println("nbJours = " + nbJours);
            if (nbJours == daysToNextState) {
                this.nextState();
            }
        }
    }

    //Level2
    public class CarrotState2 extends VegetableState{
        private int nbJours;
        private int daysToNextState = 6;
        public CarrotState2(Vegetable vegetable) {
            super(vegetable);
            vegetable.setState(this);
            System.out.println(getCurrentState().toString() + " ETAT" );
            nbJours = 3;
            System.out.println("Carrot state 2 created");
        }

        public int stateProperty(){
            return 2;
        }

        @Override
        public void nextState() {
            System.out.println("Carrot state 2 changed to state 3");
            vegetable.setState(new CarrotState3(vegetable));
            Carrot.this.getType();
            System.out.println(getType() + " TYPE");
        }


        @Override
        public int getHarvestPoints() {
            return maxScore / 5;
        }

        @Override
        public void nextDay() {
            nbJours++;
            System.out.println("nbJours = " + nbJours);
            if (nbJours == daysToNextState) {
                this.nextState();
            }
        }
        public ParcelleValue getType() {
            return ParcelleValue.CARROT2;
        }

        @Override
        public void nextDayWithGrass() {

        }

        public VegetableState getCurrentState() {
            return getState();
        }

    }

    //Level3
    public class CarrotState3 extends VegetableState{
        private int nbJours;
        private int daysToNextState = 9;
        public CarrotState3(Vegetable vegetable) {
            super(vegetable);
            vegetable.setState(this);
            System.out.println(getCurrentState().toString() + " ETAT" );
            nbJours = 6;
            System.out.println("Carrot state 3 created");
        }
        public int stateProperty(){
            return 3;
        }
        @Override
        public void nextState() {
            System.out.println("Carrot state 3 changed to state 4");
            setState(new CarrotState4(vegetable));
            Carrot.this.getType();
            System.out.println(getType() + " TYPE");
        }

        @Override
        public int getHarvestPoints() {
            return maxScore / 2 ;
        }

        @Override
        public void nextDay() {
            nbJours++;
            System.out.println("nbJours = " + nbJours);
            if ( nbJours == daysToNextState) {
                System.out.println("Carrot state 3 changed to state 4");
                this.nextState();
            }
        }
        public ParcelleValue getType() {
            return ParcelleValue.CARROT3;
        }

        @Override
        public void nextDayWithGrass() {

        }

        public VegetableState getCurrentState() {
            return getState();
        }
    }

    //Level4
    public class CarrotState4 extends VegetableState{
        private int nbJours;
        private int daysToNextState =  12;
        public CarrotState4(Vegetable vegetable) {
            super(vegetable);
            vegetable.setState(this);
            System.out.println(getCurrentState().toString() + " ETAT" );
            nbJours = 9;
            System.out.println("Carrot state 4 created");
        }
        public int stateProperty(){
            return 4;
        }
        @Override
        public void nextState() {
            System.out.println("Carrot state 4 changed to state ROTTEN");
            vegetable.setState(new CarrotState5(vegetable));
            Carrot.this.getType();
            System.out.println(getType() + " TYPE");
        }

        @Override
        public int getHarvestPoints() {
            return maxScore;
        }

        @Override
        public void nextDay() {
            nbJours++;
            System.out.println("nbJours = " + nbJours);
            if (nbJours == daysToNextState) {
                this.nextState();
            }
        }
        public ParcelleValue getType() {
            return ParcelleValue.CARROT4;
        }

        @Override
        public void nextDayWithGrass() {

        }

        public VegetableState getCurrentState() {
            return getState();
        }
    }

    //Level5-->pourri
    public class CarrotState5 extends VegetableState{
        private int nbJours;
        int maxGrowthDays = 22;
        public CarrotState5(Vegetable vegetable) {
            super(vegetable);
            vegetable.setState(this);
            System.out.println(getCurrentState().toString() + " ETAT" );
            growthDays = 12;
            System.out.println("Carrot POURRIE created");
        }

        @Override
        public void nextState() {

        }
        public int stateProperty(){
            return 5;
        }
        @Override
        public int getHarvestPoints() {
            //points_perdus = 1/10 * rendement_maximal * (jour de la récolte - premier jour du stade Pourri)

            return (int) ((1.0 / 10.0) * maxScore * (growthDays - nbJours - 12));
        }

        @Override
        public void nextDay() {
            nbJours++;
            System.out.println("nbJours = " + nbJours);
            if (nbJours == maxGrowthDays) {
                //recolte obligatoire
            }
        }
        public ParcelleValue getType() {
            return ParcelleValue.ROTTEN_CARROT;
        }

        @Override
        public void nextDayWithGrass() {

        }

        public VegetableState getCurrentState() {
            return getState();
        }
    }
}


