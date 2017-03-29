package hw1.regex;

import java.util.Scanner;

public class demo{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		String line;


		while(true){
			line = sc.nextLine();
			Tokenizer re = new Tokenizer(line);

			re.showInfo();//show the information of the Regular Expression
		}


	}
}
