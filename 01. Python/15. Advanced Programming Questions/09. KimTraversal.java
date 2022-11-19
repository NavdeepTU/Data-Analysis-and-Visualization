/*
Mr Kim has to deliver refrigerators to N customers. From the office, he is going to visit all the customers and then return to his home. Each location of the office, his home, 
and the customers is given in the form of integer coordinates (x,y) (0=x=100, 0=y=100). The distance between two arbitrary locations (x1, y1) and (x2, y2) is computed by |x1-x2| + |y1-y2|, 
where |x| denotes the absolute value of x; for instance, |3|=|-3|=3. The locations of the office, his home, and the customers are all distinct. You should plan an optimal way to visit all 
the N customers and return to his home among all the possibilities.
You are given the locations of the office, Mr Kim’s home, and the customers; the number of the customers is in the range of 5 to 10. Write a program that, starting at the office, finds a (the) 
shortest path visiting all the customers and returning to his home. Your program only has to report the distance of a (the) shortest path.
Constraints
5=N=10. Each location (x,y) is in a bounded grid, 0=x=100, 0=y=100, and x, y are integers.
Input
You are given 10 test cases. Each test case consists of two lines; the first line has N, the number of the customers, and the following line enumerates the locations of the office, Mr Kim’s home, 
and the customers in sequence. Each location consists of the coordinates (x,y), which is represented by ‘x y’.
Output
Output the 10 answers in 10 lines. Each line outputs the distance of a (the) shortest path. Each line looks like ‘#x answer’ where x is the index of a test case. ‘#x’ and ‘answer’ are separated by a space.
I/O Example
Input (20 lines in total. In the first test case, the locations of the office and the home are (0, 0) and (100, 100) respectively, and the locations of the customers are (70, 40), (30, 10), (10, 5), (90, 70), (50, 20).)
5 ? Starting test case #1
0 0 100 100 70 40 30 10 10 5 90 70 50 20
6 ? Starting test case #2
88 81 85 80 19 22 31 15 27 29 30 10 20 26 5 14
10 ? Starting test case #3
39 9 97 61 35 93 62 64 96 39 36 36 9 59 59 96 61 7 64 43 43 58 1 36
Output (10 lines in total)
#1 200
#2 304
#3 366

 */


import java.util.*;
class KimTraversal
{
	static int[][] d; // matrix to strore the distance information between two points
	static int[] x;  // stores the x coordinate of position of office, customers and home of Kim
	static int[] y;  // stores the y coordinate of position of office, customers and home of Kim
	static boolean[] visited; // To keep track of which locations already visited
	static int n;
	static int min_distance = Integer.MAX_VALUE;  // will contain final minimum cost
	
	public static int abs(int x)
	{
	    if(x>0)
	        return x;
	    else
	        return -x;
	}
	
	public static void preprocess_distance()
	{
	    for(int i=0; i<n+2; i++)
	        for(int j=0; j<n+2; j++)
	            d[i][j] = abs(x[i]-x[j])+abs(y[i]-y[j]);
	}
	
	public static void calculate(int source, int destination, int distance_so_far)
	{
	    distance_so_far = distance_so_far + d[source][destination];
	    
	    if(distance_so_far > min_distance)  // if distance covered so far is greater than already computed min_distance then return
	        return;
	
	    if(destination == n+1)
	    {
	        for(int i=0; i<=n+1; i++)
	            if(!visited[i]) // Reached Home without covering all customers
	                return;
	        if(distance_so_far < min_distance) // Reached Home By Smaller Path
	            min_distance = distance_so_far;
	        return;
	    }
	
	    for(int i=1; i<=n+1; i++)
	    {
	        if(!visited[i])
	        {
	            visited[i]=true;
	            calculate(destination, i, distance_so_far);   // after reaching the destination the destination now becomes the source for the next unvisited destination.
	            visited[i]=false;  // Backtrack
	        }
	    }
	}	

	public static void main (String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
	    for (int i = 1; i <= t; ++i) 
	    {
	    	min_distance = Integer.MAX_VALUE;
	        n = sc.nextInt();
	        x = new int[n+2]; // size is greater by 2 to store positions of home office and home
	        y = new int[n+2];
	        visited = new boolean[n+2];
	        x[0] = sc.nextInt();
	        y[0] = sc.nextInt();
	        x[n+1] = sc.nextInt();
	        y[n+1] = sc.nextInt();
	
	        for (int j = 1; j <= n; ++j) 
	        {
	            x[j] = sc.nextInt();
	            y[j] = sc.nextInt();
	            visited[j] = false;
	        }
	        d = new int[n+2][n+2];
	        preprocess_distance();  // calculating distance between locations first and storing it in matrix instead of calculating again and again in each recursive call.
	        visited[0] = true; 
	        visited[n+1] = false;
	        calculate(0,0,0); // (source, destination, distance_so_far)
	        System.out.println(i+" "+min_distance);
	    }
	}
}

/*
 
Sample input :
3
5 
0 0 100 100 70 40 30 10 10 5 90 70 50 20
6 
88 81 85 80 19 22 31 15 27 29 30 10 20 26 5 14
10
39 9 97 61 35 93 62 64 96 39 36 36 9 59 59 96 61 7 64 43 43 58 1 36
Output :
200
304
366

*/