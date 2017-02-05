package snake;

import models.Action;
import models.BodyPart;
import models.Direction;
import models.GameLogic;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dfomichev on 04.02.2017.
 */
public class BaseGame implements GameLogic, Action {

    private static final Random random = new Random();

    private BaseSnake snake;
    boolean isGameOver;
    private int fieldHeight;
    private int fieldWidth;
    private BaseFeed feed;
    private TimerTask task;
    private Timer timer;
    private int lastPartX;
    private int lastPartY;

    public BaseGame(int width, int height){
        fieldWidth = width;
        fieldHeight = height;
        snake = generateSnake();
        feed = generateNewFeed();
        start();
    }

    @Override
    public BaseSnake generateSnake() {
        BaseSnake snake = new BaseSnake();
        snake.setAlive(true);
        BaseBodyPart head = new BaseBodyPart();
        head.setX(0);
        head.setY(0);
        head.setBodyPartSize(Constants.DEFAULT_BODY_PART_SIZE);
        head.setHead(true);
        snake.addPart(head);
        snake.setDirection(Direction.RIGHT);
        snake.setMovementSpeed(1);
        snake.setColor(Color.black);
        return snake;
    }

    @Override
    public void start() {
        timer = new Timer();
        task = new MoveTimerTask();
        timer.schedule(task, Constants.DEFAULT_PERIOD_MOVEMENT, Constants.DEFAULT_PERIOD_MOVEMENT);
    }

    @Override
    public void move() {

        int horizontalMove = 0;
        int verticalMove = 0;

        switch (snake.getDirection()){
            case BOTTOM:
                verticalMove = Constants.DEFAULT_MOVE_STEP;
                break;
            case LEFT:
                horizontalMove = -Constants.DEFAULT_MOVE_STEP;
                break;
            case RIGHT:
                horizontalMove = Constants.DEFAULT_MOVE_STEP;
                break;
            case TOP:
                verticalMove = -Constants.DEFAULT_MOVE_STEP;
                break;
            default:
                break;
        }
        for(Object bodyPart : snake.getParts()){
            BaseBodyPart part = (BaseBodyPart) bodyPart;
            if(part.isHead()){
                lastPartX = part.getX();
                lastPartY = part.getY();
                part.setX(lastPartX + horizontalMove);
                part.setY(lastPartY + verticalMove);
            }
            else{
                int tempX = lastPartX;
                int tempY = lastPartY;
                lastPartX = part.getX();
                lastPartY = part.getY();
                part.setX(tempX);
                part.setY(tempY);
            }
        }
        checkResultMovement();
    }

    @Override
    public void changeDirection(Direction direction) {
        snake.setDirection(direction);
    }

    @Override
    public void checkResultMovement() {
        BaseBodyPart head = (BaseBodyPart) snake.getParts().get(0);
        Rectangle rect = new Rectangle();
        rect.x = head.getX();
        rect.y = head.getY();
        rect.width = head.getBodyPartSize();
        rect.height = head.getBodyPartSize();
        if(rect.contains(feed.getX(), feed.getY())
                || rect.contains(feed.getX() + feed.getSize(), feed.getY())
                || rect.contains(feed.getX(), feed.getY() + feed.getSize())
                || rect.contains(feed.getX() + feed.getSize(), feed.getY() + feed.getSize())){
            eat();
        }
        if(head.getX() >= fieldWidth || head.getX() < 0 || head.getY() >= fieldHeight || head.getY() < 0){
            isGameOver = true;
            gameOver();
            return;
        }
        if(snake.getParts().size() > 1) {
            for (BodyPart part : snake.getParts()) {
                if (!part.isHead() && (head.getX() == part.getX() && head.getY() == part.getY())) {
                    isGameOver = true;
                    gameOver();
                    return;
                }
            }
        }
    }

    @Override
    public void eat() {
        feed = generateNewFeed();
        BaseBodyPart bodyPart = new BaseBodyPart();
        bodyPart.setX(lastPartX);
        bodyPart.setY(lastPartY);
        bodyPart.setBodyPartSize(Constants.DEFAULT_FEED_SIZE);
        snake.addPart(bodyPart);
    }

    @Override
    public BaseSnake getSnake() {
        return this.snake;
    }

    @Override
    public BaseFeed getFeed() {
        return feed;
    }

    @Override
    public BaseFeed generateNewFeed() {
        BaseFeed feed = new BaseFeed();
        feed.setSize(Constants.DEFAULT_FEED_SIZE);
        int randomX = random.nextInt(fieldWidth - feed.getSize());
        int randomY = random.nextInt(fieldHeight - feed.getSize());
        feed.setX(randomX);
        feed.setY(randomY);
        return feed;
    }

    @Override
    public boolean gameOver() {
        timer.cancel();
        timer.purge();
        return isGameOver;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        Direction direction = snake.getDirection();
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                if(direction == Direction.TOP || direction == Direction.BOTTOM)
                    return;
                direction = Direction.TOP;
                break;
            case KeyEvent.VK_DOWN:
                if(direction == Direction.BOTTOM || direction == Direction.TOP)
                    return;
                direction = Direction.BOTTOM;
                break;
            case KeyEvent.VK_LEFT:
                if(direction == Direction.LEFT || direction == Direction.RIGHT)
                    return;
                direction = Direction.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                if(direction == Direction.RIGHT || direction == Direction.LEFT)
                    return;
                direction = Direction.RIGHT;
                break;
        }
        changeDirection(direction);

    }

    private class MoveTimerTask extends TimerTask {

        @Override
        public void run() {
            move();
        }
    }
}
