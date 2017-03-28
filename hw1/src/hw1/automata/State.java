package hw1.automata;

import hw1.automata.table.*;

public class State extends StateIterator
{
	private StateTable stateTable;
	private int index;
	private static int staticIndex = 0;

	@Override
	public int getIndex()
	{
		return this.index;
	}

	public State normalState()
	{
		makeThisNormalState();
		return this;
	}

	public State startState()
	{
		makeThisStartState();
		return this;
	}

	public State finalState()
	{
		makeThisFinalState();
		return this;
	}

	public State dummyState()
	{
		makeThisDummyState();
		return this;
	}
	
	private void setIndex(int idx)
	{
		this.index = idx;
	}

	public State()
	{
		this.setIndex(staticIndex++);
	}

	public State(StateTable stateTable)
	{
		this.stateTable = stateTable;
		this.setIndex(this.hashCode());
	}

	public State setStateTable(StateTable stateTable)
	{
		this.stateTable = stateTable;
		return this;
	}

	@Override
	public State toNextState(char token)
	{
		if(this.stateTable == null)
			return null;

		return stateTable.getNextState(this, token);
	}

	@Override
	public boolean hasNext(char token)
	{
		if(this.stateTable == null)
			return false;

		return this.stateTable.hasTuple(this, token);
	}

	@Override
	public boolean setNextState(char token, State toState)
	{
		if(this.stateTable == null)
			return false;

		return this.stateTable.changeState(this, token, toState);
	}

	@Override
	public State getNextState(char token)
	{
		return toNextState(token);
	}

	@Override
	public boolean equals(Object object)
	{
		if(object instanceof State)
		{
			State state = (State)object;

			return this.getIndex() == state.getIndex();	
		}

		else
			return false;
	}
}
