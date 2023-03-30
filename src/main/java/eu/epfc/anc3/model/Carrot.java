package eu.epfc.anc3.model;

import javafx.beans.value.ChangeListener;

/*méthodes:
        getCurrentState() : cette méthode permettra de récupérer l'état actuel de croissance du légume.
        setNextState() : cette méthode permettra de passer au stade de croissance suivant.
        isRipe() : cette méthode permettra de vérifier si le légume est arrivé à maturité.
        isRotten() : cette méthode permettra de vérifier si le légume est pourri.
        getHarvestPoints() : cette méthode permettra de récupérer le nombre de points gagnés en récoltant le légume.
     */
public class Carrot extends Vegetable implements Element {
    private final int maxScore = 100;
    public Carrot() {
        super();
        super.setState(new CarrotState1(this));
        CarrotState1 getCurrentState = new CarrotState1(this);
    }

    @Override
    public ParcelleValue getType() {
        return ParcelleValue.CARROT;
    }

    @Override
    public String toString() {
        return "Carotte";
    }

    /*----------------------------------------------------------------------------------------*/

    //Level1
    public class CarrotState1 extends VegetableState {
        private int daysToNextState = 3;

        public CarrotState1(Vegetable vegetable) {
            super(vegetable);
            growthDays = 0;
            System.out.println("Carrot State 1 created");
        }

        public VegetableState getCurrentState() {
            return this;
        }

        @Override
        public void nextState() {
            vegetable.setCurrentState(new CarrotState2(vegetable));
            System.out.println("Carrot state 1 changed to state 2");
        }

        public ParcelleValue getType() {
            return ParcelleValue.CARROT;
        }

        @Override
        public int getHarvestPoints() {
            //IntergerProperty.get() : pour récuper la valeur de la propriété sous forme d'entier.
            return maxScore / 10;
        }

        @Override
        public void nextDay() {
            growthDays++;
            if (growthDays == daysToNextState) {
                this.nextState();
            }
        }
    }

    //Level2
    public class CarrotState2 extends VegetableState{
        private int daysToNextState = 6;
        public CarrotState2(Vegetable vegetable) {
            super(vegetable);
            growthDays = 3;
            System.out.println("Carrot state 2 created");
        }

        @Override
        public void nextState() {
            System.out.println("Carrot state 2 changed to state 3");
            vegetable.setState(new CarrotState3(vegetable));
        }


        @Override
        public int getHarvestPoints() {
            return maxScore / 5;
        }

        @Override
        public void nextDay() {
            growthDays++;
            if (growthDays == daysToNextState) {
                vegetable.setCurrentState(new CarrotState3(vegetable));
            }
        }
        public ParcelleValue getType() {
            return ParcelleValue.CARROT2;
        }


    }

    //Level3
    public class CarrotState3 extends VegetableState{
        private int daysToNextState = 9;
        public CarrotState3(Vegetable vegetable) {
            super(vegetable);
            growthDays = 6;
            System.out.println("Carrot state 3 created");
        }

        @Override
        public void nextState() {
            System.out.println("Carrot state 3 changed to state 4");
            vegetable.setState(new CarrotState4(vegetable));
        }

        @Override
        public int getHarvestPoints() {
            return maxScore / 2 ;
        }

        @Override
        public void nextDay() {
            growthDays++;
            if ( growthDays == daysToNextState) {
                System.out.println("Carrot state 3 changed to state 4");
                vegetable.setCurrentState(new CarrotState4(vegetable));
            }
        }
        public ParcelleValue getType() {
            return ParcelleValue.CARROT3;
        }
    }

    //Level4
    public class CarrotState4 extends VegetableState{
        private int daysToNextState =  12;
        public CarrotState4(Vegetable vegetable) {
            super(vegetable);
            growthDays = 9;
            System.out.println("Carrot state 4 created");
        }

        @Override
        public void nextState() {
            System.out.println("Carrot state 4 changed to state ROTTEN");
            vegetable.setState(new CarrotState5(vegetable));
        }

        @Override
        public int getHarvestPoints() {
            return maxScore;
        }

        @Override
        public void nextDay() {
            growthDays++;
            if (growthDays == daysToNextState) {
                vegetable.setCurrentState(new CarrotState5(vegetable));
            }
        }
        public ParcelleValue getType() {
            return ParcelleValue.CARROT4;
        }
    }

    //Level5-->pourri
    public class CarrotState5 extends VegetableState{
        int maxGrowthDays = 22;
        public CarrotState5(Vegetable vegetable) {
            super(vegetable);
            growthDays = 12;
            System.out.println("Carrot POURRIE created");
        }

        @Override
        public void nextState() {

        }

        @Override
        public int getHarvestPoints() {
            //points_perdus = 1/10 * rendement_maximal * (jour de la récolte - premier jour du stade Pourri)

            return 1/10 * maxScore * (growthDays - 12);
        }

        @Override
        public void nextDay() {
            growthDays++;
            if (growthDays == maxGrowthDays) {
                //recolte obligatoire
            }
        }
        public ParcelleValue getType() {
            return ParcelleValue.ROTTEN_CARROT;
        }
    }
}


