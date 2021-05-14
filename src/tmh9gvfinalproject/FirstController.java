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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tmh9gv
 */
public class FirstController implements Initializable, CharacterMove
{   
    @FXML
    private AnchorPane aPane;
    
    @FXML
    private ImageView character;
    
    private final MainController MAIN_CONTROLLER = MainController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
         assert character != null : "fx:id=\"character\" was not injected: check your FXML file 'First.fxml'.";
         assert aPane != null : "fx:id=\"APane\" was not injected: check your FXML file 'First.fxml'.";
    }
    
    public void start(Stage stage) 
    {
        MAIN_CONTROLLER.stage = stage; 
        MAIN_CONTROLLER.firstScene = stage.getScene();
        MAIN_CONTROLLER.firstController = this;
        MAIN_CONTROLLER.model.addPropertyChangeListener(MAIN_CONTROLLER);
        MAIN_CONTROLLER.about();
        
        aPane.requestFocus();
    }
    
    @FXML
    public void move(KeyEvent event)
    {   
        this.moveInt(event.getCode());
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
    
    public void enterLeft()
    {
        character.setX(-540);
        character.setY(0);
    }
    
    public void enterRight() {
        character.setX(540);
        character.setY(0);
    }

    public void enterTop(Double x) {
        character.setX(x);
        character.setY(-275);
    }

    @Override
    public void goUp() 
    {
        MAIN_CONTROLLER.goToGrasslands();
        MAIN_CONTROLLER.grasslandController.enterBottom();
    }

    @Override
    public void goDown() 
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("There's nothing down here");
        alert.setContentText("It would take way to much time to code another area so...");
        alert.showAndWait();
    }

    @Override
    public void goLeft() {
        MAIN_CONTROLLER.goToShop();
        MAIN_CONTROLLER.shopController.enterRight();
    }

    @Override
    public void goRight() {
        MAIN_CONTROLLER.goToHealthCenter();
        MAIN_CONTROLLER.healController.enterBottom();
    }
    
    @FXML
    void about(ActionEvent event) 
    {
        MAIN_CONTROLLER.about();
    }
    
    @FXML
    void open(ActionEvent event) {
        MAIN_CONTROLLER.handleOpen();

    }

    @FXML
    void save(ActionEvent event) {
        MAIN_CONTROLLER.handleSave();
    }
}
