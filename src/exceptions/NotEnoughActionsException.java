package exceptions;

public class NotEnoughActionsException extends GameActionException {
	// Constructor that calls the empty constructor of the parent class GameActionException
	public NotEnoughActionsException(){
		super();
	}
	// Constructor that calls the string parameterised constructor of the parent class GameActionException
	public NotEnoughActionsException(String s){
		super(s);
	}
}
