package model.world;

public class TrapCell extends Cell{
    private int trapDamage;

    public TrapCell(){
    	super();
        trapDamage= (int) Math.round(Math.random()*2)+1;
        trapDamage*=10;

        
    }
    public int getTrapDamage() {
        return trapDamage;
    }



   
}
