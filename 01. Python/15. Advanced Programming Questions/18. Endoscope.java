/*
Anurag, a CSE student from MANIT developed an endoscope to explore inner part of ruined water pipes. It is possible to explore the inner part of the pipes putting the endoscope into a certain part of the pipe. 
The endoscope can be moved in the pipes only. Meanwhile, when the pipes are connected to each other, if the length of the endoscope is long enough to explore, it is able to inspect the connected pipe. 
However, we cannot observe every pipe because the length of the endoscope is limited.

When the map of the ground water pipe, the location where the endoscope to out in, and the length of the endoscope is given, calculate the number of pipe which are available to explore. 
Length of endoscope means the range upto which endoscope can explore. There are seven kind of pipes, and description for each pipe are shown below
  - 1   - 2   - 3   - 4   - 5   - 6   - 7
Fig 1 Shows an example of a map of ground water pipes. In this case, the height of the map is 5, and the width is 6 If a certain point where the endoscope to put in is given as (2,1), 
it means the vertical location will be 2, and the horizontal location will be 1, shown as a red highlighted pipe in fig 2. If the length of the endoscope is 1, 
we can explore only one water pipe which is at the intersection point If the length of the endoscope is 2, we can explore 3 water pipes including a blue highlighted pipe and a red marked pipe with the endoscope Fig-3 . 
If the length of the endoscope is 3, we can explore 5 water pipes total Fig-4
                
		Fig – 1							Fig – 2      
   
 		Fig – 3                           				Fig – 4 
 		
Input
In the first line, T, the number of total test cases are given. From the second line, T test cases are given. In the first line of each test case, N, the height of the map of the groundwater pipes, 
M, the width, R, the vertical location of the water pipe where to put in the endoscope, C, the horizontal location of it, and the length of the endoscope L are given. In the following N lines, 
information about the map of the groundwater pipe is given. Each line has M numbers. Each number (from 1 to 7) means the type of water pipe for that point. 0 means there is no water pipe buried in that place.

Output
Print the respective answer for T test cases in total for T lines. The answer is the number of water pipes which is available to observe using the endoscope.
Constraints
1= T =100
1= N, M =50
0= X < N
0= Y < M
1= L = 20
SAMPLE INPUT
 
2 
5 6 2 1 3 
0 0 5 3 6 0 
0 0 2 0 2 0 
3 3 1 3 7 0 
0 0 0 0 0 0 
0 0 0 0 0 0 
5 6 2 2 6 
3 0 0 0 0 3 
2 0 0 0 0 6 
1 3 1 1 3 1 
2 0 2 0 0 2 
0 0 4 3 1 1

SAMPLE OUTPUT
 
5
15

 */

import java.util.*;

class Pair 
{
	int x;
    int y;
	Pair(int x1, int y1) 
    {
    	this.x = x1;
        this.y = y1;
    }
}

class myQueue 
{
	Pair[] Q;
    int front;
    int rear;

    myQueue(int size) 
    {
    	Q = new Pair[size];
    	front = 0;
        rear = 0;
    }

    public void add(Pair el) 
    {
        Q[rear++] = el;
    }
    
    public Pair remove() 
    {
        return Q[front++];
    }
    
    public boolean isEmpty() 
    {
        return front == rear;
    }
}

