/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author m
 */

import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Random;

public class GemGeneration 
{
    
    Random random = new Random(1);
    private int gemNumber = 50;
    
    public void generateInitialGems(ArrayList entities, Hero hero)
    {
        Gem previous = null;
        for(int i=0;i< gemNumber;i++)
        {
            Gem gem = generateGem(previous, hero);
            previous = gem;
            entities.add(gem);
        }
    }
    
    public Gem generateGem(Gem previous, Hero hero)
    {
        if(previous == null)
        {   
            return new Gem(50,hero.getH(),15,15);
        }

        double ledgeX = previous.getX() + 300;
        double ledgeY = 350;

        if(previous.getY() == 350)
        {
            int determine = random.nextInt(2);

            if(determine == 1) 
            {
                ledgeY = 250;
                ledgeX += 250;
            }
            else 
            {
                ledgeY = 450;
                ledgeX += 150;
            }
        }

        else if(previous.getY() == 250) 
        {
           ledgeY = 350;
           ledgeX += 200;
        }
            

        else if(previous.getY() == 450)
        {
            int determine = random.nextInt(2);

            if(determine == 1) 
            {
                ledgeY = 350;
                ledgeX += 200;
            }
            else 
            {
                ledgeY = 550;
                ledgeX += 75;
            }
        }

        else if(previous.getY() == 550) 
        {
            ledgeY = 450;
            ledgeX += 250;
        }

        return new Gem(ledgeX,ledgeY,15,15);
    }
    
    

}
