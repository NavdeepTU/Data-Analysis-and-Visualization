/*
RAE company wants to explore some of the rare elements for its semiconductor manufacturing. Scientists use one vehicle to explore the region in order to find the rare elements. 
The vehicle can move only in explored region where roads have alrady been constructed. The vehicle cannot move on unexplored region where roads are not there. In the current situation, 
rare elements are present in explored region only. Unexplored regions do not contain any rare elements. 
Square region is provided for exploration. Roads are represented by 1 and where roads are not present that area is represented by 0. Rare elements will only be on the roads where regions have already been explored. 
Vehicle can move in four directions – up, down, left and right. 
The shortest path for vehicle to a rare element position is called Moving Path. The longest of the paths to all rare elements from a region called Longest Distance. 
Scientists need to construct one research center so that the research center will be at the position where the longest path to the rare elements will be shortest. This is called Shortest Longest Distance. 

 

• In the pic (Fig. 1), Red, Blue and Green area represents Rare Element area. (2, 2) is 
represented as Red, (2, 8) is represented as Blue and (7, 8) is represented as Green. So there are three rare elements. 
• If research center is constructed at (4, 4) then distance to Red rare element will be 4, distance to Blue rare element will be 6 and distance to Green rare element will be 7. So the Longest distance will be 7. 
• Now using the same region, if research center is constructed at (4, 5) then distance to Red rare element will be 5, distance to Blue rare element will be 5 and distance to Green rare 
element will be 6. So the Longest distance will be 6. 
• So when research center is constructed at (4, 5) then the longest distance will be 
shortest. And the value of the Shortest Longest Distance will be 6. This will be the output. 
• There can be multiple locations from where the shortest longest distance can be same. For ex if research center is constructed at (5, 5) then still the Shortest Longest distance will be 6. 
• So write a program to find the Shortest Longest Distance. 

INPUT 
First line will be the number of test cases. Second line will indicate region area (N) and number of rare elements (C).Next C lines will contain the position of rare elements. 
After that N lines will provide the region details where to tell where roads are present and where roads are not present. 

Constraints 
The region provided will be square region i.e. NxN (where 5 <= N <= 20). There can be minimum of 2 rare elements and maximum of 4 rare elements, i.e. 2 <= C <= 4. 
Roads are represented by 1 while no road area is represented by 0. Vehicle can move only on roads in explored area. The rare elements will only be present where road are there. 
Rare elements will not be present where roads are not present. Vehicle can move in UP, DOWN, LEFT and RIGHT directions.The starting index for rare element is considers as 1. 

2 
5 2 
4 3 
3 4 
1 1 0 0 0 
1 1 0 0 0 
1 1 1 1 1 
1 1 1 0 1 
1 1 1 1 1 
8 2 
5 6 
6 4 
1 1 1 1 1 1 0 0 
1 1 1 1 1 1 1 0 
1 1 0 1 0 1 1 0 
1 1 1 1 0 1 1 0 
1 1 1 1 1 1 1 0 
1 1 1 1 1 1 1 0 
0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 0 
	
Answer – 1
             2 

 */


/*    Approach-1
    1-> Run BFS from every point on road
    2-> Find distance of all the rare-elements from that point and store its maximum
    3-> Return the minimum of all the Maximums calculated
*/


import java.util.*;

class node
{
   int x;
   int y;
   int level;
}
class queue    // queue of nodes
{ 
	node[] q;
	int front;
	int back;
	
	public queue(int size)
	{
		front = 0;
		back = 0;
		q = new node[size];
		for(int i=0; i<size; i++)
			q[i] = new node();
	}
 
	public void enqueue(int x, int y, int level)
	{
	  q[back].x = x;
	  q[back].y = y;
	  q[back].level = level;
	  back++;
	}
	public node dequeue()
	{
   		return q[front++];
	}
	public boolean empty()
	{
  		return (front == 0 && back == 0);
	}
}

 
class ResearchCenter
{
	static int n;    // dimension of input matrix
	static int c;    // number of rare elements
	static int[][] a;  // input matrix
	static int[][] rare;  // coordinates where the rare elements are present
	static boolean[][] vis;  // for bfs
 	static int xx[] = {-1,0,1,0};  
	static int yy[] = {0,1,0,-1};
 
	static boolean valid(int row, int col)
	{
  		if(row>=0 && row<n && col>=0 && col<n && a[row][col] == 1 && !vis[row][col])
  			return true;
  		return false;
	}	
 
