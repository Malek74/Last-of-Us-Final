package model.world;
import model.characters.Character;

public class CharacterCell extends Cell {
    private Character character;
    private boolean isSafe;

    public CharacterCell(Character c){
        super();
        character=c;
        
    }

    public CharacterCell(Character c,boolean safety){
        super();
        character=c;
        isSafe=safety;
    }

    public Character getCharacter() {
        return character;
    }
    public CharacterCell(boolean safe){
        this.isSafe=safe;
    }
    public void setCharacter(Character character) {
        this.character = character;
    }

	public boolean isSafe() {
		return isSafe;
	}

	public void setSafe(boolean isSafe) {
		this.isSafe = isSafe;
	}
    
}
