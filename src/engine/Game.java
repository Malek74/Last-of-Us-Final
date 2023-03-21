package engine;

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import model.characters.Hero;
import model.characters.Zombie;
import model.world.Cell;

public class Game {
    public static ArrayList<Hero> availableHeros;
    public static ArrayList<Hero> heros;
    public static ArrayList<Zombie> zombies;
    public static Cell [][] map;

    public static void loadHeros(String filePath)throws Exception {
        String hero;
        int i = 0;
        Scanner sc = new Scanner(new File("C:\\GUC\\Spring 2023 (4)\\CSEN 401 (Computer Programming Lab)\\GameProject\\Last-of-Us-Final\\test_heros"));  
        sc.useDelimiter(",");
        //sets the delimiter pattern (separation of different cells in the csv file)
        while(sc.hasNext()){
        	availableHeros.set(i,sc.next());
        	//find and adds the next complete token from this scanner
        	i+=1;
        }
        sc.close();
    }
  //File Reader Citation: https://www.javatpoint.com/how-to-read-csv-file-in-java
 // File Reader Citation: https://www.geeksforgeeks.org/java-filereader-class-read-method-with-examples/?ref=rp

    
}
