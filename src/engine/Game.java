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

	public static Cell[][] map = new Cell[15][15];
	public static ArrayList<Hero> availableHeroes = new ArrayList<Hero>();
	public static ArrayList<Hero> heroes = new ArrayList<Hero>();
	public static ArrayList<Zombie> zombies = new ArrayList<Zombie>();

	public static void main(String[] args) throws InvalidTargetException, NotEnoughActionsException {
		System.out.println(null instanceof Hero);
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
		// cell = (CharacterCell) Game.map[0][0];
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

		//fill map with traps , vaccines and supplies
		for (int i = 0; i < 5; i++) {
			Point location = randomPoint();
			Game.map[location.x][location.y] = new CollectibleCell(new Vaccine());

			location = randomPoint();
			Game.map[location.x][location.y] = new CollectibleCell(new Supply());

			location = randomPoint();
			Game.map[location.x][location.y] = new TrapCell();
		}

		// spawn 10 zombies in random locations
		for (int i = 0; i < 10; i++) {

			spawnZombie();
		}
		Character.setMapVisbility(true, h.getAdjacentCells());

	}

		// Win or loss helpers:
		public static boolean checkWin() {

			// tests that all vaccines are collected
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					if (map[i][j] instanceof CollectibleCell) {
						CollectibleCell cell = (CollectibleCell) map[i][j];
						if (cell.getCollectible() instanceof Vaccine) {
							return false;
						}
					}
				}
			}
	
			// checks that no hero has a vaccine in his inventory
			for (int i = 0; i < heroes.size(); i++) {
				if (heroes.get(i).getVaccineInventory().size() != 0) {
					return false;
				}
			}
	
			if (heroes.size() < 5) {
				return false;
			}
	
			return true;
		}

		public static boolean checkGameOver() {

			boolean condition1 = false;
			boolean condition2 = true;

			if (heroes.size() == 0) {
				condition1 = true;
			}
	
			// tests that all vaccines are collected
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					if (map[i][j] instanceof CollectibleCell) {
						CollectibleCell cell = (CollectibleCell) map[i][j];
						if (cell.getCollectible() instanceof Vaccine) {
							condition2 = false;
						}
					}
				}
			}
	
			// checks that no hero has a vaccine in his inventory
			for (int i = 0; i < heroes.size(); i++) {
				if (heroes.get(i).getVaccineInventory().size() != 0) {
					condition2 = false;
				}
			}
			return (condition1 | condition2);
		}

	public static void endTurn() throws InvalidTargetException, NotEnoughActionsException {
		zombiesAttackAdjacentCells();
		Character.setMapVisbility(false);
		spawnZombie();
		resetHeroes();
		resetZombies();

	}

	// **HELPER METHODS**

	// endTurn helpers:

	private static void resetZombies() {
		for (int i = 0; i < Game.zombies.size(); i++) {
			Game.zombies.get(i).setTarget(null);
		}
	}

	private static void resetHeroes() {
		for (int i = 0; i < heroes.size(); i++) {
			Hero currHero = heroes.get(i);
			currHero.setActionsAvailable(currHero.getMaxActions());
			Game.map[(int) currHero.getLocation().getX()][(int) currHero.getLocation().getY()].setVisible(true);
			currHero.setTarget(null);
			currHero.setSpecialAction(false);
			ArrayList<Point> adjacent = currHero.getAdjacentCells();

			Character.setMapVisbility(true, adjacent);

		}
	}

	private static void zombiesAttackAdjacentCells() throws InvalidTargetException, NotEnoughActionsException {
		// checks the adjacent cells to each zombie on the map and the first hero
		// adjacent to a zombie is attacked

		for (int i = 0; i < zombies.size(); i++) {
			Zombie currZombie = zombies.get(i);
			ArrayList<Point> adjacentToZombie = currZombie.getAdjacentCells();
			for (int j = 0; j < adjacentToZombie.size(); j++) {
				if (map[(int) adjacentToZombie.get(j).getX()][(int) adjacentToZombie.get(j).getY()] instanceof CharacterCell) {
					CharacterCell adjacentCharacterCell = (CharacterCell) map[(int) adjacentToZombie.get(j).getX()][(int) adjacentToZombie.get(j).getY()];
					Character adjacentCharacter = (Character) adjacentCharacterCell.getCharacter();
					if (adjacentCharacter instanceof Hero) {
						adjacentCharacter.setCurrentHp(adjacentCharacter.getCurrentHp()-currZombie.getAttackDmg());							
						adjacentCharacter.defend(currZombie);
						break;
					}
				}
			}
		}
	}





	// Random location spawners:
	public static Point randomPoint() {
		// ArrayList<Point> emptyCells = new ArrayList<>();
		Random rand = new Random();

		int randomX = rand.nextInt(15);
		int randomY = rand.nextInt(15);
		// keeps generateing random x & y co-ordinates till he finds empty cell
		Cell cell = map[randomX][randomY];

		
		while (!(cell instanceof CharacterCell) || (cell instanceof CharacterCell && ((CharacterCell) cell).getCharacter()!=null)) {
			randomX = rand.nextInt(15);
			randomY = rand.nextInt(15);
			cell = map[randomX][randomY];
		}
		return new Point(randomX, randomY);

	}

	public static Point characterRandomPoint() {
		Random rand = new Random();

		int randomX = rand.nextInt(15);
		int randomY = rand.nextInt(15);
		
		Cell cell = Game.map[randomX][randomY];

		while (!(cell instanceof CharacterCell) || (cell instanceof CharacterCell && ((CharacterCell) cell).getCharacter()!=null)) {

			randomX = rand.nextInt(15);
			randomY = rand.nextInt(15);
			cell = Game.map[randomX][randomY];
		}
		return new Point(randomX, randomY);

	}

	// zombie spawner:
	public static void spawnZombie() {
		Zombie zombie = new Zombie();
		zombie.setLocation(characterRandomPoint());
		zombies.add(zombie);
		map[(int) zombie.getLocation().getX()][(int) zombie.getLocation().getY()] = new CharacterCell(zombie);
	}

	public static void spawnZombie(Point loc) {
		Zombie zombie = new Zombie();
		zombie.setLocation(characterRandomPoint());
		while(zombie.getLocation().x==loc.x && zombie.getLocation().y==loc.y){
		zombie.setLocation(characterRandomPoint());
		}
		zombies.add(zombie);
		map[(int) zombie.getLocation().getX()][(int) zombie.getLocation().getY()] = new CharacterCell(zombie);
	}


}