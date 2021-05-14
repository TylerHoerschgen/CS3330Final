/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmh9gvfinalproject;

import java.util.Random;

/**
 *
 * @author bball
 */
public class JavaMonster 
{
    private String name;
    private Double health;
    private Integer level;
    private Double attackMultiplier;
    private Double defenseMultiplier;
    private Double experience;
    private Boolean leveled;
    private static final Double BASE_DAMAGE=0.2;
    private static final Random RANDOM = new Random();
    
    JavaMonster()
    {
        this.leveled = false;
        this.experience = 0.0;
        this.defenseMultiplier = 1.0;
        this.attackMultiplier = 1.0;
        this.level = 1;
        this.health = 1.0;
        this.name="Crookodile";
    }
    JavaMonster(String name)
    {
        this();
        this.name=name;
    }
    JavaMonster(String name, Integer level, Double attackMultiplier, Double defenseMultiplier)
    {
        this(name);
        this.level=level;
        this.attackMultiplier=attackMultiplier;
        this.defenseMultiplier=defenseMultiplier;
    }
    JavaMonster(String name, Double health, Integer level, Double attackMultiplier, Double defenseMultiplier, Double experience)
    {
        this(name, level, attackMultiplier, defenseMultiplier);
        this.health=health;
        this.experience = experience;
    }
    
    public Integer addExperience(int mult)
    {
        leveled = false;
        Integer temp = RANDOM.nextInt(150);
        if(temp!=null)
        {
            temp=temp*mult;
            experience+=temp;
            if(experience >= (100*level))
            {
                experience=experience%(100*level);
                level+=1;
                leveled=true;

                attackMultiplier+=0.1;
                if(this.defenseMultiplier>0.2)
                {
                    defenseMultiplier-=0.1;
                }
            }
            return temp;
        }
        return 0;
    }
    
    public Double getExperiencePercentage()
    {
        return experience/(100*level);
    }
    
    public String getName()
    {
        return name;
    }
    
    public Integer getLevel()
    {
        return level;
    }
    
    public Double getHealth()
    {
        return health;
    }

    public Double getAttackMultiplier() {
        return attackMultiplier;
    }

    public Double getDefenseMultiplier() {
        return defenseMultiplier;
    }

    public Double getExperience() {
        return experience;
    }
    
    public Boolean isLeveled()
    {
        return leveled;
    }
    
    public void useFruit()
    {
        health+=0.5;
        if(health >1.0)
        {
            health=1.0;
        }
    }
    
    public Double getDamage()
    {
        return (BASE_DAMAGE+(double)RANDOM.nextInt(20)/100)*attackMultiplier;
    }
    
    public void attacked(Double damage)
    {
        health-=damage;
    }
    
    public void heal()
    {
        health=1.0;
    }
}
