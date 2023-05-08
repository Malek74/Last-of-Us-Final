package model.collectibles;

import java.awt.Point;
import java.util.ArrayList;

import engine.Game;
import exceptions.*;
import model.characters.Hero;
import model.characters.Zombie;
import model.world.CharacterCell;



public class Vaccine implements Collectible {

    public Vaccine() {

    }
    public void pickUp(Hero h){
        h.getVaccineInventory().add(this);

    }
    public void use(Hero h) {
       

		h.getVaccineInventory().remove(0);

        // should we add here no available resources exception or the item is already in our inventory?
    }


}