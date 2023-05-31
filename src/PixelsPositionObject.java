public abstract class PixelsPositionObject {
    int PositionX;
    int PositionY;

    public void setPosition(int x, int y) {
        this.PositionX = x;
        this.PositionY = y;
    }

    public int getPositionX() {
        return PositionX;
    }

    public int getPositionY() {
        return PositionY;
    }

    public void setPositionX(int PositionX) {
        this.PositionX = PositionX;
    }

    public void setPositionY(int yPosition) {
        this.PositionY = yPosition;
    }
}
