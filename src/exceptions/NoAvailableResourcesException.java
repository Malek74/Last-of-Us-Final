package exceptions;

public class NoAvailableResourcesException extends GameActionException {
	// Constructor that calls the empty constructor of the parent class GameActionException
	public NoAvailableResourcesException(){
		super();
	}
	// Constructor that calls the string parameterised constructor of the parent class GameActionException
	public NoAvailableResourcesException(String s){
		super(s);
	}
}
