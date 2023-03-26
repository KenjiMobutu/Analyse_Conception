package eu.epfc.anc3.model;
//Level2
// Classe concrète représentant l'état 2
public class CarrotSproutState extends VegetableState{
    /*méthodes:
    getCurrentState() : cette méthode permettra de récupérer l'état actuel de croissance du légume.
    setNextState() : cette méthode permettra de passer au stade de croissance suivant.
    isRipe() : cette méthode permettra de vérifier si le légume est arrivé à maturité.
    isRotten() : cette méthode permettra de vérifier si le légume est pourri.
    getHarvestPoints() : cette méthode permettra de récupérer le nombre de points gagnés en récoltant le légume.
    * */
    public CarrotSproutState(Vegetable vegetable) {
        super(vegetable);
    }

    @Override
    public void nextState() {
        vegetable.setCurrentState(new CarrotVegetativeState(vegetable));
        vegetable.setDaysSincePlanted(0);
        vegetable.setState(new CarrotVegetativeState(vegetable));
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

}

