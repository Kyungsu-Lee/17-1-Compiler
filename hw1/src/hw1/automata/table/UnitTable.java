package hw1.automata.table;

import hw1.automata.State;

public class UnitTable
{
	private State fromState;
	private char token;
	private State toState;

	private UnitTable()
	{
	
	}

	public UnitTable(State fromState, char token, State toState)
	{
		this.fromState = fromState;
		this.token = token;
		this.toState = toState;
	}

	public State getNextState(State fromState, char token)
	{
		if(this.fromState.equals(fromState) && this.token == token)
			return this.toState;

		else
			return null;
	}
	
	//must use after checking
	public State getNextState()
	{
		return this.toState;
	}

	public boolean isState(State fromState, char token)
	{
		return this.fromState.equals(fromState) && this.token == token;
	}

	private void changeState(State toState)
	{
		this.toState = toState;
	}

	//change state with check
	public boolean changeState(State fromState, char token, State toState)
	{
		if(isState(fromState, token))
			changeState(toState);

		return isState(fromState, token);
	}
	
	@Override
	public String toString()
	{
		String str = "";
		str += this.fromState.getIndex() + " => (";
		str += this.token + ") => ";
		str += this.toState.getIndex();

		return str;
	}
	
}
