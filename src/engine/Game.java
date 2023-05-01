package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.NotActiveException;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

import java.util.Random.*;

import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.characters.*;
import model.characters.Character;
import model.collectibles.*;
import model.world.*;


public class Game {
	
	public static Cell [][] map ;
	public static ArrayList <Hero> availableHeroes = new ArrayList<Hero>();
	public static ArrayList <Hero> heroes =  new ArrayList<Hero>();
	public static ArrayList <Zombie> zombies =  new ArrayList<Zombie>();
	
	
	
		
	public static void loadHeroes(String filePath)  throws IOException {
		
		
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();
		while (line != null) {
			String[] content = line.split(",");
			Hero hero=null;
			switch (content[1]) {
			case "FIGH":
				hero = new Fighter(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]), Integer.parseInt(content[3]));
				break;
			case "MED":  
				hero = new Medic(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]), Integer.parseInt(content[3])) ;
				break;
			case "EXP":  
				hero = new Explorer(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]), Integer.parseInt(content[3]));
				break;
			}
			availableHeroes.add(hero);
			line = br.readLine();
			
			
		}
		br.close();

		
		
	}

	public static Point randomPoint()
	{
		Random rand = new Random();

		int randomX = rand.nextInt(15);
		int randomY = rand.nextInt(15);

		//keeps generateing random x & y co-ordinates till he finds empty cell
		while(map[randomX][randomY] != null)
		{
			randomX = rand.nextInt(15);
			randomY = rand.nextInt(15);
		}
		return new Point(randomX,randomY);

	}

	public static void spawnZombie()
	{
		Zombie zombie = new Zombie();
		zombie.setLocation(randomPoint());
		map[(int) zombie.getLocation().getX()][(int) zombie.getLocation().getY()] = new CharacterCell(zombie);
	}


	public static void startGame (Hero h)
	{
		map = new Cell[15][15];
		map[0][0] = new CharacterCell(h);
		heroes.add(h);
		availableHeroes.remove(h);
		for(int i = 0; i < 5; i++)
		{
			Vaccine vaccine = new Vaccine();
			Point vaccinePoint = randomPoint();
			map[(int) vaccinePoint.getX()][(int) vaccinePoint.getY()] = new CollectibleCell(vaccine);

			TrapCell trap = new TrapCell();
			Point trapPoint =  randomPoint();
			map[(int) trapPoint.getX()][(int) trapPoint.getY()] = trap;

			Supply supply = new Supply();
			Point supplyPoint = randomPoint();
			map[(int) supplyPoint.getX()][(int) supplyPoint.getY()] = new CollectibleCell(supply);

			for(int j = 0; j<2; j++){
				spawnZombie();
			}
		}
		//loop that makes every cell that isnt occupied by anything a CharacterCell
		for(int hor = 0; hor<map.length; hor++)
		{
			for(int ver = 0; ver<map.length; ver++){
				if(map[hor][ver] == null)
				{
					map[hor][ver] = new CharacterCell(null);
				}
			}
		}
	}

	public static boolean checkWin()
	{
		return heroes.size() >= 5 && vaccinesCollected == 5;
	}

	public static boolean checkGameOver()
	{
		return heroes.size() == 0;
	}

	private static void zombiesAttackAdjacentCells()
	{
		//checks the adjacent cells to each zombie on the map and the first hero adjacent to a zombie is attacked
		int i = 0;
		while(zombies.get(i) != null)
		{
			Zombie currZombie = zombies.get(i);
			ArrayList <Cell> adjacentToZombie = currZombie.getAdjacentCells();
			for (int j = 0; j<adjacentToZombie.size(); j++)
			{
				CharacterCell adjacentCharacterCell = (CharacterCell) adjacentToZombie.get(j);
				Character adjacentCharacter = (Character) adjacentCharacterCell.getCharacter();
				if(adjacentCharacter instanceof Character)
				{
					try{
						currZombie.setTarget(adjacentCharacter);
						currZombie.attack();
					} catch(Exception e){}	
					break;
				}											
			}
			i++;
		}
	}

	private static void resetHeroes()
	{
		int heroCount = 0;
		while(heroes.get(heroCount) != null)
		{
			Hero currHero = heroes.get(heroCount);
			currHero.setActionsAvailable(currHero.getMaxActions());
			currHero.setTarget(null);
			currHero.setSpecialAction(false);
			ArrayList<Cell> adjacent = currHero.getAdjacentCells();
			for(int i = 0; i<adjacent.size(); i++)
			{
				adjacent.get(i).setVisible(true);
			}
			
		}
	}

	public static void endTurn()
	{
		zombiesAttackAdjacentCells();
		resetHeroes();
		spawnZombie();

		
	}


}