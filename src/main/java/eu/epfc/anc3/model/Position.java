package eu.epfc.anc3.model;

class Position {

    private int posX, posY;

    public Position(){

    }

    public Position(int x, int y){
        this.posX =x;
        this.posY =y;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void getPos(){this.toString();}

    @Override
    public String toString() {
        return  "X : " + getPosX() + " Y : " + getPosY();
    }
}
