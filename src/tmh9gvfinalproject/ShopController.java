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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bball
 */
public class ShopController implements Initializable, CharacterMove
{    
    @FXML
    private AnchorPane aPane;

    @FXML
    private Text moneyText;

    @FXML
    private ImageView character;

    @FXML
    private Text javaBallsText;

    @FXML
    private Text fruitText;
    
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
        updateScreen();
        aPane.requestFocus();
        
    }
    
    void updateScreen()
    {
        moneyText.setText(mainController.moneyFormat.format(mainController.model.getMoney()));
        fruitText.setText(mainController.model.getNumFruit().toString());
        javaBallsText.setText(mainController.model.getNumJavaBalls().toString());
    }
    
    private void goBackToPrevious() 
    {
        mainController.goToStart();
    }
    
    @FXML
    void buyFruit(ActionEvent event) 
    {
        mainController.model.fruitIncrement();
        updateScreen();

    }

    @FXML
    void buyJavaBall(ActionEvent event) 
    {
        mainController.model.javaBallsIncrement();
        updateScreen();
    }

    
    @FXML
    void move(KeyEvent event)
    {   
        moveInt(event.getCode());
    }

    @Override
    public void goUp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("That's a wall");
        alert.setContentText("I don't know if you know this, but typically people don't run into walls.");
        alert.showAndWait();
    }

    @Override
    public void goDown() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("You can't go down either.");
        alert.setContentText("You're uh... scared of smelling produce or something.");
        alert.showAndWait();
    }

    @Override
    public void goLeft() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("That's also a wall!");
        alert.setContentText("Don't run into walls please.");
        alert.showAndWait();
    }

    @Override
    public void goRight() {
        goBackToPrevious();
    }

    @Override
    public void moveUp() {
        goUp();
    }

    @Override
    public void moveDown() {
        goDown();
    }

    @Override
    public void moveLeft() {
        if(xLocation() > -320)
        {
            character.setX(character.getX()-10);
        }
        else
        {
            goLeft();
        }
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
    
    public void enterRight() {
        character.setX(540);
        character.setY(-285);
    }
    
    @FXML
    void about(ActionEvent event) 
    {
        mainController.about();
    }
    
    @FXML
    void open(ActionEvent event) {
        mainController.handleOpen();
        updateScreen();

    }

    @FXML
    void save(ActionEvent event) {
        mainController.handleSave();
    }
}
