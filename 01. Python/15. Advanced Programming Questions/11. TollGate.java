/*

Please find the minimum cost to travel from Source to Destination location with multiple toll gates across 

There are challenges at each toll gate to minimize the cost. 
• One can either choose to pay the toll or 

• One can battle at the toll gate to avoid paying by having his own set of men's they travel with them (initially zero) or 

• One can pay double the toll cost and hire all the men at the each tolls for the next toll to battle and avoid toll cost,
 
• If you choose to battle at particular toll only if you can have no.of.. hired men is more than the count hired men at respective toll gate. 

Note: Each hired men can battle for 3 times only 

• For each battle you will lose equal no.of.men with you as well as available in the toll gate . Rest of them will lose 1 round of battle irrespective of they are alive or not. 
After 3 battle they will not survive. If you have 10 men with you and toll no. of. Toll men is 8, then you lose 8 men in battle and remaining 2 men lost 1 round of battle 
and hence they can be available for 2 more rounds only. 

Ex: 
7              //toll no.of.tolls 
10 100    //toll hire men and toll cost 
70 5 
80 15 
20 60 
50 90 
30 80 
10 10 
Min cost: 150

 */


import java.util.*;
// TollGate - March'16 Advance Test problem Solution using DFS or Preorder Tree traversal 
class TollGate
{
	static int N;
	static int[] t_hire;
	static int[] t_cost;
	static int min_cost;

	public static void dfs(int t_pos, int bpool3, int bpool2, int bpool1, int cur_cost)
	{
		int  tot_bpool = bpool3 + bpool2 + bpool1;
		
		if (cur_cost > min_cost) 
			return;   // condition important to avoid unnecessary cpu cycle
			
		if (t_pos == N-1)   // base condition to check last toll gate. 
		{
			// Note that at the last toll gate one will never pay double the toll cost to hier men to fight for next toll.
			if (tot_bpool < t_hire[t_pos])  
				cur_cost += t_cost[t_pos];  // pay the toll
			if (cur_cost < min_cost)
				min_cost = cur_cost;
			return;   // if tot_bpool >= t_hire[t_pos] then battle will happen and cur_cost will remain same and we don't need to keep track of pools in this case as it is the last toll.
		}
		
	    dfs(t_pos+1, bpool3, bpool2, bpool1, cur_cost + t_cost[t_pos]);  // toll pay option
	    dfs(t_pos+1, bpool3 + t_hire[t_pos], bpool2, bpool1 , cur_cost + 2*t_cost[t_pos]);  // toll hire option by paying double the toll cost
	 
		if ( tot_bpool  >= t_hire[t_pos] )  // toll battle option
		{
			if (t_hire[t_pos] > bpool2 + bpool1)  
	        { 
	            bpool3  =  tot_bpool - t_hire[t_pos];  
	            bpool1 = bpool2 = 0;
	        }
			else if (t_hire[t_pos] > bpool1 )
	        {
	             bpool2 = (bpool1+bpool2) - t_hire[t_pos];
	             bpool1 = 0;
	        }
			dfs(t_pos+1, 0,  bpool3 , bpool2, cur_cost); // note: pool3 is zero, pool3 becomes pool2 and pool2 as pool1
	    }
	}

	public static void main (String[] args)
	{
		Scanner sc = new Scanner(System.in);
	    int T = sc.nextInt();
	    for(int i=0; i<T; i++)
	    {
	    	N = sc.nextInt();   // N is the total number of tolls
	    	t_hire = new int[N];
	    	t_cost = new int[N]; 
	    	for (int j=0; j<N; j++)
	    	{
	    		t_hire[j] = sc.nextInt();
	    		t_cost[j] = sc.nextInt();
	    	}
			min_cost = Integer.MAX_VALUE; //some large number
	    	dfs(0, 0, 0, 0, 0);  // curr_toll, men_with_3_rounds_remaining, men_with_2_rounds_remaining, men_with_1_rounds_remaining, curr_cost
	    	System.out.println("MinCost = "+min_cost);
	     }
	}
}


/*  input test case  5 is no.of.TC, 7 is no.of. toll gates,  each line no.of.hire and toll cost
cut and paste from next line*/ /*
5
7
10 100
70 5
80 15
20 60
50 90
30 80
10 10

9
600 800
300 400
300 400
1000 400
300 600
100 300
600 300
600 500
1000 300

11
1000 10
700 900
400 500
300 10
900 900
300 10
50 900
50 900
700 900
500 900
50 10

20
896 546
543 216
454 310
408 367
40 602
252 582
954 627
850 234
763 479
232 278
301 538
528 508
936 154
629 443
758 336
432 700
882 256
278 738
517 882
317 136

20
410 610
831 909
675 629
421 774
386 869
544 219
492 414
996 557
499 482
231 285
804 978
304 881
489 911
75 315
927 648
252 914
330 396
937 133
495 882
813 717

Output for above sample input
150 3000 2370 4721 8231
*/
