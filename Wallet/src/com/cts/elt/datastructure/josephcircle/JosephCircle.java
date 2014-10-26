package com.cts.elt.datastructure.josephcircle;
public class JosephCircle {

	/**
	 * @param args
	 */
	public void josephCircle(int n,int k){
		int flag=0;
		boolean[] kick = new boolean[n];
		//set kick flag to False;
		for(int i=0;i<n-1;i++){
			kick[flag]=false;
		}
		int counter=0;
		int accumulate=0;
		while(true){
			if(!kick[flag]){
				accumulate++;
				if(counter==n-1){
					System.out.println("kick last person===="+(flag+1));
					break;
				}
				if(accumulate==k){
					kick[flag]=true;
					System.out.println("kick person===="+(flag+1));
					accumulate=0;
					counter++;
				}				
			}
			flag=(flag+1)%n;
		}
		
	}
	public static void main(String[] args) {
		JosephCircle jCircle = new JosephCircle();
		jCircle.josephCircle(15, 3);

	}

}
