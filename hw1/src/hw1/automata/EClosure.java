package hw1.automata;

import java.util.*;
import hw1.automata.table.*;

public class EClosure
{
	HashSet<State> states = new HashSet<State>();

	public EClosure()
	{
		
	}

	public void add(State state)
	{
		states.add(state);
	}

	public boolean contains(State state)
	{
		return states.contains(state);
	}

	public static EClosure geteClosure(StateTable stateTable, State state)
	{
		EClosure eClosure = new EClosure();
		
		HashSet<State> h = new HashSet<State>();

		for(State tmp : p_eClosure(stateTable, state, h))
			eClosure.add(tmp);

		return eClosure;
	}

	public HashSet<State> getAllStates()
	{
		return this.states;
	}

	public boolean isFinalState()
	{
		boolean flag = false;

		for(State state : getAllStates())
			flag |= state.isFinalState();

		return flag;
	}

	public boolean notNull()
	{
		return this.states.size() != 0;
	}

	public static EClosure getNextState(StateTable stateTable, EClosure eClosure, char token)
	{
		EClosure tmp = new EClosure();

		for(State state : getNextState(stateTable, token, eClosure.getAllStates()))
			tmp.add(state);

		return tmp;
	}

	@Override
	public String toString()
	{
		String str = "";
		for(State state : states)
			str += state.getIndex() + " ";

		return str;
	}

	@Override
	public boolean equals(Object o)
	{
		if(o instanceof EClosure)
		{
			boolean flag = true;
			EClosure eClosure = (EClosure)o;
			
			for(State state : eClosure.getAllStates())
				flag &= contains(state);

			return flag;
		}

		return false;
	}


	private static HashSet<State> p_eClosure(StateTable stateTable, State state, HashSet<State> hashSet)
	{
		hashSet.add(state);
		for(UnitTable unitState : stateTable.getRows())
		{
			State tmp = unitState.getNextState(state, 'e');
			
			if(tmp != null)
			{
	
				if(!hashSet.contains(tmp))
				{
					p_eClosure(stateTable, tmp, hashSet);
				}
			}
		}

		return hashSet;
	}

	private static HashSet<State> getNextState(StateTable stateTable, char token, HashSet<State> hashSet)
	{
		HashSet<State> h = new HashSet<State>();

		for(State tmp : hashSet)
		{
			for(UnitTable unitTable : stateTable.getRows())
			{
				State tmp_next = unitTable.getNextState(tmp, token);

				if(tmp_next != null)
				{
					p_eClosure(stateTable, tmp_next, h);
				}
			}		
		}

		return h;
	}
}
