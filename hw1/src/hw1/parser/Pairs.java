package hw1.parser;

import hw1.automata.*;
import java.util.*;

public class Pairs
{
	ArrayList<Pair> pairs = new ArrayList<Pair>();

	public Pairs()
	{
		
	}

	public void add(EClosure eClosure, State state)
	{
		if(!containsKey(eClosure))
			pairs.add(new Pair(eClosure, state));
	}

	public boolean containsKey(EClosure e)
	{
		boolean flag = false;

		for(Pair pair : pairs)
			flag |= pair.key().equals(e);
		return flag;
	}

	public State get(EClosure e)
	{
		for(Pair pair : pairs)
			if(pair.key().equals(e))
				return pair.value();

		return null;
	}

	public ArrayList<State> values()
	{
		ArrayList<State> states = new ArrayList<State>();
		for(Pair pair : pairs)
			states.add(pair.value());

		return states;
	}
}
