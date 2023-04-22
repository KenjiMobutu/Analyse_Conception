package eu.epfc.anc3.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;

public class Corn extends Vegetable implements Element{
    private final IntegerProperty nbJours = new SimpleIntegerProperty(0);
    private boolean stateChanged = false;
    private final ReadOnlyObjectWrapper<VegetableState> state = new ReadOnlyObjectWrapper<>();
    private final int maxScore = 400;
    Parcelle parcelle;
    public Corn(Parcelle parcelle) {
        super();
       setParcelle(parcelle);
        nbJours.addListener((obs, oldVal, newVal) -> {
            System.out.println("++day");
            this.getCurrentState().nextDay();
        });
        this.setState(new CornState1(this));
    }
    @Override
    public ParcelleValue getType() {
        return state.get().getType();
    }
    IntegerProperty nbJoursProperty() {
        return nbJours;
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
    public boolean canBeFetilize() {return false;}

    @Override
    public Parcelle getParcelle() {return parcelle;}

    @Override
    public boolean isGrass() {return false;}
    void setParcelle(Parcelle parcelle){
        this.parcelle = parcelle;
    }


    public void setState(VegetableState newState) {
        state.set(newState);
    }
    public VegetableState getState() {
        return state.get();
    }
    @Override
    public String toString() {
        return "Maïs";
    }
    public VegetableState getCurrentState() {
        return getState();
    }

    public class CornState1 extends VegetableState{
        private int nbJours;
        private static final int daysToNextState = 4;
        public CornState1(Vegetable vegetable) {
            super(vegetable);
            nbJours = 0;
            System.out.println("CornState1 created");
        }
        public int stateProperty(){
            return 1;
        }

        @Override
        public void nextState() {
            vegetable.setCurrentState(new CornState2(vegetable));
            System.out.println("CORN state 1 changed to state 2");
            Corn.this.getType();
            parcelle.setStateChange(true);
            System.out.println(getType() + " TYPE");
        }

        @Override
        public int getHarvestPoints() {
            return maxScore / 4;
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
            return ParcelleValue.CORN1;
        }

        @Override
        public void nextDayWithGrass() {

        }


        @Override
        public boolean isRotten() {
            return false;
        }
    }
    public class CornState2 extends VegetableState {
        private int nbJours;
        public CornState2(Vegetable vegetable) {
            super(vegetable);
            parcelle.setStateChange(false);
            vegetable.setState(this);
            System.out.println(getCurrentState().toString() + " ETAT" );
            nbJours = 4;
            System.out.println("CORN state 2 created");
        }

        @Override
        public void nextState() {
            System.out.println("Carrot state 2 changed to state 3");
            vegetable.setState(new Corn.CornState3(vegetable));
            Corn.this.getType();
            parcelle.setStateChange(true);
            System.out.println(getType() + " TYPE");
        }

        @Override
        public int getHarvestPoints() {
            return maxScore / 3;
        }

        @Override
        public void nextDay() {
            nbJours++;
            System.out.println("nbJours = " + nbJours);
            int daysToNextState = 6;
            if (nbJours == daysToNextState) {
                this.nextState();
            }
        }

        @Override
        public ParcelleValue getType() {
            return ParcelleValue.CORN2;
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
    public class CornState3 extends VegetableState {
        private int nbJours;
        public CornState3(Vegetable vegetable) {
            super(vegetable);
            vegetable.setState(this);
            parcelle.setStateChange(false);
            System.out.println(getCurrentState().toString() + " ETAT" );
            nbJours = 8;
            System.out.println("Carrot state 3 created");
        }

        @Override
        public void nextState() {
            System.out.println("CORN state 3 changed to state 4");
            vegetable.setState(new Corn.CornState4(vegetable));
            Corn.this.getType();
            parcelle.setStateChange(true);
            System.out.println(getType() + " TYPE");
        }

        @Override
        public int getHarvestPoints() {
            return maxScore / 2;
        }

        @Override
        public void nextDay() {
            nbJours++;
            System.out.println("nbJours = " + nbJours);
            int daysToNextState = 10;
            if ( nbJours == daysToNextState) {
                System.out.println("CORN state 3 changed to state 4");
                this.nextState();
            }
        }

        @Override
        public ParcelleValue getType() {
            return ParcelleValue.CORN3;
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
    public class CornState4 extends VegetableState {
        private int nbJours;
        public CornState4(Vegetable vegetable) {
            super(vegetable);
            vegetable.setState(this);
            parcelle.setStateChange(false);
            System.out.println(getCurrentState().toString() + " ETAT" );
            nbJours = 12;
        }

        @Override
        public void nextState() {

        }

        @Override
        public int getHarvestPoints() {
            return maxScore;
        }

        @Override
        public void nextDay() {

        }

        @Override
        public ParcelleValue getType() {
            return ParcelleValue.CORN4;
        }

        @Override
        public void nextDayWithGrass() {

        }

        @Override
        public int stateProperty() {
            return 0;
        }

        @Override
        public boolean isRotten() {
            return false;
        }
        public VegetableState getCurrentState() {
            return getState();
        }
    }
}
