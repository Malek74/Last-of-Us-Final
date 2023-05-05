package model.collectibles;

import model.characters.Hero;

public interface Collectible {
	
    public void pickup(Hero h);
    
    public void use(Hero h);
}
