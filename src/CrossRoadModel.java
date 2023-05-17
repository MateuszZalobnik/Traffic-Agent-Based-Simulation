import java.util.ArrayList;
import java.util.Random;

public class CrossRoadModel {
    private int numberOfCars = 40;
    private int MaxTimeReaction = 4; // max time reaction
    private int MinTimeReaction = 0; // min time reaction

    private ArrayList<Car> AllCarsFromTop = new ArrayList<>(); // all cars
    private ArrayList<Car> AllCarsFromRight = new ArrayList<>(); // all cars
    private ArrayList<Car> AllCarsFromBottom = new ArrayList<>(); // all cars
    private ArrayList<Car> AllCarsFromLeft = new ArrayList<>(); // all cars
    private ArrayList<Car> listOfCars = new ArrayList<>(); // cars on board
    private Car[][] board;
    private TrafficLight trafficLight;
    private int timeStep = 0;
    public static int boardWidth = 40;

    CrossRoadModel() {
        this.trafficLight = new TrafficLight(0);
        this.board = new Car[boardWidth][boardWidth];

        for (int i = 0; i < numberOfCars; i++) {
            Random random = new Random();
            int randomDestination = random.nextInt(10);
            int randomStartPosition = random.nextInt(4);
            int randomReaction = random.nextInt(this.MaxTimeReaction) + this.MinTimeReaction; // reaction
                                                                                              // between min and
                                                                                              // max;
            Car car;
            String destination = "straight";
            if (randomDestination > 7) {
                destination = "right";
            } else if (randomDestination > 5) {
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
        // przesuwanie aut za sygnalizacją
        if (timeStep % 5 == 0) {
            trafficLight.changeLight();
        }
        PrzesuwanieAutZaSygnalizacja();

        WjazdNaSkrzyzowanie();
        // przesuwanie aut na skrzyżowaniu

        PrzesuwanieAutPrzedSygnalizacja();
        newCarsOnBoard();
        addCarFromBoardToList();
        timeStep++;
        return listOfCars;
    }

    private void addCarFromBoardToList() {
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if (board[i][j] != null) {
                    board[i][j].setPosition(j, i * 20);
                    listOfCars.add(board[i][j]);
                }
            }
        }
    }

    private void WjazdNaSkrzyzowanie() {
        if (trafficLight.getState() == 0) {
            if (board[20][18] != null) {
                if (board[20][18].getDestination() == "straight" && board[20][21] == null) {
                    board[20][21] = board[20][18];
                    board[20][18] = null;
                } else if (board[20][18].getDestination() == "right" && board[21][19] == null) {
                    board[21][19] = board[20][18];
                    board[20][18] = null;
                }
            }
            if (board[19][21] != null) {
                if (board[19][21].getDestination() == "straight" && board[19][18] == null) {
                    board[19][18] = board[19][21];
                    board[19][21] = null;
                } else if (board[19][21].getDestination() == "right" && board[18][20] == null) {
                    board[18][20] = board[19][21];
                    board[19][21] = null;
                }
            }

        } else if (trafficLight.getState() == 1) {
            if (board[20][18] != null) {
                if (board[20][18].getDestination() == "left" && board[18][20] == null) {
                    board[18][20] = board[20][18];
                    board[20][18] = null;
                }
            }
            if (board[19][21] != null) {
                if (board[19][21].getDestination() == "left" && board[21][19] == null) {
                    board[21][19] = board[19][21];
                    board[19][21] = null;
                }
            }
        } else if (trafficLight.getState() == 2) {
            if (board[21][20] != null) {
                if (board[21][20].getDestination() == "straight" && board[18][20] == null) {
                    board[18][20] = board[21][20];
                    board[21][20] = null;
                } else if (board[21][20].getDestination() == "right" && board[20][21] == null) {
                    board[20][21] = board[21][20];
                    board[21][20] = null;
                }
            }
            if (board[18][19] != null) {
                if (board[18][19].getDestination() == "straight" && board[21][19] == null) {
                    board[21][19] = board[18][19];
                    board[18][19] = null;
                } else if (board[18][19].getDestination() == "right" && board[19][18] == null) {
                    board[19][18] = board[18][19];
                    board[18][19] = null;
                }
            }

        } else if (trafficLight.getState() == 3) {
            if (board[18][19] != null) {
                if (board[18][19].getDestination() == "left" && board[20][21] == null) {
                    board[20][21] = board[18][19];
                    board[18][19] = null;
                }
            }
            if (board[21][20] != null) {
                if (board[21][20].getDestination() == "left" && board[19][18] == null) {
                    board[19][18] = board[21][20];
                    board[21][20] = null;
                }
            }
        }
    }

    private void PrzesuwanieAutZaSygnalizacja() {

        for (int i = boardWidth - 1; i >= 21; i--) {
            if (board[20][i] != null) {
                if (i == boardWidth - 1) {
                    board[20][i] = null;
                } else if (board[20][i + 1] == null) {
                    board[20][i + 1] = board[20][i];
                    board[20][i] = null;
                }
            }
            if (board[i][19] != null) {
                if (i == boardWidth - 1) {
                    board[i][19] = null;
                } else if (board[i + 1][19] == null) {
                    board[i + 1][19] = board[i][19];
                    board[i][19] = null;
                }
            }
        }

        for (int i = 0; i <= boardWidth / 2 - 2; i++) {
            if (board[19][i] != null) {
                if (i == 0) {
                    board[19][i] = null;
                } else if (board[19][i - 1] == null) {
                    board[19][i - 1] = board[19][i];
                    board[19][i] = null;
                }
            }
            if (board[i][20] != null) {
                if (i == 0) {
                    board[i][20] = null;
                } else if (board[i - 1][20] == null) {
                    board[i - 1][20] = board[i][20];
                    board[i][20] = null;
                }
            }
        }

    }

    private void PrzesuwanieAutPrzedSygnalizacja() {
        for (int i = 17; i >= 0; i--) {
            if (board[20][i] != null) {
                if (board[20][i + 1] == null) {
                    board[20][i + 1] = board[20][i];
                    board[20][i] = null;
                }
            }
            if (board[i][19] != null) {
                if (board[i + 1][19] == null) {
                    board[i + 1][19] = board[i][19];
                    board[i][19] = null;
                }
            }
        }
        for (int i = 22; i < boardWidth; i++) {
            if (board[19][i] != null) {
                if (board[19][i - 1] == null) {
                    board[19][i - 1] = board[19][i];
                    board[19][i] = null;
                }
            }
            if (board[i][20] != null) {
                if (board[i - 1][20] == null) {
                    board[i - 1][20] = board[i][20];
                    board[i][20] = null;
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

    private void display() {
        System.out.println("---------------------------------------------");
        System.out.println(this.trafficLight.getState());
        System.out.println("---------------------------------------------");

        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].getDestination() == "left") {
                        System.out.print("L ");

                    } else if (board[i][j].getDestination() == "right") {
                        System.out.print("R ");
                    } else {
                        System.out.print("S ");
                    }
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println("");
        }

    }

    // public static void main(String[] args) {

    // CrossRoadModel first = new CrossRoadModel();
    // for (int i = 0; i < 20; i++) {
    // first.move();
    // }
    // for (int i = 0; i < 40; i++) {
    // if (i % 5 == 0) {
    // first.display();
    // }
    // first.move();
    // }
    // first.display();
    // }

}
