package model.collectibles;

import java.awt.Point;
import java.lang.annotation.Target;
import java.util.ArrayList;

import engine.Game;
import exceptions.*;
import model.characters.Character;
import model.characters.Hero;
import model.characters.Zombie;
import model.world.CharacterCell;



public class Vaccine implements Collectible {

    public Vaccine() {

    }
    public void pickUp(Hero h){
        h.getVaccineInventory().add(this);

    }
    public void use(Hero h) throws InvalidTargetException {
    
        
            ArrayList<Point> adjacentCells = h.getAdjacentCells();
            if(!(adjacentCells.contains(h.getTarget().getLocation()))){
                throw new InvalidTargetException("Target is not in adjacent cells");
            }

        
        

        if(!(h.getTarget() instanceof Zombie)){
            throw new InvalidTargetException("Target is not a Zombie");   
        }

        if(!(h.getAdjacentCells().contains(h.getTarget().getLocation()))){
            throw new InvalidTargetException("Zombie is not adjacent");
        }
        
        h.getVaccineInventory().remove(0);
        
        Hero newHero= Game.availableHeroes.remove(0);
        newHero.setLocation(h.getTarget().getLocation());
        // Game.map[(int) h.getTarget().getLocation().getY()][(int) h.getTarget().getLocation().getX()]= new CharacterCell(newHero);
        // Character.setMapVisbility(true,newHero.getAdjacentCells());
        // Game.map[(int) h.getTarget().getLocation().getY()][(int) h.getTarget().getLocation().getX()].setVisible(true);

        



        // should we add here no available resources exception or the item is already in our inventory?
    }


}