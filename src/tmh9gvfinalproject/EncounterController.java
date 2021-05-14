/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmh9gvfinalproject;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bball
 */
public class EncounterController extends FightSuper implements Initializable, Runnable
{
    @FXML
    private ImageView enemyMonster;

    @FXML
    private Button bagButton;

    @FXML
    private ImageView javaMonster;

    @FXML
    private ProgressBar javaMonsterHealthBar;

    @FXML
    private ProgressBar enemyMonsterHealthBar;

    @FXML
    private Button fightButton;

    @FXML
    private TextArea dialogTextArea;
    
    @FXML
    private Text fruitText;
    
    @FXML
    private Text javaBallsText;
    
    @FXML
    private AnchorPane bagPane;
    
    @FXML
    private Label experienceLabel;

    @FXML
    private ProgressBar experienceProgressBar;
    
    @FXML
    private Text javaMonsterLevelText;
    
    @FXML
    private Text javaMonsterNameText;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void start(Stage stage) 
    {
        mainController = MainController.getInstance();
        mainController.model.prepareBattle();
        mainController.model.checkHealth();
        
        super.setEnemyMonsterImage(enemyMonsterImage, enemyMonster);
        super.setJavaMonsterImage(javaMonsterImage, javaMonster);
        
        super.updateHealthBar(javaMonsterHealthBar, enemyMonsterHealthBar);
        
        super.updateGUIText(javaMonsterNameText, javaMonsterLevelText);
        
        turnDisable();
        
        try
        {
            thread = new Thread(this);
            ///Daemon ensure the thread doesn't prevent closing the program.
            //I only use this in case the user closes when using on the linked scene.
            //https://www.javatpoint.com/daemon-thread
            thread.setDaemon(true);
            thread.start();
        }
        catch(Exception ex)
        {
            Logger.getLogger(EncounterController.class.getName()).log(Level.SEVERE, null, ex);
            mainController.goToGrasslands();
        }
        
    }
    
    void turnDisable()
    {
        fightButton.setDisable(true);
        bagButton.setDisable(true);
        bagPane.setVisible(false);
        dialogTextArea.setDisable(false);
    }
    void turnEnable()
    {
        fightButton.setDisable(false);
        bagButton.setDisable(false);
        dialogTextArea.setDisable(true);
    }
    
    private void goBackToPrevious() 
    {
       mainController.goToGrasslands();
    }
    
    @FXML
    void openBag(ActionEvent event) 
    {
        super.openBag(bagPane, bagButton, fruitText, javaBallsText);
    }
    
    @FXML
    void useFruit(ActionEvent event) 
    {
        Platform.runLater(() -> 
        {
            if(mainController.model.useFruit())
            {
                turnDisable();
                javaMonsterHealthBar.setProgress(mainController.model.getJavaMonsterHealth());
                dialogTextArea.setText((javaMonsterName +" ate fruit and was healed."));
                fruitText.setText(mainController.model.getNumFruit().toString());
                resume();
            }
            
            bagPane.setVisible(false);
            bagButton.setText("Bag");
        }); 
    }

    @FXML
    void throwBall(ActionEvent event) 
    {
        Integer caught=mainController.model.tryCatch();
        
        Platform.runLater(() -> 
        {
            bagPane.setVisible(false);
            bagButton.setText("Bag");
            javaBallsText.setText(mainController.model.getNumJavaBalls().toString());
            if(caught==0)
            {
                dialogTextArea.setText(enemyMonsterName + " broke free!");
            }
        });
        
        if(caught!=-1)
        {
            Platform.runLater(() -> 
            {
                dialogTextArea.setDisable(false);
                fightButton.setDisable(true);
                bagButton.setDisable(true);
            });
      
            resume();
        }
    }

    @FXML
    void attack(ActionEvent event)
    {
        Platform.runLater(() -> 
        {
            dialogTextArea.setText(javaMonsterName + " attacked!");
            mainController.model.javaMonsterAttack();
            turnDisable();
            bagButton.setText("Bag");
            super.updateHealthBar(javaMonsterHealthBar, enemyMonsterHealthBar);
            super.enemyLowerThanMin(enemyMonster, enemyMonsterHealthBar);
        });
        
        
        resume();
    }
    
