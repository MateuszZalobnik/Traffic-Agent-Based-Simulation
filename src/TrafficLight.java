public class TrafficLight extends PixelsPositionObject {
    private boolean state;

    public TrafficLight(boolean state, int x, int y) {
        this.state = state;
        this.PositionX = x;
        this.PositionY = y;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

}