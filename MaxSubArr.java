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
 *
 *  Assignment: Assignment1
 *  Problem: The Maximum Subarray implemented with two different algorithms
 *  Description: Comparing the runnings time of two algorithms to get the max sub array
 *
 *  Input: int n
 *  Output: Displays running times of both Algorithm1 & 2
 *
 *  Visible data fields:
 *  NONE
 *
 *  Visible methods:
 *  Algorithm1: Brute Force algorithm	
 *  Algorithm2: Divide and Conquer algorithm 		
 *  MaxCrossingSubArray: part of Algorithm2 code
 *
 *
 *   Remarks
 *   -------
 *
 *   2) 							          Running Time
 *   n            			  |	10^3		|	10^4			|	10^5				|	150,000	 		|	200,000		
 *   Brute Force              |	4,867,758	|	35,242,557		|	2,828,925,554		|	7,390,881,800	|	11,316,483,106	 
 *   Divide and Conquer       |	1,925,107	|	2,868,377		|	10,5,00,937			|	14,050,618		|	19,921,839
 *   
 *   3) The running times for Brute force do match up with O(n^2). From 10^3 to 10^4, the running times increased from 5 million to 35 million nanoseconds. from 10^4 to 10^5
 *   	the running time increased from 35 million nanoseconds to 7 billion nanoseconds. This is not logarithmic or linear trend. This is exponential since the amount of nanoseconds are exponentially increasing.
 *   	The running times are going from million to 10 million to 10 billion nanoseconds.I decided to use n=150,000 and n=200,000 and it increased exponentially as well. It went from 7 billion nanoseconds to 11 billion nanoseconds. 
 *      The running times for Divide and Conquer do match with O(nlogn). From 10^3 to 10^4, the time only increased 1 million nanoseconds. From 10^4 to 10^5, time only increased 8 million nanoseconds. This shows that the increase isn't linear since it doesnt have a constant interval and it isnt increasing greatly.
 *      This matches that Divide and conquer is O(nlogn). 
 *
 *************************************************************************/
//	Author: Stella Solano (based on the code that was given)
//	Date: 2/12/20
//	Purpose/Description: To compare the running times of finding the Max Subarray with two algorithms, brute force and divide/conquer.
//	Visible	Methods	and	Data: Algorithm1: Brute Force algorithm		Algorithm2: Divide and Conquer algorithm 		MaxCrossingSubArray: part of Algorithm2 code
import java.util.*;

public class MaxSubArr {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter an integer");
		int size = scan.nextInt();
		int arr[] = new int[size];
		for (int i=0; i<size;i++) {
			arr[i]= (int) (Math.random()*20 -10); // Range from -10 - 10 
		}

		// store the time now
        long startTime = System.nanoTime();
		Algorithm1(arr,size);        
        // display the time elapsed
        System.out.println("Brute Force t = " + (System.nanoTime() - startTime) + " nanosecs.");
        
        // store the time now
         startTime = System.nanoTime();
        Algorithm2(arr,0,size-1);        
        // display the time elapsed
        System.out.println("Divide and Conquer t = " + (System.nanoTime() - startTime) + " nanosecs.");
		
	}
	
	public static int Algorithm1(int arr[],int size) {
		int maxSum = 0;
		for(int i = 0; i<size; i++) {
			int sum=0;
			for(int j=i; j<size; j++) {
				sum=sum+arr[j];
				maxSum = Math.max(maxSum, sum);
			}
			
		}
		return maxSum;
	}
	
	public static int Algorithm2(int arr[], int low, int high) {
		if (high == low) {
			return arr[low];
		}
		else {
			int mid=(int) (Math.floor(low+high))/2;
			int leftSum=Algorithm2(arr,low,mid);
			int rightSum=Algorithm2(arr, mid+1,high);
			int crossSum=MaxCrossingSubArray(arr,low,mid,high);
			int max1=Math.max(leftSum, crossSum);
			int max2=Math.max(max1, rightSum);
			return max2;
		}
	}
	
	public static int MaxCrossingSubArray(int arr[], int low, int mid, int high){
		int leftSum=0, rightSum=0;
		int sum=0;
		for (int i=mid; i>low; i--) {
			sum=sum+arr[i];
			if(sum > leftSum)
				leftSum=sum;
		}
		
		sum=0;
		for(int j=mid+1; j<high; j++) {
			sum=sum+arr[j];
			if (sum>rightSum) {
				rightSum=sum;
			}
		}
		return leftSum+rightSum;
}

}
