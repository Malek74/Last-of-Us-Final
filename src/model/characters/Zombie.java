package model.characters;

public class Zombie extends Character{
	private  static int ZOMBIESCOUNT=1;
	
	public Zombie() {
		super("Zombie " +ZOMBIESCOUNT,40,10);
	}
}
