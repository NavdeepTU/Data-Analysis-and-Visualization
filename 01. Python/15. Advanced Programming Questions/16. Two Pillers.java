/*
 
You have to place an electronic banner of a company as high as it can be, so that whole the city can view the banner standing on top of TWO PILLERS.
The height of two pillers are to be chosen from given array.. say [1, 2, 3, 4, 6]. We have to maximise the height of the two pillars standing side by side, 
so that the pillars are of EQUAL HEIGHT and banner can be placed on top of it.
In the above array, (1, 2, 3, 4, 6) we can choose pillars like this, say two pillars as p1 and p2..
Then pillars can be,
p1 = 3 unit… Choosing element (3) from array,
Similarly p2 = 3 choosing (2 + 1) from array.
Since, two pillars are equal, we can put board on it…
But we have two maximise the height of the pillars,
And if we check for other heights, we can see p1 = 6 p2 = 4 + 2 which is greater than 3 ( the previous height)..
We have to see if we can further maximize the height… Yes it can be 8.
I.e. p1 = 6 + 2 = 8. p2 = 4 + 3 + 1 = 8.
Both pillars are equal and banner can be placed… And since this is the maximum height attainable for two pillars, we print the answer as 8. In case, there is no combination possible, print 0 (zero).



INPUT :
1
5
1 2 3 4 6
First line is  T  number of test cases to be followed.
Second line of input is number of different pillars.
Third line of input is  different available heights of pillars.
Note : heights of given pillars can be same .. I.e. array can have same elements repeated.
Output.
8
Simply print the maximum height attainable so that board/ banner can be placed.
In case there is no possible combination for placing banner with equal weighted pillars, then print 0.

 */



import java.util.Scanner;

class TwoPillers {
    static boolean[] visited;   // so that we do not use a bar twice in constructing both the pillers
    static int[] bars;         // to store the input (height of the bars)
    static int barCount;      // size of array 
    static int sumOfAllBars;   // sum of all the bars
    static int maximumBarLength;    // to start in a greedy way
    
    
    static boolean isMaxBarLengthPossible(int barLengthTotal) {            // recursively checking if we can construct a bar of length barLengthTotal from given bar lengths
    	
    	if (barLengthTotal < 0) {
    		return false;
    	}
    	else if(barLengthTotal == 0) {
    		return true;
    	}
    	
        for(int i= barCount-1; i>=0; i--) {
        	
          if(!visited[i]) {
                visited[i]=true;
                int barLength = barLengthTotal - bars[i];
                
                if( isMaxBarLengthPossible(barLength) )
                    return true;
                    
                visited[i]=false;
          } 
        }
        
     	return false;
    }
    
    
    static int maximumBarLength() 
    {
       maximumBarLength = sumOfAllBars / 2;  // you cannot have 2 bars with equal max lengths greater than sumOfAllBars / 2
       visited = new boolean[barCount];
       for(int i = maximumBarLength; i>=0; i--)  
       {
           for(int j=0;j<barCount;j++)  // before constructing 2 equal bars with maximum length all bars are unvisited
                visited[j]=false;
           boolean isPipe1Possible = isMaxBarLengthPossible(i);
           boolean isPipe2Possible = false;
           if(isPipe1Possible)
                isPipe2Possible = isMaxBarLengthPossible(i);
           if(isPipe1Possible && isPipe2Possible)  // both pipes possible to construct with length(maximum) i
                return i;
           else
                continue;
       }
        return 0;
    }
    
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        barCount = sc.nextInt();
        sumOfAllBars=0;
        bars = new int[barCount];
        for(int i=0;i<barCount;i++)
        {
            bars[i]=sc.nextInt();
            sumOfAllBars += bars[i];
        }
        
        System.out.println("Maximum height of the banner can be : "+maximumBarLength());
    }
}