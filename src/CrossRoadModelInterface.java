import java.util.ArrayList;

public interface CrossRoadModelInterface {
    ArrayList<Car> move();

    boolean isEnd();

    int getTimeStep();
}