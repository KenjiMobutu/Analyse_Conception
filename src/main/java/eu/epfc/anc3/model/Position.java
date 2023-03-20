package eu.epfc.anc3.model;

class Position {
    int x, y;

    int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }

    void setPos(int x, int y){
        this.setX(x); this.setY(y);
    }

    void getPos(){
        this.toString();
    }

    Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "current pos :  x :  " + x + ",  y : "+y;
    }
}
