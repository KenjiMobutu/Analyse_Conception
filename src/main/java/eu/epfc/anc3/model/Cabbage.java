package eu.epfc.anc3.model;

public class Cabbage extends Vegetable {
    int daysSincePlanted;
    public Cabbage() {
        super();
        super.setState(new CabbageState1(this));
        System.out.println("Cabbage created");
    }

   /* public Cabbage(VegetableState currentState, boolean hasGrass, int growthDays, int yieldPoints, int rottenDays, int daysSincePlanted, boolean isFertilized, boolean isPlantedOnGrass, boolean isHarvested, boolean isRotten, int nextDay) {
        super(currentState, hasGrass, growthDays, yieldPoints, rottenDays, daysSincePlanted, isFertilized, isPlantedOnGrass, isHarvested, isRotten, nextDay);
    }

    public Cabbage(int growthDays, int yieldPoints, int rottenDays) {
        super(growthDays, yieldPoints, rottenDays);
    }
    *//*méthodes class interne:
        getCurrentState() : cette méthode permettra de récupérer l'état actuel de croissance du légume.
        setNextState() : cette méthode permettra de passer au stade de croissance suivant.
        isRipe() : cette méthode permettra de vérifier si le légume est arrivé à maturité.
        isRotten() : cette méthode permettra de vérifier si le légume est pourri.
        getHarvestPoints() : cette méthode permettra de récupérer le nombre de points gagnés en récoltant le légume.
     */

    //Level1
    public class CabbageState1 extends State1 {
        public CabbageState1(Vegetable vegetable) {
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
    public class CabbageState2 extends VegetableState{
        public CabbageState2(Vegetable vegetable) {
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
    public class CabbageState3 extends VegetableState {
        public CabbageState3(Vegetable vegetable) {
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
    public class CabbageState4 extends VegetableState{
        public CabbageState4(Vegetable vegetable) {
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
    public class CabbageRottenState extends VegetableState {
        public CabbageRottenState(Vegetable vegetable) {
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
