package models;

/**
 * Created by dfomichev on 04.02.2017.
 */
public abstract class BodyPart {

    private int bodyPartSize;
    private int x;
    private int y;
    private boolean isHead;

    public int getBodyPartSize() {
        return bodyPartSize;
    }

    public void setBodyPartSize(int bodyPartSize) {
        this.bodyPartSize = bodyPartSize;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isHead() {
        return isHead;
    }

    public void setHead(boolean head) {
        isHead = head;
    }
}
