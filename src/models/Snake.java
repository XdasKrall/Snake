package models;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dfomichev on 04.02.2017.
 */
public abstract class Snake {

    private int movementSpeed;
    final private ArrayList<BodyPart> parts;
    private Color color;
    private boolean isAlive;
    private Direction direction;

    public Snake(){
        parts = new ArrayList<>();
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(int speed){
        this.movementSpeed = speed;
    }

    public List<BodyPart> getParts() {
        return Collections.unmodifiableList(this.parts);
    }

    public BodyPart getHead(){
        if(parts.size() > 0)
            return parts.get(0);
        else
            return null;
    }

    public void addPart(BodyPart part){
        this.parts.add(part);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
