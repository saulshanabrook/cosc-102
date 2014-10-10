/*
    Ball.java
    
    This file defines the class representing Ball objects
    used in the Elastic Collision simulation program.

    Saul Shanabrook
    COSC 102, Colgate University.
    
    I spent 4 hours on this.

    I implemented color change by switching the colors of the two
    balls, when they hit.
*/

import java.awt.Color;
import java.awt.geom.Point2D;

public class Ball
{

    /*
     *  DEMO OPTION
     * 
     *  In order to see how the program should work,
     *  there is an option to use code from the DemoBall class.
     * 
     *  When you first download this file from Moodle, it is set
     *  to use this demo version.
     * 
     *  In order to use YOUR version instead, you should change
     *  the value of the DEMO variable below to false.
     */
  
    // set to false to use your code
    private static final boolean DEMO = false;
    public DemoBall db;
    
    // END DEMO OPTION
    
    
    /* You can put your own instance variables here */
    double radius;
    Point position = new Point(0, 0);
    Point velocity;
    Color color;
    
    public Ball(double radius, double speed, double angle, Color color)
    {
        if (DEMO) {
            db = new DemoBall(radius, speed, angle, color);
            return;
        }

        double angleInRadians = (Math.toRadians(angle));
        this.radius = radius;
        this.velocity = new Point(speed*Math.cos(angleInRadians), speed*Math.sin(angleInRadians));
        this.color = color;
    }

    
    public void updatePosition(int xmax, int ymax)
    {
        if (DEMO) {
            db.updatePosition(xmax, ymax);
            return;
        }

        double[] xRange = {(double)-xmax + radius, (double)xmax - radius};
        double[] yRange = {(double)-ymax + radius, (double)ymax - radius};

        position.move(velocity);

        if (position.getX() <= xRange[0]) {
            velocity.setSigns(1, null);
        }
        if (position.getX() >= xRange[1]) {
            velocity.setSigns(-1, null);
        }
        if (position.getY() <= yRange[0]) {
            velocity.setSigns(null, 1);
        }
        if (position.getY() >= yRange[1]) {
            velocity.setSigns(null, -1);
        }

    }
    
    
    public Color getColor() 
    {
      if (DEMO) return db.getColor();

      return color;
    }
    
    public double getRadius()
    {
        if (DEMO) return db.getRadius();
        
        return radius;
    }
    
    
    public double getX()
    {
        if (DEMO) return db.getX();

        return position.getX();
    }
    
    
    public double getY()
    {
        if (DEMO) return db.getY();

        return position.getY();
    }
    
    
    public double getMass()
    {
        if (DEMO) return db.getMass();
        
        return 4/3 * Math.PI * Math.pow(radius, 2);
    }
    

    private double getP(Ball other) {
        return (position.getX() - other.position.getX()) * (other.velocity.getX() - velocity.getX()) +
               (position.getY() - other.position.getY()) * (other.velocity.getY() - velocity.getY());
    }

    private double getD(Ball other) {
        return Math.pow(position.distance(other.position), 2);
    }

    private double getS(Ball other) {
        return Math.pow(radius + other.radius, 2);
    }
    public boolean isCollidingWith(Ball other) {
        if (DEMO) return db.isCollidingWith(other);

        if (getD(other) <= getS(other)) {
            if (getP(other) > 0) {
                return true;
            }
        }
        return false;

        
    }
    
    public void collide(Ball other)
    {
        if (DEMO) {
            db.collide(other);
            return;
        }

        double CX = (position.getX() - other.position.getX()) * getP(other) / getD(other);
        double CY = (position.getY() - other.position.getY()) * getP(other) / getD(other);
        double massSum = getMass() + other.getMass();


        velocity.move(2 * CX * other.getMass()/massSum,
                      2 * CY * other.getMass()/massSum);

        other.velocity.move(- 2 * CX * getMass()/massSum,
                            - 2 * CY * getMass()/massSum);

        Color _ = color;
        this.color = other.color;
        other.color = _;
    }

}


class Point extends java.awt.geom.Point2D.Double implements java.io.Serializable {

    Point (int x, int y) {
        super(x, y);
    }

    Point  (double x, double y) {
        super(x, y);
    }

    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public void move(Point velocity) {
        move(velocity.x, velocity.y);
    }


    public void setSigns(Integer xSign, Integer ySign) {
        if (xSign != null) {
            x = Math.abs(x) * xSign;
        }
        if (ySign != null) {
            y = Math.abs(y) * ySign;
        }
    }

}