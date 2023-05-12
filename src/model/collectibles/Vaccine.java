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



        h.getVaccineInventory().remove(this);

        Hero newHero= Game.availableHeroes.remove(0);
        newHero.setLocation(h.getTarget().getLocation());
        Game.map[(int) h.getTarget().getLocation().getX()][(int) h.getTarget().getLocation().getY()]= new CharacterCell(newHero);
        Game.map[(int) h.getTarget().getLocation().getX()][(int) h.getTarget().getLocation().getY()].setVisible(true);

        Game.zombies.remove(h.getTarget());
        Game.heroes.add(newHero);
        }

}