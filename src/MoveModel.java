// import java.util.ArrayList;
// import java.util.Random;

// public class MoveModel {
//     private int numberOfCars = 0; // number of cars in one wave
//     private int numberOfWaves = 1; // all cars
//     private int wavesTime = 1; // all cars
//     private int MaxTimeReaction = 0; // max time reaction
//     private int MinTimeReaction = 0; // min time reaction
//     private int trafficLightsTime = 9; // traffic lights time change
//     private boolean reverse = false; // true = move right to left

//     private ArrayList<Car> AllCars; // all cars
//     private static int positionYLeftToRight = 32; // y position
//     private static int positionYRightToLeft = 5; // y position
//     private Car[] board;
//     private ArrayList<Car> listOfCars;
//     private TrafficLight trafficLight;
//     private int timeStep = 0;
//     public static int boardWidth = 40;

//     MoveModel(int numberOfCars, int numberOfWaves, int wavesTime, boolean reverse, int MinTimeReaction,
//             int MaxTimeReaction, int trafficLightsTime) {
//         this.numberOfWaves = numberOfWaves;
//         this.wavesTime = wavesTime;
//         this.numberOfCars = numberOfCars;
//         this.MinTimeReaction = MinTimeReaction;
//         this.MaxTimeReaction = MaxTimeReaction;
//         this.trafficLightsTime = trafficLightsTime;
//         this.reverse = reverse;
//         this.trafficLight = new TrafficLight("green");
//         this.board = new Car[boardWidth];
//         this.AllCars = new ArrayList<>();
//         this.listOfCars = new ArrayList<>();
//     }

//     public TrafficLight getLight() {
//         return this.trafficLight;
//     }

//     public ArrayList<Car> move() {
//         newWaveOfCars();

//         listOfCars.clear();
//         trafficLightsChange();

//         for (int i = boardWidth - 1; i >= 0; i--) {
//             if (board[i] != null) {
//                 if (i == boardWidth - 1) {
//                     board[i] = null;
//                 } else if (i == MoveModel.boardWidth / 2 - 5) {
//                     if (board[i + 1] == null && this.trafficLight.getState().equals("green")) {
//                         board[i].setIsMoving(true);
//                         if (board[i].getCurrentReaction() == 0) {
//                             board[i + 1] = board[i];
//                             board[i] = null;
//                         } else {
//                             board[i].currentReactionDecrease();
//                         }
//                     } else {
//                         board[i].setIsMoving(false);
//                     }
//                 } else {
//                     if (board[i + 1] == null) {
//                         board[i].setIsMoving(true);
//                         if (board[i].getCurrentReaction() == 0) {
//                             board[i + 1] = board[i];
//                             board[i] = null;
//                         } else {
//                             board[i].currentReactionDecrease();
//                         }
//                     } else {
//                         board[i].setIsMoving(false);
//                     }
//                 }
//             }
//         }

//         newCarOnBoard();

//         addCarFromBoardToList();

//         if (this.reverse) {
//             reverseCoordination();
//         }

//         // for (Car item : listOfCars) {
//         // System.out.print("car:");
//         // System.out.println(item.getPositionX());
//         // }
//         // System.out.println("");
//         timeStep++;
//         return this.listOfCars;
//     }

//     private void reverseCoordination() {
//         for (Car car : listOfCars) {
//             int x = car.getPositionX();
//             int y = positionYRightToLeft;
//             car.setPosition(boardWidth - x - 1, y);
//         }
//     }

//     private void newWaveOfCars() {
//         if (numberOfWaves > 0 && timeStep % wavesTime == 0) {
//             for (int i = 0; i < numberOfCars; i++) {
//                 Car car = new Car();
//                 Random random = new Random();
//                 if (this.MaxTimeReaction > 0) {
//                     int randomReaction = random.nextInt(this.MaxTimeReaction) + this.MinTimeReaction; // reaction
//                                                                                                       // between min and
//                                                                                                       // max;
//                     car.setReaction(randomReaction);
//                 }
//                 this.AllCars.add(car);
//             }
//             this.numberOfWaves--;
//         }
//     }

//     private void trafficLightsChange() {
//         if (timeStep % trafficLightsTime == 0) {
//             if (this.trafficLight.getState().equals("green")) {
//                 this.trafficLight.setState("red");
//             } else {
//                 this.trafficLight.setState("green");
//             }
//         }
//     }

//     private void newCarOnBoard() {
//         if (AllCars.size() > 0 && board[0] == null) { // new car from list appear on road
//             board[0] = AllCars.get(0);
//             AllCars.remove(0);
//         }
//     }

//     private void addCarFromBoardToList() {
//         for (int i = 0; i < boardWidth; i++) {
//             if (board[i] != null) {
//                 board[i].setPosition(i, positionYLeftToRight);
//                 listOfCars.add(board[i]);
//             }
//         }
//     }

//     public boolean isEnd() {
//         if (numberOfWaves == 0 && AllCars.size() == 0 && listOfCars.size() == 0) {
//             return true;
//         }
//         return false;
//     }
// }
