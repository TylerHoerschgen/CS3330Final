/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmh9gvfinalproject;

import java.awt.Desktop;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import static java.util.Locale.US;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author tmh9gv
 */
public class MainController implements PropertyChangeListener
{
    protected Model model;
    protected Stage stage;
    protected Scene firstScene;
    protected Scene shopScene;
    protected Scene battleScene;
    protected Scene healScene;
    protected Scene grasslandScene;
    protected Scene encounterScene;
    protected FirstController firstController;
    protected ShopController shopController;
    protected HealController healController;
    protected BattleController battleController;
    protected GrasslandController grasslandController;
    protected EncounterController encounterController;
    protected NumberFormat moneyFormat = NumberFormat.getCurrencyInstance(US);
    
    private static MainController instance;
    
    private MainController()
    {
        model = new Model();
    }
    
    public static synchronized MainController getInstance()
    {
        if(instance == null)
        {
            instance = new MainController();
        }
        return instance;
    }
    
    void about()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Welcome to Java Monsters!");
        alert.setHeaderText("Thanks for trying my game!");
        //I got the code for the Hyperlink from MrTheEdge at
        //https://www.reddit.com/r/javahelp/comments/4bqcci/how_to_make_a_link_hyperlink_in_javafx/
        Hyperlink link = new Hyperlink();
        link.setText("Can you beat my record? (24:01)");
        link.setOnAction((ActionEvent e) -> {
            if(Desktop.isDesktopSupported())
            {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=P4J8qFWgOvk"));
                } catch (IOException | URISyntaxException ex)
                {
                    Logger.getLogger(FirstController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //I borrowed this code for the TextArea and GridPane from Professor Wergeles
        TextArea textArea = new TextArea();
        textArea.setFont(Font.font(20));
        textArea.setText("Overview \n\n" +
        "This Java game was made as a final program for a Java Object Oriented Programming course I took, however, I also made this game for fun. "
        + " I spent a ton of time on and I’m proud of what I have. Some of the code may seem strange due to the limitations of JavaFX and the FXMLLoader,"
        + " but I did my best with what I knew. I’ve tested the game thoroughly and everything works as intended. In the future I may add the ability"
        + " to swap the position of Java Monsters and add types to each monster. I was thinking of having four types, water, earth, fire, and air.  \n" +
        "\nPurpose\n\n" +
        "The purpose of this game was to push my knowledge of Java and object-oriented principles to the limits. I learned a lot while creating this game and I’m glad that I created it. "
        + "For those playing the game, the purpose is to beat the final trainer and maybe even attempt to beat my world record.  \n" +
        "\nRequired Information \n\n" +
        "This game is a heavily inspired from the Pokémon games. I used to play them for hours and hours when I was a kid, so they hold a special place in my heart."
        + " When you start, you will notice a path leading to the left, right, and upwards. To the left is the shop where you can buy fruit and JavaBalls. To the right"
        + " of the is the Health Center where you can heal your Java Monsters. Heading upwards leads to the battles. You can encounter a wild Java Monster by walking through the"
        + " grass or battle a trainer by following the trail upwards again. \n\n" +
        "How to Play \n\n" +
        "You move your character with w,a,s,d or arrow keys. You start with one Java Monster, Crookodile, and your primary objective is to beat the sixth and final trainer, Strong Man Sam. To beat Sam, you will have to capture Java Monsters"
        + " and train them to a high level. The Trainers get progressively harder leading up to Sam so don’t be afraid to battle them. The only way to obtain new Java Monsters is to buy "
        + "JavaBalls from the Shop and use them on wild Java Monster. You cannot catch trainer’s Java Monsters. You can have a maximum of six Java Monsters. To train you Java Monsters you must"
        + " battle and defeat enemy monsters. When you battle enough enemy monsters your Java Monster will level up. Leveling up increases your monsters attack and defense. I’ve included save files"
        + " in the project if you want to load those. One starts on the final battle with Sam. \n" +
        "\n" +
        "How to Battle \n\n" +
        "The battles with wild monsters or with trainers have the same controls. You continue the battle by clicking in the TextArea. The battles are turn based and the starting side is random. When it’s your turn, you can fight, heal, or catch. "
        + "The fight button attacks the enemy dealing damage based on your Java Monster’s level and the enemy’s level. Pressing the “bag” button opens a menu to use fruit or to use a JavaBall."
        + " Fruit can only be used if your Java Monster’s health isn’t full and you have at least one JavaBall. JavaBalls attempt to catch the enemy monster, but once again, it cannot be a trainer’s monster. "
        + "The battle ends whenever the enemy monster is caught, or you win or lose the battle. That’s all you need to know. Good luck!");
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane content = new GridPane();
        content.setMaxWidth(Double.MAX_VALUE);
        content.add(textArea, 0, 0);
        content.add(link, 0, 1);
        alert.getDialogPane().setExpandableContent(content);
        alert.getDialogPane().setExpanded(true);
        alert.showAndWait();
        //end of borrowed codes
    }
    
    //This method was taken from Professor Wergeles' JSON lecture.
    protected void handleOpen()
    {
        Model formModel = new Model();
        if(model != null)
        {
            if(model.equals(formModel))
            {
                return;
            }
        }
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("JSON", "*.json"));
        File file = fileChooser.showOpenDialog(stage);
        if(file != null)
        {
            try
            {
                String json;
                try (FileReader fileReader = new FileReader(file.getPath()); 
                    BufferedReader bufferedReader = new BufferedReader(fileReader)) 
                {
                    json = "";
                    String line = null;
                    while((line = bufferedReader.readLine()) != null)
                    {
                        json += line;
                    }                  
                }
                
                model.initFromJsonString(json);
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("File was opened successfully!");
                alert.setHeaderText("Opened");
                alert.show();
            }
            catch(IOException ioex)
            {
                String message = "Exception occurred while saving to " + file.getPath();
                displayExceptionAlert(message, ioex);
            }
        }
    }
    
    //This method was taken from Professor Wergeles' JSON lecture.
    protected void handleSave()
    {
        if(model == null)
        {
            return;
        }
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("JSON", "*.json"));
        File file = fileChooser.showSaveDialog(stage);
        if(file != null)
        {
            try
            {
                String jsonString = model.toJsonString();
                
                try (PrintWriter out = new PrintWriter(file.getPath())) {
                    out.print(jsonString);
                }
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("File was saved successfully!");
                alert.setHeaderText("Saved");
                alert.show();
            }
            catch(IOException ioex)
            {
                String message = "Exception occurred while saving to " + file.getPath();
                displayExceptionAlert(message, ioex);
            }
        }
    }
    //This method was taken from Professor Wergeles' JSON lecture.
    private void displayExceptionAlert(String message, Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText(ex.getClass().getSimpleName());      
        alert.setContentText(message + "\n\n" + ex.getMessage());

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }
    
    //The "goTo..." functions are borrowed from the SwitchScenes lecture. 
    //I've tailored them to better fit my application by using this class.
    void goToGrasslands()
    {
        if (grasslandScene == null) 
        {
           try 
           {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("Grassland.fxml"));
               Parent page2Root = loader.load(); 
               grasslandController = loader.getController();
               
               if(grasslandController != null)
               {
                    grasslandScene = new Scene(page2Root);
                    grasslandController.start(stage);
               }

           } catch (IOException ex) {
               Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
        stage.setScene(grasslandScene);
    }
    void goToStart()
    {
        stage.setScene(firstScene);
    }
    
    void goToShop()
    {
        if (shopScene == null) 
        {
           try 
           {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("Shop.fxml"));
               Parent page2Root = loader.load();
               
               shopController = loader.getController();
               
               if(shopController != null)
               {
                    shopScene = new Scene(page2Root);
                    shopController.start(stage);
               }

           } catch (IOException ex) {
               Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
        shopController.enterRight();
        stage.setScene(shopScene);
        shopController.updateScreen();
    }
    
    void goToTrainerBattle()
    {
        if (battleScene == null) 
        {
           try 
            {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("Battle.fxml"));
                Parent page4Root = loader.load(); 
                battleController = loader.getController();
               if(battleController != null)
               {
                    battleScene = new Scene(page4Root);
                    battleController.start(stage);
               }

            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            battleController.start(stage);
        }
        stage.setScene(battleScene);
    }
    
    void goToHealthCenter()
    {
        if (healScene == null) 
        {
           try 
           {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("Heal.fxml"));
               Parent page3Root = loader.load(); 
               healController = loader.getController();
               if(healController != null)
               {
                    healScene = new Scene(page3Root);
                    healController.start(stage);
               }

           } catch (IOException ex) {
               Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
        healController.enterBottom();
        stage.setScene(healScene);
    }
    
    void goToEncounter()
    {
        if (encounterScene == null) 
        {
           try 
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Encounter.fxml"));
                Parent page4Root = loader.load(); 
                encounterController = loader.getController();
               if(encounterController != null)
                {
                    encounterScene = new Scene(page4Root);
                    encounterController.start(stage);
                }

            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            encounterController.start(stage);
        }
        stage.setScene(encounterScene);
    }
    //end of borrowed "goTo..." methods

    @Override
    public void propertyChange(PropertyChangeEvent evt) 
    {
        switch (evt.getPropertyName()) {
            case "win":
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Congratulations! You beat the final trainer! Thanks for playing!");
                alert.setHeaderText("You Win!");
                alert.show();
                break;
            case "lose":
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("You rushed your JavaMonster(s) back to the Health Center and their health has been restored!");
                alert.setHeaderText("All your Java Monsters fainted!");
                alert.show();
                goToHealthCenter();
                break;
            case "caught":
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(evt.getNewValue() + " has been added to your party!");
                alert.setHeaderText("You Caught " + evt.getNewValue() + "!");
                alert.show();
                goToGrasslands();
                break;
            case "healedAll":
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("All your Java Monsters have been healed!");
                alert.setHeaderText("Healed!");
                alert.show();
                break;
            case "noMoney":
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("You don't have enough money for " + evt.getNewValue());
                alert.setHeaderText("No money!");
                alert.show();
                break;
            case "noFruit":
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("You don't have any fruit!");
                alert.setHeaderText("No fruit!");
                alert.show();
                break;
            case "healthIsFull":
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Your Java Monster's health is already full");
                alert.setHeaderText("Cannot heal");
                alert.show();
                break;
            case "cannotUse":
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("You cannot use " + evt.getNewValue() + " right now.");
                alert.setHeaderText("Cannot use item!");
                alert.show();
                break;
            case "fullParty":
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Cannot use JavaBalls! Party is full!");
                alert.setHeaderText("Cannot use item!");
                alert.show();
                break;
            case "leveled":
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(evt.getOldValue().toString() +" leveled up to level " + evt.getNewValue());
                alert.setHeaderText("Leveled up!");
                alert.show();
                break;
            default:
                break;
            
        }
    }
}
