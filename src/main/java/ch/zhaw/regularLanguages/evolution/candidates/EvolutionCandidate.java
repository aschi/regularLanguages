package ch.zhaw.regularLanguages.evolution.candidates;

public abstract class EvolutionCandidate<T, PS> implements Comparable<EvolutionCandidate<T, PS>>{
	private T obj;
	private int fitness;
	private Class<T> classTypeDef;
	
	public EvolutionCandidate(T obj, int fitness) {
		super();
		this.obj = obj;
		this.fitness = fitness;
		
	}
	
	public EvolutionCandidate(Class<T> classTypeDef){
		super();
		this.classTypeDef = classTypeDef;
	}
	
	public EvolutionCandidate(){
		super();
	}
	
	public void setObj(T obj){
		this.obj = obj;
	}
	
	public abstract T initObj();
	
	public Class<T> getClassTypeDef(){
		return classTypeDef;
	}
	
	public void setClassTypeDef(Class<T> classTypeDef){
		this.classTypeDef = classTypeDef;
	}
	
	public T getObj() {
		return obj;
	}
	public int getFitness() {
		return fitness;
	}
	
	public void setFitness(int fitness){
		this.fitness = fitness;
	}

	public abstract int fitness(PS problemSet);
	public abstract Object cloneWithMutation();
	public abstract boolean stressTest(PS problemSet);
	
	
	@Override
	public int compareTo(EvolutionCandidate<T, PS> o) {
		// TODO Auto-generated method stub
		return o.getFitness()-this.getFitness();
	}
}
