package ch.zhaw.regularLanguages.dfa;


public class State {
	private String id;
	private TransitionTable tt;
	
	public State(String id, TransitionTable tt){
		this.id = id;
		this.tt = tt;
	}
	
	public State(String id){
		this.id = id;
	}
	
	public TransitionTable getTransitionTable(){
		return tt;
	}
	
	public void setTransitionTable(TransitionTable tt){
		this.tt = tt;
	}
	
	public State process(Character character){
		return tt.process(character);
	}
	
	public String getId(){
		return this.id;
	}
	
	
	public String toString(){
		return id;
	}
}