package hw1.automata.table;

import hw1.automata.State;
import java.util.ArrayList;

public class StateTable
{
	private ArrayList<UnitTable> row;

	public StateTable()
	{
		row = new ArrayList<UnitTable>();
	}

	private UnitTable find(State fromState, char token)
	{
		for(UnitTable unitTable : row)
			if(unitTable.isState(fromState, token))
				return unitTable;

		return null;
	}
	
	public boolean hasTuple(State fromState, char token, State toState)
	{
		UnitTable unitTable = find(fromState, token);

		if(unitTable == null)
			return false;	//there is no tuple matched.

		else if(unitTable.getNextState().equals(toState))
			return true;	

		else
			return false;	// there is tuple but not matched.
	}

	public boolean hasTuple(State fromState, char token)
	{		
		return find(fromState, token) != null;
	}

	public State getNextState(State fromState, char token)
	{
		if(hasTuple(fromState, token))
		{
			UnitTable unitTable = find(fromState, token);
			
			return unitTable.getNextState(fromState, token);
		}
	
		return null;
	}

	public boolean addState(State fromState, char token, State toState)
	{
	//	if(hasTuple(fromState, token))	//already exist tuple.
	//		return false;

		row.add(new UnitTable(fromState, token, toState));

		return true;
	}

	public boolean addState(UnitTable unitState)
	{
		row.add(unitState);
	
		return true;
	}

	public boolean changeState(State fromState, char token, State toState)
	{
		if(!hasTuple(fromState,token))	//not exist tuple
			return false;
	
		return find(fromState, token).changeState(fromState, token, toState);
	}

	public boolean removeState(State fromState, char token)
	{
		row.remove(find(fromState, token));

		return true;
	}

	public boolean removeState(State fromState, char token, State toState)
	{
		if(hasTuple(fromState, token, toState))
			return removeState(fromState, token);

		else
			return false;
	}

	public ArrayList<UnitTable> getRows()
	{
		return this.row;
	}

	public static StateTable spanTable(StateTable one, StateTable other)
	{
		StateTable stateTable = new StateTable();

		for(UnitTable unitState : one.getRows())
			stateTable.addState(unitState);

		for(UnitTable unitState : other.getRows())
			stateTable.addState(unitState);

		return stateTable;
	}

	@Override
	public String toString()
	{
		String str = "";
		for(UnitTable unitTable : row)
			str += unitTable.toString() + "\n";

		return str;
	}
}
