package hw1.parser;

import hw1.automata.*;

public class Pair
{
	private EClosure eClosure;
	private State state;

	public Pair(EClosure e, State s)
	{
		eClosure = e;
		state = s;
	}	

	public EClosure key()
	{
		return this.eClosure;
	}

	public State value()
	{
		return this.state;
	}
}
