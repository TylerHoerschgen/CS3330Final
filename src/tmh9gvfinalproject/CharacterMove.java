/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmh9gvfinalproject;

import javafx.scene.input.KeyCode;

/**
 *
 * @author bball
 */
public interface CharacterMove 
{
    void goUp();
    void goDown();
    void goLeft();
    void goRight();
    
    void moveUp();
    void moveDown();
    void moveLeft();
    void moveRight();
    
    double xLocation();
    double yLocation();
    
    //I use an interface for my movement since I these functions are required to move, but also
    //because it allows me a default functions without the need to accessing the character image that is moved.
    default void moveInt(KeyCode key)
    {
        switch(key)
        {
            case A:
            case LEFT:
                if(xLocation()<-530)
                {
                    goLeft();
                }
                else
                {
                    moveLeft();
                }
                break;
            case D:
            case RIGHT:
                if(xLocation()>530)
                {
                    goRight();
                }
                else
                {
                    moveRight();
                }
                break;    
            case W:
            case UP:
                if(yLocation()<-270)
                {
                    goUp();
                }
                else
                {
                    moveUp();
                }
                break;
            case S:
            case DOWN:
                if(yLocation()>240)
                {
                    goDown();
                }
                else
                {
                    moveDown();
                }
                break;
        }
    }   
}