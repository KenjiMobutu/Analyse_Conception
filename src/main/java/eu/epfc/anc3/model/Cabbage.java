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
    class CabbageState1 extends VegetableState {
        CabbageState1(Vegetable vegetable) {
            super(vegetable);
        }

        @Override
        public void nextState() {
            vegetable.setCurrentState(new CabbageState2(vegetable));
        }

        @Override
        public boolean isRipe() {
            return false;
        }

        @Override
        public boolean isRotten() {
            return false;
        }

        @Override
        public int getHarvestPoints() {
            return 0;
        }

        @Override
        public void nextDay() {
            day++;
           /* if (vegetable.getDaysSincePlanted() >= growthDays) {
                vegetable.setCurrentState(new CabbageState2(vegetable));
            }*/
        }
    }

    //Level2
    class CabbageState2 extends VegetableState{
        CabbageState2(Vegetable vegetable) {
            super(vegetable);
        }

        @Override
        public void nextState() {

        }

        @Override
        public boolean isRipe() {
            return false;
        }

        @Override
        public boolean isRotten() {
            return false;
        }

        @Override
        public int getHarvestPoints() {
            return 0;
        }

        @Override
        public void nextDay() {
            day++;
            /*if (vegetable.getDaysSincePlanted() >= growthDays) {
                vegetable.setCurrentState(new CabbageState3(vegetable));
            }*/
        }

    }

    //Level3
    class CabbageState3 extends VegetableState {
        CabbageState3(Vegetable vegetable) {
            super(vegetable);
        }

        @Override
        public void nextState() {

        }

        @Override
        public boolean isRipe() {
            return false;
        }

        @Override
        public boolean isRotten() {
            return false;
        }

        @Override
        public int getHarvestPoints() {
            return 0;
        }

        @Override
        public void nextDay() {
            day++;
            /*if (vegetable.getDaysSincePlanted() >= growthDays) {
                vegetable.setCurrentState(new CabbageState4(vegetable));
            }*/
        }

    }

    //Level4
    class CabbageState4 extends VegetableState{
        CabbageState4(Vegetable vegetable) {
            super(vegetable);
        }

        @Override
        public void nextState() {

        }

        @Override
        public boolean isRipe() {
            return false;
        }

        @Override
        public boolean isRotten() {
            return false;
        }

        @Override
        public int getHarvestPoints() {
            return 0;
        }

        @Override
        public void nextDay() {
            day++;
            /*if (vegetable.getDaysSincePlanted() >= growthDays) {
                vegetable.setCurrentState(new CabbageState5(vegetable));
            }*/
        }

    }

    //Level5 --> Pourrie
    class CabbageRottenState extends VegetableState {
        CabbageRottenState(Vegetable vegetable) {
            super(vegetable);
        }

        @Override
        public void nextState() {

        }

        @Override
        public boolean isRipe() {
            return false;
        }

        @Override
        public boolean isRotten() {
            return true;
        }

        @Override
        public int getHarvestPoints() {
            return 0;
        }

        @Override
        public void nextDay() {
            day++;
        }


    }
}
