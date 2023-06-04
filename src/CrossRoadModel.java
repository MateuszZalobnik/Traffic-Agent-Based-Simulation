import java.util.ArrayList;
import java.util.Random;

public class CrossRoadModel implements CrossRoadModelInterface {
    private int numberOfCars = 40; // number of cars in one wave
    private int MaxTimeReaction = 4; // max time reaction, 5 BEST
    private int MinTimeReaction = 0; // min time reaction, 1 BEST
    private int numberOfWaves = 2; // number of waves
    private int wavesTime = 100; // new wave time
    private int trafficLightsTime = 9; // traffic lights time change

    private ArrayList<Car> AllCarsFromTop = new ArrayList<>(); // all cars from top not on board
    private ArrayList<Car> AllCarsFromRight = new ArrayList<>(); // all cars from right not on board
    private ArrayList<Car> AllCarsFromBottom = new ArrayList<>(); // all cars from bottom not on board
    private ArrayList<Car> AllCarsFromLeft = new ArrayList<>(); // all cars from left not on board
    private ArrayList<Car> listOfCars = new ArrayList<>(); // cars on board
    private ArrayList<TrafficLight> listofLights = new ArrayList<>();
    private Car[][] board;

    private TrafficLight RightXStraightAndRight;
    private TrafficLight LeftXStraightAndRight;
    private TrafficLight TopYStraightAndRight;
    private TrafficLight BottomYStraightAndRight;
    private TrafficLight RightXLeft;
    private TrafficLight LeftXLeft;
    private TrafficLight TopYLeft;
    private TrafficLight BottomYLeft;
    private int timeStep = 0;
    public static int boardWidth = 40;
    public static int halfOfBoardWidth = boardWidth / 2;

    CrossRoadModel(int numberOfCars, int numberOfWaves, int wavesTime, int MinTimeReaction, int MaxTimeReaction,
            int trafficLightsTime) {
        this.numberOfCars = numberOfCars;
        this.numberOfWaves = numberOfWaves;
        this.wavesTime = wavesTime;
        this.trafficLightsTime = trafficLightsTime;
        this.MinTimeReaction = MinTimeReaction;
        this.MaxTimeReaction = MaxTimeReaction;

        this.board = new Car[boardWidth][boardWidth];
        RightXStraightAndRight = new TrafficLight(false, 430, 370);
        LeftXStraightAndRight = new TrafficLight(false, 370, 430);
        TopYStraightAndRight = new TrafficLight(false, 370, 370);
        BottomYStraightAndRight = new TrafficLight(false, 430, 430);
        RightXLeft = new TrafficLight(false, 450, 370);
        LeftXLeft = new TrafficLight(false, 350, 430);
        TopYLeft = new TrafficLight(false, 370, 350);
        BottomYLeft = new TrafficLight(false, 430, 450);
        listofLights.add(RightXStraightAndRight);
        listofLights.add(LeftXStraightAndRight);
        listofLights.add(TopYStraightAndRight);
        listofLights.add(BottomYStraightAndRight);
        listofLights.add(RightXLeft);
        listofLights.add(LeftXLeft);
        listofLights.add(TopYLeft);
        listofLights.add(BottomYLeft);
    }

    private void newWaveOfCars() {
        for (int i = 0; i < numberOfCars; i++) {
            Random random = new Random();
            int randomDestination = random.nextInt(100);
            int randomStartPosition = random.nextInt(4);
            int randomReaction = 0;
            if (MaxTimeReaction != 0) {
                randomReaction = random.nextInt(this.MaxTimeReaction) + this.MinTimeReaction; // reaction
            }
            // between min and
            // max;
            Car car;
            String destination = "straight";
            if (randomDestination >= 75) {
                destination = "right";
            } else if (randomDestination >= 50) {
                destination = "left";
            } else {
                destination = "straight";
            }
            if (randomStartPosition == 0) {
                car = new Car("top", destination);
                this.AllCarsFromTop.add(car);
            } else if (randomStartPosition == 1) {
                car = new Car("right", destination);
                this.AllCarsFromRight.add(car);
            } else if (randomStartPosition == 2) {
                car = new Car("bottom", destination);
                this.AllCarsFromBottom.add(car);
            } else {
                car = new Car("left", destination);
                this.AllCarsFromLeft.add(car);
            }
            car.setReaction(randomReaction);

        }
    }

