package hw1.automata;



public abstract class StateIterator
{
	private boolean isFinal = false;
	private boolean isStart = false;
	private boolean isDummy = false;

	public void makeThisFinalState()
	{
		this.isFinal = true;
	}

	public void makeThisStartState()
	{
		this.isStart = true;
	}

	public void makeThisDummyState()
	{
		this.isDummy = true;
	}

	public void makeThisNormalState()
	{
		isFinal = false;
		isStart = false;
		isDummy = false;
	}

	public boolean isFinalState()
	{
		return this.isFinal;
	}

	public boolean isStartState()
	{
		return this.isStart;
	}


	//not yet completed.
	public abstract State toNextState(char token);
	public abstract boolean hasNext(char token);
	public abstract boolean setNextState(char token, State toState);
	public abstract State getNextState(char token);
	public abstract boolean equals(Object object);
	public abstract int getIndex();
}
