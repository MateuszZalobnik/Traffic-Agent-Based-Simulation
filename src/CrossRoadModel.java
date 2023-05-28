import java.util.ArrayList;
import java.util.Random;

public class CrossRoadModel {
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
    private Car[][] board;
    private TrafficLight trafficLight;
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
        this.trafficLight = new TrafficLight(0);
        this.board = new Car[boardWidth][boardWidth];

    }

    private void newWaveOfCars() {
        for (int i = 0; i < numberOfCars; i++) {
            Random random = new Random();
            int randomDestination = random.nextInt(100);
            int randomStartPosition = random.nextInt(4);
            int randomReaction = random.nextInt(this.MaxTimeReaction) + this.MinTimeReaction; // reaction
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

    public ArrayList<Car> move() {
        listOfCars.clear();
        if (numberOfWaves > 0 && timeStep % wavesTime == 0) {
            newWaveOfCars();
            this.numberOfWaves--;
        }
        if (timeStep % trafficLightsTime == 0) {
            trafficLight.changeLight();
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
        if (trafficLight.getState() == 0) {
            if (board[halfOfBoardWidth][halfOfBoardWidth - 2] != null) {
                if (board[halfOfBoardWidth][halfOfBoardWidth - 2].getDestination() == "straight"
                        && board[halfOfBoardWidth][halfOfBoardWidth + 1] == null) {
                    board[halfOfBoardWidth][halfOfBoardWidth + 1] = board[halfOfBoardWidth][halfOfBoardWidth - 2];
                    board[halfOfBoardWidth][halfOfBoardWidth - 2] = null;
                } else if (board[halfOfBoardWidth][halfOfBoardWidth - 2].getDestination() == "right"
                        && board[halfOfBoardWidth + 1][halfOfBoardWidth - 1] == null) {
                    board[halfOfBoardWidth + 1][halfOfBoardWidth - 1] = board[halfOfBoardWidth][halfOfBoardWidth - 2];
                    board[halfOfBoardWidth][halfOfBoardWidth - 2] = null;
                }
            }
            if (board[halfOfBoardWidth - 1][halfOfBoardWidth + 1] != null) {
                if (board[halfOfBoardWidth - 1][halfOfBoardWidth + 1].getDestination() == "straight"
                        && board[halfOfBoardWidth - 1][halfOfBoardWidth - 2] == null) {
                    board[halfOfBoardWidth - 1][halfOfBoardWidth - 2] = board[halfOfBoardWidth - 1][halfOfBoardWidth
                            + 1];
                    board[halfOfBoardWidth - 1][halfOfBoardWidth + 1] = null;
                } else if (board[halfOfBoardWidth - 1][halfOfBoardWidth + 1].getDestination() == "right"
                        && board[halfOfBoardWidth - 2][halfOfBoardWidth] == null) {
                    board[halfOfBoardWidth - 2][halfOfBoardWidth] = board[halfOfBoardWidth - 1][halfOfBoardWidth + 1];
                    board[halfOfBoardWidth - 1][halfOfBoardWidth + 1] = null;
                }
            }

        } else if (trafficLight.getState() == 1) {
            if (board[halfOfBoardWidth][halfOfBoardWidth - 2] != null) {
                if (board[halfOfBoardWidth][halfOfBoardWidth - 2].getDestination() == "left"
                        && board[halfOfBoardWidth - 2][halfOfBoardWidth] == null) {
                    board[halfOfBoardWidth - 2][halfOfBoardWidth] = board[halfOfBoardWidth][halfOfBoardWidth - 2];
                    board[halfOfBoardWidth][halfOfBoardWidth - 2] = null;
                }
            }
            if (board[halfOfBoardWidth - 1][halfOfBoardWidth + 1] != null) {
                if (board[halfOfBoardWidth - 1][halfOfBoardWidth + 1].getDestination() == "left"
                        && board[halfOfBoardWidth + 1][halfOfBoardWidth - 1] == null) {
                    board[halfOfBoardWidth + 1][halfOfBoardWidth - 1] = board[halfOfBoardWidth - 1][halfOfBoardWidth
                            + 1];
                    board[halfOfBoardWidth - 1][halfOfBoardWidth + 1] = null;
                }
            }
        } else if (trafficLight.getState() == 2) {
            if (board[halfOfBoardWidth + 1][halfOfBoardWidth] != null) {
                if (board[halfOfBoardWidth + 1][halfOfBoardWidth].getDestination() == "straight"
                        && board[halfOfBoardWidth - 2][halfOfBoardWidth] == null) {
                    board[halfOfBoardWidth - 2][halfOfBoardWidth] = board[halfOfBoardWidth + 1][halfOfBoardWidth];
                    board[halfOfBoardWidth + 1][halfOfBoardWidth] = null;
                } else if (board[halfOfBoardWidth + 1][halfOfBoardWidth].getDestination() == "right"
                        && board[halfOfBoardWidth][halfOfBoardWidth + 1] == null) {
                    board[halfOfBoardWidth][halfOfBoardWidth + 1] = board[halfOfBoardWidth + 1][halfOfBoardWidth];
                    board[halfOfBoardWidth + 1][halfOfBoardWidth] = null;
                }
            }
            if (board[halfOfBoardWidth - 2][halfOfBoardWidth - 1] != null) {
                if (board[halfOfBoardWidth - 2][halfOfBoardWidth - 1].getDestination() == "straight"
                        && board[halfOfBoardWidth + 1][halfOfBoardWidth - 1] == null) {
                    board[halfOfBoardWidth + 1][halfOfBoardWidth - 1] = board[halfOfBoardWidth - 2][halfOfBoardWidth
                            - 1];
                    board[halfOfBoardWidth - 2][halfOfBoardWidth - 1] = null;
                } else if (board[halfOfBoardWidth - 2][halfOfBoardWidth - 1].getDestination() == "right"
                        && board[halfOfBoardWidth - 1][halfOfBoardWidth - 2] == null) {
                    board[halfOfBoardWidth - 1][halfOfBoardWidth - 2] = board[halfOfBoardWidth - 2][halfOfBoardWidth
                            - 1];
                    board[halfOfBoardWidth - 2][halfOfBoardWidth - 1] = null;
                }
            }

        } else if (trafficLight.getState() == 3) {
            if (board[halfOfBoardWidth - 2][halfOfBoardWidth - 1] != null) {
                if (board[halfOfBoardWidth - 2][halfOfBoardWidth - 1].getDestination() == "left"
                        && board[halfOfBoardWidth][halfOfBoardWidth + 1] == null) {
                    board[halfOfBoardWidth][halfOfBoardWidth + 1] = board[halfOfBoardWidth - 2][halfOfBoardWidth - 1];
                    board[halfOfBoardWidth - 2][halfOfBoardWidth - 1] = null;
                }
            }
            if (board[halfOfBoardWidth + 1][halfOfBoardWidth] != null) {
                if (board[halfOfBoardWidth + 1][halfOfBoardWidth].getDestination() == "left"
                        && board[halfOfBoardWidth - 1][halfOfBoardWidth - 2] == null) {
                    board[halfOfBoardWidth - 1][halfOfBoardWidth - 2] = board[halfOfBoardWidth + 1][halfOfBoardWidth];
                    board[halfOfBoardWidth + 1][halfOfBoardWidth] = null;
                }
            }
        }
    }

    private void MoveCarBehindCrossroad() {
        for (int i = boardWidth - 1; i >= halfOfBoardWidth + 1; i--) {
            if (board[halfOfBoardWidth][i] != null) {
                if (i == boardWidth - 1) {
                    board[halfOfBoardWidth][i] = null;
                } else if (board[halfOfBoardWidth][i + 1] == null) {
                    board[halfOfBoardWidth][i + 1] = board[halfOfBoardWidth][i];
                    board[halfOfBoardWidth][i] = null;
                }
            }
            if (board[i][halfOfBoardWidth - 1] != null) {
                if (i == boardWidth - 1) {
                    board[i][halfOfBoardWidth - 1] = null;
                } else if (board[i + 1][halfOfBoardWidth - 1] == null) {
                    board[i + 1][halfOfBoardWidth - 1] = board[i][halfOfBoardWidth - 1];
                    board[i][halfOfBoardWidth - 1] = null;
                }
            }
        }

        for (int i = 0; i <= boardWidth / 2 - 2; i++) {
            if (board[halfOfBoardWidth - 1][i] != null) {
                if (i == 0) {
                    board[halfOfBoardWidth - 1][i] = null;
                } else if (board[halfOfBoardWidth - 1][i - 1] == null) {
                    board[halfOfBoardWidth - 1][i - 1] = board[halfOfBoardWidth - 1][i];
                    board[halfOfBoardWidth - 1][i] = null;
                }
            }
            if (board[i][halfOfBoardWidth] != null) {
                if (i == 0) {
                    board[i][halfOfBoardWidth] = null;
                } else if (board[i - 1][halfOfBoardWidth] == null) {
                    board[i - 1][halfOfBoardWidth] = board[i][halfOfBoardWidth];
                    board[i][halfOfBoardWidth] = null;
                }
            }
        }

    }

    private void MoveCarBeforeCrossroad() {
        for (int i = halfOfBoardWidth - 3; i >= 0; i--) {
            if (board[halfOfBoardWidth][i] != null) {
                if (board[halfOfBoardWidth][i + 1] == null) {
                    board[halfOfBoardWidth][i].setIsMoving(true);
                    if (board[halfOfBoardWidth][i].getCurrentReaction() == 0) {
                        board[halfOfBoardWidth][i + 1] = board[halfOfBoardWidth][i];
                        board[halfOfBoardWidth][i] = null;
                    } else {
                        board[halfOfBoardWidth][i].currentReactionDecrease();
                    }

                } else {
                    board[halfOfBoardWidth][i].setIsMoving(false);
                }
            }
            if (board[i][halfOfBoardWidth - 1] != null) {
                if (board[i + 1][halfOfBoardWidth - 1] == null) {
                    board[i][halfOfBoardWidth - 1].setIsMoving(true);
                    if (board[i][halfOfBoardWidth - 1].getCurrentReaction() == 0) {
                        board[i + 1][halfOfBoardWidth - 1] = board[i][halfOfBoardWidth - 1];
                        board[i][halfOfBoardWidth - 1] = null;
                    } else {
                        board[i][halfOfBoardWidth - 1].currentReactionDecrease();
                    }

                } else {
                    board[i][halfOfBoardWidth - 1].setIsMoving(false);
                }
            }
        }
        for (int i = halfOfBoardWidth + 2; i < boardWidth; i++) {
            if (board[halfOfBoardWidth - 1][i] != null) {
                if (board[halfOfBoardWidth - 1][i - 1] == null) {
                    board[halfOfBoardWidth - 1][i].setIsMoving(true);
                    if (board[halfOfBoardWidth - 1][i].getCurrentReaction() == 0) {
                        board[halfOfBoardWidth - 1][i - 1] = board[halfOfBoardWidth - 1][i];
                        board[halfOfBoardWidth - 1][i] = null;
                    } else {
                        board[halfOfBoardWidth - 1][i].currentReactionDecrease();
                    }
                } else {
                    board[halfOfBoardWidth - 1][i].setIsMoving(false);
                }
            }
            if (board[i][halfOfBoardWidth] != null) {
                if (board[i - 1][halfOfBoardWidth] == null) {
                    board[i][halfOfBoardWidth].setIsMoving(true);
                    if (board[i][halfOfBoardWidth].getCurrentReaction() == 0) {
                        board[i - 1][halfOfBoardWidth] = board[i][halfOfBoardWidth];
                        board[i][halfOfBoardWidth] = null;
                    } else {
                        board[i][halfOfBoardWidth].currentReactionDecrease();
                    }

                } else {
                    board[i][halfOfBoardWidth].setIsMoving(false);
                }
            }
        }
    }

    private void newCarsOnBoard() {// new cars from list appear on road
        if (AllCarsFromTop.size() > 0 && board[0][boardWidth / 2 - 1] == null) {
            board[0][boardWidth / 2 - 1] = AllCarsFromTop.get(0);
            AllCarsFromTop.remove(0);
        }
        if (AllCarsFromRight.size() > 0 && board[boardWidth / 2 - 1][boardWidth - 1] == null) {
            board[boardWidth / 2 - 1][boardWidth - 1] = AllCarsFromRight.get(0);
            AllCarsFromRight.remove(0);
        }
        if (AllCarsFromBottom.size() > 0 && board[boardWidth - 1][boardWidth / 2] == null) {
            board[boardWidth - 1][boardWidth / 2] = AllCarsFromBottom.get(0);
            AllCarsFromBottom.remove(0);
        }
        if (AllCarsFromLeft.size() > 0 && board[boardWidth / 2][0] == null) {
            board[boardWidth / 2][0] = AllCarsFromLeft.get(0);
            AllCarsFromLeft.remove(0);
        }
    }

    public boolean isEnd() {
        if (numberOfWaves == 0 && AllCarsFromTop.size() == 0 && AllCarsFromRight.size() == 0
                && AllCarsFromBottom.size() == 0 && AllCarsFromLeft.size() == 0 && listOfCars.size() == 0) {
            return true;
        }
        return false;
    }

    public int getTrafficLight() {
        return this.trafficLight.getState();
    }

    public int getTimeStep() {
        return this.timeStep;
    }
}