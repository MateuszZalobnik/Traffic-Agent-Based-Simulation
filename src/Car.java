public class Car extends PixelsPositionObject {
    private String startPosition;
    private String destination;
    private int reaction = 0;
    private int currentReaction = 0;
    private boolean isMovig = true;

    Car(String startPosition, String destination) {
        this.destination = destination;
        if (startPosition == "top") {
            this.PositionX = 19;
            this.PositionY = 0;
        } else if (startPosition == "bottom") {
            this.PositionX = 20;
            this.PositionY = 40;
        } else if (startPosition == "left") {
            this.PositionX = 0;
            this.PositionY = 20;
        } else if (startPosition == "right") {
            this.PositionX = 40;
            this.PositionY = 19;
        }
    }

    public void setReaction(int r) {
        this.reaction = r;
    }

    public int getCurrentReaction() {
        return this.currentReaction;
    }

    public void currentReactionDecrease() {
        this.currentReaction--;
    }

    public void setIsMoving(boolean value) {
        if (value == false) {
            this.currentReaction = this.reaction;
        }
        this.isMovig = value;
    }

    public boolean getIsMoving() {
        return this.isMovig;
    }

    public String getDestination() {
        return this.destination;
    }

}