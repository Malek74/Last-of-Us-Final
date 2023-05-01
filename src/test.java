import model.characters.*;

public class test{
    public static void main(String[] args) {
        Fighter f =new Fighter("null", 0, 0, 0);
        
        System.out.println(f instanceof Hero);
    }
}