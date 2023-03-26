package eu.epfc.anc3.model;
//Level5 --> Pourrie
// Classe concrète représentant l'état pourrie
public class CarrotRottenState extends VegetableState{
        /*méthodes:
    getCurrentState() : cette méthode permettra de récupérer l'état actuel de croissance du légume.
    setNextState() : cette méthode permettra de passer au stade de croissance suivant.
    isRipe() : cette méthode permettra de vérifier si le légume est arrivé à maturité.
    isRotten() : cette méthode permettra de vérifier si le légume est pourri.
    getHarvestPoints() : cette méthode permettra de récupérer le nombre de points gagnés en récoltant le légume.
    * */

    public CarrotRottenState(Vegetable vegetable) {
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

}
