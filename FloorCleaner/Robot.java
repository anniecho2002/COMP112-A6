/* Code for COMP-102-112 - 2021T1, Assignment 6
 * Name: Annie Cho
 * Username: choanni
 * ID: 300575457
 */

import java.util.*;
import ecs100.*;
import java.awt.Color;
import java.lang.Math;
import java.util.Random;

/** The robot is a circular vacuum cleaner than runs around
 * a floor, erasing any "dirt".
 * The parameters of the constructor should include the initial position,
 * and possibly its size and color.
 * It has methods to make it step and change direction:
 *  step() makes it go forward one step in its current direction.
 *  changeDirection() makes it go backward one step, and then turn to a new
 *     (random) direction.
 * It has methods to report its current position (x and y) with the
 *     getX() and getY() methods.
 * It has methods to erase and draw itself
 *  draw() will make it draw itself,
 *  erase() will make it erase itself (cleaning the floor under it also!)
 *
 * Hint: if the the current direction of the robot is d (expressed in
 *  degrees clockwise from East), then it should step
 *     cos(d * pi/180) in the horizontal direction, and
 *     sin(d * pi/180) in the vertical direction.
 * Hint: see the Math class documentation!
 */

public class Robot{

    // Fields to store the state of the robot.
    private double diameter, xPosition, yPosition;
    private double xPositionDir, yPositionDir;
    private Color color;
    
    private double speed = 4;
    double d; // random degrees, current angle
    double max = 360;
    double min = 0;

    /** Construct a new Robot object.
     *  set its direction to a random direction, 0..360
     */
    public Robot(double diam, double xpos, double ypos, Color color){
        diameter = diam;
        xPosition = xpos;
        yPosition = ypos;
        color = color;
        d = Math.floor(Math.random()*(max-min+1)+min);
        UI.println(d);
    }

    // Methods to return the x and y coordinates of the current position
    public double getX(){
        return xPosition;
    }
    public double getY(){
        return yPosition;
    } 
    
    public void directionIndicator(){
        UI.setColor(Color.yellow);
        UI.fillOval(xPosition + 30 + Math.cos(d*(Math.PI/180))*15, 
                    yPosition + 30 + Math.sin(d*(Math.PI/180))*15, 
                    10, 10);
    }
    
    /** Step one unit in the current direction (but don't redraw) */
    public void step(){
        xPosition = xPosition + Math.cos(d*(Math.PI/180))*speed;
        yPosition = yPosition + Math.sin(d*(Math.PI/180))*speed;
    }

    /** changeDirection: move backwards one unit and change direction randomly */
    public void changeDirection(){
        xPosition = xPosition - Math.cos(d*(Math.PI/180))*4;
        yPosition = yPosition - Math.sin(d*(Math.PI/180))*4;
        d = Math.floor(Math.random()*(max-min+1)+min); // gives new direction
    }

    /** Erase the robot */
    public void erase(){
        UI.eraseOval(xPosition-1, yPosition-1, diameter+2, diameter+2);
    }

    /** Draw the robot */
    public void draw(){
        UI.setColor(color);
        UI.fillOval(xPosition, yPosition, diameter, diameter);
    }
}
