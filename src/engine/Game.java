package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

import java.util.Random.*;
import model.characters.*;


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
	}
	public static void main(String[] args) {
	}
	public static void check(Object o){
		if( o instanceof Vaccine){
			System.out.println("YES");
		}
		else{
			System.out.println("NO");
		}
	}
	public static boolean checkWin()
	{
		return heroes.size() == 5;
	}

	public static boolean checkGameOver()
	{
		return heroes.size() == 0;
	}

	public static void endTurn()
	{
		
	}

	/*HELPERS*/
	public static void spawnZombie(){
		Point location = Game.randomPoint();
		Game.map[(int) location.getX()][(int)location.getY()]= new CharacterCell(new Zombie());
	}


}
