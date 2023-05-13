public class Car {
    private int x;
    private int y;
    private int reaction = 0;
    private int currentReaction = 0;
    private boolean isMovig = true;

    Car() {
        this.x = 0;
        this.y = 0;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getPositionX() {
        return this.x;
    }

    public int getPositionY() {
        return this.y;
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

}
