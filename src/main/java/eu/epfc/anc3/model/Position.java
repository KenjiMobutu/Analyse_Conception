package eu.epfc.anc3.model;

class Position {
    private int posX, posY;
    Position(){}

    Position(int x, int y){
        posY = y;
        posX = x;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
