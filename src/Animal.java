// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2024T1, Assignment 3
 * Name:
 * Username:
 * ID:
 */

import java.awt.Color;

import ecs100.*;

/**
 * An Animal object is an animal character, displayed on the screen
 * that can 
 *   go left,
 *   go right,
 *   jump,
 *   speak, "think" or shout a phrase,
 *   introduce itself.
 */

public class Animal {
    public static final double STEP = 30;

    /* Fields representing the state of a Animal */
    private String animal;
    private String name;
    private double imageX = -100;   // top left corner of image
    private double imageY = -100;
    private String direction = "left";

    /* Fields containing dimensions of Animals */
    private int imageHeight = 62;
    private int imageWidth = 70;

    private int wordsWidth = 160;
    private int wordsHeight = 45;
    private int wordSize = 12;

    // used to keep track of the animation frame of the death star
    private int frame_num = 0;

    /** Constructor requires
     *  - the type of animal,
     *  - the name of the animal,
     *  - and the coordinates (left, top) of where it should be placed.
     *    For example
     *    new Animal("dog", "Scruff", 100, 50);
     */
    public Animal(String typeOfAnimal, String nameOfAnimal, double x, double y ){
        this.animal=typeOfAnimal;
        this.name=nameOfAnimal;
        this.imageX = x;
        this.imageY = y;
        UI.setFontSize(wordSize);
        this.draw();
    }

    /** Return the horizontal central position of the Animal */
    public double getX(){
        return this.imageX+imageWidth/2.0;
    }

    /** Return the vertical central position of the Animal */
    public double getY(){
        return this.imageY+imageHeight/2.0;
    }

    /**
     * Make the Animal face to the left and
     * then move one step to the left
     */
    public void goLeft() {
        this.erase();
        this.direction="left";
        this.draw();
        UI.sleep(50);
        this.erase();
        this.imageX = this.imageX - STEP;
        this.draw();
        UI.sleep(50);
    }

    /**
     * Make the Animal face to the right and
     * then move one step to the right
     */
    public void goRight() {
        this.erase();
        this.direction="right";
        this.draw();
        UI.sleep(50);
        this.erase();
        this.imageX = this.imageX + STEP;
        this.draw();
        UI.sleep(50);
    }

    /** Move the Animal up then down */
    public void jump(double dist) {
        this.erase();
        this.imageY = this.imageY - dist*0.7;
        this.draw();
        UI.sleep(100);
        this.erase();
        this.imageY = this.imageY - dist*0.3;
        this.draw();
        UI.sleep(100);
        this.erase();
        this.imageY = this.imageY + dist*0.2;
        this.draw();
        UI.sleep(100);
        this.erase();
        this.imageY = this.imageY + dist*0.4;
        this.draw();
        UI.sleep(100);
        this.erase();
        this.imageY = this.imageY + dist*0.4;
        this.draw();
        UI.sleep(500);
    }

    /** Make the Animal say something in a speech box */
    public void speak(String words) {
        UI.setColor(Color.black);
        double boxX = this.imageX;
        double boxY = this.imageY - this.wordsHeight - 20;

        if (this.direction.equals("right"))
            boxX += 15 ;
        else
            boxX +=  this.imageWidth  - 15 - this.wordsWidth;

        UI.eraseRect(boxX, boxY, this.wordsWidth, this.wordsHeight);
        UI.drawRect(boxX, boxY, this.wordsWidth, this.wordsHeight);
        UI.drawString(words, boxX + 5, boxY + this.wordsHeight/2 + 3);

        UI.sleep(1000);

        UI.eraseRect(boxX, boxY, this.wordsWidth+1, this.wordsHeight+1);
    }

    /** Make the Animal introduce itself with a greeting word */
    public void introduce(String greeting) {
        UI.setColor(Color.black);
        this.speak(greeting + " my name is " + name);
        this.speak("I am a " + animal);
    }

    /** Makes the Animal shout in big block letter */
    public void shout(String words) {
        UI.setColor(Color.black);
        UI.setFontSize(20);
        this.speak(words.toUpperCase());
        UI.setFontSize(wordSize);
    }

    /** Make the Animal think something in a speech bubble */
    public void think(String words) {
        UI.setColor(Color.black);
        double bubbleX = this.imageX;
        double bubbleY = this.imageY - this.wordsHeight - 2;
        double circleX = this.imageX;
        double circleY = this.imageY;

        if (this.direction.equals("right")){
            bubbleX += 15 ;
            circleX += this.imageWidth + 20;
        }
        else{
            bubbleX +=  this.imageWidth  - 15 - this.wordsWidth;
            circleX -= 30;
        }

        UI.eraseOval(bubbleX, bubbleY, this.wordsWidth, this.wordsHeight);
        UI.drawOval(bubbleX, bubbleY, this.wordsWidth, this.wordsHeight);
        UI.drawString(words, bubbleX + 12, bubbleY + this.wordsHeight/2 + 3);

        UI.drawOval(circleX, circleY, 10, 10);

        UI.sleep(1000);

        UI.eraseRect(bubbleX, bubbleY, this.wordsWidth+1, this.wordsHeight+1);
        UI.eraseOval(circleX, circleY, 10, 10);
    }

    /** Make the Animal exit */
    public void exit() {
        this.erase();
        this.imageX = -(imageWidth);
    }
    

    /** Helper method that erases the Animal 
     * All the public methods that change the image call erase first */

    private void erase() {
        UI.eraseRect(this.imageX, this.imageY, this.imageWidth+1, this.imageHeight+1);
    }

    /** Helper method that draws the Animal
     * All the public methods that change the image call draw.
     */
    private void draw(){

        //draws the dark grey out line of the death star
        UI.setColor(Color.gray.darker().darker());
        UI.fillOval(this.imageX, this.imageY, this.imageWidth, this.imageHeight);
        //draws the black dish of the death star
        UI.setColor(Color.black);
        UI.fillOval(imageX + (3f/20f)*imageWidth, imageY+ (3f/20f)*imageHeight, (3f/10f)*imageWidth, (3f/10f)*imageHeight);
        //draws the black equatorial line of the death star
        UI.setLineWidth(1);
        //The plus one to the iamge_x is to make sure the whole death star is erased other wise a balck dot is some times left
        UI.drawLine(imageX+1, imageY +(1f/2f)*imageHeight, imageX +imageWidth, imageY +(1f/2f)*imageHeight);

        //draws the green charging circle of the death star laser
        if(frame_num > 5 && frame_num <10){
            UI.setColor(Color.green);
            UI.fillOval(imageX + (2f/10f)*imageWidth, imageY+ (2f/10f)*imageHeight, (2f/10f)*imageWidth, (2f/10f)*imageHeight);
            //adds one to the frame number so the animation progresses
            frame_num += 1;
        //draws the green laser of the death star    
        }else if(frame_num > 10 && frame_num <25){
            UI.setColor(Color.green);
            UI.setLineWidth(3);
            UI.drawLine(imageX + (3f/10f)*imageWidth, imageY+ (3f/10f)*imageHeight, imageX, imageY);
            //resets the frame number of the animation so it restarts
            frame_num =0;
        }else{
            //adds one to the frame number so the animation progresses
            frame_num += 1;
        }
        
    }

}

