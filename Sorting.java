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
 *  https://en.wikipedia.org/wiki/Quicksort#Lomuto_partition_scheme --- Pseudocode was used to create randomized quicksort
 *
 *  Assignment: Assignment2
 *  Problem: Measuring the running times of bucket sort and quick sort
 *  Description: The array is uniformly distributed between [0,1] and each element can have multiple duplicates
 *
 *  Input: int n, int r
 *  Output: Displays running times of both Algorithm1 & 2
 *
 *  Visible data fields:
 *	NONE
 *
 *  Visible methods:
 * 	public static int partition(double arr[], int low, int high) 
 *  public static void quickSort(double arr[], int low, int high) 
 *  public static double[] bucketSort(double[] arr) 
 *  public static void insertionSort(double arr[]) 
 *
 *   Remarks
 *   -------
 *
 *   3) 							         			 Running Time for QuickSort
 *                   |	n1=10 			|	n2=100			|	n3=1000				|	n4=5000			|	n5=10000		|	n6=100000			 		
 *   r1= 10          |	35,829  		|	96,445			|	1,444,331			|	3,203,254		|	5,882,167		|	32,431,129 		
 *   r2= 100       	 |	37,706			|	234,262			|	4,102,417			|	13,215,181 		|	14,479,871		|	70,099,680		
 *   r3= 500         |	46,868  		|	233,410			|	3,956,342			|	24,522,821		|	30,804,882	 	|	195,012,617		
 *   r4= 1,000    	 |	37,066			|	226,839			|	6,166,710			|	34,543,841		|	35,385,138		|	775,202,984	
 *   r5= 2,500    	 |	36,553		    |	228,364			|	5,822,044			|	25,388,139		|	40,410,540		|	1,424,557,725		
 *   r6= 5,000   	 |	38,083			|	233,318			|	10,500,937			|	26,635,487		|	63,927,665		|	2,209,870,663
 *   
 *   													Running Time for BucketSort
 *                   |	n1=10 			|	n2=100			|	n3=1000				|	n4=5000			|	n5=10000		|	 n6=100000		 		
 *   r1= 10          |	14,614	    	|	18,283			|	107,754				|	767,556			|	1,100,716 		|	 10,424,554
 *   r2= 100       	 |	11,998			|	18,516			|	116,793				|	599,401			|	1,125,368		|	 8,801,451
 *   r3= 500         |	16,855			|	36,290			|	114,658				|	595,126	 		|	1,139,339		|	 18,316,554
 *   r4= 1,000	  	 |	14,611			|	41,076			|	136,404				|	589,403			|	1,130,484		|	 22,621,383
 *   r5= 2,500    	 |	14,515			|	19,422			|	116,499				|	600,155			|	1,166,871		|	 27,056,448
 *   r6= 5,000   	 |	15,606			|	19,184			|	118,065				|	521,149			|	1,046,718		|	 30,432,342
 *   
 *   
 *   4)Both algorithms are not similar because quicksort is growing significantly larger in comparison to bucket sort. Just from going n=10 to n=100,000
 *   the running times for quicksort are exponentially growing from thousands to even reaching billions. Bucket Sort could did not even go to the billion but at most 
 *   reached million nanoseconds. In terms of repetition, quick sort was not affected in the beginning when n = 10 and n=100. But when n is increasing and the amount of repetitions are increasing significantly,
 *   the running times are increasing as well but they stay within the same range. In the row n5=10000, as repetitions increased, it went from 5 million nanosecs to 60 billion nanosecs
 *   so repetitions do affect when the size increases significantly. 
 *   As for BucketSort, repetitions do not seem to affect the size. This puzzles me because if there are duplicates, then they should be place in the same bucket so that should take more than O(n).
 *   
 *   
 *   
 *   
 *
 *************************************************************************/


import java.util.*;

public class Sorting {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		int n,r,a,rep;
		
		System.out.print("Enter the size of the array");
		n = scan.nextInt();
		System.out.print("How many repetitions in the range[1,2r]?"); //if the person chooses 3 then the number of repetitions ranging from 1-6
		r = scan.nextInt();
		
		a=2*r;  
		
		double arr1[] = new double[n];
		double arr2[] = new double[n];

	
        int j=0;
		while(true){
		    double double_random = rand.nextDouble(); //gets random number from [0,1] exclusive
		    double temp = Math.random(); 
		    if(temp==1){
		        rep = (int) (temp*a);
		    }
		    else{
		        rep = (int)(1+(temp*a));
		    }
		    int k=0;
		    while(j<n && k<rep){  //repeats elements depending on the number of repetitions
		        arr1[j] = double_random;
		        arr2[j] = double_random;
		        j++;
		        k++;
		    }
		    if(j==n){
		        break;
		    }
		}
		
		
		//QuickSort method
		
		// store the time now
        long startTime = System.nanoTime();
		Sorting.quickSort(arr1, 0, arr1.length-1);
        // display the time elapsed
        System.out.println("QuickSort t = " + (System.nanoTime() - startTime) + " nanosecs.");
        
		//BucketSort method
         startTime = System.nanoTime();
		Sorting.bucketSort(arr1);
        // display the time elapsed
        System.out.println("BucketSort t = " + (System.nanoTime() - startTime) + " nanosecs.");
        
		
	}
	//Quicksort Method - EXTRA CREDIT***
    static int partition(double[] arr, int low, int high)  
    {  
        Random rand= new Random(); 
        int pivot = rand.nextInt(high-low) + low;  //Choose a random pivot
          
        double temp1=arr[pivot]; //Swap the pivots with arr[pivot] and arr[high
        arr[pivot]=arr[high]; 
        arr[high]=temp1; 
      
        int i = (low-1); 
        for (int j = low; j < high; j++){ 
            if (arr[j] <= pivot)  
            {  
                i++;  
                double temp = arr[i];  
                arr[i] = arr[j];  
                arr[j] = temp;  
            }  
        }  

        double temp = arr[i+1];  
        arr[i+1] = arr[high];  
        arr[high] = temp;  
        return i+1;  
    }  
	  
    public static void quickSort(double arr[], int low, int high) 
    { 
        if (low < high) 
        { 
            double pi = partition(arr, low, high); 
            quickSort(arr, low, (int) (pi-1)); 
            quickSort(arr, (int) (pi+1), high); 
        } 
    }
    
    
    // Bucketsort Method
        public static double[] bucketSort(double[] arr) 
        {
        	int n=arr.length;
            // Bucket Sort
            double[] Bucket = new double[n - 1]; //creating a new array
     
            for (int i = 0; i < n-1; i++) //initializing array to 0
                Bucket[i]=0;
            
            for (int i = 0; i < n-1; i++) 
                Bucket[i] = n * arr[i];
            
            //performing insertion sort on bucket
            Sorting.insertionSort(Bucket);
            
            for(double num : Bucket){ //concatenating the lists
            	int b = 0;
                arr[b++] = num;
             }
            return arr;
        }

       
    
	public static void insertionSort(double arr[]) 
    { 
        int n = arr.length; 
        for (int i = 1; i < n; ++i) { 
            double key = arr[i]; 
            int j = i - 1; 
            while (j >= 0 && arr[j] > key) { 
                arr[j + 1] = arr[j]; 
                j = j - 1; 
            } 
            arr[j + 1] = key; 
        } 
    } 
}
    
    


