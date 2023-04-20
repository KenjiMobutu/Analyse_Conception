package eu.epfc.anc3.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;

public class PurpleCarrot extends Vegetable implements Element {
    private final IntegerProperty nbJours = new SimpleIntegerProperty(0);
    private boolean stateChanged = false;
    private final ReadOnlyObjectWrapper<VegetableState> state = new ReadOnlyObjectWrapper<>();
    private final int maxScore = 600;
    private Parcelle parcelle ;


    public PurpleCarrot(Parcelle parcelle){
        super();
        setParcelle(parcelle);
        nbJours.addListener((obs,oldVal, newVal) -> {
            System.out.println("Special ++ days");
            this.getCurrentState().nextDay();
        });

        this.setState(new PurpleCarrotState1(this));
    }
    void setParcelle(Parcelle parcelle){
        this.parcelle = parcelle;
    }
    IntegerProperty nbJoursProperty() {
        return nbJours;
    }
    public boolean getStateChanged() {
        return stateChanged;
    }

    @Override
    public boolean canBeFetilize() {
        return true;
    }

    @Override
    public ParcelleValue getType() {
        return state.get().getType();
    }

    @Override
    public void setStateChanged(boolean stateChanged) {
        this.stateChanged = stateChanged;
    }


    @Override
    public boolean isRotten() {
        return this.getState().isRotten();
    }

    @Override
    public boolean isVegetable() {
        return true;
    }

    public void setState(VegetableState newState) {
        state.set(newState);
    }
    @Override
    public Parcelle getParcelle() {
        return null;
    }
    public VegetableState getState() {
        return state.get();
    }
    @Override
    public boolean isGrass() {
        return false;
    }

    @Override
    public boolean isBlock() {
        return false;
    }

    public VegetableState getCurrentState() {
        return getState();
    }


    //Level1
    public class PurpleCarrotState1 extends VegetableState {
        private int nbJours;
        private static final int daysToNextState = 3;

        public PurpleCarrotState1(Vegetable vegetable) {
            super(vegetable);
            //this.setState(new CarrotState1(this));
            nbJours = 0;
            //growthDays = 0;
            System.out.println("Carrot State 1 created");
        }

        public int stateProperty(){
            return 1;
        }

        @Override
        public boolean isRotten() {
            return false;
        }

        @Override
        public void nextState() {
            vegetable.setCurrentState(new PurpleCarrot.PurpleCarrotState2(vegetable));
            System.out.println("Carrot state 1 changed to state 2");
            PurpleCarrot.this.getType();
            parcelle.setStateChange(true);
            System.out.println(getType() + " TYPE");
        }

