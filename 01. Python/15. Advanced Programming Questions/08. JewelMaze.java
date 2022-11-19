/*
There is a maze that has one entrance and one exit. Jewels are placed in passages of the maze. You want to pick up the jewels after getting into the maze through the entrance and before getting out of it through the exit. 
You want to get as many jewels as possible, but you don’t want to take the same passage you used once.
When locations of a maze and jewels are given, find out the greatest number of jewels you can get without taking the same passage twice, and the path taken in this case.
 
Input
There can be more than one test case in the input file. The first line has T, the number of test cases. Then the totally T test cases are provided in the following lines (T = 10 ).
In each test case, In the first line, the size of the maze N (1 = N = 10) is given. The maze is N×N square-shaped. From the second line through N lines, information of the maze is given. 
“0” means a passage, “1” means a wall, and “2” means a location of a jewel. The entrance is located on the upper-most left passage and the exit is located on the lower-most right passage. 
There is no case where the path from the entrance to the exit doesn’t exist.
Output
From the first line through N lines, mark the path with 3 and output it. In N+1 line, output the greatest number of jewels that can be picked up. Each test case must be output separately as a empty.
[I/O Example]
	
	Input
	2
	5
	0 0 0 2 0
	2 1 0 1 2
	0 0 2 2 0
	0 1 0 1 2
	2 0 0 0 0
	6
	0 1 2 1 0 0
	0 1 0 0 0 1
	0 1 2 1 2 1
	0 2 0 1 0 2
	0 1 0 1 0 1
	2 0 2 1 0 0
	
	
	Output
	
	Case #1
	
	3 0 3 3 3 
	3 1 3 1 3 
	3 0 3 2 3 
	3 1 3 1 3 
	3 3 3 0 3 
	6
	
	Case #2
	
	3 1 2 1 0 0 
	3 1 3 3 3 1 
	3 1 3 1 3 1 
	3 2 3 1 3 2 
	3 1 3 1 3 1 
	3 3 3 1 3 3 
	4

 */



import java.util.Scanner;

class jewelMaze 
{
    static int[][] mazeInformation;            // input maze        
    static boolean[][] visitedPath;            // so that we do not go back the same path that we came
    static int[][] visitedPathInfo;           // this will contain the final output maze with the path traversed      
    static int[] rowMove = new int[] {1,-1,0,0};
    static int[] columnMove = new int[] {0,0,1,-1};
    static int sizeOfMaze;
    static int maximumJewels = Integer.MIN_VALUE;
   

    static void maximumJewels(int row, int column, int jewelCount, int[][] visitedPathInfoLocal) 
    {
        if( (row == sizeOfMaze -1) && (column == sizeOfMaze -1) )      // Base Case when reached the exit point
        {
        	if(mazeInformation[row][column] == 2)  // if jewel present at exit point
        		jewelCount += 1;
            if(maximumJewels < jewelCount)
            {
                maximumJewels = jewelCount;
                visitedPathInfo = new int[sizeOfMaze][sizeOfMaze];
                for (int i = 0; i < sizeOfMaze; i++) 
                {
                    for (int j = 0; j < sizeOfMaze; j++) 
                    {
                        visitedPathInfo[i][j]= visitedPathInfoLocal[i][j];    // setting visitedPathInfo to visitedPathInfoLocal as we got maximum jewels from current path
                    }
                }
                return;
            }
        }

        for(int i=0;i<4;i++)
        {
             int nextRow = row + rowMove[i];
             int nextColumn = column + columnMove[i];
             if(isValid(nextRow,nextColumn))
             {
                 visitedPath[nextRow][nextColumn] = true;
                 visitedPathInfoLocal[nextRow][nextColumn] = 3;
                 if(mazeInformation[nextRow][nextColumn] == 2)  // if jewel placed at newRow and newColumn
                 {
              	    maximumJewels(nextRow, nextColumn, jewelCount+1, visitedPathInfoLocal);
                    visitedPathInfoLocal[nextRow][nextColumn] = 2; // backtrack
                 }
                 else
                 {
                    maximumJewels(nextRow, nextColumn, jewelCount, visitedPathInfoLocal);
                    visitedPathInfoLocal[nextRow][nextColumn] = 0; // backtrack
                 }
                 visitedPath[nextRow][nextColumn] = false; // backtrack
             }
        } 
    }

    private static boolean isValid(int nextRow, int nextColumn) 
    {
        if( nextRow < 0 || nextColumn < 0 || nextRow >= sizeOfMaze || nextColumn >= sizeOfMaze || visitedPath[nextRow][nextColumn] || mazeInformation[nextRow][nextColumn] == 1)
            return false;
        return true;
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        sizeOfMaze = sc.nextInt();
        mazeInformation = new int[sizeOfMaze][sizeOfMaze];
        visitedPath = new boolean[sizeOfMaze][sizeOfMaze];
        int[][] visitedPathInfoLocal = new int[sizeOfMaze][sizeOfMaze];    // This matrix will contain the path for each recursive call made (not changing the maze Information matrix to store path)

        for (int i = 0; i < sizeOfMaze; i++) 
        {
            for (int j = 0; j < sizeOfMaze; j++) 
            {
                mazeInformation[i][j]= sc.nextInt();
            }
        }
        sc.close();
        for (int i = 0; i < sizeOfMaze; i++) 
        {
            for (int j = 0; j < sizeOfMaze; j++) 
            {
                visitedPath[i][j]=false;
                visitedPathInfoLocal[i][j]= mazeInformation[i][j];
            }
        }
        visitedPathInfoLocal[0][0]=3;
        visitedPath[0][0] = true;
        if(mazeInformation[0][0] == 2)        // if there is a jewel at the enterance
        	maximumJewels(0,0,1,visitedPathInfoLocal);
        else
        	maximumJewels(0,0,0,visitedPathInfoLocal);

        System.out.println("Here is the path to it..");

        for (int i = 0; i < sizeOfMaze; i++) 
        {
            for (int j = 0; j < sizeOfMaze; j++) 
            {
               System.out.print(visitedPathInfo[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Maximum Jewel/s is/are : " + maximumJewels);
    }
}
/* Sample Input
6
0 1 2 1 0 0
0 1 0 0 0 1
0 1 2 1 2 1
0 2 0 1 0 2
0 1 0 1 0 1
2 0 2 1 0 0
*/