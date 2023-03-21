package model.world;

public class TrapCell extends Cell{
    private int trapDamage;

    public TrapCell(){
    	super();
        int[] damage={10,20,30};
        int pos= (int) Math.round(Math.random()*3);
        trapDamage=damage[pos];
        
    }
    public int getTrapDamage() {
        return trapDamage;
    }



   
}
