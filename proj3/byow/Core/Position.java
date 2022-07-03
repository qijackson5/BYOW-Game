package byow.Core;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object p) {
        if (!(p instanceof Position)) {
            return false;
        }
        Position castP = (Position) p;
        return this.x == castP.x && this.y == castP.y;
    }

    @Override
    public int hashCode(){
        return 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
