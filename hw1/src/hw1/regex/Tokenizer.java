package hw1.regex;

import java.util.ArrayList;

public class Tokenizer{
	int totalDepth;
	String inputString;
	ArrayList<RE> REList = new ArrayList<RE>();


	public Tokenizer(String input){
		inputString = input;
		if(checker(inputString,0))System.out.println("valid RE");
		else {
			System.out.println("invalid RE");
			REList.clear();
		}

	}
	public void showInfo(){
		if(!REList.isEmpty()){
			sort();
			for(int i=0;i<REList.size();i++){
				for(int j=0;j<REList.get(i).getDepth();j++)System.out.print("  ");
				System.out.print("Index: " + REList.get(i).getIndex() + " Depth:"+REList.get(i).getDepth()+"\n");
				//REList.get(i).showList();

			}

		}

	}

	public void sort(){ 	
		if(!REList.isEmpty())
			for(int i=0;i<REList.size()-1;i++){
				for(int j=i+1;j<REList.size()-1;j++){
					if(REList.get(i).getDepth()>REList.get(j).getDepth()){
						REList.get(j).addRE(REList.get(i));
						break;

					}
				}
			}
	}

	public boolean indexCheck(int i){
		if(!REList.isEmpty())
			if(i==REList.get(REList.size()-1).getIndex())return true;

		return false;
	}

	public boolean checker(String input,int depth){
		int ptCount=0;

		if(input.equals("e")){
			RE re = new RE(1,depth);
			re.setValue("e");
			REList.add(re);
			return true;
		}
		else if(input.charAt(0)=='['){
			if(input.charAt(input.length()-1)==']'){
				if(input.charAt(1)=='^'){
					if(digitCheck(input.substring(2,input.length()-1))&&input.substring(2,input.length()-1).length()==1){
						RE re = new RE(3,depth);
						re.setValue(input.substring(2,input.length()-1));
						REList.add(re);
						return true;
					}
					else return false;

				}
				else{
					if(digitCheck(input.substring(1,input.length()-1))){
						RE re = new RE(2,depth);
						re.setValue(input.substring(1,input.length()-1));
						REList.add(re);
						return true;
					}
					else return false;

				}
			}
			else
				return false;
		}
		else if(input.charAt(0)=='('){
			if(input.charAt(input.length()-1)==')'){
				for(int i =0;i<input.length();i++){
					if(input.charAt(i)=='(')ptCount++;
					else if(input.charAt(i)==')')ptCount--;

					if(input.charAt(i)=='|'&&ptCount==1){
						if(checker(input.substring(1,i),depth+1)&&checker(input.substring(i+1,input.length()-1),depth+1)){
							RE re = new RE(5,depth);
							REList.add(re);
							return true;
						}
						else return false;
					}
					else if(input.charAt(i)=='.'&&ptCount==1){
						if(checker(input.substring(1,i),depth+1)&&checker(input.substring(i+1,input.length()-1),depth+1)){
							RE re = new RE(4,depth);
							REList.add(re);
							return true;
						}
						else return false;
					}
				}
				return false;
			}
			else if(input.charAt(input.length()-1)=='*'&&input.charAt(input.length()-2)==')'){
				if(checker(input.substring(1,input.length()-2),depth+1)){
					RE re = new RE(6,depth);
					REList.add(re);
					return true;
				}
				else return false;

			}
			else return false;
		}
		else return false;

	}

	public boolean digitCheck(String line){
		for(int i=0;i<line.length();i++){
			if(!Character.isDigit(line.charAt(i)))return false;
		}
		return true;
	}

}

