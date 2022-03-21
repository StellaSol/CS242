//import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Sort1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		//DecimalFormat df = new DecimalFormat("#.###");
		int n,r,a,b,rep;
		
		System.out.print("Enter the size of the array");
		n = scan.nextInt();
		System.out.print("How many repetitions in the range[1,2r]?"); //if the person chooses 3 then the number of repetitions ranging from 1-6
		r = scan.nextInt();
		
		a=2*r;  
		
		double arr1[] = new double[n];
		double arr2[] = new double[n]; //Have to create an array with the same content so just copy it

		//Creating array of random numbers between the interval [0,1] exclusive
		//Each element randomly repeats until the end of the array
		
        int j=0;
		while(true){
		    double double_random = rand.nextDouble();
		    double temp = Math.random();
		    if(temp==1){
		        rep = (int) (temp*a);
		    }
		    else{
		        rep = (int)(1+(temp*a));
		    }
		    int k=0;
		    while(j<n && k<rep){
		        arr1[j] = double_random;
		        arr2[j] = double_random;
		        j++;
		        k++;
		    }
		    if(j==n){
		        break;
		    }
		}
		
		// Prints elements in the array
		for(int i=0;i<n;i++) {
			System.out.println(arr1[i]);
		}
		for(int i=0;i<n;i++) {
			System.out.println(arr2[i]);
		}
	}

}
