package snake;

import models.Feed;
import models.Field;
import models.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.Timer;

/**
 * Created by dfomichev on 04.02.2017.
 */
public class BaseField extends JPanel implements Field, KeyListener {

    private BaseGame game;
    private TimerTask task;
    private Timer timer;


    public BaseField(){
        setBounds(0, 0, Constants.PANEL_WIDTH, Constants.PANEL_HEIGHT);
        setVisible(true);
        setBackground(Color.blue);
        setFocusable(true);
        addKeyListener(this);
        timer = new Timer();
        task = new UpdateTimerTask();
        timer.schedule(task, Constants.DEFAULT_PERIOD_UPDATING, Constants.DEFAULT_PERIOD_UPDATING);
        game = new BaseGame(Constants.PANEL_WIDTH, Constants.PANEL_HEIGHT);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Constants.PANEL_WIDTH, Constants.PANEL_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BaseSnake snake = game.getSnake();
        drawSnake(g, snake);
        BaseFeed feed = game.getFeed();
        drawFeed(g, feed);
        if(game.isGameOver){
            drawText(g, "Game over");
        }
    }

    @Override
    public void drawSnake(Graphics g, Snake snake) {
        for(Object bodyPart : snake.getParts()){
            g.setColor(snake.getColor());
            BaseBodyPart part = (BaseBodyPart) bodyPart;
            int x = part.getX();
            int y = part.getY();
            int size = part.getBodyPartSize();
            g.fillRect(x, y, size, size);
            if(((BaseBodyPart) bodyPart).isHead()){
                g.setColor(Color.red);
                g.fillOval(x, y, size, size);
            }
        }
    }

    @Override
    public void drawFeed(Graphics g, Feed feed) {
        g.setColor(Color.green);
        int x = feed.getX();
        int y = feed.getY();
        int size = feed.getSize();
        g.fillRect(x, y, size, size);
    }

    @Override
    public void drawText(Graphics g, String text) {
        g.setColor(Color.black);
        int x = (Constants.PANEL_WIDTH / 2) - text.length() * 4;
        int y = (Constants.PANEL_HEIGHT / 2) - text.length() * 4;
        g.drawString(text, x, y);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() != KeyEvent.VK_UP && e.getKeyCode() != KeyEvent.VK_DOWN
                && e.getKeyCode() != KeyEvent.VK_LEFT && e.getKeyCode() != KeyEvent.VK_RIGHT)
            return;

        game.keyPressed(e);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private class UpdateTimerTask extends TimerTask {

        @Override
        public void run() {
            repaint();
        }
    }
}
