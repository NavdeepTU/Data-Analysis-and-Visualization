/*
 
Mr. Lee has to travel various offices abroad to assist branches of each place. But he has a problem. The airfare would be real high as all offices he has to visit are in foreign countries. 
He wants to visit every location only one time and return home with the lowest expense. Help this company-caring man calculate the lowest expense.
Time limit : 1 second (java : 2 seconds)
Input format
Several test cases can be included in the inputs. T, the number of cases is given in the first row of the inputs. After that, the test cases as many as T (T = 30) are given in a row. 
N, the number of offices to visit is given on the first row per each test case. At this moment, No. 1 office is regarded as his company (Departure point). 
(1 = N = 12) Airfares are given to move cities in which branches are located from the second row to N number rows. I.e. jth number of ith row is the airfare to move from ith city to jth city. 
If it is impossible to move between two cities, it is given as zero.
Output format
Output the minimum airfare used to depart from his company, visit all offices, and then return his company on the first row per each test case.
Example of Input
2
5
0 14 4 10 20
14 0 7 8 7
4 5 0 7 16
11 7 9 0 2
18 7 17 4 0
5
9 9 2 9 5
6 3 5 1 5
1 8 3 3 3
6 0 9 6 8
6 6 9 4 8
Example of Output
30
18

 */



import java.util.Scanner;

class leeTravel {
    static int[][] airfare;   // To store the input matrix
    static boolean[] visitedOffices;    // To keep track of the visited offices
    static int officesCount; // Stores total number offices to visit, will be helpful to handle the base case
    static int minimumAirfare = Integer.MAX_VALUE;   // will store actual result
   

    static void minimumAirfare(int cityCount, int currentCity, int costTillNow) {
       
        if((cityCount == officesCount) && airfare[currentCity][0] != 0 ){
            minimumAirfare = Math.min(costTillNow + airfare[currentCity][0], minimumAirfare);
            return;
        }

        for(int i=0;i<officesCount;i++){
            if(!visitedOffices[i] && airfare[currentCity][i] !=0 ){
                visitedOffices[i] = true;
                minimumAirfare(cityCount+1, i, costTillNow + airfare[currentCity][i]);
                visitedOffices[i] = false;   // Backtrack
            }

        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int test=0; test<t; test++) {
	        officesCount = sc.nextInt();
	        airfare = new int[officesCount][officesCount];
	        visitedOffices = new boolean[officesCount];
	        for (int i = 0; i < officesCount; i++) {
	            for (int j = 0; j < officesCount; j++) {
	                airfare[i][j]= sc.nextInt();
	            }
	        }
	        for(int i =0 ; i<officesCount;i++){
	            visitedOffices[i]= false;
	        }
	        visitedOffices[0]=true;
	        minimumAirfare(1,0,0);
	
	        System.out.println("Minimum Airfare " + minimumAirfare);
        }
        sc.close();
    }
}
/* Sample Inputs :

2
5
0 14 4 10 20
14 0 7 8 7
4 5 0 7 16
11 7 9 0 2
18 7 17 4 0

Minimum Airfare 30
5
9 9 2 9 5
6 3 5 1 5
1 8 3 3 3
6 0 9 6 8
6 6 9 4 8
Minimum Airfare 18

*/