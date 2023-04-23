package eu.epfc.anc3.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;

public class Strawberry extends Vegetable implements Element{
    private final IntegerProperty nbJours = new SimpleIntegerProperty(0);
    private boolean stateChanged = false;
    private final ReadOnlyObjectWrapper<VegetableState> state = new ReadOnlyObjectWrapper<>();
    private final int maxScore = 500;
    private Parcelle parcelle ;
    public Strawberry(Parcelle parcelle) {
        super();
        setParcelle(parcelle);
        nbJours.addListener((obs, oldVal, newVal) -> {
            System.out.println("++day");
            this.getCurrentState().nextDay();
        });
        this.setState(new StrawberryState1(this));
    }

    IntegerProperty nbJoursProperty() {
        return nbJours;
    }
    @Override
    public ParcelleValue getType() {
        return state.get().getType();
    }

    @Override
    public boolean getStateChanged() {
        return stateChanged;
    }

    @Override
    public void setStateChanged(boolean stateChanged) {
        this.stateChanged = stateChanged;
    }

    @Override
    public boolean isRotten() {
        return false;
    }

    @Override
    public boolean isVegetable() {
        return true;
    }

    @Override
    public boolean canBeFetilize() {
        return false;
    }

    @Override
    public Parcelle getParcelle() {
        return parcelle;
    }

    @Override
    public boolean isGrass() {
        return false;
    }
    @Override
    public String toString() {
        return "Fraise";
    }
    public void setState(VegetableState newState) {
        state.set(newState);
    }
    public VegetableState getState() {
        return state.get();
    }
    void setParcelle(Parcelle parcelle){
        this.parcelle = parcelle;
    }
    public VegetableState getCurrentState() {
        return getState();
    }

    public class StrawberryState1 extends VegetableState {
        private int nbJours;
        private static final int daysToNextState = 5;
        public StrawberryState1(Vegetable vegetable) {
            super(vegetable);
            nbJours = 0;
            System.out.println("Strawberry State 1 created");
        }

        @Override
        public void nextState() {
            vegetable.setCurrentState(new StrawberryState2(vegetable));
            System.out.println("STRAWBERRY state 1 changed to state 2");
            Strawberry.this.getType();
            parcelle.setStateChange(true);
            System.out.println(getType() + " TYPE");
        }

        @Override
        public int getHarvestPoints() {
            return maxScore/3;
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
            return ParcelleValue.STRAWBERRY1;
        }

        @Override
        public void nextDayWithGrass() {

        }

        @Override
        public int stateProperty() {
            return 1;
        }

        @Override
        public boolean isRotten() {
            return false;
        }
    }

    public class StrawberryState2 extends VegetableState {
        private int nbJours;

        public StrawberryState2(Vegetable vegetable) {
            super(vegetable);
            parcelle.setStateChange(false);
            vegetable.setState(this);
            System.out.println(getCurrentState().toString() + " ETAT" );
            nbJours = 5;
            System.out.println("Strawberry State 2 created");
        }
        @Override
        public void nextState() {
            System.out.println("STRAWBERRY state 2 changed to state 3");
            vegetable.setState(new Strawberry.StrawberryState3(vegetable));
            Strawberry.this.getType();
            parcelle.setStateChange(true);
            System.out.println(getType() + " TYPE");
        }

        @Override
        public int getHarvestPoints() {
            return maxScore/2;
        }

        @Override
        public void nextDay() {
            nbJours++;
            System.out.println("nbJours = " + nbJours);
            int daysToNextState = 10;
            if (nbJours == daysToNextState) {
                this.nextState();
            }
        }

        @Override
        public ParcelleValue getType() {
            return ParcelleValue.STRAWBERRY2;
        }

        @Override
        public void nextDayWithGrass() {

        }

        @Override
        public int stateProperty() {
            return 2;
        }

        @Override
        public boolean isRotten() {
            return false;
        }
    }
    public class StrawberryState3 extends VegetableState {
        private int nbJours;
        public StrawberryState3(Vegetable vegetable) {
            super(vegetable);
            vegetable.setState(this);
            parcelle.setStateChange(false);
            System.out.println(getCurrentState().toString() + " ETAT" );
            nbJours = 10;
            System.out.println("Strawberry State 3 created");
        }

        @Override
        public void nextState() {}

        @Override
        public int getHarvestPoints() {
            return maxScore;
        }

        @Override
        public void nextDay() {
            nbJours++;
            System.out.println("nbJours = " + nbJours);
        }

        @Override
        public ParcelleValue getType() {
            return ParcelleValue.STRAWBERRY3;
        }

        @Override
        public void nextDayWithGrass() {

        }

        @Override
        public int stateProperty() {
            return 3;
        }

        @Override
        public boolean isRotten() {
            return false;
        }
    }
}