    private void changeLight() {
        if (RightXStraightAndRight.getState() == true) {
            RightXStraightAndRight.setState(false);
            LeftXStraightAndRight.setState(false);
            LeftXLeft.setState(true);
            RightXLeft.setState(true);
        } else if (RightXLeft.getState() == true) {
            RightXLeft.setState(false);
            LeftXLeft.setState(false);
            TopYStraightAndRight.setState(true);
            BottomYStraightAndRight.setState(true);
        } else if (TopYStraightAndRight.getState() == true) {
            TopYStraightAndRight.setState(false);
            BottomYStraightAndRight.setState(false);
            TopYLeft.setState(true);
            BottomYLeft.setState(true);
        } else {
            TopYStraightAndRight.setState(false);
            BottomYStraightAndRight.setState(false);
            TopYLeft.setState(false);
            BottomYLeft.setState(false);
            RightXLeft.setState(false);
            LeftXLeft.setState(false);
            RightXStraightAndRight.setState(true);
            LeftXStraightAndRight.setState(true);
        }
    }

    public ArrayList<Car> move() {
        listOfCars.clear();
        if (numberOfWaves > 0 && timeStep % wavesTime == 0) {
            newWaveOfCars();
            this.numberOfWaves--;
        }
        if (timeStep % trafficLightsTime == 0) {
            changeLight();
        }
        // move car behind crossroad
        MoveCarBehindCrossroad();

        // move car on crossroad
        CarOnCrossroad();

        // move car before crossroad
        MoveCarBeforeCrossroad();
        newCarsOnBoard();
        addCarFromBoardToList();
        timeStep++;
        return listOfCars;
    }

