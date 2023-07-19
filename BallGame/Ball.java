/* Code for COMP-102-112 - 2021T1, Assignment 6
 * Name: Annie Cho
 * Username: choanni
 * ID: 300575457
 */

import ecs100.*;
import java.awt.Color;
import java.util.Random;

/** Ball represents a ball that is launched by the mouse towards a direction.
 *    Each time the step() method is called, it will take one step.  
 * For the Completion part, gravity should act on the ball by reducing its vertical speed.
 */

public class Ball{

    // Constants for all balls: size, position of the ground
    public static final double DIAM = 16;  // diameter of the balls.
    public static final double GROUND = BallGame.GROUND;
    public static final double RIGHT_END = BallGame.RIGHT_END;

    // Fields to store state of the ball:
    // x position, height above ground, stepX, stepY, colour
    //   The ball should initially be not moving at all. (step should be 0)
    //
    double xPosition = 0;
    double yPosition = 0; // height above ground
    double stepX = 0;
    double stepY = 0;
    Color color = Color.blue;
    
    double horizSpeed = 0;
    double vertSpeed = 0;

    // Constructor
    /** Construct a new Ball object.
     *    Parameters are the initial position (x and the height above the ground),
     *    Stores the parameters into fields 
     *    and initialises the colour to a random colour
     *  SHOULD NOT DRAW THE BALL!
     */
    public Ball(double x, double h){
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        
        color = new Color(r, g, b);
        xPosition = x;
        yPosition = h;
    }
    
    public Color returnColor(){
        return color;
    }

    // Methods
    /**
     * Draw the ball on the Graphics Pane in its current position
     * (unless it is past the RIGHT_END )
     */
    public void draw(){
        if (xPosition < RIGHT_END){
            UI.setColor(color);
            UI.fillOval(xPosition-DIAM/2, (GROUND-yPosition)-DIAM, DIAM, DIAM);
        }
    }

    /**
     * Move the ball one step (DO NOT REDRAW IT)
     * Core:
     *    Change its height and x position using the vertical and horizonal steps
     * Completion:
     *    Reduce its vertical speed each step (due to gravity), 
     *    If it would go below ground, then change its y position to ground level and
     *      set the  vertical speed back to 0.
     */
    public void step(){
        vertSpeed = vertSpeed - 0.2;
        stepX = 1;
        stepY = 1; // distance of one step
        
        if (horizSpeed < 0){
            xPosition = xPosition - stepX*horizSpeed;
        }
        else {
            xPosition = xPosition + stepX*horizSpeed;
        }
        
        yPosition = yPosition + stepY*vertSpeed;
        if (yPosition < 0){
            yPosition = 0;
        }
        
        /*if (vertSpeed < 0){
            yPosition = yPosition - stepY*vertSpeed;
            //if (yPosition < 0){
            //    yPosition = 0;
            //}
        }
        else{
            yPosition = yPosition + stepY*vertSpeed;
        } */
    }

    /**
     * Set the horizontal speed of the ball: how much it moves to the right in each step.
     * (negative if ball going to the left).
     */
    public void setXSpeed(double xSpeed){
        horizSpeed = xSpeed;
    }

    /**
     * Set the vertical speed of the ball: how much it moves up in each step
     * (negative if ball going down).
     */
    public void setYSpeed(double ySpeed){
        vertSpeed = ySpeed;
    }

    /**
     * Return the height of the ball above the ground
     */
    public double getHeight(){
        return yPosition;
    }

    /**
     * Return the horizontal position of the ball
     */
    public double getX(){
        return xPosition;
    }
}
