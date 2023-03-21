package exceptions;

public class MovementException extends GameActionException{
    // Constructor that calls the empty constructor of the parent class GameActionException
    public MovementException(){
        super();
    }
    // Constructor that calls the string parameterised constructor of the parent class GameActionException
    public MovementException(String s){
        super(s);
    }

}
