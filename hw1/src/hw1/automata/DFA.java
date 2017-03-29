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

	public ArrayList<State> getFinalState()
	{
		ArrayList<State> tmp = new ArrayList<State>();

		if(states == null)
			return null;

		for(State state : states)
			if(state.isFinalState())
				tmp.add(state);

		return tmp;
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

	public boolean isAccept(String str)
	{
		if(str.equals("e"))
		{
			return getStartState().isFinalState();
		}
	
		iter = getStartState();

		try
		{
			for(int i=0; i<str.length(); i++)
				toNextState(str.charAt(i));
		}
		catch(Exception e)
		{
			return false;
		}

		if(iter == null)
			return false;

		return iter.isFinalState();
	}

	public void toNextState(char token) throws Exception
	{
		iter = iter.toNextState(token);
	}
}
