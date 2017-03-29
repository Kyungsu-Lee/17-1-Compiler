package hw1.automata;

import hw1.automata.table.*;
import java.util.ArrayList;

public class ENfa
{
	private StateTable stateTable;
	private StateIterator iter;
	private ArrayList<State> states = new ArrayList<State>();
		

	public ENfa()
	{
	}

	public ENfa addState(State... states)
	{
		for(State state : states)
			this.states.add(state);
		return this;
	}

	public void setStateTable(StateTable stateTable)
	{
		this.stateTable = stateTable;
	}

	public static ENfa makeENfa(final String re)
	{
		ENfa enfa = new ENfa();

		if(!(re.indexOf('[') >= 0 && re.indexOf(']') > 0))	//not a case
		{
			return null;
		}

		else if(re.indexOf('^') > 0)
		{
			String str = "[";
			for(int i=0; i<10; i++)
				if(Integer.parseInt(re.substring(2,3)) != i)
					str += Integer.toString(i);
			str += "]";
			return makeENfa(str);
		}

		else if(re.length() == 3)	// case of [a]
		{
			char token = re.charAt(1);	//get a

			StateTable stateTable = new StateTable();

			State startState = new State().startState().setStateTable(stateTable);
			State state1 = new State().setStateTable(stateTable);
			State state2 = new State().setStateTable(stateTable);
			State finalState = new State().finalState().setStateTable(stateTable);

			System.out.println(startState.getIndex());
			System.out.println(state1.getIndex());
			System.out.println(state2.getIndex());
			System.out.println(finalState.getIndex());
			System.out.println("====================");

			stateTable.addState(startState, 'e', state1);
			stateTable.addState(state1, token, state2);
			stateTable.addState(state2, 'e', finalState);

			enfa.addState(startState, state1, state2, finalState);
			enfa.setStateTable(stateTable);

			return enfa;
		}

		else	// case of([aaaaaaaaa])
		{
			char token = re.charAt(1);
			String sub = "[" + re.substring(2,re.length());
			enfa = ENfa.or(ENfa.makeENfa("["+token+"]"), ENfa.makeENfa(sub));

			return enfa;
		}
	}

	public static ENfa concat(ENfa one, ENfa other)	//.
	{
		StateTable stateTable = StateTable.spanTable(one.getStateTable(), other.getStateTable());
	
		stateTable.addState(one.getFinalState().normalState(), 'e', other.getStartState().normalState());
	
		ENfa enfa = new ENfa();

		for(State state : one.getAllStates())
			enfa.addState(state);
		for(State state : other.getAllStates())
			enfa.addState(state);
		
		enfa.setStateTable(stateTable);

		return enfa;
	}

	public static ENfa or(ENfa one, ENfa other)		// |
	{
		StateTable stateTable = StateTable.spanTable(one.getStateTable(), other.getStateTable());
		
		State state1 = new State().startState().setStateTable(stateTable);
		State state2 = new State().finalState().setStateTable(stateTable);

		stateTable.addState(state1, 'e', one.getStartState().normalState());
		stateTable.addState(state1, 'e', other.getStartState().normalState());
		stateTable.addState(one.getFinalState().normalState(), 'e', state2);
		stateTable.addState(other.getFinalState().normalState(), 'e', state2);

		ENfa enfa = new ENfa();
		enfa.addState(state1, state2);

		for(State state : one.getAllStates())
			enfa.addState(state);
		for(State state : other.getAllStates())
			enfa.addState(state);
		
		enfa.setStateTable(stateTable);

		return enfa;
	}

	public static ENfa asterisk(ENfa one)			// *
	{
                StateTable stateTable = one.getStateTable();
                 
                 State state1 = new State().startState().setStateTable(stateTable);
                 State state2 = new State().finalState().setStateTable(stateTable);
                 
                 ENfa enfa = new ENfa();
                 enfa.addState(state1, state2);
                 
		stateTable.addState(state1, 'e', one.getStartState().normalState());
		stateTable.addState(one.getFinalState().normalState(), 'e', state2);
		stateTable.addState(state1, 'e', state2);
		stateTable.addState(state2, 'e', state1);

                 for(State state : one.getAllStates())
                         enfa.addState(state);
     
		enfa.setStateTable(stateTable);
			            
		return enfa;
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
