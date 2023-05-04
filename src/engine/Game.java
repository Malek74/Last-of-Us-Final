package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.characters.*;
import model.characters.Character;
import model.collectibles.*;
import model.world.*;

public class Game {

	public static Cell[][] map= new Cell[15][15];
	public static ArrayList<Hero> availableHeroes = new ArrayList<Hero>();
	public static ArrayList<Hero> heroes = new ArrayList<Hero>();
	public static ArrayList<Zombie> zombies = new ArrayList<Zombie>();

	
	public static void main(String[] args) throws InvalidTargetException, NotEnoughActionsException {
		// Fighter f = new Fighter("Gasser", 10, 10, 10);
		// startGame(f);
		// // f.setActionsAvailable(0);
		// // System.out.println(f.getActionsAvailable());
		// // endTurn();
		// System.out.println(zombies.size());

		// Zombie z = new Zombie();
		// z.setLocation(new Point(0, 1));
		// CharacterCell cell = (CharacterCell) Game.map[0][0];
		// System.out.println(cell.getCharacter().getName());
		// f.setTarget(z);
		// f.attack();
		// f.attack();
		//  cell = (CharacterCell) Game.map[0][0];
		// System.out.println(cell.getCharacter());
		// System.out.println(map[0].length);

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

	public static void startGame(Hero h) {
		map = new Cell[15][15];
		map[0][0] = new CharacterCell(h);
		map[0][0].setVisible(true);
		h.setLocation(new Point(0, 0));
		heroes.add(h);
		availableHeroes.remove(h);

		// loop that makes every cell that isnt occupied by anything a CharacterCell
		for (int hor = 0; hor < map.length; hor++) {
			for (int ver = 0; ver < map.length; ver++) {
				if (map[hor][ver] == null) {
					map[hor][ver] = new CharacterCell(null);
				}
			}
		}
		ArrayList<Point> cells = randomPoint();
		// spawn 5 vaccines , 5 supplies & 5 trap cells in random locations
		for (int i = 0; i < 5; i++) {
			map[(int) cells.get(0).getX()][(int) cells.get(0).getY()] = new CollectibleCell(new Vaccine());
			cells.remove(0);
			map[(int) cells.get(0).getX()][(int) cells.get(0).getY()] = new CollectibleCell(new Supply());
			cells.remove(0);
			map[(int) cells.get(0).getX()][(int) cells.get(0).getY()] = new TrapCell();
			cells.remove(0);
		}

		// spawn 10 zombies in random locations
		for (int i = 0; i < 10; i++) {
			spawnZombie();
		}
		Character.setMapVisbility(true, h.getAdjacentCells());

	}

	// QUESTION: should i throw or try catch exceptions
	public static void endTurn() throws InvalidTargetException, NotEnoughActionsException {
		zombiesAttackAdjacentCells();
		Character.setMapVisbility(false);
		resetHeroes();
		spawnZombie();

	}

	// **HELPER METHODS**

	// endTurn helpers:

	private static void resetHeroes() {
		for (int i = 0; i < heroes.size(); i++) {
			Hero currHero = heroes.get(i);
			currHero.setActionsAvailable(currHero.getMaxActions());
			currHero.setTarget(null);
			currHero.setSpecialAction(false);
			ArrayList<Point> adjacent = currHero.getAdjacentCells();

			for (int j = 0; j < adjacent.size(); j++) {
				Character.setMapVisbility(true, adjacent);
			}

		}
	}

	private static void zombiesAttackAdjacentCells() throws InvalidTargetException, NotEnoughActionsException {
		// checks the adjacent cells to each zombie on the map and the first hero
		// adjacent to a zombie is attacked

		for (int i = 0; i < zombies.size(); i++) {
			Zombie currZombie = zombies.get(i);
			ArrayList<Point> adjacentToZombie = currZombie.getAdjacentCells();
			for (int j = 0; j < adjacentToZombie.size(); j++) {
				if (map[(int)adjacentToZombie.get(j).getY()][(int)adjacentToZombie.get(j).getX()] instanceof CharacterCell) {
					CharacterCell adjacentCharacterCell = (CharacterCell) map[(int)adjacentToZombie.get(j).getY()][(int)adjacentToZombie.get(j).getX()];
					Character adjacentCharacter = (Character) adjacentCharacterCell.getCharacter();
					if (adjacentCharacter instanceof Hero) {
						currZombie.setTarget(adjacentCharacter);
						currZombie.attack();
						return;
					}
				}
			}	
		}
	}

	// Win or loss helpers:
	public static boolean checkWin() {

		//tests that all vaccines are collected
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				if(map[i][j] instanceof CollectibleCell){
					CollectibleCell cell =  (CollectibleCell) map[i][j];
					if(cell.getCollectible() instanceof Vaccine){
						return false;
					}
				}
			}
		}

		//checks that no hero has a vaccine in his inventory  
		for(int i=0;i<heroes.size();i++){
			if(heroes.get(i).getVaccineInventory().size()!=0){
				return false;
			}
		}

		if(heroes.size()<5){
			return false;
		}

		return true;
	}

	public static boolean checkGameOver() {
		return heroes.size() == 0 ;
	}

	// Random location spawners:
	public static ArrayList<Point> randomPoint() {
		ArrayList<Point> emptyCells = new ArrayList<>();
		Random rand = new Random();

		int randomX = rand.nextInt(15);
		int randomY = rand.nextInt(15);

		// keeps generateing random x & y co-ordinates till he finds empty cell
		CharacterCell cell = (CharacterCell) map[randomX][randomY];
		for (int i = 0; i < 15; i++) {
			// comment
			while (!(cell.getCharacter() == null) || (emptyCells.contains(new Point(randomX, randomY)))) {
				randomX = rand.nextInt(15);
				randomY = rand.nextInt(15);
			}
			emptyCells.add(new Point(randomX, randomY));
		}
		return emptyCells;

	}

	public static Point characterRandomPoint() {
		Random rand = new Random();

		int randomX = rand.nextInt(15);
		int randomY = rand.nextInt(15);
		boolean found = false;

		// keeps generateing random x & y co-ordinates till he finds empty cell
		while (!found) {
			try {
				randomX = rand.nextInt(15);
				randomY = rand.nextInt(15);
				CharacterCell cell = (CharacterCell) (map[randomX][randomY]);
				while (!(cell.getCharacter() == null)) {
					randomX = rand.nextInt(15);
					randomY = rand.nextInt(15);
					cell = (CharacterCell) (map[randomX][randomY]);
				}
				found = true;
			} catch (Exception e) {

			}
		}

		return new Point(randomX, randomY);

	}

	// zombie spawner:
	public static void spawnZombie() {
		Zombie zombie = new Zombie();
		zombie.setLocation(characterRandomPoint());
		zombies.add(zombie);
		map[(int) zombie.getLocation().getX()][(int) zombie.getLocation().getY()] = new CharacterCell(zombie, false);
	}

}