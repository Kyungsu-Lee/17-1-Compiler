package hw1.parser;

import hw1.automata.*;
import hw1.automata.table.*;
import hw1.regex.*;

public class Parser
{
	public static final char[] characters = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

	private static Pairs pairs;
	private static StateTable stateTable;

	public static ENfa RE2ENFA(String re)
	{
		if(!Checker.check(re))	//	check re
			return null;
	
		if(Checker.isConCat(re))
		{
			return ENfa.concat(RE2ENFA(Checker.substringConCat(re)[0]), RE2ENFA(Checker.substringConCat(re)[1]));
		}

		else if(Checker.isOr(re))
		{
			return ENfa.or(RE2ENFA(Checker.substringOr(re)[0]), RE2ENFA(Checker.substringOr(re)[1]));
		}

		else if(Checker.isAsterisk(re))
		{
			return ENfa.asterisk(RE2ENFA(Checker.substringAsterisk(re)));
		}

		else
		{
			return ENfa.makeENfa(re);
		}
	}

	public static DFA ENFA2DFA(ENfa enfa)
	{
		pairs = new Pairs();
		stateTable = new StateTable();

		EClosure e = EClosure.geteClosure(enfa.getStateTable(), enfa.getStartState());
		State startState = new State().startState().setStateTable(stateTable);
		if(e.isFinalState()) startState.makeThisFinalState();
		pairs.add(e, startState);

		for(char token : characters)
		{
			EClosure tmp = EClosure.getNextState(enfa.getStateTable(), e, token);

			if(tmp != null && tmp.notNull())
			{
				if(pairs.containsKey(tmp))
				{
					stateTable.addState(pairs.get(e), token, pairs.get(tmp));
				}
				else
				{
					State state = new State().setStateTable(stateTable);
					if(tmp.isFinalState())
						state.makeThisFinalState();
					pairs.add(tmp, state);
					stateTable.addState(startState, token, state);

					for(char tokens : characters)
						addState(enfa.getStateTable(), tmp, tokens);
				}
			}
		}

		DFA dfa = new DFA();
		dfa.setStateTable(stateTable);
		for(State state : pairs.values())
			dfa.addState(state);

		return dfa;
	}

	private static void addState(StateTable table, EClosure eClosure, char token)
	{
		EClosure tmp = EClosure.getNextState(table, eClosure, token);

		if(tmp != null && tmp.notNull())
		{
			if(pairs.containsKey(tmp))
			{
				stateTable.addState(pairs.get(eClosure), token, pairs.get(tmp));
			}
			else
			{
				State state = new State().setStateTable(stateTable);
				if(tmp.isFinalState())
					state.makeThisFinalState();
				pairs.add(tmp, state);
				stateTable.addState(pairs.get(eClosure), token, state);

				for(char tokens : characters)
					addState(table, tmp, tokens);
			}
		}
	}
}
