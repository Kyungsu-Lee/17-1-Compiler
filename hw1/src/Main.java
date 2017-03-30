import hw1.automata.*;
import hw1.parser.*;
import hw1.regex.*;

import java.util.Scanner;

class Main
{
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args)
	{
		String rex = "";

		if(args.length < 1)
			return;

		for(String s : args)
			rex += s;

		rex = rex.trim();

		if(!Checker.check(args[0]))
		{
			System.out.println("invalid regex");
			return;
		}

		DFA dfa = Parser.ENFA2DFA(Parser.RE2ENFA(rex));
		try
		{
			//System.out.println(dfa);
			while(true)
			{
				String str = sc.nextLine();
				str = str.trim();
				
				if(dfa.isAccept(str))	System.out.println("accepted");
				else			System.out.println("rejected");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