	static int bfs(int sx,int sy,int dx,int dy)
	{
 		queue q = new queue(1000);
	    q.enqueue(sx,sy,0);   // here 3rd argument(level) is the distance of first 2 arguments from sx, sy
	    vis[sx][sy] = true;
	   
	    while(!q.empty())
	    {
	 		node temp = q.dequeue();
	        if(temp.x == dx && temp.y == dy)
	        	return temp.level;
	   
	        for(int i = 0; i<4; i++)
	        {
	 
	            int valx = temp.x + xx[i];
	            int valy = temp.y + yy[i];
	            int lvl = temp.level + 1;
	 
	            if(valid(valx,valy))
	            {
	                    q.enqueue(valx, valy, lvl);
	                    vis[valx][valy] = true;
	            }
	        }
	    }
	    return -1;   // not found rare element(this would never happen)
	}
 
 
	public static void main (String[] args) 
	{
		Scanner sc = new Scanner(System.in);
	  	int t = sc.nextInt();
	  	for(int test=0; test<t; test++)
	  	{
		    n = sc.nextInt();
		    c = sc.nextInt();
		    a = new int[n][n];
		    vis = new boolean[n][n];
		    rare = new int[c][2];
		    
		    for(int i =0; i<c; i++)   // input rare element positions
		    {
		       int x = sc.nextInt();
		       int y = sc.nextInt();
		       /*  indexing is so very important*/
		       x--;
		       y--;
		       rare[i][0] = x;
		       rare[i][1] = y;
		    }
		   
		    for(int i = 0; i<n; i++)  // input road info
		      for(int j =0; j<n; j++)
		        a[i][j] = sc.nextInt();
		   
		    int ans = Integer.MAX_VALUE;
		   
		    for(int i=0; i<n; i++)
		    {
		      for(int j=0; j<n; j++)
		      {
		        int temp;
		        if(a[i][j] == 1)  // for every point on road
		        {
		            temp = 0;
		            for(int k=0; k<c; k++)  // finding shortest largest distance of c rare elements from position i, j
		            {
		            	for(int l=0; l<n; l++)  // setting visited matrix to false 
		            		for(int m =0; m<n; m++)
		            			vis[l][m] = false;
		            			
		                int res = bfs(i, j, rare[k][0], rare[k][1]);  // finding kth rare element from ith, jth position    (source_x, source_y, dest_x, dest_y)
		                temp = Math.max(res,temp);  // getting max distance among all the rare elements from position i, j
		            }
		            /* if ke bahar mt likhio pehle galti se likh dia tha */
		            ans = Math.min(ans,temp);  // getting shortest distance among all the largest distances computed from every point on the road
		        }
		      }
		    }
		    System.out.println(ans);
	  }
	}
}
/*
Input:
6
8 3
2 2
2 8
7 8
1 1 0 0 0 0 0 0
1 1 0 0 1 1 1 1
1 1 0 0 1 0 0 0
1 1 1 1 1 0 0 0
0 1 0 0 1 0 0 0
0 1 0 0 1 0 0 0
0 1 0 0 1 1 1 1
0 1 0 0 0 0 0 0
5 2
4 3
3 4
1 1 0 0 0
1 1 0 0 0
1 1 1 1 1
1 1 1 0 1
1 1 1 1 1
8 2
5 6
6 4
1 1 1 1 1 1 0 0
1 1 1 1 1 1 1 0
1 1 0 1 0 1 1 0
1 1 1 1 0 1 1 0
1 1 1 1 1 1 1 0
1 1 1 1 1 1 1 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
10 3
8 2
5 3
7 1
0 0 0 1 1 1 1 1 1 0
1 1 1 1 1 1 1 1 1 0
1 0 0 1 0 0 0 0 1 0
1 1 1 1 1 1 1 1 1 1
1 1 1 1 0 1 0 0 1 1
1 1 1 1 0 1 0 0 1 1
1 1 1 1 0 1 0 0 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 0 0 1 0 0 1 1
1 1 1 1 1 1 1 1 1 1
15 4
11 15
15 9
1 2
14 3
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 0 1 1 1 1 1 1 1 1 1 1 1 0 1
1 0 1 0 0 0 1 0 0 0 0 1 1 0 1
1 0 1 0 0 0 1 0 0 0 0 1 1 0 1
1 0 1 1 1 1 1 1 1 1 1 1 1 1 1
1 0 1 0 0 0 1 0 0 0 0 1 1 0 1
1 0 1 0 0 0 1 1 1 1 1 1 1 1 1
1 0 1 0 0 0 1 0 0 0 0 1 1 0 1
1 0 1 0 0 0 1 0 0 0 0 1 1 0 1
1 0 1 0 0 0 1 0 0 0 0 1 1 0 1
1 0 1 0 0 0 1 0 0 0 0 1 1 0 1
1 0 1 0 0 0 1 0 0 0 0 1 1 0 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
0 0 1 0 0 0 1 1 1 1 1 1 1 0 1
0 0 1 1 1 1 1 1 1 1 1 1 1 1 1
20 4
13 6
20 4
1 2
17 16
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 0 0 0 0
1 0 1 1 1 1 1 1 1 1 1 1 1 1 1 0 0 0 0 0
1 0 1 0 0 0 0 0 0 0 1 0 0 1 1 0 0 0 0 0
1 0 1 0 0 0 0 0 0 0 1 0 0 1 1 0 0 0 0 0
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 0 0 0
1 0 1 0 0 0 0 0 0 0 1 0 0 1 1 1 0 0 0 0
1 0 1 0 0 0 0 0 0 0 1 0 0 1 1 1 0 0 0 0
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 0 1 0 0 0 0 0 0 0 1 0 0 1 1 1 0 0 1 1
1 0 1 0 0 0 0 0 0 0 1 0 0 1 1 1 0 0 1 1
1 0 1 0 0 0 0 0 0 0 1 0 0 1 1 1 0 0 1 1
1 0 1 0 0 0 0 0 0 0 1 0 0 1 1 1 0 0 1 1
1 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 0 1 1
1 0 1 0 0 0 0 0 0 0 1 0 0 0 1 1 0 0 1 1
1 0 1 0 0 0 0 0 0 0 1 0 0 0 1 1 0 0 1 1
1 0 1 0 0 0 0 0 0 0 1 0 0 0 1 1 0 0 1 1
1 0 1 0 0 0 0 0 0 0 1 0 0 0 1 1 0 0 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 0 0 0 0 0 0 0 0 0
Output:
6
1
2
2
12
15
*/