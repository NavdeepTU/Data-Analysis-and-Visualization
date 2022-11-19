/*
A binary matrix of nxm was given, you have to toggle(make 0?1 and 1? 0) any column k number of times so that you can get the maximum number of rows having all 1’s.
for eg, 
n=3, m=3,

1 0 0

1 0 1

1 0 0

if k=2, then we will toggle column 2 and 3 once and we will get rows 1 and 3 with 1 1 1 and 1 1 1 i.e. all 1’s so answer is 2 as there are 2 rows with all 1’s.

if k=3, then we will toggle column 2 thrice and we will get row 2 with 1 1 1 i.e. all 1’s so answer is 1 as there is 1 row with all 1’s.

Constraints :
N = 20 and M = 10^5

 */
 
 
 
import java.util.*;
class KToggle 
{   
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner (System.in);

        //inputting variables
        int n = sc.nextInt();
        int m = sc.nextInt();
        int mat[][] = new int [n][m];
        int k = sc.nextInt();

        //inputting the matrix
        for(int i =0;i<n;i++)
        {
            for(int j = 0;j<m;j++)
                mat[i][j] = sc.nextInt();
        }

        HashMap<String , Integer> map = new HashMap<String, Integer>();

        //Storing frequency in map
        for(int i=0; i<n; i++){
            String temp = "";
            for(int j=0; j<m; j++)
                temp += mat[i][j];
            
            //if map already contains that string increase the freq
            if(map.containsKey(temp))
            {
                int oldFreq = map.get(temp);
                map.put(temp, oldFreq+1);
            }
            //if not insert that string and set freq to 1
            else
                map.put(temp , 1);
        }

        int ans = Integer.MIN_VALUE;
		
		
        for(String entry : map.keySet())
        {
            //counting number of zeros in each row
            int num_zeros = 0;
            for(int i=0; i<entry.length(); i++)
                if(entry.charAt(i) == '0')
                	num_zeros++;
            if( num_zeros <= k && (k-num_zeros)%2 == 0 )  // most imp step
                ans = Math.max(map.get(entry), ans);
        }

        System.out.println("Maximum Rows that can be 1 are :"+ ans);
    }
}
/* Sample input

3 3
2
1 0 0
1 0 1
1 0 0


Output : 2
*/