package eu.epfc.anc3.model;

class Vegetable {
    private VegetableState currentState;
    private boolean hasGrass;
    private int growthDays;
    private int yieldPoints;
    private int rottenDays;
    private int daysSincePlanted;
    private boolean isFertilized;
    private boolean isPlantedOnGrass;
    private boolean isHarvested;
    private boolean isRotten;

    public Vegetable() {

        this.hasGrass = false;
        this.isFertilized = false;
    }
    public Vegetable(int growthDays, int yieldPoints, int rottenDays) {
        this.growthDays = growthDays;
        this.yieldPoints = yieldPoints;
        this.rottenDays = rottenDays;
        this.daysSincePlanted = 0;
        this.isFertilized = false;
        this.isPlantedOnGrass = false;
        this.isHarvested = false;
        this.isRotten = false;
        //this.currentState = new SeedState(this);
    }
    public void setState(VegetableState state) {
        this.currentState = state;
    }
   // public void grow() {
   //     currentState.grow();}

   /* public void harvest() {
        currentState.harvest();
    }
*/
    public boolean hasGrass() {
        return hasGrass;
    }

    public void setHasGrass(boolean hasGrass) {
        this.hasGrass = hasGrass;
    }
    public boolean isFertilized() {
        return isFertilized;
    }

    public void setFertilized(boolean fertilized) {
        isFertilized = fertilized;
    }

    public void nextState() {
        currentState.nextState();
    }


    public int getHarvestPoints() {
        return currentState.getHarvestPoints();
    }

    public int getDaysSincePlanted() {
        return daysSincePlanted;
    }

    public void setDaysSincePlanted(int daysSincePlanted) {
        this.daysSincePlanted = daysSincePlanted;
    }

    public boolean isPlantedOnGrass() {
        return isPlantedOnGrass;
    }

    public void setPlantedOnGrass(boolean plantedOnGrass) {
        isPlantedOnGrass = plantedOnGrass;
    }

    public boolean isHarvested() {
        return isHarvested;
    }

    public void setHarvested(boolean harvested) {
        isHarvested = harvested;
    }



    public void setRotten(boolean rotten) {
        isRotten = rotten;
    }

    public VegetableState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(VegetableState currentState) {
        this.currentState = currentState;
    }

    public int getGrowthDays() {
        return growthDays;
    }

    public int getYieldPoints() {
        return yieldPoints;
    }

    public int getRottenDays() {
        return rottenDays;
    }


}

