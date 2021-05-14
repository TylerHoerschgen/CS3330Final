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
public class GrasslandController implements Initializable, CharacterMove
{
    @FXML
    private ImageView character;

    @FXML
    private AnchorPane aPane;
    
    private MainController mainController;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert character != null : "fx:id=\"character\" was not injected: check your FXML file 'Grassland.fxml'.";
        assert aPane != null : "fx:id=\"aPane\" was not injected: check your FXML file 'Grassland.fxml'.";
    }    
    
    public void start(Stage stage) 
    {
        mainController = MainController.getInstance();
        aPane.requestFocus();
    }
    
    private void goBackToPrevious() 
    {
        mainController.goToStart();
        mainController.firstController.enterTop(xLocation());
    }

    @FXML
    public void move(KeyEvent event)
    {   
        this.moveInt(event.getCode());
        if(xLocation()< -70 || xLocation() > 170)
        {
            if(mainController.model.startEncounter())
            {
                goToEncounter();
            }
        }
    }
    
    public void goToEncounter()
    {
        mainController.goToEncounter();
    }
    
    @Override
    public void goUp() 
    {
        mainController.goToTrainerBattle();
    }

    @Override
    public void goDown() {
        goBackToPrevious();
    }

    @Override
    public void goLeft() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Don't go any further left");
        alert.setContentText("You might realize that's there's an invisible wall.");
        alert.showAndWait();
    }

    @Override
    public void goRight() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Don't go any further right");
        alert.setContentText("You're scared of falling off the edge of the application!");
        alert.showAndWait();
    }

    @Override
    public void moveUp() {
        character.setY(character.getY()-10);
    }

    @Override
    public void moveDown() {
        character.setY(character.getY()+10);
    }

    @Override
    public void moveLeft() {
        character.setX(character.getX()-10);
    }

    @Override
    public void moveRight() {
        character.setX(character.getX()+10);
    }

    @Override
    public double xLocation() {
        return character.getX();
    }

    @Override
    public double yLocation() {
        return character.getY();
    }
    
    public void enterTop() {
        character.setY(-270);
        character.setX(0);
    }
    
    public void enterBottom() {
        character.setY(260);
        character.setX(0);
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
