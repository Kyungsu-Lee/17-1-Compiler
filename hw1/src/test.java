import hw1.automata.*;
import hw1.automata.table.*;
import hw1.parser.Parser;
import java.util.*;
import hw1.regex.*;

public class test
{
	public static void main(String[] args)
	{
		try
		{
			ENfa enfa = ENfa.makeENfa("[3]");
			enfa = ENfa.asterisk(enfa);
			ENfa enfa2 = ENfa.makeENfa("[2]");
			enfa2 = ENfa.asterisk(enfa2);
			ENfa enfa3 = ENfa.makeENfa("[2]");
			ENfa enfa4 = ENfa.makeENfa("[145]");
			enfa = ENfa.concat(enfa, enfa2);
			enfa = ENfa.concat(enfa, enfa3);
			enfa = ENfa.asterisk(enfa);
			enfa = ENfa.or(enfa, enfa4);

			System.out.println(enfa.getStateTable());


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
			System.out.print("final : ");
			for(State s : dfa.getFinalState())
				System.out.print(s.getIndex() + " ");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.print("all states : ");
			for(State s : dfa.getAllStates())
				System.out.print(s.getIndex() + " ");
			System.out.println("");
			System.out.println("");
			System.out.println(dfa.getStateTable());
			System.out.println("==========");

			System.out.println(dfa.isAccept("32222322"));
			System.out.println("==========");
			System.out.println("==========");

	//		System.out.println(Checker.check("(((([123]|[56]).[789]))*.([^1].[123]))"));

		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}
}
