/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmh9gvfinalproject;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author bball
 */
public abstract class JSONModel implements java.io.Serializable
{
    Integer enemyTrainerNumber;
    Integer javaBalls;
    Integer fruit;
    Double money;
    JavaMonster selectedJavaMonster;
    JavaMonster[] PLAYER_PARTY;
    
    
    //The method was created with the help of Professor Wergeles and 
    //https://www.tutorialspoint.com/json_simple/json_simple_primitive_object_array.htm
    public String toJsonString()
    {
        JSONObject obj = new JSONObject();
        if(money != null)
        {
            obj.put("money", money);
        }
        if(javaBalls != null)
        {
            obj.put("javaBalls", javaBalls);
        }
        if(fruit != null)
        {
            obj.put("fruit", fruit);
        }
        if(enemyTrainerNumber != null)
        {
            obj.put("enemyTrainerNumber", enemyTrainerNumber);
        }
        
        if(PLAYER_PARTY != null)
        {
            if(PLAYER_PARTY[0] != null)
            {
                JSONArray arr = new JSONArray();
                arr.add(PLAYER_PARTY[0].getName());
                arr.add(PLAYER_PARTY[0].getHealth());
                arr.add(PLAYER_PARTY[0].getLevel());
                arr.add(PLAYER_PARTY[0].getAttackMultiplier());
                arr.add(PLAYER_PARTY[0].getDefenseMultiplier());
                arr.add(PLAYER_PARTY[0].getExperience());
                
                obj.put("playerParty0", arr);
            }
            if(PLAYER_PARTY[1] != null)
            {
                JSONArray arr = new JSONArray();
                arr.add(PLAYER_PARTY[1].getName());
                arr.add(PLAYER_PARTY[1].getHealth());
                arr.add(PLAYER_PARTY[1].getLevel());
                arr.add(PLAYER_PARTY[1].getAttackMultiplier());
                arr.add(PLAYER_PARTY[1].getDefenseMultiplier());
                arr.add(PLAYER_PARTY[1].getExperience());
                
                obj.put("playerParty1", arr);
            }
            if(PLAYER_PARTY[2] != null)
            {
                JSONArray arr = new JSONArray();
                arr.add(PLAYER_PARTY[2].getName());
                arr.add(PLAYER_PARTY[2].getHealth());
                arr.add(PLAYER_PARTY[2].getLevel());
                arr.add(PLAYER_PARTY[2].getAttackMultiplier());
                arr.add(PLAYER_PARTY[2].getDefenseMultiplier());
                arr.add(PLAYER_PARTY[2].getExperience());
                
                obj.put("playerParty2", arr);
            }
            if(PLAYER_PARTY[3] != null)
            {
                JSONArray arr = new JSONArray();
                arr.add(PLAYER_PARTY[3].getName());
                arr.add(PLAYER_PARTY[3].getHealth());
                arr.add(PLAYER_PARTY[3].getLevel());
                arr.add(PLAYER_PARTY[3].getAttackMultiplier());
                arr.add(PLAYER_PARTY[3].getDefenseMultiplier());
                arr.add(PLAYER_PARTY[3].getExperience());
                
                obj.put("playerParty3", arr);
            }
            if(PLAYER_PARTY[4] != null)
            {
                JSONArray arr = new JSONArray();
                arr.add(PLAYER_PARTY[4].getName());
                arr.add(PLAYER_PARTY[4].getHealth());
                arr.add(PLAYER_PARTY[4].getLevel());
                arr.add(PLAYER_PARTY[4].getAttackMultiplier());
                arr.add(PLAYER_PARTY[4].getDefenseMultiplier());
                arr.add(PLAYER_PARTY[4].getExperience());
                
                obj.put("playerParty4", arr);
            }
            if(PLAYER_PARTY[5] != null)
            {
                JSONArray arr = new JSONArray();
                arr.add(PLAYER_PARTY[5].getName());
                arr.add(PLAYER_PARTY[5].getHealth());
                arr.add(PLAYER_PARTY[5].getLevel());
                arr.add(PLAYER_PARTY[5].getAttackMultiplier());
                arr.add(PLAYER_PARTY[5].getDefenseMultiplier());
                arr.add(PLAYER_PARTY[5].getExperience());
                
                obj.put("playerParty5", arr);
            }
        }
        
        return obj.toString();
    }
    
