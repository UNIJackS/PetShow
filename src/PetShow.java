// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2024T1, Assignment 3
 * Name:
 * Username:
 * ID:
 */

 import ecs100.*;

 /** Program to create simple animated animal character using the
  *  Animal class.  
  */
 
 public class PetShow{
 
     public static final double EXIT_LEFT = 200;    
     public static final double EXIT_RIGHT = 700;    
     public static final double START_LEFT = 300;    
     public static final double START_RIGHT = 600;    
 
     /** animate creates three animals (randomly situated),
      *   that performs the same routine.
      *   Then the left and right most animals will compete
      *      towards their respective exit (the closest to them).
      *   The animal in the middle (horizontally) remains still.
      *   The animation stops when one of them have crossed their
      *      respective exit line.
      */
     public void animate(){
         UI.drawLine(EXIT_LEFT, 10, EXIT_LEFT, 700);
         UI.drawLine(EXIT_RIGHT, 10, EXIT_RIGHT, 700);
         /*# YOUR CODE HERE */

         Animal a1 = new Animal("grasshopper", "dave", (Math.random()*(START_RIGHT-START_LEFT))+START_LEFT, 100);
         Animal a2 = new Animal("dinosaur", "steve", (Math.random()*(START_RIGHT-START_LEFT))+START_LEFT, 300);
         Animal a3 = new Animal("turtle", "john doe", (Math.random()*(START_RIGHT-START_LEFT))+START_LEFT, 500);
         routine(a1);
         routine(a2);
         routine(a3);

         while(true){
            if(closestToLeft(a1, a2, a3).getX() < EXIT_LEFT ){
                closestToLeft(a1, a2, a3).shout("I won");
                break;
            }
            if(closestToRight(a1, a2, a3).getX() > EXIT_RIGHT ){
                closestToLeft(a1, a2, a3).shout("I won");
                break;
            }

            closestToLeft(a1, a2, a3).goLeft();
            closestToRight(a1, a2, a3).goRight();

         }


     }

        public void routine (Animal a){
        /*# YOUR CODE HERE */

        a.introduce("Hi");
        for(int i=0; i < 6; i +=1){
            a.goLeft();
        }
        a.jump(30);
        for(int i=0; i < 12; i +=1){
            a.goRight();
        }
        a.jump(30);
        for(int i=0; i < 6; i +=1){
            a.goLeft();
        }
        a.jump(50);
        a.shout("Ready");
    }
 
     /** return the animal closest to the left */
     public Animal closestToLeft (Animal a1, Animal a2, Animal a3){
         if(a1.getX() <= a2.getX() && a1.getX() <= a3.getX()){
            return(a1);
         }
         if(a2.getX() < a1.getX() && a2.getX() <= a3.getX()){
            return(a2);
         }
         if(a3.getX() < a2.getX() && a3.getX() < a1.getX()){
            return(a3);
         }

         return(a1);
 
     }
        /** return the animal closest to the left */
        public Animal closestToRight (Animal a1, Animal a2, Animal a3){
        if(a1.getX() > a2.getX() && a1.getX() > a3.getX()){
            return(a1);
        }
        if(a2.getX() > a1.getX() && a2.getX() > a3.getX()){
            return(a2);
        }
        if(a3.getX() > a2.getX() && a3.getX() > a1.getX()){
            return(a3);
        }

        return(a1);

    }

     /** Make buttons to let the user run the methods */
     public void setupGUI(){
         UI.initialise();
         UI.addButton("Clear", UI::clearGraphics );
         UI.addButton("Animate", this::animate );
         UI.addButton("Quit", UI::quit );
         UI.setWindowSize((int)(EXIT_RIGHT+100), 700);
         UI.setDivider(0);       // Expand the graphics area
     }
 
     public static void main(String[] args){
         PetShow ps = new PetShow();
         ps.setupGUI();
     }
 }
 
 