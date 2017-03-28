package hw1.automata;

import hw1.automata.table.*;
import java.util.*;

public class DFA
{
	private StateTable stateTable;
	private StateIterator iter;
	private ArrayList<State> states = new ArrayList<State>();
		

	public DFA()
	{
	}

	public DFA addState(State... states)
	{
		for(State state : states)
			this.states.add(state);
		return this;
	}

	public void setStateTable(StateTable stateTable)
	{
		this.stateTable = stateTable;
	}

	public StateTable getStateTable()
	{
		return this.stateTable;
	}

	public State getFinalState()
	{
		if(states == null)
			return null;

		for(State state : states)
			if(state.isFinalState())
				return state;

		return null;
	}

	public State getStartState()
	{
		if(states == null)
			return null;

		for(State state : states)
			if(state.isStartState())
				return state;

		return null;
	}

	public ArrayList<State> getAllStates()
	{
		return this.states;
	}
}
