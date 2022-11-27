/*

Mr. Bolt has to do a marathon of D distance. He can run at 5 different paces; each pace will have its time consumed per km and its energy consumption. 
Mr. Bolt can only run till he had energy left. Find the minimum time required for Mr. Bolt to complete marathon if he has H energy.
INPUT :
Input order :
Total test cases
Total Energy Total Dist
Next 5 lines - input for 5 different paces in min, sec and energy order
2
10 5
6 19 6
6 29 5
6 39 4
6 49 3
6 59 2
600 40
3 11 20
3 18 16
3 36 14
3 41 13
3 53 12 =>ans: 137min 11sec for 2nd TC

 */


import java.util.*;

class node
{
    int timeSec;
    int energy;
}

class Marathon {
	
	static int MAX_P = 6;  // paces stored from index 1 to 5 (0 not to used)
	
	static node[] paceInfo;

	static boolean[] visited = new boolean[MAX_P];

	static int H, D;
	static int finalTime;
	
	public static void resetGlobal()
	{
	    for (int i = 0; i < MAX_P; i++)
	        visited[i] = false;
	}

	//Recursion with For Loop
	public static void ComputeRecurssion(int tmpDist, int tmpEnergy, int tmpTime, int node)
	{
	
	    if (tmpTime > finalTime)
	        return;
	
	    if (tmpEnergy > H)
	        return;
	
	    if (tmpDist == D)
	    {
	        if (tmpTime < finalTime)
	            finalTime = tmpTime;
	        return;
	    }
	
	
	    for (int i = node; i <= 5; i++)
	    {
	        if (!visited[i])
	        {
	            visited[i] = true;
	            int j = 1;
	
	            while (true)  // use ith pace as much as you can
	            {
	                if ( j * paceInfo[i].energy + tmpEnergy <= H && j + tmpDist <= D )
	                {
	                    ComputeRecurssion(j + tmpDist, j*paceInfo[i].energy + tmpEnergy, tmpTime + j*paceInfo[i].timeSec, i+1);
	                }
	                else
	                    break;
	                j++;
	            }
	        }
	        //backtrack
	        visited[i] = false;
	    }
	}
	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
	    int T;
	
	    T = sc.nextInt();
	    for (int test_case = 0; test_case < T; test_case++)
	    {
	        int tempMin = 0, tempSec = 0;
	        H = sc.nextInt();
	        D = sc.nextInt();
	        paceInfo = new node[MAX_P];
	        for(int i=0; i<MAX_P; i++)
	        	paceInfo[i] = new node();
	        finalTime = 9999999;    // resetting global variable
	        resetGlobal();  // resetting visited array
	        for (int i = 1; i <= 5; i++)
	        {
	            tempMin = sc.nextInt();
	            tempSec = sc.nextInt();
	            paceInfo[i].timeSec = tempMin * 60 + tempSec;
	            paceInfo[i].energy = sc.nextInt();
	        }
	        //Logic here
	
	        ComputeRecurssion(0,0,0, 1);   //  distance_covered_till_now, energy_consumed_till_now, time_spent_till_now, starting_from_pace_1
	
	        // Print the answer to standard output(screen).
	        System.out.println("Case #" + (test_case + 1) + " " + (finalTime/60) + " " + (finalTime%60));
	    }
	}
}


/*
 *
2
10 5
6 19 6
6 29 5
6 39 4
6 49 3
6 59 2
600 40
3 11 20
3 18 16
3 36 14
3 41 13
3 53 12
Case #1 34 55
Case #2 137 11

 */