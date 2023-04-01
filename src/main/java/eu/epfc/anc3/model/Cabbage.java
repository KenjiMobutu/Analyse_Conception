package eu.epfc.anc3.model;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

//k:trouver une solution pour retirer public de la classe
public class Cabbage extends Vegetable implements Element {
    private ReadOnlyObjectWrapper<VegetableState> state = new ReadOnlyObjectWrapper<>();
    private final int maxScore = 200;

    public Cabbage() {
        super();
        this.setState(new CabbageState1(this));
        System.out.println("Cabbage created");
    }
    @Override
    public ParcelleValue getType(){return ParcelleValue.CABBAGE1;}
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
        return "Chou";
    }

    public VegetableState getCurrentState() {
        return getState();
    }
    /*----------------------------------------------------------------------------------------*/

    //Level1
    class CabbageState1 extends VegetableState {
        private int nbJours;
        private static final int daysToNextState = 5;
        CabbageState1(Vegetable vegetable) {
            super(vegetable);
            nbJours = 0;
            System.out.println("Cabbage State 1 created");
        }

        @Override
        public void nextState() {
            vegetable.setCurrentState(new CabbageState2(vegetable));
            System.out.println("Cabbage state 1 changed to state 2");
            Cabbage.this.getType();
            System.out.println(getType() + " TYPE");
        }

        @Override
        public int getHarvestPoints() {
            return 0;
        }

        @Override
        public void nextDay() {
            nbJours++;
           /* if (vegetable.getDaysSincePlanted() >= growthDays) {
                vegetable.setCurrentState(new CabbageState2(vegetable));
            }*/
            System.out.println("nbJours = " + nbJours);
            if (nbJours == daysToNextState) {
                this.nextState();
            }
        }
        @Override
        public ParcelleValue getType() {
            return ParcelleValue.CABBAGE1;
        }

        @Override
        public void nextDayWithGrass() {
            nbJours++;
            System.out.println("nbJours = " + nbJours);
            if (nbJours == daysToNextState-1) {
                this.nextState();
            }
        }

        @Override
        public int stateProperty() {
            return 1;
        }

    }

    //Level2
    class CabbageState2 extends VegetableState{
        private int nbJours;
        private int daysToNextState = 9;
        CabbageState2(Vegetable vegetable) {
            super(vegetable);
            vegetable.setState(this);
            System.out.println(getCurrentState().toString() + " ETAT" );
            nbJours = 5;
            System.out.println("Cabbage state 2 created");
        }

        @Override
        public void nextState() {
            System.out.println("Cabbage state 2 changed to state 3");
            vegetable.setState(new Cabbage.CabbageState3(vegetable));
            Cabbage.this.getType();
            System.out.println(getType() + " TYPE");
        }

        @Override
        public int getHarvestPoints() {
            return 0;
        }

        @Override
        public void nextDay() {
            nbJours++;
            System.out.println("nbJours = " + nbJours);
            if (nbJours == daysToNextState) {
                this.nextState();
            }
        }

        @Override
        public ParcelleValue getType() {
            return ParcelleValue.CABBAGE2;
        }

        @Override
        public void nextDayWithGrass() {
            nbJours++;
            System.out.println("nbJours = " + nbJours);
            if (nbJours == daysToNextState-1) {
                this.nextState();
            }
        }

        @Override
        public int stateProperty() {
            return 2;
        }

        public VegetableState getCurrentState() {
            return getState();
        }


    }

    //Level3
    class CabbageState3 extends VegetableState {
        private int nbJours;
        private int daysToNextState = 12;
        CabbageState3(Vegetable vegetable) {
            super(vegetable);
            vegetable.setState(this);
            System.out.println(getCurrentState().toString() + " ETAT" );
            nbJours = 9;
            System.out.println("Cabbage state 3 created");
        }

        @Override
        public void nextState() {
            System.out.println("Cabbage state 3 changed to state 4");
            vegetable.setState(new Cabbage.CabbageState4(vegetable));
            Cabbage.this.getType();
            System.out.println(getType() + " TYPE");
        }

        @Override
        public int getHarvestPoints() {
            return 3 * (maxScore/4);
        }

        @Override
        public void nextDay() {
            nbJours++;
            System.out.println("nbJours = " + nbJours);
            if (nbJours == daysToNextState) {
                this.nextState();
            }
        }

        @Override
        public ParcelleValue getType() {
            return ParcelleValue.CABBAGE3;
        }

        @Override
        public void nextDayWithGrass() {
            nbJours++;
            System.out.println("nbJours = " + nbJours);
            if (nbJours == daysToNextState-1) {
                this.nextState();
            }
        }

        @Override
        public int stateProperty() {
            return 3;
        }

        public VegetableState getCurrentState() {
            return getState();
        }

    }

    //Level4
    class CabbageState4 extends VegetableState{
        private int nbJours;
        private int daysToNextState = 14;
        CabbageState4(Vegetable vegetable) {
            super(vegetable);
            vegetable.setState(this);
            System.out.println(getCurrentState().toString() + " ETAT" );
            nbJours = 12;
            System.out.println("Cabbage state 4 created");
        }

        @Override
        public void nextState() {
            System.out.println("Carrot state 4 changed to state ROTTEN");
            vegetable.setState(new CabbageRottenState(vegetable));
            Cabbage.this.getType();
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
            return ParcelleValue.CABBAGE4;
        }

        @Override
        public void nextDayWithGrass() {
            nbJours++;
            System.out.println("nbJours = " + nbJours);
            if (nbJours == daysToNextState-1) {
                this.nextState();
            }
        }

        @Override
        public int stateProperty() {
            return 4;
        }

        public VegetableState getCurrentState() {
            return getState();
        }

    }

    //Level5 --> Pourrie
    class CabbageRottenState extends VegetableState {
        private int nbJours;
        int maxGrowthDays = 24;
        CabbageRottenState(Vegetable vegetable) {
            super(vegetable);
            vegetable.setState(this);
            System.out.println(getCurrentState().toString() + " ETAT" );
            growthDays = 12;
            System.out.println("Cabbage state ROTTEN created");
        }

        @Override
        public void nextState() {

        }

        @Override
        public int getHarvestPoints() {

            return (int) ((1.0 / 10.0) * maxScore * (growthDays - nbJours - 12));
        }

        @Override
        public void nextDay() {
            nbJours++;
            System.out.println("nbJours = " + nbJours);
            if (nbJours == maxGrowthDays) {
                vegetable.setCurrentState(new CabbageRottenState(vegetable));
            }
        }

        @Override
        public ParcelleValue getType() {
            return ParcelleValue.ROTTEN_CABBAGE;
        }

        @Override
        public void nextDayWithGrass() {
            nbJours++;
            System.out.println("nbJours = " + nbJours);
            if (nbJours == maxGrowthDays / 2) {
                vegetable.setCurrentState(new CabbageRottenState(vegetable));
            }
        }

        @Override
        public int stateProperty() {
            return 5;
        }

        public VegetableState getCurrentState() {
            return getState();
        }


    }
}
