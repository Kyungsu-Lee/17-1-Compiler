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
	//		System.out.println(Checker.check("(((([123]|[56]).[789]))*.([^1].[123]))"));
	//		System.out.println(Checker.check("((((([1].[12])|[4]).[56]))*|([123].([67]|[68])))"));
	//		System.out.println(Checker.check("([123].[23])"));
	//		System.out.println(Checker.check("(([123])*|[67])"));
			
			ENfa ee = Parser.RE2ENFA("((([123])*|[78]).[^1])");
			System.out.println(ee.getStateTable());
			System.out.println("==========");
			DFA dfa = Parser.ENFA2DFA(ee);
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
			System.out.println(dfa.isAccept("11111111"));
			System.out.println(dfa.isAccept("1111172"));
			System.out.println(dfa.isAccept("1111177"));
			System.out.println(dfa.isAccept("111117"));

		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}
}
