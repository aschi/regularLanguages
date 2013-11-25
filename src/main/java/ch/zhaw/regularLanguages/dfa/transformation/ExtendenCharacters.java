package ch.zhaw.regularLanguages.dfa.transformation;

public enum ExtendenCharacters {
	Empty	(0),
	Epsilon	(1);
	
	private final int value;
	private ExtendenCharacters(int value) {
		this.value=value;
	}
	
	public int getValue(){
		return value;
	}	
}