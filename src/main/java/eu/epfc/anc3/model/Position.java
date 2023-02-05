package eu.epfc.anc3.model;

public class Position {
    private int posX, posY;
    public Position(){
        posY =0;
        posX =0;
    }

    public Position(int x, int y){
        posY = y;
        posX = x;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