    private void addCarFromBoardToList() {
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if (board[i][j] != null) {
                    board[i][j].setPosition(j, i * halfOfBoardWidth);
                    listOfCars.add(board[i][j]);
                }
            }
        }
    }

    private void CarOnCrossroad() {
        if (RightXStraightAndRight.getState()) {
            moveCarIfDestination(board, halfOfBoardWidth, halfOfBoardWidth - 2, halfOfBoardWidth, halfOfBoardWidth + 1,
                    "straight");
            moveCarIfDestination(board, halfOfBoardWidth, halfOfBoardWidth - 2, halfOfBoardWidth + 1,
                    halfOfBoardWidth - 1, "right");
            moveCarIfDestination(board, halfOfBoardWidth - 1, halfOfBoardWidth + 1, halfOfBoardWidth - 1,
                    halfOfBoardWidth - 2, "straight");
            moveCarIfDestination(board, halfOfBoardWidth - 1, halfOfBoardWidth + 1, halfOfBoardWidth - 2,
                    halfOfBoardWidth, "right");
        } else if (RightXLeft.getState()) {
            moveCarIfDestination(board, halfOfBoardWidth, halfOfBoardWidth - 2, halfOfBoardWidth - 2, halfOfBoardWidth,
                    "left");
            moveCarIfDestination(board, halfOfBoardWidth - 1, halfOfBoardWidth + 1, halfOfBoardWidth + 1,
                    halfOfBoardWidth - 1, "left");
        } else if (TopYStraightAndRight.getState()) {
            moveCarIfDestination(board, halfOfBoardWidth + 1, halfOfBoardWidth, halfOfBoardWidth - 2, halfOfBoardWidth,
                    "straight");
            moveCarIfDestination(board, halfOfBoardWidth + 1, halfOfBoardWidth, halfOfBoardWidth, halfOfBoardWidth + 1,
                    "right");
            moveCarIfDestination(board, halfOfBoardWidth - 2, halfOfBoardWidth - 1, halfOfBoardWidth + 1,
                    halfOfBoardWidth - 1, "straight");
            moveCarIfDestination(board, halfOfBoardWidth - 2, halfOfBoardWidth - 1, halfOfBoardWidth - 1,
                    halfOfBoardWidth - 2, "right");
        } else if (TopYLeft.getState()) {
            moveCarIfDestination(board, halfOfBoardWidth - 2, halfOfBoardWidth - 1, halfOfBoardWidth,
                    halfOfBoardWidth + 1, "left");
            moveCarIfDestination(board, halfOfBoardWidth + 1, halfOfBoardWidth, halfOfBoardWidth - 1,
                    halfOfBoardWidth - 2, "left");
        }
    }

    private void moveCarIfDestination(Car[][] board, int fromX, int fromY, int toX, int toY, String destination) {
        if (board[fromX][fromY] != null && board[fromX][fromY].getDestination().equals(destination)) {
            if (toX < 0 || toY < 0 || toX >= board.length || toY >= board[0].length) {
                board[fromX][fromY] = null;
                return;
            }

            if (board[toX][toY] == null) {
                board[toX][toY] = board[fromX][fromY];
                board[fromX][fromY] = null;
            }
        }
    }

    private void MoveCarBehindCrossroad() {
        board[halfOfBoardWidth][boardWidth - 1] = null;
        board[boardWidth - 1][halfOfBoardWidth - 1] = null;
        board[0][halfOfBoardWidth] = null;
        board[halfOfBoardWidth - 1][0] = null;
        for (int i = boardWidth - 1; i >= halfOfBoardWidth + 1; i--) {
            moveCar(board, halfOfBoardWidth, i, halfOfBoardWidth, i + 1);
            moveCar(board, i, halfOfBoardWidth - 1, i + 1, halfOfBoardWidth - 1);
        }

        for (int i = 0; i <= halfOfBoardWidth - 2; i++) {
            moveCar(board, halfOfBoardWidth - 1, i, halfOfBoardWidth - 1, i - 1);
            moveCar(board, i, halfOfBoardWidth, i - 1, halfOfBoardWidth);
        }
    }

    private void moveCar(Car[][] board, int fromX, int fromY, int toX, int toY) {
        if (board[fromX][fromY] != null) {
            if (toX < 0 || toY < 0 || toX >= board.length || toY >= board[0].length)
                return;
            if (board[toX][toY] == null) {
                board[toX][toY] = board[fromX][fromY];
                board[fromX][fromY] = null;
            }
        }
    }

    private void MoveCarBeforeCrossroad() {
        for (int i = halfOfBoardWidth - 3; i >= 0; i--) {
            moveCarIfSpaceAvailable(board, halfOfBoardWidth, i, halfOfBoardWidth, i + 1);
            moveCarIfSpaceAvailable(board, i, halfOfBoardWidth - 1, i + 1, halfOfBoardWidth - 1);
        }
        for (int i = halfOfBoardWidth + 2; i < boardWidth; i++) {
            moveCarIfSpaceAvailable(board, halfOfBoardWidth - 1, i, halfOfBoardWidth - 1, i - 1);
            moveCarIfSpaceAvailable(board, i, halfOfBoardWidth, i - 1, halfOfBoardWidth);
        }
    }

    private void moveCarIfSpaceAvailable(Car[][] board, int fromX, int fromY, int toX, int toY) {
        if (board[fromX][fromY] != null) {
            if (board[toX][toY] == null) {
                board[fromX][fromY].setIsMoving(true);
                if (board[fromX][fromY].getCurrentReaction() == 0) {
                    board[toX][toY] = board[fromX][fromY];
                    board[fromX][fromY] = null;
                } else {
                    board[fromX][fromY].currentReactionDecrease();
                }
            } else {
                board[fromX][fromY].setIsMoving(false);
            }
        }
    }

    private void newCarsOnBoard() {
        placeCarIfSpaceAvailable(AllCarsFromTop, 0, boardWidth / 2 - 1);
        placeCarIfSpaceAvailable(AllCarsFromRight, boardWidth / 2 - 1, boardWidth - 1);
        placeCarIfSpaceAvailable(AllCarsFromBottom, boardWidth - 1, boardWidth / 2);
        placeCarIfSpaceAvailable(AllCarsFromLeft, boardWidth / 2, 0);
    }

    private void placeCarIfSpaceAvailable(ArrayList<Car> carList, int row, int column) {
        if (carList.size() > 0 && board[row][column] == null) {
            board[row][column] = carList.get(0);
            carList.remove(0);
        }
    }

    public boolean isEnd() {
        if (numberOfWaves == 0 && AllCarsFromTop.size() == 0 && AllCarsFromRight.size() == 0
                && AllCarsFromBottom.size() == 0 && AllCarsFromLeft.size() == 0 && listOfCars.size() == 0) {
            return true;
        }
        return false;
    }

    public ArrayList<TrafficLight> getTrafficLightV2() {
        return this.listofLights;
    }

    public int getTimeStep() {
        return this.timeStep;
    }
}