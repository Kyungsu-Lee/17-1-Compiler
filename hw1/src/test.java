import hw1.automata.*;
import hw1.automata.table.*;
import hw1.parser.Parser;
import java.util.*;

public class test
{
	public static void main(String[] args)
	{
		try
		{
			ENfa enfa = ENfa.makeENfa("[1]");
			enfa = ENfa.asterisk(enfa);
			ENfa enfa2 = ENfa.makeENfa("[2]");
			enfa = ENfa.concat(enfa, enfa2);
			enfa = ENfa.asterisk(enfa);

			System.out.println(enfa.getStateTable());
			System.out.println("start : " + enfa.getStartState().getIndex());
			System.out.println("final : " + enfa.getFinalState().getIndex());
			System.out.println("===========");

			for(State state : enfa.getAllStates())
			{
				if(state.isStartState())
					System.out.println("start : " + state.getIndex());

				if(state.isFinalState())
					System.out.println("final : " + state.getIndex());
			}

			System.out.println("==========");

			DFA dfa = Parser.ENFA2DFA(enfa);
			System.out.println(dfa.getStartState().getIndex());
			System.out.println(dfa.getFinalState().getIndex());
			System.out.println(dfa.getStateTable());

		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}
}
