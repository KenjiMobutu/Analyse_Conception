package eu.epfc.anc3.model;

class Cabbage extends Vegetable implements Element {
    public Cabbage() {
        super();
        super.setState(new CabbageState1(this));
        System.out.println("Cabbage created");
    }

    @Override
    public ParcelleValue getType(){return ParcelleValue.CABBAGE;}

    @Override
    public String toString() {
        return "Je suis un chou";
    }
    /*----------------------------------------------------------------------------------------*/

    //Level1
    public class CabbageState1 extends VegetableState {
        public CabbageState1(Vegetable vegetable) {
            super(vegetable);
        }

        @Override
        public void nextState() {
            vegetable.setCurrentState(new CabbageState2(vegetable));
        }


        @Override
        public int getHarvestPoints() {
            return 0;
        }

        @Override
        public void nextDay() {
            growthDays++;
           /* if (vegetable.getDaysSincePlanted() >= growthDays) {
                vegetable.setCurrentState(new CabbageState2(vegetable));
            }*/
        }

        @Override
        public ParcelleValue getType() {
            return null;
        }
    }

    //Level2
    public class CabbageState2 extends VegetableState{
        public CabbageState2(Vegetable vegetable) {
            super(vegetable);
        }

        @Override
        public void nextState() {

        }

        @Override
        public int getHarvestPoints() {
            return 0;
        }

        @Override
        public void nextDay() {
            growthDays++;
            /*if (vegetable.getDaysSincePlanted() >= growthDays) {
                vegetable.setCurrentState(new CabbageState3(vegetable));
            }*/
        }

        @Override
        public ParcelleValue getType() {
            return null;
        }

    }

    //Level3
    public class CabbageState3 extends VegetableState {
        public CabbageState3(Vegetable vegetable) {
            super(vegetable);
        }

        @Override
        public void nextState() {

        }

        @Override
        public int getHarvestPoints() {
            return 0;
        }

        @Override
        public void nextDay() {
            growthDays++;
            /*if (vegetable.getDaysSincePlanted() >= growthDays) {
                vegetable.setCurrentState(new CabbageState4(vegetable));
            }*/
        }

        @Override
        public ParcelleValue getType() {
            return null;
        }

    }

    //Level4
    public class CabbageState4 extends VegetableState{
        public CabbageState4(Vegetable vegetable) {
            super(vegetable);
        }

        @Override
        public void nextState() {

        }

        @Override
        public int getHarvestPoints() {
            return 0;
        }

        @Override
        public void nextDay() {
            growthDays++;
            /*if (vegetable.getDaysSincePlanted() >= growthDays) {
                vegetable.setCurrentState(new CabbageState5(vegetable));
            }*/
        }

        @Override
        public ParcelleValue getType() {
            return null;
        }

    }

    //Level5 --> Pourrie
    public class CabbageRottenState extends VegetableState {
        public CabbageRottenState(Vegetable vegetable) {
            super(vegetable);
        }

        @Override
        public void nextState() {

        }

        @Override
        public int getHarvestPoints() {
            return 0;
        }

        @Override
        public void nextDay() {
            growthDays++;
        }

        @Override
        public ParcelleValue getType() {
            return null;
        }


    }
}
