package eu.epfc.anc3.model;

abstract class VegetableState {
    protected int growthDays ; // Nombre de jours nécessaires pour que le légume arrive à maturité
    protected Vegetable vegetable; // Légume auquel cet état est associé

    // Constructeur
    public VegetableState(Vegetable vegetable) {
        this.vegetable = vegetable;
    }

    // Méthode abstraite pour passer à l'état suivant
    public abstract void nextState();

    // Méthode abstraite pour obtenir le nombre de points gagnés en récoltant le légume
    public abstract int getHarvestPoints();

    // Méthode abstraite pour faire avancer la croissance du légume
    public abstract void nextDay();

    @Override
    public String toString() {
        return "VegetableState{" +
                ", vegetable=" + vegetable +
                '}';
    }
    // Méthode pour récupérer le type de légume associé à cet état
    public abstract ParcelleValue getType();

    public abstract void nextDayWithGrass();

    public abstract int stateProperty();
    public abstract boolean isGoodState();

    public abstract boolean isRotten();
}
