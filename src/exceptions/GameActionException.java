package exceptions;

abstract class GameActionException extends Exception{
    // Constructor that calls the empty constructor of the parent class Exception
    public GameActionException(){
        super();
    }
    // Constructor that calls the string parameterised constructor of the parent class Exception
    public GameActionException(String s){
        super(s);
    }

}