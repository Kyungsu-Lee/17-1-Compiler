package hw1.regex;

import java.util.ArrayList;

public class RE{
	String value;
	int depth;
	int index; //1.e 2.[] 3. [^] 4. (RE.RE) 5.(RE|RE) 6.(RE)*
	int length;
	ArrayList<RE> NREList = new ArrayList<RE>();

	public RE(int i, int d){
		index = i;
		if(index == 1||index==2||index==3)length=1;
		depth = d;

	}
	/* //show nested list
	public void showList(){
		System.out.print("\n chileren: ");
		for(int i=0;i<NREList.size();i++){
			System.out.print(NREList.get(i).getDepth()+" ");
		}
		System.out.print("\n ");
	}
	*/
	public void addRE(RE re){
		NREList.add(re);
	}
	public void getList(){

	}
	public void setLength(int len){
		length = len;
	}
	public int getLength(){
		return length;
	}
	public void setValue(String val){
		value = val;
	}
	public String getValue(){
		return value;
	}
	public int getIndex(){
		return index;

	}
	public int getDepth(){
		return depth;
	}
}
