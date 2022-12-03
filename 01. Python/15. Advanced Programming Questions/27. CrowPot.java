/*

There are N pots. Every pot has some water in it. They may be partially filled. Every pot is associated with overflow number O which tell how many minimum no. of stones required for that pot to overflow. 
The crow knows O1 to On(overflow no. for all the pots). Crow wants some K pots to be overflow. So the task is a minimum number of stones he can make K pots overflow in the worst case.
Array of overflow no--. {1,...,On}
Number of pots--n
No of pots to overflow-- k
Let say two pots are there with overflow no.s {5,58}, and the crow has to overflow one pot(k=1). So crow will put 5 stones in a pot with overflow no.(58), it will not overflow, 
then he will put in the pot with overflow no.(5), hence the total no. of stones to make overflow one-pot is=10. What is the best algorithm to do it?

 */
 
import java.util.*;

class CrowPot{

	public static int minCrowPotStone(int n, int[] overflow_numbers, int k){
	    int total_stones=0;
	
	    for(int i=n-1; i>0; i--)
	        overflow_numbers[i] = Math.max(0, overflow_numbers[i]-overflow_numbers[i-1]);
	        
	    for(int i=0; i<k; i++)
	        total_stones += overflow_numbers[i] * (n-i);
	    
	    return total_stones;
	}
	
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] overflow_numbers = new int[n];
		for(int i=0;i<n;i++){
			overflow_numbers[i] = sc.nextInt();
		}
		Arrays.sort(overflow_numbers);  // first sort the overflow numbers in ascending order
		System.out.println("Minimum number of stones he can make "+k+" pots overflow in the worst case are :");
		System.out.println(minCrowPotStone(n, overflow_numbers, k));
	}
}