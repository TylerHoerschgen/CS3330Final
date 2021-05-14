/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmh9gvfinalproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bball
 */
public class HealController implements Initializable, CharacterMove
{  
    @FXML
    private AnchorPane aPane;

    @FXML
    private ImageView character;
    
    private Boolean allHealed=false;
    
    private MainController mainController;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert aPane != null : "fx:id=\"APane\" was not injected: check your FXML file 'Shop.fxml'.";
        assert character != null : "fx:id=\"character\" was not injected: check your FXML file 'Shop.fxml'.";
    }    
    
    public void start(Stage stage) 
    {
        mainController = MainController.getInstance();
        aPane.requestFocus();
    }
    
    private void goBackToPrevious() {
       allHealed=false;
       mainController.goToStart();
       mainController.firstController.enterRight();
    }
    
    @FXML
    void move(KeyEvent event)
    {
        moveInt(event.getCode());
    }

    @Override
    public void goUp() 
    {
        if(allHealed==false)
        {
            mainController.model.healAll();
        }
        allHealed=true;
    }

    @Override
    public void goDown() {
        goBackToPrevious();
    }

    @Override
    public void goLeft() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Gross, and i'm not talking about the Java Monster!");
        alert.setContentText("You don't want to go to the left! It smells like Ahpah got in here!");
        alert.showAndWait();
    }

    @Override
    public void goRight() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Don't go there!");
        alert.setContentText("You don't want to go to the right! It smells like gelatinous cheeseburger!");
        alert.showAndWait();
    }

    @Override
    public void moveUp() {
        if(yLocation() > -130)
        {
            character.setY(character.getY()-10);
        }
        else
        {
            mainController.model.healAll();
        }
    }

    @Override
    public void moveDown() {
        character.setY(character.getY()+10);
    }

    @Override
    public void moveLeft() {
        goLeft();
    }

    @Override
    public void moveRight() {
        if(yLocation() <= 250)
        {
            goRight();
        }
    }

    @Override
    public double xLocation() {
        return character.getX();
    }

    @Override
    public double yLocation() {
        return character.getY();
    }

    public void enterBottom() {
        character.setY(260);
    }
    
    @FXML
    void about(ActionEvent event) 
    {
        mainController.about();
    }
    
    @FXML
    void open(ActionEvent event) {
        mainController.handleOpen();

    }

    @FXML
    void save(ActionEvent event) {
        mainController.handleSave();
    }
}
