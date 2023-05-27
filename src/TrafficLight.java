public class TrafficLight {
    private int state;
    // state = 0 oś = x prosto i prawo dla osi x
    // state = 1 oś = x lewo
    // state = 2 oś = y prosto i prawo dla osi y
    // state = 3 oś = y lewo

    public TrafficLight(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void changeLight() {
        if (this.state == 3) {
            this.state = 0;
        } else {
            this.state++;
        }
    }
}
