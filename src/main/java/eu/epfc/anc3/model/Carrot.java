package eu.epfc.anc3.model;

class Carrot extends Vegetable implements Element {
    public Carrot() {
        super();
        super.setState(new CarrotState1(this));
        System.out.println("Carrot created");
    }

    @Override
    public ParcelleValue getType() {
        return ParcelleValue.CARROT;
    }

    @Override
    public String toString() {
        return "Je Suis une Carotte";
    }

    /*----------------------------------------------------------------------------------------*/

    //Level1
    public class CarrotState1 extends VegetableState {

        /*méthodes:
    getCurrentState() : cette méthode permettra de récupérer l'état actuel de croissance du légume.
    setNextState() : cette méthode permettra de passer au stade de croissance suivant.
    isRipe() : cette méthode permettra de vérifier si le légume est arrivé à maturité.
    isRotten() : cette méthode permettra de vérifier si le légume est pourri.
    getHarvestPoints() : cette méthode permettra de récupérer le nombre de points gagnés en récoltant le légume.
    * */

         CarrotState1(Vegetable vegetable) {
            super(vegetable);
            growthDays = 5;
            yieldPoints = 0;
            rottenDays = 3;
         }

        VegetableState getCurrentState() {
            return this;
        }

        @Override
        public void nextState() {
            vegetable.setCurrentState(new CarrotState2(vegetable));
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
            if (vegetable.getDaysSincePlanted() >= growthDays) {
                vegetable.nextState();
            }
        }
    }

    //Level2
    class CarrotState2 extends VegetableState{

        CarrotState2(Vegetable vegetable) {
            super(vegetable);
            System.out.println("Carrot state2 created");
        }

        @Override
        public void nextState() {
            //vegetable.setCurrentState(new CarrotState3(vegetable));
            //vegetable.setDaysSincePlanted(0);
            vegetable.setState(new CarrotState3(vegetable));
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
            if (vegetable.getDaysSincePlanted() >= growthDays) {
                vegetable.setCurrentState(new CarrotState3(vegetable));
            }
        }

    }

    //Level3
    class CarrotState3 extends VegetableState{

        CarrotState3(Vegetable vegetable) {
            super(vegetable);
            System.out.println("Carrot state3 created");
        }

        @Override
        public void nextState() {
            //vegetable.setCurrentState(new CarrotState4(vegetable));
            //vegetable.setDaysSincePlanted(0);
            vegetable.setState(new CarrotState4(vegetable));
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
            if (vegetable.getDaysSincePlanted() >= growthDays) {
                vegetable.setCurrentState(new CarrotState4(vegetable));
            }
        }
    }

    //Level4
    class CarrotState4 extends VegetableState{

        CarrotState4(Vegetable vegetable) {
            super(vegetable);
            System.out.println("Carrot state4 created");
        }

        @Override
        public void nextState() {
            vegetable.setState(new CarrotState5(vegetable));
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
            if (vegetable.getDaysSincePlanted() >= growthDays) {
                vegetable.setCurrentState(new CarrotState5(vegetable));
            }
        }
    }

    //Level5-->pourri
    class CarrotState5 extends VegetableState{

        CarrotState5(Vegetable vegetable) {
            super(vegetable);
            System.out.println("Carrot POURRIE created");
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
            if (vegetable.getDaysSincePlanted() >= growthDays) {
                vegetable.setCurrentState(new CarrotState5(vegetable));
            }
        }
    }
}


