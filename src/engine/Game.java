package engine;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import exceptions.*;

public class Game {

	public static Cell[][] map = new Cell[15][15];
	public static ArrayList<Hero> availableHeroes = new ArrayList<Hero>();
	public static ArrayList<Hero> heroes = new ArrayList<Hero>();
	public static ArrayList<Zombie> zombies = new ArrayList<Zombie>();



	public static void main(String[] args) throws InvalidTargetException , NotEnoughActionsException
	{
		Explorer f = new Explorer("malek", 100, 20, 5);
		f.setLocation(new Point(0,0));
		map[0][0]=new CharacterCell(f);
		Zombie z = new Zombie();
		z.setLocation(new Point(0, 1));
		map[0][1]=new CharacterCell(z);
		f.setTarget(z);
		f.setSpecialAction(true);
		f.attack();

		
		System.out.println(f.getActionsAvailable());
	
	}


	public static void createCharacter(Character c){
		int x=(int)(Math.random()*15) +1;
		int y=(int)(Math.random()*15) +1;

		

	}

	public static void endTurn(){
	}

	


	public static void loadHeroes(String filePath) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();
		while (line != null) {
			String[] content = line.split(",");
			Hero hero = null;
			switch (content[1]) {
				case "FIGH":
					hero = new Fighter(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]),
							Integer.parseInt(content[3]));
					break;
				case "MED":
					hero = new Medic(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]),
							Integer.parseInt(content[3]));
					break;
				case "EXP":
					hero = new Explorer(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]),
							Integer.parseInt(content[3]));
					break;
			}
			availableHeroes.add(hero);
			line = br.readLine();

		}
		br.close();

	}

}
