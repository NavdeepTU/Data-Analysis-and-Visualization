/*
 
Four 5G base station towers needs to be installed in a  Landscape which is divided as hexagon cells as shown in Fig below, which also contains number of people living in each cell. Need to find four cells  to install the 5G towers which can cover maximum number of people  combining all four cells, with below conditions
- Only one tower can be placed in a cell
- Each of the four chosen cell should be neighbor to atleast one of the remaining 3 cells. 
- All four cells should be connected  (like  one island)
Input range:  1 <= N, M <= 15
Sample input Format for Fig below                                                               
3 4
150 450 100 320
120 130 160 110
10 60 200 220
Output
Square of  Maximum number of people covered by 4 towers
 
Some valid combinations :
     
 
Case 3 has maximum sum, so output is 1130 * 1130  => 1276900

 */



import java.util.*;
class BaseStations
{
	static int N, M;
	static int[][] cells;
	static boolean[][] visited;
	static int maxcount;
	
	static int[][] edir = {{-1, -1, -1, 0, 1, 0},
						   {-1, 0, 1, 1, 0, -1}};
	static int[][] odir = {{ 0, -1, 0, 1, 1, 1},
						   { -1, 0, 1, 1, 0, -1}};
	
	public static boolean isvalid(int i, int j)
	{
		if(i < 0 || i >= N || j < 0 || j >= M)
			return false;
		return true;
	}
	
	public static void findmaxscore(int cX, int cY, int count, int curValue)
	{
		int nX, nY;
		if (count == 4)    // base condition ( when installed all 4 base stations )
		{
			if (curValue > maxcount)
				maxcount = curValue;
			return;
		}
	
		for (int i=0; i<6; i++)   // covering all six neighbours of the current cell(Hexagon)
		{
			if (cY % 2 == 0)
			{
				nX = cX + edir[0][i];
				nY = cY + edir[1][i];
			}
			else
			{
				nX = cX + odir[0][i];
				nY = cY + odir[1][i];
			}
			if (isvalid(nX, nY) && !visited[nX][nY])
			{
				visited[nX][nY] = true;
				findmaxscore(cX, cY, count + 1, curValue + cells[nX][nY]);  // combination which includes more than 1 neighbours of current cell (imp case)
				findmaxscore(nX, nY, count + 1, curValue + cells[nX][nY]);
				visited[nX][nY] = false;  // Backtrack
			}
		}
	}
	
	public static void main (String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test=0; test<T; test++)
		{
			N = sc.nextInt();
			M = sc.nextInt();
			cells = new int[N][M];
			visited = new boolean[N][M];
	
			for(int i=0; i<N; i++)
			{
				for(int j = 0; j < M; j++)
					cells[i][j] = sc.nextInt();
			}
	
			maxcount = 0;
			for (int i = 0; i < N; i++)  // installing base station at each cell
			{
				for (int j = 0; j < M; j++)
				{
					visited[i][j] = true;
					findmaxscore(i, j, 1, cells[i][j]);  // ( curr_x_where_station_installed, curr_y_where_station_installed, stations_installed_till_now, people_covered_till_now )
					visited[i][j] = false;
				}
			}
			System.out.println(maxcount*maxcount);
		}
	}
}

/* Sample input
 
6
3 4
150 450 100 320
120 130 160 110
10 60 200 220
1 4
10 20 30 40
3 5
300 410 150 55 370 
120 185 440 190 450 
165 70 95 420 50 
5 5
356 55 41 453 12 
401 506 274 506 379 
360 281 421 311 489 
425 74 276 371 164 
138 528 461 477 470 
3 13
197 51 443 274 47 552 160 96 501 102 469 318 308 
516 128 506 471 381 418 328 517 380 78 569 58 90 
113 238 179 444 541 27 444 62 264 93 245 353 37 
11 7
292 182 586 607 259 190 239 
511 716 425 367 511 462 714 
593 713 231 60 118 442 82 
626 577 579 682 136 176 681 
240 23 410 193 230 729 109 
453 231 287 383 444 578 409 
729 401 408 330 213 574 54 
684 224 75 62 660 472 227 
606 37 473 487 222 185 476 
84 477 158 94 141 484 122 
616 333 302 626 29 99 674
*/