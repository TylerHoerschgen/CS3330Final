/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmh9gvfinalproject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author bball
 */
public class Model extends JSONModel
{
    protected transient PropertyChangeSupport propertyChangeSupport;
    private transient final Random RANDOM;
    private transient final String[] NAMES;
    private transient final ArrayList<String> MONSTERS;
    private transient final JavaMonster[] ENEMY_PARTY;
    private transient JavaMonster selectedEnemyMonster;
    private transient Integer enemyTrainerFruit;
    private static final transient Double MIN_HEALTH = 0.02;
    private static final transient Double CATCH_CHANCE = 0.7;
    private static final transient Double ENEMY_FRUIT = 0.3;
    private transient Integer experience;
    private transient Double earnings;
    private transient Boolean playerWon;
    private transient Boolean gameOver;
    
    Model()
    {
        this.playerWon = false;
        this.money = 100.0;
        this.NAMES = new String[6];
        this.MONSTERS = new ArrayList();
        this.PLAYER_PARTY = new JavaMonster[6];
        this.ENEMY_PARTY = new JavaMonster[6];
        this.javaBalls = 0;
        this.fruit = 0;
        this.enemyTrainerNumber = 0;
        this.enemyTrainerFruit = 0;
        propertyChangeSupport = new PropertyChangeSupport(this);
        
        PLAYER_PARTY[0] = new JavaMonster("Crookodile", 1, 1.1, 1.0);
        selectedJavaMonster=PLAYER_PARTY[0];
        
        gameOver=false;
        
        RANDOM = new Random();
        populateNameArray();
        populateMonsterArrayList();
    }
    
    String getJavaMonsterName()
    {
        return selectedJavaMonster.getName();
    }
    
    final void populateNameArray()
    {
        NAMES[0]="Baby Cletus";
        NAMES[1]="Fisherman Crosby";
        NAMES[2]="Activist Nicole";
        NAMES[3]="Superstar Kayla";
        NAMES[4]="CS Major Tyler";
        NAMES[5]="Strong Man Sam";
    }
    
    //The arrayList allows me to easily add new Java Monsters
    final void populateMonsterArrayList()
    {
        MONSTERS.add("Po-Po Peacock");
        MONSTERS.add("Gross");
        MONSTERS.add("Platypunk");
        MONSTERS.add("Butterfly");
        MONSTERS.add("Zombie Thing");
        MONSTERS.add("Hand");
        MONSTERS.add("Crookodile");
        MONSTERS.add("Snake");
        MONSTERS.add("Ahpah");
        MONSTERS.add("Mike");
        MONSTERS.add("⎍⋏☍⋏⍜⍙");
        MONSTERS.add("Shmeergle");
    }
    
    String getTrainerName()
    {
        return NAMES[enemyTrainerNumber];
    }
    
    Integer getSelectedJavaMonsterLevel()
    {
        return selectedJavaMonster.getLevel();
    }
    
    Integer getExperience()
    {
        if(selectedJavaMonster.isLeveled())
        {
            firePropertyChange("leveled", selectedJavaMonster.getName(), selectedJavaMonster.getLevel());
        }
        return experience;
    }
    
    Double getEarnings()
    {
        return earnings;
    }
    
    Double getExperiencePercentage()
    {
        return selectedJavaMonster.getExperiencePercentage();
    }
    
    Double getMoney()
    {
        return money;
    }
    
    Double getJavaMonsterHealth()
    {
        return selectedJavaMonster.getHealth();
    }
   
    Double getEnemyMonsterHealth()
    {
        return selectedEnemyMonster.getHealth();
    }
    
    Double getMinimumHealth()
    {
        return MIN_HEALTH;
    }
    
    String getEnemyMonsterName()
    {
        return selectedEnemyMonster.getName();
    }
    
    Boolean getStatus()
    {
        return gameOver;
    }
    
    Double getJavaMonsterDamage()
    {
        return selectedJavaMonster.getDamage()*selectedEnemyMonster.getDefenseMultiplier();
    }
    
    Double getEnemyMonsterDamage()
    {
        return selectedEnemyMonster.getDamage()*selectedJavaMonster.getDefenseMultiplier();
    }
    
    Integer getNumJavaBalls()
    {
        return javaBalls;
    }
    
    Double getMoneyEarned()
    {
        return RANDOM.nextDouble()*10;
    }
    
    Integer getNumFruit()
    {
        return fruit;
    }
    
