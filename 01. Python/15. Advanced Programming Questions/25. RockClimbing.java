/*
There is a man who wants to climb a rock from a starting point to the destination point. Given a map of the rock mountain which N = height, M = width. 
In the map, character '-' is the possible foot place spot (where can climb) represented by 1. Rock is represented by 0 and destination by 3.
He can freely move down/up at vertical spots which '-' exists sequentially. It's impossible to move horizontally in case there is more than one space between '-' in the same height level.
Depending on how high/low he moves towards the upper or lower direction at one time, the level of difficulty of rock climbing gets determined.
The maximum height of moving from the starting point to destination point is the level of difficulty of rock climbing .
The total distance of movement is not important. There are more than one path from the starting point to destination point. => Output: The minimum level of difficulty of all rock climbing paths level.
Hint: Start with difficulty level 0 and then keep increasing it one by one.

 */

import java.util.*;


class RockClimbing {
	static int n, m;
	static int[][] map; 
	static boolean[][] visited; 
	static int minDifficulty = Integer.MAX_VALUE;
	
	// two moves - horizontal and vertical
	public static void climbRock(int i, int j, int maxClimbTillNow) {
		
	    if(i >= n || i < 0)
	        return;
	    else if(j >= m || j < 0)
	        return;
	    else if(map[i][j] == 3) {       // reached destination
	        if(maxClimbTillNow < minDifficulty)
	            minDifficulty = maxClimbTillNow;
	    } else if(map[i][j] == 0)
	        return;
	    else if(visited[i][j])
	        return;
	    else {
	        visited[i][j] = true;
	        int up = i-1;
	        int down = i+1;
	        int t1 = 0;
	        // vertical move up
	        while(up != -1 && map[up][j] == 0)
	            up--;
	        // cout << "Climbing Up " << i << " to " << down << " and maxClimbTillNow " << maxClimbTillNow << endl;
	        if(up != -1 && !visited[up][j] && map[up][j] != 0) {
	            t1 = i - up;
	            if(maxClimbTillNow > t1)
	                t1 = maxClimbTillNow;
	            // cout << "Climbing Down (" << i << ", " << j << ") to " << up << " and maxClimbTillNow "<< t1 << endl;
	            climbRock(up, j, t1);
	        }
	        // vertical move down
	        while(down != n && map[down][j] == 0)
	            down++;
	        // cout << "Climbing Up " << i << " to " << down << " and maxClimbTillNow " << maxClimbTillNow << endl;
	        if(down != n && !visited[down][j] && map[down][j] != 0) {
	            t1 = down - i;
	            if(maxClimbTillNow > t1)
	                t1 = maxClimbTillNow;
	            // cout << "Climbing Up (" << i << ", " << j << ") to (" << down << ", " << j << ") = " << map[down][j] <<" and maxClimbTillNow " << t1 << endl;
	            climbRock(down, j, t1);
	        }
	        //horizontal move
	        if((j>=0 && j<m-1) && (map[i][j+1] == 1 || map[i][j+1] == 3) && ( !visited[i][j+1]) ) {
	            climbRock(i, j+1, maxClimbTillNow);
	        }
	        if((j>0 && j<m) && (map[i][j-1] == 1 || map[i][j-1] == 3) && ( !visited[i][j-1]) ) {
	            climbRock(i, j-1, maxClimbTillNow);
	        }
	        visited[i][j] = false;
	    }
	}
	
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    n = sc.nextInt();
	    m = sc.nextInt();
	    
	    map = new int[n][m];
	    visited = new boolean[n][m];
	
	    for(int i=0;i<n;i++) {
	        for(int j=0;j<m;j++)
	            map[i][j] = sc.nextInt();
	    }
	    climbRock(n-1, 0, 0);
	    System.out.println(minDifficulty);
	}
}


/*
Input -
4
3
0 0 3
1 0 0
1 1 1
1 1 0

Output -
2
 */