/*
A Doctor travels from a division to other division where divisions are connected like a graph(directed graph) and the edge weights are the probabilities of the doctor going from that 
division to other connected division but the doctor stays 10mins at each division now there will be given time and had to find the division in which he will be staying by that time and is determined by 
finding division which has high probability. Input is number of test cases followed by the number of nodes, edges, time after which we need to find the division in which he will be there, 
the edges starting point, end point, probability. Note: If he reaches a point where there are no further nodes then he leaves the lab after 10 mins and the traveling time is not considered 
and during that 10min at 10th min he will be in next division, so be careful

input 1 
6(#Nodes) 10(#Edges) 40(#time) 1 2 0.3 1 3 0.7 3 3 0.2 3 4 0.8 2 4 1 4 5 0.9 4 4 0.1 5 6 1.0 6 3 0.5 6 6 0.5

output 1 
6 0.774000

input 2 
6 10 10 1 2 0.3 1 3 0.7 3 3 0.2 3 4 0.8 2 4 1 4 5 0.9 4 4 0.1 5 6 1.0 6 3 0.5 6 6 0.5

output 2 
3 0.700000
	find the division with highest probability that doctor is in it after 10 min is div 3 and probability is 0.7.

After 9 minutes dr has yet to move to another division he is in div 1 and probability is 1.0. Forty minutes later the division with highest probabilty is 6 and probabilty is 0.77400

 */



import java.util.*;
class DoctorProbabilities
{
	static double[][] G;  // for storing the input probabilities
	static double[][] pro;
	
	public static void main (String[] args)
	{
		Scanner sc = new Scanner(System.in);
	    int n = sc.nextInt();
	    G = new double[n][n];
		for(int i=0;i<n;i++)
	        for(int j=0;j<n;j++)
	            G[i][j] = sc.nextDouble();  //inputing probabilities
		int time = sc.nextInt();
		if(time%10!=0)
	        time=time-(time%10);
	    pro = new double[n][time+11];
	    pro[0][0] = 1;  // initially doctor is at division 0 with probability 1 for time<10
		for(int i=0;i<n;i++)
	        if(G[0][i] != 0)
	            pro[i][10] = G[0][i];  
		int cnt=0;
		for(int t=10; t<time; t+=10)//At the end of tth iteration pro[i][t] will containt probability the doctor will be in ith division after time t+10. 
	    {
	        cnt=0;
	        for(int i=0;i<n;i++)
	        {
	            if(pro[i][t]==0)
	            {
	                cnt++;
	                continue;
	            }
	            for(int j=0;j<n;j++)
	                pro[j][t+10] += pro[i][t]*G[i][j];  // Key step
	        }
			if(cnt==n)
	            break;   // Doctor has left the lab
	    }
		double mx = Double.MIN_VALUE;
	    int division_present = 0;
	    for(int i=0;i<n;i++)
	    {
	    	if(pro[i][time] > mx)
	    	{
	        	mx = pro[i][time];
	        	division_present = i;
	    	}
	    }
	    System.out.println("Division Present : "+division_present);
	    System.out.println("Probability : "+mx);
	}
}
/* Sample Input
6
0 0.3 0.7 0 0 0 0 0 0 1 0 0 0 0 0.2 0.8 0 0 0 0 0 0.1 0.9 0 0 0 0 0 0 1 0 0 0.5 0 0 0.5
40
Output :
Division Present : 5
Probability : 0.7739999999999999 
*/