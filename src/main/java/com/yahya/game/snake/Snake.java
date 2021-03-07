package com.yahya.game.snake;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Snake {

    private final LinkedList<Point> points;
    private SnakeDirection direction;

    public Snake() {
        points = new LinkedList<>();
        this.direction = SnakeDirection.EAST;
    }

    void eat() {
        points.add(new Point(0, 0));
    }

    void addPoint(int x, int y) {
        points.add(new Point(x, y));
    }

    Point getFront() {
        Point front = new Point(getPoints().get(0));
        switch (direction) {
            case EAST -> front.x += 1;
            case NORTH -> front.y -= 1;
            case WEST -> front.x -= 1;
            case SOUTH -> front.y += 1;
        }
        return front;
    }

    void moveForward() {
        for (int i = points.size() - 1; i > 0; i--) {
            points.get(i).x  = points.get(i-1).x;
            points.get(i).y  = points.get(i-1).y;
        }
        Point front = getFront();
        getPoints().get(0).x = front.x;
        getPoints().get(0).y = front.y;
    }

    public LinkedList<Point> getPoints() {
        return points;
    }

    public SnakeDirection getDirection() {
        return direction;
    }

    public void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }

}
