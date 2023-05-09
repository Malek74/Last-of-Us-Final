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
        
                // ArrayList<Point> adjacentCells = h.getAdjacentCells();
            // if(!(adjacentCells.contains(h.getTarget().getLocation()))){
            //     throw new InvalidTargetException("Target is not in adjacent cells");
            // }

        
        

        // if(!(h.getTarget() instanceof Zombie)){
        //     throw new InvalidTargetException("Target is not a Zombie");   
        // }

        
        h.getVaccineInventory().remove(this);
        
        Hero newHero= Game.availableHeroes.remove(0);
        newHero.setLocation(h.getTarget().getLocation());
        Game.map[(int) h.getTarget().getLocation().getX()][(int) h.getTarget().getLocation().getY()]= new CharacterCell(newHero);
        Game.map[(int) h.getTarget().getLocation().getX()][(int) h.getTarget().getLocation().getY()].setVisible(true);

        Game.zombies.remove(h.getTarget());
        Game.heroes.add(newHero);
        System.out.println(Game.heroes.size());

        
       





        // should we add here no available resources exception or the item is already in our inventory?
    }


}