class EndoScope 
{
    public static void main(String args[])
    {
    	Scanner scn = new Scanner(System.in);
        int T = scn.nextInt();
        for(int t=0; t<T; t++) 
        {
            int N = scn.nextInt();
            int M = scn.nextInt();
            int R = scn.nextInt();
            int C = scn.nextInt();
            int L = scn.nextInt();
            
            int[][] Maze = new int[N][M];
            
            for (int i = 0; i < N; i++)
                for (int j = 0; j < M; j++) 
                    Maze[i][j] = scn.nextInt();
                    
            boolean[][] visit = new boolean[N][M];

            int[][] dist = new int[N][M];    // initially all values 0
            
            // 7 pipe infos (ignore index 0)
            int[] up =    { 0, 1, 1, 0, 1, 0, 0, 1 };
            int[] down =  { 0, 1, 1, 0, 0, 1, 1, 0 };
            int[] left =  { 0, 1, 0, 1, 0, 0, 1, 1 };
            int[] right = { 0, 1, 0, 1, 1, 1, 0, 0 };

            myQueue Q = new myQueue(N*M);  
            if(Maze[R][C] != 0)
            {
                Q.add(new Pair(R, C));
                visit[R][C] = true;
                dist[R][C] = 1;
            }

            while (!Q.isEmpty()) 
            {
                Pair elem = Q.remove();

                int x = elem.x;
                int y = elem.y;
				
				// checking all the cells adjacent to x,y
				
				// checking upper cell
                if (x-1 >= 0 && up[Maze[x][y]] == 1 && down[Maze[x-1][y]] == 1 && !visit[x-1][y]) 
                {
                    int d = dist[x][y] + 1;
                    if (d <= L) 
                    {
                        Q.add(new Pair(x-1, y));
                        visit[x-1][y] = true;
                        dist[x-1][y] = d;
                    }
                }
				// checking down cell
                if (x+1 < N && down[Maze[x][y]] == 1 && up[Maze[x+1][y]] == 1 && !visit[x+1][y]) 
                {
                    int d = dist[x][y] + 1;
                    if (d <= L) 
                    {
                        Q.add(new Pair(x+1, y));
                        visit[x+1][y] = true;
                        dist[x+1][y] = d;
                    }
                }
				// checking left cell
                if (y-1 >= 0 && left[Maze[x][y]] == 1 && right[Maze[x][y-1]] == 1 && !visit[x][y-1]) 
                {
                    int d = dist[x][y] + 1;
                    if (d <= L) 
                    {
                        Q.add(new Pair(x, y-1));
                        visit[x][y-1] = true;
                        dist[x][y-1] = d;
                    }
                }
				// checking right cell
                if (y+1 < M && right[Maze[x][y]] == 1 && left[Maze[x][y+1]] == 1 && !visit[x][y+1]) 
                {
                    int d = dist[x][y] + 1;
                    if (d <= L) 
                    {
                        Q.add(new Pair(x, y + 1));
                        visit[x][y+1] = true;
                        dist[x][y+1] = d;
                    }
                }
            }

            int count = 0;
            for (int i = 0; i < N; i++) 
                for (int j = 0; j < M; j++) 
                    if (dist[i][j] > 0) 
                        count++;
            System.out.println(count);
        }
    }
}
/*
potential errors :
1. overflow due to limited size of queue
2. wrong implementation of queue
3. pipes written wrongly

TEST CASES
 
12
5  6  2  1  3
0  0  5  3  6  0
0  0  2  0  2  0
3  3  1  3  7  0
0  0  0  0  0  0
0  0  0  0  0  0
 
5  6  2  2  3
0  0  5  3  6  0
0  0  2  0  2  0
3  3  1  3  7  0
0  0  0  0  0  0
0  0  0  0  0  0
 
5  6  1  4  3
0  0  5  3  6  0
0  0  2  0  2  0
3  3  1  3  7  0
0  0  0  0  0  0
0  0  0  0  0  0
 
5  6  1  2  3
0  0  5  3  6  0
0  0  2  0  2  0
3  3  1  3  7  0
0  0  0  0  0  0
0  0  0  0  0  0
 
5  6  1  1  3
0  0  5  3  6  0
0  0  2  0  2  0
3  3  1  3  7  0
0  0  0  0  0  0
0  0  0  0  0  0
 
5  6  1  1  0
0  0  5  3  6  0
0  0  2  0  2  0
3  3  1  3  7  0
0  0  0  0  0  0
0  0  0  0  0  0
 
5  6  2  2  10
0  0  5  3  6  0
0  0  2  3  1  6
3  3  1  3  7  3
0  0  0  0  0  0
0  0  0  0  0  0
 
5  6  1  5  10
0  0  5  3  6  0
0  0  2  3  1  6
3  3  1  3  7  3
0  0  0  0  0  0
0  0  0  0  0  0
 
5 6 2 1 3
0 0 5 3 6 0
0 0 2 0 2 0
3 3 1 3 7 0
0 0 0 0 0 0
0 0 0 0 0 0
 
5 6 2 2 6
3 0 0 0 0 3
2 0 0 0 0 6
1 3 4 1 3 1
2 0 2 0 0 2
0 0 4 3 1 1
 
5 6 2 2 6
3 0 0 0 0 3
2 0 0 0 5 6
1 3 4 1 1 1
2 0 2 0 0 2
0 0 4 3 1 1
 
50 50 0 0 20
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 
OUTPUT 
5
7
5
6
-1
-1
12
12
5
7
8
210
 
*/
