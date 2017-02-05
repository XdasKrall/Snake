package models;

/**
 * Created by dfomichev on 04.02.2017.
 */
public interface GameLogic<T extends Snake, R extends Feed> {

    T generateSnake();

    void start();

    void move();

    void changeDirection(Direction direction);

    void checkResultMovement();

    void eat();

    T getSnake();

    R getFeed();

    R generateNewFeed();

    boolean gameOver();
}
