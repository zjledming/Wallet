package test;

public class StringReference {

	public void fx(String str){
		str="abc";
		System.out.println("Str===="+str);
	}
	public static void main(String[] args){
		StringReference str=new StringReference();
		String a="ymk";
		String b=a;
		b="abc";
		System.out.println("a===="+a);
		System.out.println("b===="+b);
	}
}