    FirstPlayer getFirstPlayer()
    {
        int x = RANDOM.nextInt(2);
        if(x==1)
        {
            return FirstPlayer.ME;
        }
        else
        {
            return FirstPlayer.OPPONENT;
        }
    } 
    
    Boolean startEncounter()
    {
        return RANDOM.nextInt(25) == 1;
    }
    
    void prepareBattle()
    {
        selectedEnemyMonster=new JavaMonster(MONSTERS.get(RANDOM.nextInt(MONSTERS.size())));
        gameOver=false;
        playerWon=false;
    }
    
    void prepareTrainerBattle()
    {
        gameOver=false;
        playerWon=false;
        switch(enemyTrainerNumber)
        {
            case 0:
                enemyTrainerFruit=0;
                ENEMY_PARTY[0]=new JavaMonster("Po-Po Peacock", 1, 1.0, 1.0);
                break;
            case 1:
                enemyTrainerFruit=1;
                ENEMY_PARTY[0]=new JavaMonster("Platypunk", 2, 1.1, 0.9);
                ENEMY_PARTY[1]=new JavaMonster("Gross", 3, 1.2, 0.8);
                break;
            case 2:
                enemyTrainerFruit=2;
                ENEMY_PARTY[0]=new JavaMonster("Hand", 3, 1.2, 0.8);
                ENEMY_PARTY[1]=new JavaMonster("Zombie Thing", 3, 1.2, 0.8);
                ENEMY_PARTY[2]=new JavaMonster("⎍⋏☍⋏⍜⍙", 4, 1.3, 0.7);
                break;
            case 3:
                enemyTrainerFruit=3;
                ENEMY_PARTY[0]=new JavaMonster("Butterfly", 4, 1.3, 0.7);
                ENEMY_PARTY[1]=new JavaMonster("Shmeergle", 4, 1.3, 0.7);
                ENEMY_PARTY[2]=new JavaMonster("Snake", 4, 1.3, 0.7);
                ENEMY_PARTY[3]=new JavaMonster("Crookodile", 5, 1.4, 0.6);
                break;
            case 4:
                enemyTrainerFruit=3;
                ENEMY_PARTY[0]=new JavaMonster("Ahpah", 5, 1.4, 0.6);
                ENEMY_PARTY[1]=new JavaMonster("Mike", 5, 1.4, 0.6);
                ENEMY_PARTY[2]=new JavaMonster("Po-Po Peacock", 5, 1.4, 0.6);
                ENEMY_PARTY[3]=new JavaMonster("Gross", 6, 1.5, 0.5);
                break;
            case 5:
                enemyTrainerFruit=4;
                ENEMY_PARTY[0]=new JavaMonster("⎍⋏☍⋏⍜⍙", 6, 1.5, 0.5);
                ENEMY_PARTY[1]=new JavaMonster("Gross", 6, 1.5, 0.5);
                ENEMY_PARTY[2]=new JavaMonster("Ahpah", 6, 1.5, 0.5);
                ENEMY_PARTY[3]=new JavaMonster("Mike", 6, 1.5, 0.5);
                ENEMY_PARTY[4]=new JavaMonster("Crookodile", 7, 1.6, 0.4);
                break;
        }
        selectedEnemyMonster=ENEMY_PARTY[0];
    } 
    
    void checkHealth()
    {
        if(selectedJavaMonster.getHealth() <= MIN_HEALTH)
        {
            Integer check = findNextAvailableMonster(PLAYER_PARTY);
            if(check <= -1)
            {
                gameOver=true;
            }
            else
            {
                selectedJavaMonster = PLAYER_PARTY[check];
            }
        }
        
    }
    
    //Not to be confused with try{} catch(){}
    //I think the name is funny.
    Integer tryCatch()
    {
        if(PLAYER_PARTY[5] == null)
        {
            if(javaBalls >=1)
            {
                javaBalls--;
                Integer location = 1;

                while(location <= 5 && PLAYER_PARTY[location] != null)
                {
                    location++;
                }
                if(RANDOM.nextDouble() > CATCH_CHANCE)
                {
                    PLAYER_PARTY[location]=selectedEnemyMonster;
                    gameOver=true;
                    firePropertyChange("caught", null, selectedEnemyMonster.getName());
                    return 1;
                }
                else
                {
                    return 0;
                }
            }
            else
            {
                firePropertyChange("cannotUse", null, "JavaBalls");
                return -1;
            }
        }
        else
        {
            firePropertyChange("fullParty", null, null);
            return -1;
        }
    }
    