        public ParcelleValue getType() {
            return ParcelleValue.YELLOWTHING1;
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
    public class PurpleCarrotState2 extends VegetableState{
        private int nbJours;

        public PurpleCarrotState2(Vegetable vegetable) {
            super(vegetable);
            parcelle.setStateChange(false);
            vegetable.setState(this);
            System.out.println(getCurrentState().toString() + " ETAT" );
            nbJours = 3;
            System.out.println("Carrot state 2 created");
        }

        public int stateProperty(){
            return 2;
        }

        @Override
        public boolean isRotten() {
            return false;
        }

        @Override
        public void nextState() {
            System.out.println("Carrot state 2 changed to state 3");
            vegetable.setState(new PurpleCarrot.PurpleCarrotState3(vegetable));
            PurpleCarrot.this.getType();
            parcelle.setStateChange(true);
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
            int daysToNextState = 6;
            if (nbJours == daysToNextState) {
                this.nextState();
            }
        }
        public ParcelleValue getType() {
            return ParcelleValue.YELLOWTHING2;
        }

        @Override
        public void nextDayWithGrass() {

        }

        public VegetableState getCurrentState() {
            return getState();
        }

    }

    //Level3
    public class PurpleCarrotState3 extends VegetableState{
        private int nbJours;

        public PurpleCarrotState3(Vegetable vegetable) {
            super(vegetable);
            vegetable.setState(this);
            parcelle.setStateChange(false);
            System.out.println(getCurrentState().toString() + " ETAT" );
            nbJours = 6;
            System.out.println("Carrot state 3 created");
        }
        public int stateProperty(){
            return 3;
        }

        @Override
        public boolean isRotten() {
            return false;
        }

        @Override
        public void nextState() {
            System.out.println("Carrot state 3 changed to state 4");
            setState(new PurpleCarrot.PurpleCarrotState4(vegetable));
            PurpleCarrot.this.getType();
            parcelle.setStateChange(true);
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
            int daysToNextState = 9;
            if ( nbJours == daysToNextState) {
                System.out.println("Carrot state 3 changed to state 4");
                this.nextState();
            }
        }
        public ParcelleValue getType() {
            return ParcelleValue.YELLOWTHING3;
        }

        @Override
        public void nextDayWithGrass() {

        }

        public VegetableState getCurrentState() {
            return getState();
        }
    }

    //Level4
    public class PurpleCarrotState4 extends VegetableState{
        private int nbJours;

        public PurpleCarrotState4(Vegetable vegetable) {
            super(vegetable);
            vegetable.setState(this);
            parcelle.setStateChange(false);
            System.out.println(getCurrentState().toString() + " ETAT" );
            nbJours = 9;
            System.out.println("Carrot state 4 created");
        }
        public int stateProperty(){
            return 4;
        }

        @Override
        public boolean isRotten() {
            return false;
        }

        @Override
        public void nextState() {
            System.out.println("Carrot state 4 changed to state 5");
            vegetable.setState(new PurpleCarrot.PurpleCarrotState5(vegetable));
            PurpleCarrot.this.getType();
            parcelle.setStateChange(true);
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
            int daysToNextState = 12;
            if (nbJours == daysToNextState) {
                this.nextState();
            }
        }
        public ParcelleValue getType() {
            return ParcelleValue.YELLOWTHING4;
        }

        @Override
        public void nextDayWithGrass() {

        }

        public VegetableState getCurrentState() {
            return getState();
        }
    }

    //Level5-->pourri
    public class PurpleCarrotState5 extends VegetableState{
        private int nbJours;
        int maxGrowthDays = 22;
        public PurpleCarrotState5(Vegetable vegetable) {
            super(vegetable);
            setState(this);
            vegetable.setState(this);
            parcelle.setStateChange(false);
            System.out.println(getCurrentState().toString() + " ETAT" );
            nbJours = 14;
            growthDays = 12;
            System.out.println("Carrot POURRIE created");
        }

        @Override
        public void nextState() {}

        public int stateProperty(){
            return 5;
        }

        @Override
        public boolean isRotten() {
            return 11 == maxGrowthDays / 2;
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
                vegetable.setCurrentState(new PurpleCarrot.PurpleCarrotState5(vegetable));
                this.nextState();
            }
        }
        public ParcelleValue getType() {
            return ParcelleValue.YELLOWTHING5;
        }

        @Override
        public void nextDayWithGrass() {}

        public VegetableState getCurrentState() {
            return getState();
        }
    }

    public class PurpleCarrotState6 extends VegetableState{
        private int nbJours;
        int maxGrowthDays = 22;
        public PurpleCarrotState6(Vegetable vegetable) {
            super(vegetable);
            setState(this);
            vegetable.setState(this);
            parcelle.setStateChange(false);
            System.out.println(getCurrentState().toString() + " ETAT" );
            nbJours = 14;
            growthDays = 12;
            System.out.println("Yellow Carrot POURRIE created");
        }

        @Override
        public void nextState() {}

        public int stateProperty(){
            return 5;
        }

        @Override
        public boolean isRotten() {
            return 11 == maxGrowthDays / 2;
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
                vegetable.setCurrentState(new PurpleCarrot.PurpleCarrotState6(vegetable));
                this.nextState();
            }
        }
        public ParcelleValue getType() {
            return ParcelleValue.ROTTEN_YELLOWTHING1;
        }

        @Override
        public void nextDayWithGrass() {}

        public VegetableState getCurrentState() {
            return getState();
        }
    }
}