    void enemyAttack()
    {
            if(mainController.model.enemyMonsterAttack())
            {
                Platform.runLater(() -> {
                    dialogTextArea.setText(javaMonsterName + "fainted!");
                    String tempName=javaMonsterName;
                    super.setJavaMonsterImage(javaMonsterImage, javaMonster);
                    super.updateGUIText(javaMonsterNameText, javaMonsterLevelText);
                    dialogTextArea.setText(tempName + " fainted! You sent out " + javaMonsterName + ".");
                });
                
            }
            Platform.runLater(() -> {
                super.updateHealthBar(javaMonsterHealthBar, enemyMonsterHealthBar);
                if(mainController.model.getJavaMonsterHealth() < mainController.model.getMinimumHealth())
                {
                    javaMonster.setVisible(false);
                    javaMonsterHealthBar.setVisible(false);
                    javaMonsterNameText.setVisible(false);
                    javaMonsterLevelText.setVisible(false);
                }
            });
    }
    
    @FXML
    void resume(MouseEvent event)
    {
        super.resume();
    }
    
    void enemyAttackDialog()
    {
        Platform.runLater(() -> {
            dialogTextArea.setText("It's " + enemyMonsterName + "'s turn!");
        });
        
        pause();
        
        Platform.runLater(() -> {
        dialogTextArea.setText(enemyMonsterName + " attacked!");
        enemyAttack();
        });

        pause();
    }
    
    void myAttackDialog()
    {
        Platform.runLater(() -> 
        {
            dialogTextArea.setText("It's my turn!");
        });
        
        pause();
        
        Platform.runLater(() -> 
        {
            turnEnable();
        });
        
        pause();
        
        pause();
    }

    @Override
    public void run() 
    {
        Platform.runLater(() -> {
            dialogTextArea.setText("A battle has started with " + enemyMonsterName + "!");
            enemyMonster.setVisible(true);
            enemyMonsterHealthBar.setVisible(true);
        });
        
        pause();
        
        Platform.runLater(() -> {
            dialogTextArea.setText("You sent out " + javaMonsterName + "!");
            javaMonster.setVisible(true);
            javaMonsterHealthBar.setVisible(true);
            javaMonsterNameText.setVisible(true);
            javaMonsterLevelText.setVisible(true);
        });
        
        pause();
        
        javaMonsterHealthBar.setProgress(mainController.model.getJavaMonsterHealth());
        
        switch(mainController.model.getFirstPlayer())
        {
            case ME:
                while(mainController.model.getStatus()==false)
                {
                    myAttackDialog();
                    if(mainController.model.getStatus()==false)
                    {
                        enemyAttackDialog();
                    }
                }
                break;
            case OPPONENT:
                while(mainController.model.getStatus()==false)
                {
                    enemyAttackDialog();
                    if(mainController.model.getStatus()==false)
                    {
                        myAttackDialog();
                    }
                }
                break;
            default:
                System.out.println("An error has occurred in picking first player");
                break;
        }
        
        if(mainController.model.playerWon())
        {
            Platform.runLater(() -> {
                experienceProgressBar.setProgress(mainController.model.getExperiencePercentage());
                experienceProgressBar.setVisible(true);
                experienceLabel.setVisible(true);
                dialogTextArea.setDisable(false);
                enemyMonster.setVisible(false);
                enemyMonsterHealthBar.setVisible(false);
                dialogTextArea.setText("You beat " + enemyMonsterName + "!");
            });

            pause();

            Platform.runLater(() -> {
                dialogTextArea.setText(javaMonsterName + " gained " + mainController.model.getExperience() + " experience!");
                experienceProgressBar.setProgress(mainController.model.getExperiencePercentage());
                super.updateGUIText(javaMonsterNameText, javaMonsterLevelText);
            });

            pause();

            Platform.runLater(() -> {
                dialogTextArea.setText("You gained " + mainController.moneyFormat.format(mainController.model.getEarnings()) + "!");
            });

            pause();

            Platform.runLater(() -> {
                experienceProgressBar.setVisible(false);
                experienceLabel.setVisible(false);
                goBackToPrevious();
            });
        }
        else
        {
            Platform.runLater(() -> {
                dialogTextArea.setText(enemyMonsterName + " beat you!");
                javaMonster.setVisible(false);
                javaMonsterHealthBar.setVisible(false);
                javaMonsterNameText.setVisible(false);
                javaMonsterLevelText.setVisible(false);
            });

            pause();

            Platform.runLater(() -> {
                dialogTextArea.setText("You rush back to the Health Center to heal your Java Monsters!");
            });

            pause();

            Platform.runLater(() -> 
            {
                mainController.goToHealthCenter();
                mainController.model.healAll();
            });
        }
    }
    
    @FXML
    void about(ActionEvent event) 
    {
        mainController.about();
    }
    
    @FXML
    void open(ActionEvent event) 
    {
        super.open();
    }

    @FXML
    void save(ActionEvent event) 
    {
        super.save();
    }
}