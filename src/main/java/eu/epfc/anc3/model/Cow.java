package eu.epfc.anc3.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;

class Cow extends Animal implements Element{
    private final IntegerProperty nbJours = new SimpleIntegerProperty(0);
    private final ReadOnlyObjectWrapper<AnimalState> state = new ReadOnlyObjectWrapper<>();
    private Parcelle parcelle ;

    public Cow(Parcelle parcelle) {
        super();
        setParcelle(parcelle);
        nbJours.addListener((obs, oldVal, newVal) -> {
            System.out.println("++day");
            this.getCurrentState().nextDay();
        });
        this.setState(new CowState1(this));
    }

    private void setParcelle(Parcelle parcelle) {
        this.parcelle = parcelle;
    }

    @Override
    public ParcelleValue getType() {
        return state.get().getType();
    }

    @Override
    public boolean getStateChanged() {
        return false;
    }

    IntegerProperty nbJoursProperty() {
        return nbJours;
    }
    @Override
    public void setStateChanged(boolean stateChanged) {

    }
    public void setState(AnimalState newState) {
        state.set(newState);
    }

    @Override
    public boolean isRotten() {
        return false;
    }

    @Override
    public boolean isVegetable() {
        return false;
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
        return "Vache";
    }
    public AnimalState getState() {
        return state.get();
    }
    public AnimalState getCurrentState() {
        return getState();
    }

    public class CowState1 extends AnimalState {
        private int nbJours;
        private static final int daysToNextState = 5;
        public CowState1(Animal animal) {
            super(animal);
            nbJours = 0;
        }
        @Override
        public void nextState() {
            animal.setCurrentState(new CowState2(animal));
            System.out.println("Baby Cow to state 2 --> COW");
            parcelle.setStateChange(true);

            Cow.this.getType();

            System.out.println(getType() + " TYPE");
        }
        @Override
        public void nextDay() {
            nbJours++;
            if (nbJours == daysToNextState) {
                this.nextState();
            }
        }
        @Override
        public ParcelleValue getType() {
            return ParcelleValue.COWBABY;
        }
    }

    public class CowState2 extends AnimalState {
        private int nbJours;
        public CowState2(Animal animal) {
            super(animal);
            setState(this);
            nbJours = 0;
        }
        @Override
        public void nextState() {}
        @Override
        public void nextDay() {
            nbJours++;
            System.out.println("nbJours = " + nbJours);
        }
        @Override
        public ParcelleValue getType() {
            return ParcelleValue.COW;
        }
    }
}
