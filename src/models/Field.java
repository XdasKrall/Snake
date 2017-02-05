package models;

import java.awt.*;

/**
 * Created by dfomichev on 04.02.2017.
 */
public interface Field {

    void drawSnake(Graphics g, Snake snake);

    void drawFeed(Graphics g, Feed feed);

    void drawText(Graphics g, String text);
}
