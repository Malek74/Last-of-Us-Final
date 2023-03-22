package engine;

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import model.characters.Hero;
import model.characters.*;
import model.world.Cell;

public class Game {
    public static ArrayList<Hero> availableHeroes=new ArrayList<>();
    public static ArrayList<Hero> heroes;
    public static ArrayList<Zombie> zombies;
    public static Cell [][] map;

    public static void main(String[] args){
        try {
            loadHeroes("src\\engine\\test_heros.csv");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public static void loadHeroes(String filePath)throws Exception {
        int i = 0;
        
            Scanner sc = new Scanner(new File(filePath));

            Hero hero;
            String[] heroData= new String[5];
            while(sc.hasNext()){
    
                heroData=sc.next().split(",");
                switch (heroData[1]){
                    case "FIGH":
                    hero=new Fighter(heroData[0],Integer.parseInt( heroData[2]), Integer.parseInt( heroData[4]),Integer.parseInt( heroData[3]));
                    break;
    
                    case "MED":
                    hero=new Medic(heroData[0],Integer.parseInt( heroData[2]), Integer.parseInt( heroData[4]),Integer.parseInt( heroData[3]));
                    break;
    
                    case "EXP":
                    hero=new Explorer(heroData[0],Integer.parseInt( heroData[2]), Integer.parseInt( heroData[4]),Integer.parseInt( heroData[3]));
                    break;
    
                    default:
                    hero=new Hero(heroData[0],Integer.parseInt( heroData[2]), Integer.parseInt( heroData[4]),Integer.parseInt( heroData[3]));
    
                }
    
                
                availableHeroes.add(i, hero);

                //find and adds the next complete token from this scanner
                i++;
            }
            sc.close();
        
        }
  //File Reader Citation: https://www.javatpoint.com/how-to-read-csv-file-in-java
 // File Reader Citation: https://www.geeksforgeeks.org/java-filereader-class-read-method-with-examples/?ref=rp

    
}