    // I used Professor Wergeles lecture and https://www.tutorialspoint.com/how-to-read-parse-json-array-using-java
    public void initFromJsonString(String jsonString)
    {
        if(jsonString == null || jsonString.equals(""))
        {
            return;
        }
        
        JSONObject jsonObj;
        try
        {
            jsonObj = (JSONObject)JSONValue.parse(jsonString);
        }
        catch(Exception ex)
        {
            return;
        }
        
        if(jsonObj == null)
        {
            return;
        }
        
        Object moneyObj = jsonObj.getOrDefault("money", null);
        money = (Double) jsonObj.getOrDefault("money", 100.0);
        
        Object javaBallsObj = jsonObj.getOrDefault("javaBalls", null);
        if(javaBallsObj != null)
        {
            if(javaBallsObj instanceof Long)
            {
                Long longJavaBalls = (Long)javaBallsObj;
                javaBalls = longJavaBalls.intValue();
            }
            else
                javaBalls = 0;
        }
        
        Object fruitObj = jsonObj.getOrDefault("fruit", null);
        if(fruitObj != null)
        {
            if(fruitObj instanceof Long)
            {
                Long longFruit = (Long)fruitObj;
                fruit = longFruit.intValue();
            }
            else
                fruit = 0;
        }
        
        Object enemyTrainerNumberObj = jsonObj.getOrDefault("enemyTrainerNumber", null);
        if(enemyTrainerNumberObj != null)
        {
            if(enemyTrainerNumberObj instanceof Long)
            {
                Long longEnemyTrainerNumber = (Long)enemyTrainerNumberObj;
                enemyTrainerNumber = longEnemyTrainerNumber.intValue();
            }
            else
                enemyTrainerNumber = 0;
        }
        
        PLAYER_PARTY[0]=null;
        JSONArray arr = (JSONArray) jsonObj.get("playerParty0");
        if(arr !=null)
        {
            String name = null;
            Double health = null;
            Integer level = null;
            Double attackMult = null;
            Double defenseMult = null;
            Double tempExp = null;
            if(arr.get(0) != null)
                name = (String) arr.get(0);
            if(arr.get(1) != null)
                health = (Double) arr.get(1);
            Object l = (Long) arr.get(2);
            if(l != null)
            {
                if(l instanceof Long)
                {
                    level=((Long) l).intValue();
                }
            }
            else
            {
                level=1;
            }
            if(arr.get(3) != null)
                attackMult = (Double) arr.get(3);
            if(arr.get(4) != null)
                defenseMult = (Double) arr.get(4);
            if(arr.get(5) != null)
                tempExp = (Double) arr.get(5);
            
            try
            {
                PLAYER_PARTY[0]=new JavaMonster(name, health, level, attackMult, defenseMult, tempExp);
                selectedJavaMonster=PLAYER_PARTY[0];
            }
            catch (Exception ex) 
            {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            }
        }      
        
        arr=null; 
        PLAYER_PARTY[1]=null;
        arr = (JSONArray) jsonObj.get("playerParty1");
        if(arr !=null)
        {
            String name = null;
            Double health = null;
            Integer level = null;
            Double attackMult = null;
            Double defenseMult = null;
            Double tempExp = null;
            if(arr.get(0) != null)
                name = (String) arr.get(0);
            if(arr.get(1) != null)
                health = (Double) arr.get(1);
            Object l = (Long) arr.get(2);
            if(l != null)
            {
                if(l instanceof Long)
                {
                    level=((Long) l).intValue();
                }
            }
            else
            {
                level=1;
            }
            if(arr.get(3) != null)
                attackMult = (Double) arr.get(3);
            if(arr.get(4) != null)
                defenseMult = (Double) arr.get(4);
            if(arr.get(5) != null)
                tempExp = (Double) arr.get(5);
            
            try
            {
                PLAYER_PARTY[1]=new JavaMonster(name, health, level, attackMult, defenseMult, tempExp);
            }
            catch (Exception ex) 
            {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            }
        }      
       
        arr=null; 
        PLAYER_PARTY[2]=null;
        arr = (JSONArray) jsonObj.get("playerParty2");
        if(arr !=null)
        {
            String name = null;
            Double health = null;
            Integer level = null;
            Double attackMult = null;
            Double defenseMult = null;
            Double tempExp = null;
            if(arr.get(0) != null)
                name = (String) arr.get(0);
            if(arr.get(1) != null)
                health = (Double) arr.get(1);
            Object l = (Long) arr.get(2);
            if(l != null)
            {
                if(l instanceof Long)
                {
                    level=((Long) l).intValue();
                }
            }
            else
            {
                level=1;
            }
            if(arr.get(3) != null)
                attackMult = (Double) arr.get(3);
            if(arr.get(4) != null)
                defenseMult = (Double) arr.get(4);
            if(arr.get(5) != null)
                tempExp = (Double) arr.get(5);
            
            try
            {
                PLAYER_PARTY[2]=new JavaMonster(name, health, level, attackMult, defenseMult, tempExp);
            }
            catch (Exception ex) 
            {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
        
        arr=null; 
        PLAYER_PARTY[3]=null;
        arr = (JSONArray) jsonObj.get("playerParty3");
        if(arr !=null)
        {
            String name = null;
            Double health = null;
            Integer level = null;
            Double attackMult = null;
            Double defenseMult = null;
            Double tempExp = null;
            if(arr.get(0) != null)
                name = (String) arr.get(0);
            if(arr.get(1) != null)
                health = (Double) arr.get(1);
            Object l = (Long) arr.get(2);
            if(l != null)
            {
                if(l instanceof Long)
                {
                    level=((Long) l).intValue();
                }
            }
            else
            {
                level=1;
            }
            if(arr.get(3) != null)
                attackMult = (Double) arr.get(3);
            if(arr.get(4) != null)
                defenseMult = (Double) arr.get(4);
            if(arr.get(5) != null)
                tempExp = (Double) arr.get(5);
            
            try
            {
                PLAYER_PARTY[3]=new JavaMonster(name, health, level, attackMult, defenseMult, tempExp);
            }
            catch (Exception ex) 
            {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
        
        arr=null; 
        PLAYER_PARTY[4]=null;
        arr = (JSONArray) jsonObj.get("playerParty4");
        if(arr !=null)
        {
            String name = null;
            Double health = null;
            Integer level = null;
            Double attackMult = null;
            Double defenseMult = null;
            Double tempExp = null;
            if(arr.get(0) != null)
                name = (String) arr.get(0);
            if(arr.get(1) != null)
                health = (Double) arr.get(1);
            Object l = (Long) arr.get(2);
            if(l != null)
            {
                if(l instanceof Long)
                {
                    level=((Long) l).intValue();
                }
            }
            else
            {
                level=1;
            }
            if(arr.get(3) != null)
                attackMult = (Double) arr.get(3);
            if(arr.get(4) != null)
                defenseMult = (Double) arr.get(4);
            if(arr.get(5) != null)
                tempExp = (Double) arr.get(5);
            
            try
            {
                PLAYER_PARTY[4]=new JavaMonster(name, health, level, attackMult, defenseMult, tempExp);
            }
            catch (Exception ex) 
            {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
        
        arr=null; 
        PLAYER_PARTY[5]=null;
        arr = (JSONArray) jsonObj.get("playerParty5");
        if(arr !=null)
        {
            String name = null;
            Double health = null;
            Integer level = null;
            Double attackMult = null;
            Double defenseMult = null;
            Double tempExp = null;
            if(arr.get(0) != null)
                name = (String) arr.get(0);
            if(arr.get(1) != null)
                health = (Double) arr.get(1);
            Object l = (Long) arr.get(2);
            if(l != null)
            {
                if(l instanceof Long)
                {
                    level=((Long) l).intValue();
                }
            }
            else
            {
                level=1;
            }
            if(arr.get(3) != null)
                attackMult = (Double) arr.get(3);
            if(arr.get(4) != null)
                defenseMult = (Double) arr.get(4);
            if(arr.get(5) != null)
                tempExp = (Double) arr.get(5);
            
            try
            {
                PLAYER_PARTY[5]=new JavaMonster(name, health, level, attackMult, defenseMult, tempExp);
            }
            catch (Exception ex) 
            {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }
}
