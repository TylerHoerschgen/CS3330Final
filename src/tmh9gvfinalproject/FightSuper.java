/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmh9gvfinalproject;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 *
 * @author tmh9gv
 */
//This is a superclass for BattleController and EncounterController.
//I tried to combine a lot of code, however, a lot of the code is dependent
//on the FXML objects and those are created by the FXMLLoader so I don't know how
//to access them in other classes.
public abstract class FightSuper 
{
    MainController mainController;
    Image enemyMonsterImage;
    Image javaMonsterImage;
    Thread thread;
    String enemyMonsterName;
    String javaMonsterName;
    String enemyTrainerName;
    
    FightSuper()
    {
        mainController = MainController.getInstance();
    }
    
    void pause()
    {
        //unfortunately, I don't know how to make this thread final and have it work with the subclasses.
        //Also I need to create new threads in the subclasses many times.
        //Luckily I've not experienced any concurrency issues.
        synchronized(thread)
        {
            try 
            {
                thread.wait();
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(BattleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
    
    void resume()
    {
        synchronized(thread)
        {
            thread.notify();
        }
    }
    
    void setEnemyMonsterImage(Image enemyMonsterImage, ImageView enemyMonsterImageView)
    {
        try
        {
            enemyMonsterName = mainController.model.getEnemyMonsterName();
            enemyMonsterImage= new Image("/images/" + enemyMonsterName + ".png");
            enemyMonsterImageView.setImage(enemyMonsterImage);
        }
        catch(Exception ex)
        {
            Logger.getLogger(FightSuper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void setJavaMonsterImage(Image javaMonsterImage, ImageView javaMonsterImageView)
    {
        try
        {
            javaMonsterName = mainController.model.getJavaMonsterName();
            javaMonsterImage= new Image("/images/" + javaMonsterName + ".png");
            javaMonsterImageView.setImage(javaMonsterImage);
        }
        catch(Exception ex)
        {
            Logger.getLogger(FightSuper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void updateGUIText(Text name, Text level)
    {
        name.setText(javaMonsterName);
        level.setText("Level:" +  mainController.model.getSelectedJavaMonsterLevel().toString());
    }
    
    void updateHealthBar(ProgressBar javaMonsterHealthBar, ProgressBar enemyMonsterHealthBar)
    {
        javaMonsterHealthBar.setProgress(mainController.model.getJavaMonsterHealth());
        enemyMonsterHealthBar.setProgress(mainController.model.getEnemyMonsterHealth());
    }
    
    void enemyLowerThanMin(ImageView enemyMonster, ProgressBar enemyMonsterHealthBar)
    {
        if(mainController.model.getEnemyMonsterHealth() < mainController.model.getMinimumHealth())
            {
                enemyMonster.setVisible(false);
                enemyMonsterHealthBar.setVisible(false);
            }
    }
    
    void openBag(AnchorPane bagPane, Button bagButton, Text fruitText, Text javaBallsText)
    {
        if(bagPane.isVisible())
        {
            bagPane.setVisible(false);
            bagButton.setText("Bag");
        }
        else
        {
            bagPane.setVisible(true);
            bagButton.setText("Close Bag");
            fruitText.setText(mainController.model.getNumFruit().toString());
            javaBallsText.setText(mainController.model.getNumJavaBalls().toString());
        }
    }
    
    void open()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("You can't open a file during battle!");
        alert.setHeaderText("Cannot open");
        alert.show();
    }
    
    void save()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("You can't save a file during battle! That's cheating! >:(");
        alert.setHeaderText("Cannot save");
        alert.show();
    }
}