    Boolean useFruit()
    {
        if(selectedJavaMonster.getHealth() < 1.0)
        {
            if(fruit >=1)
            {
                fruit--;
                selectedJavaMonster.useFruit();
                return true;
            }
            else
            {
                firePropertyChange("noFruit", null, null);
                return false;
            }
        }
        else
        {
            firePropertyChange("healthIsFull", null, null);
            return false;
        }
        
    }

    void gameOver()
    {
        gameOver=true;
    }
    
    Integer javaMonsterAttackTrainer()
    {
        Double damage = getJavaMonsterDamage();
        selectedEnemyMonster.attacked(damage);
        if(selectedEnemyMonster.getHealth() <= MIN_HEALTH)
        {
            Integer next = findNextAvailableMonster(ENEMY_PARTY);
            if(next <= 0)
            {
                if(enemyTrainerNumber >= 5)
                {
                    firePropertyChange("win", null, null);
                }
                else
                {
                    enemyTrainerNumber++;
                }
                gameOver=true;
                playerWon=true;
                experience=selectedJavaMonster.addExperience(enemyTrainerNumber);
                earnings = getMoneyEarned();
                money+=earnings;
                return 1;
            }
            else
            {
                selectedEnemyMonster=ENEMY_PARTY[next];
                return 0;
            } 
        }
        return -1;
    }
    
    void javaMonsterAttack()
    {
        Double damage = getJavaMonsterDamage();
        selectedEnemyMonster.attacked(damage);
        if(selectedEnemyMonster.getHealth() <= MIN_HEALTH)
        {
                experience=selectedJavaMonster.addExperience(1);
                gameOver=true;
                playerWon=true;
                earnings = getMoneyEarned();
                money+=earnings;
        }
    }
    
    Integer enemyMonsterAttackTrainer()
    {
        if(selectedEnemyMonster.getHealth() <= ENEMY_FRUIT && enemyTrainerFruit >0)
        {
            enemyTrainerFruit--;
            selectedEnemyMonster.useFruit();
            return 2;
        }
        else
        {
            Double damage = getEnemyMonsterDamage();
            selectedJavaMonster.attacked(damage);
            if(selectedJavaMonster.getHealth() <= MIN_HEALTH)
            {
                Integer next = findNextAvailableMonster(PLAYER_PARTY);
                if(next <= 0)
                {
                    gameOver=true;
                    playerWon=false;
                    return 1;
                }
                else
                {
                    selectedJavaMonster=PLAYER_PARTY[next];
                    return 0;
                } 
            }
            return -1;
        }
    }
    
    Boolean enemyMonsterAttack()
    {
        Double damage = getEnemyMonsterDamage();
        selectedJavaMonster.attacked(damage);
        if(selectedJavaMonster.getHealth() <= MIN_HEALTH)
        {
            Integer next = findNextAvailableMonster(PLAYER_PARTY);
            if(next <= 0)
            {
                gameOver=true;
                playerWon=false;
            }
            else
            {
                selectedJavaMonster=PLAYER_PARTY[next];
                return true;
            } 
        }
        return false;
    }
    
    Integer findNextAvailableMonster(JavaMonster[] testParty)
    {
        Integer location = 0;
        
        while(location < 6 && testParty[location] != null)
        {
            if(testParty[location].getHealth() > MIN_HEALTH && selectedJavaMonster != testParty[location])
            {
                return location;
            }
            location++;
        }
            return -1;
    }
    
    void healAll()
    {
        Integer location = 0;
        while(location < 6 && PLAYER_PARTY[location] != null)
        {
            PLAYER_PARTY[location].heal();
            location++;
        }
        if(PLAYER_PARTY[0]!=null)
        {
            selectedJavaMonster=PLAYER_PARTY[0];
        }
        firePropertyChange("healedAll", null, null);
    }
    
    void fruitIncrement()
    {
        if(money>=20)
        {
            money-=20;
            fruit++;
        }
        else
            firePropertyChange("noMoney", null, "fruit");
    }
    
    void javaBallsIncrement()
    {
        if(money>=20)
        {
            money-=20;
            javaBalls++;
        }
        else
            firePropertyChange("noMoney", null, "Java Balls");
    }
    
    Boolean playerWon()
    {
        return playerWon;
    }
    
    //All this code came from https://www.oracle.com/technical-resources/articles/java/java-se-app-design-with-mvc.html
    public void addPropertyChangeListener(PropertyChangeListener listener) 
    {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) 
    {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) 
    {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
}
