/*************************************************************************
 *
 *  Pace University
 *  Spring 2020
 *  Algorithms and Computing Theory
 *
 *  Course: CS 242
 *  Team members: Stella Solano
 *  Collaborators: NONE
 *  References: Cormen textbook, class notes, Assignment that contains the pseudocode
 *  		     https://www.geeksforgeeks.org/io-bufferedwriter-class-methods-java/  - To Output to text file
 *
 *  Assignment: Assignment3
 *  Problem: Dynamic Programming using Text Justification with different algorithms
 *  Description: Compare experimentally the result obtained justifying text with a greedy algorithm against Dynamic Programming.
 *
 *  Input: Int num of words and int page width
 *  Output: Two text files and prints the array 
 *
 *  Visible data fields: NONE
 *
 *  Visible methods:
 *  public static double badness(String[] words, int i, int j, int w)
 *  public static int l(String[]W, int i,int j) 
 *  public static int[] split(String[]W, int w)
 *  public static int MemoizedMinimumBadness(String[]W, int j, int[] memo, int[] linebreaks_memo,int w) 
 *	public static void justify(String[]W,int w, int[]breakpoints) throws IOException 
 *
 *   Remarks
 *   -------
 *   5) I can't compare because program compiles but doesn't work.  
 *************************************************************************/

import java.util.*;
import java.io.*;

public class DynamicProgram {
	
	//Badness Method
	public static double badness(String[] W, int i, int j, int w){
		int l = l(W,i,j);
		if ( w-l >= 0) {
			return Math.pow(w - l, 3);
		}
		else {
			return Integer.MAX_VALUE;
		}
	}	
	
	//Gets the length of all the characters in the strings
	public static int l(String[]W, int i,int j) {
		int result = 0 ;
		for(int k=i;k<j-1;k++) {
			result = W[k].length()+1;
		}
		return result;
	}

	
	//Memoization method
	public static int[] split(String[] W, int w) { // MinimumBadness Algorithm
		int n = W.length;
		int[] memo = new int[n];
		int[] linebreaks_memo = new int[n];
		for(int i=0;i<n;i++) {
			memo[i]= -1;
		}
		MemoizedMinimumBadness(W,0,memo,linebreaks_memo,w);
		return linebreaks_memo;
	}
	
	
	
	public static int MemoizedMinimumBadness(String[]W, int i, int[] memo, int[] linebreaks_memo,int w) {
		int n = W.length;
		if (memo[i]>=0){
			return memo[i];
		}
	
		if(i==n) {
			memo[i]=0;
			linebreaks_memo[i]=n;
		}
		
		else {
			int min = Integer.MAX_VALUE;
			int index_of_min = 0;
			for(int j=i+1;j<n;j++) {
				double temp = badness(W,i,j,w);
				temp = temp + MemoizedMinimumBadness(W,j,memo,linebreaks_memo,w);
				if(temp<min) {
					min = (int) temp;
					index_of_min = j;
				}
			}
			memo[i]=min;
			linebreaks_memo[i]=index_of_min;
		}
		return memo[i];
	}
	
	//justify method
	public static void justify(String[]W,int w, int[]breakpoints) throws IOException {
		
			int length = 0;
			for(int i=0;i<W.length;i++) {
				length += W[i].length(); //getting the length for each string 
			}
			int spaces = (w - length) / W.length; //width minus length divided by the number of words to get each space 
 
			StringBuilder sb = new StringBuilder();
			 
			// inserting the linebreaks
			for(String str : W) {
				  sb.append(str); // appends the the the first string into the string builder
	              for(int i=0;i<breakpoints.length;i++) {
	            	  if(breakpoints[i]>0) {
	            		  sb.append("\n");
	            	  }
	              }
	            }
			//inserting the spaces 
			for(String str1 : W) {
			  sb.append(str1); // appends the the the first string into the string builder
                int count = 0;
                while(count<spaces){//inserts the amount of spaces after each string 
                    sb.append(" ");
                    count++;
                }
            }
			
			BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\programs\\just.txt"));
	        for (String line : W) {
	            writer.write(sb.toString());
	        }
	        writer.close();
		}
		
	
    public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter number of words");
		int numWords = scan.nextInt();
		System.out.print("Enter page width");
		int w = scan.nextInt();
		
		String[] W = new String[numWords];
		//array to test text justification with length range [1,15]
		String[] temp = {"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa","aaaaaaaaaaa","aaaaaaaaaaaa","aaaaaaaaaaaaa","aaaaaaaaaaaaa","aaaaaaaaaaaaa"};
		
		int max = 14; 
        int min = 0; 
        int range = max - min + 1; 
        
        
		for(int i = 0; i < numWords; i++){
			int index = (int) (Math.random() * range); //Randomly gets the length
			W[i] = temp[index];
		}
		
		for(int i = 0; i < numWords; i++){
			System.out.print(W[i]+" ");
		}
		
		
		//Text Justification test
		int[] L = DynamicProgram.split(W, w); 
		justify(W,w,L);

        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\programs\\unjust.txt"));
        for (String line : W) {
            writer.write(line + " ");
        }
        writer.close();

    }


}

