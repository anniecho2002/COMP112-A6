// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP-102-112 - 2021T1, Assignment 6
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.awt.Color;
import java.util.ArrayList;

/** Runs a simulation of a robot vacuum cleaner that moves around a floor area,
 *      changing to a new random direction every time it hits the edge of the
 *      floor area.
 */
public class FloorCleaner{

    // Constants for drawing the robot and the floor.
    public static final double DIAM = 60;  //diameter of the robot.
    public static final double LEFT = 50;  // borders of the floor area.
    public static final double TOP = 50;
    public static final double RIGHT = 550;
    public static final double BOT = 420;
    private Robot roomba, buddy;

    /* Simulation loop.
     * The method should draw a dirty floor (a gray rectangle), and then
     * create one robot (core) or two robots (completion) and make them run around for ever.
     * Each time step, each robot will erase the "dirt" under it, and then
     *  move forward a small amount.
     * After it has moved, the program should ask for the robot's
     *  position and check the position against the edges of the floor area.
     * If it has gone over the edge, it will make the robot step back onto the floor
     *  and change its direction.
     * For the completion, it will also check if the robots have hit each other, and
     *  if so, make them both back off and change direction
     * 
     * Hint: A robot should start in a "safe" initial position (not over the edge):
     *  its x position should be between  LEFT+DIAM/2 and RIGHT-DIAM/2
     *  its y position should be between  TOP+DIAM/2 and BOT-DIAM/2
     * Hint: For the completion, you have to make sure that starting positions of
     *  the robots are not on top of each other (otherwise they get "stuck" to each other!)
     */
    public void cleanFloor(){
        // setting up the floor
        boolean run = true;
        drawFloor();
        drawBox();
        roomba = new Robot(DIAM, LEFT+200, TOP+50, Color.RED);
        buddy = new Robot(DIAM, LEFT+350, TOP+300, Color.BLUE);
        roomba.draw(); buddy.draw();
        double xCenterDifference, yCenterDifference;
        double roomba_xObsDiff1, roomba_yObsDiff1;
        double buddy_xObsDiff1, buddy_yObsDiff1;
        double roomba_xObsDiff2, roomba_yObsDiff2;
        double buddy_xObsDiff2, buddy_yObsDiff2;
        
        while (true){
            UI.setColor(Color.blue);
            roomba.erase(); buddy.erase();
            roomba.step(); buddy.step();
            roomba.draw(); buddy.draw();
            roomba.directionIndicator(); buddy.directionIndicator();
            UI.sleep(15);
            if ((roomba.getX()<LEFT+2) || (roomba.getX()>RIGHT - 15) || (roomba.getY()<TOP+2) || (roomba.getY()>BOT - 15)){
                roomba.erase();
                roomba.changeDirection();
                drawBox();
                roomba.directionIndicator();
            }
            if ((buddy.getX()<LEFT+2) || (buddy.getX()>RIGHT - 15) || (buddy.getY()<TOP+2) || (buddy.getY()>BOT - 15)){
                buddy.erase();
                buddy.changeDirection();
                drawBox();
                buddy.directionIndicator();
            }
            xCenterDifference = Math.abs((roomba.getX()+DIAM/2) - (buddy.getX()+DIAM/2));
            yCenterDifference = Math.abs((roomba.getY()+DIAM/2) - (buddy.getY()+DIAM/2));
            
            roomba_xObsDiff1 = Math.abs((roomba.getX()+DIAM/2) - 300);
            roomba_yObsDiff1 = Math.abs((roomba.getY()+DIAM/2) - 220);
            buddy_xObsDiff1 = Math.abs((buddy.getX()+DIAM/2) - 300);
            buddy_yObsDiff1 = Math.abs((buddy.getY()+DIAM/2) - 220);
            if (xCenterDifference<=DIAM && yCenterDifference<=DIAM){
                roomba.erase(); buddy.erase();
                roomba.changeDirection(); buddy.changeDirection();
                roomba.directionIndicator(); buddy.directionIndicator();
            }
            if (roomba_xObsDiff1<=DIAM/2 + 50 && roomba_yObsDiff1<=DIAM/2 + 50){
                roomba.erase();
                roomba.changeDirection();
                roomba.directionIndicator();
                drawBox();
            }
            if (buddy_xObsDiff1<=DIAM/2 + 50 && buddy_yObsDiff1<=DIAM/2 + 50){
                buddy.erase();
                buddy.changeDirection();
                buddy.directionIndicator();
                drawBox(); // to make sure it's still intact haha, a bit unreliable
            }
            roomba_xObsDiff2 = Math.abs((roomba.getX()+DIAM/2) - 100);
            roomba_yObsDiff2 = Math.abs((roomba.getY()+DIAM/2) - 100);
            buddy_xObsDiff2 = Math.abs((buddy.getX()+DIAM/2) - 100);
            buddy_yObsDiff2 = Math.abs((buddy.getY()+DIAM/2) - 100);
            if (roomba_xObsDiff2<=DIAM/2 + 50 && roomba_yObsDiff2<=DIAM/2 + 50){
                roomba.erase();
                roomba.changeDirection();
                roomba.directionIndicator();
                drawBox();
            }
            if (buddy_xObsDiff2<=DIAM/2 + 50 && buddy_yObsDiff2<=DIAM/2 + 50){
                buddy.erase();
                buddy.changeDirection();
                buddy.directionIndicator();
                drawBox(); 
            }
        }
    }
    
    public void drawFloor (){
        UI.setColor(Color.gray);
        UI.fillRect(LEFT, TOP, RIGHT, BOT);
    }
    public void drawBox(){
        UI.setColor(Color.black);
        UI.drawRect(LEFT, TOP, RIGHT, BOT);
        
        UI.setColor(Color.red);
        UI.fillRect(250,170,100,100);
        UI.setColor(Color.black);
        UI.drawRect(250,170,100,100);
        
        UI.setColor(Color.red);
        UI.fillRect(50,50,100,100);
        UI.setColor(Color.black);
        UI.drawRect(50,50,100,100);
    }
    

    //------------------ Set up the GUI (buttons) ------------------------
    /** Make buttons to let the user run the methods */
    public void setupGUI(){
        UI.addButton("Start", this::cleanFloor);
        UI.addButton("Quit", UI::quit);
        UI.setWindowSize(650,500);
        UI.setDivider(0);
    }    

    // Main
    public static void main(String[] arguments){
        FloorCleaner fc = new FloorCleaner();
        fc.setupGUI();
    }    

}
