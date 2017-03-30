package hw1.regex;

public class Checker
{
	public static boolean check(String str)
	{
		System.out.println("str :: " + str);

		if(!( paraCheck('(', ')', str) && paraCheck('[', ']', str))) return false;

		if(str.equals("e"))
			return true;

		else if(str.charAt(0) == '(')
		{
			return isAsterisk(str) | isConCat(str) | isOr(str);
		}

		else if(str.charAt(0) == '[')
		{
			if(!paraCheck('[',']', str)) return false;

			if(str.indexOf('^') >=0)
				return str.length() == 4 && (str.charAt(2) >= '0' || str.charAt(2) <= '9');

			else
			{
				boolean flag = true;
				for(int i=1; i<str.length()-1; i++)
					flag &= ('0' <= str.charAt(i) && str.charAt(i) <= '9');

				return flag;
			}
		}

		else
			return false;
	}

	private static int indexOf(String str, char open, char close)
	{
		int num_open = 0;
		int num_close = 0;
		for(int i=0; i<str.length(); i++)
		{
			if(str.charAt(i) == open)	num_open++;
			if(str.charAt(i) == close)	num_close++;

			if(num_open == num_close && num_open != 0)
				return i;
		}

		return -1;
	}

	private static boolean paraCheck(char open, char close, String str)
	{
		int num_open 	= 0;
		int num_close 	= 0;

		for(int i=0; i<str.length(); i++)
		{
			if(str.charAt(i) == open)	num_open++;
			if(str.charAt(i) == close)	num_close++;
		}


		return num_open == num_close;
	}

	public static boolean isAsterisk(String str)
	{
		if( 	str.length() >= 3 &&
				str.charAt(0) == '(' &&
				str.charAt(str.length()-1) == '*' &&
				str.charAt(str.length()-2) == ')')

		{
			return check(str.substring(1, str.length()-2));	//remove ()*
		}

		return false;
	}

	public static boolean isConCat(String str)
	{
		System.out.println("concat : " + str);
		boolean flag = true;

		flag &= paraCheck('(', ')', str);
		String tmp = str.substring(1, str.length()-1);

		int indexOfConCat = matchedIndexOf(tmp, '.');
		flag &= (indexOfConCat >= 0 && tmp.charAt(indexOfConCat) == '.');

		if(!flag) return false;


		System.out.println(tmp.substring(0, indexOfConCat) + " :::::" + str.substring(indexOfConCat+2, str.length()-1));
		return check(tmp.substring(0,indexOfConCat)) && check(str.substring(indexOfConCat+2, str.length()-1));	//check A.B
	}

	public static boolean isOr(String str)
	{
		System.out.println("ir : " + str);
		boolean flag = true;

		flag &= paraCheck('(', ')', str);
		String tmp = str.substring(1, str.length()-1);

		int indexOfConCat = matchedIndexOf(tmp, '|');
		flag &= (indexOfConCat >= 0 && tmp.charAt(indexOfConCat) == '|');

		if(!flag) return false;
		System.out.println(tmp.substring(0, indexOfConCat) + " :::::" + str.substring(indexOfConCat+2, str.length()-1));
		return check(tmp.substring(0,indexOfConCat)) && check(str.substring(indexOfConCat+2, str.length()-1));	//check A.B
	}

	private static int matchedIndexOf(String str, char token)
	{
		int idx = indexOf(str, '(', ')');

		if(idx+1 < str.length() && str.charAt(idx+1) == token)
			return idx+1;

		else if(idx+1 < str.length() && (str.charAt(idx+1) == '*') && idx+2 < str.length() && str.charAt(idx+2) == token)
			return idx + 2;

		else
		{
			idx = indexOf(str, '[', ']');

			if(str.charAt(idx+1) == token)
				return idx+1;

			else if((str.charAt(idx+1) == '*') && idx+2 < str.length() && str.charAt(idx+2) == token)
				return idx + 2;
			else
				return -1;
		}
	}
}
