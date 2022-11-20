/*

Given a level K , you have to find out the sum of data of all the nodes at level K in a binary tree. Input is given as:
(P(C()())(C()())) P is for Parent, C is for child. if parent has one child : (P(C()())()) if parent has no child : (P()())

Examples:
Input : tree = "(0(5(6()())(4()(9()())))(7(1()())(3()())))" 
        k = 2
Output : 14
Its tree representation is shown below
 
Elements at level k = 2 are 6, 4, 1, 3
sum of the digits of these elements = 6+4+1+3 = 14 


Input : tree = "(8(3(2()())(6(5()())()))(5(10()())(7(13()())())))" 
        k = 3
Output : 9
Elements at level k = 3 are 5, 1 and 3
sum of digits of these elements = 5+1+3 = 9

 */



import java.util.*;

class SumAtKthLevel {
	
	static int kthLevelSum;
	
	public static int sumAtKthLevel(String tree, int k)  
	{  
	    int level = -1;  
	    int sum = 0; // Initialize result  
	    int n = tree.length();  
	  
	    for (int i=0; i<n; i++)  
	    {  
	        // increasing level number  
	        if (tree.charAt(i) == '(')  
	            level++;  
	  
	        // decreasing level number  
	        else if (tree.charAt(i) == ')')  
	            level--;  
	  
	        else
	        {  
	            // check if current level is  
	            // the desired level or not  
	            if (level == k)  
	                sum += (tree.charAt(i)-'0');  
	        }  
	    }  
	  
	    // required sum  
	    return sum;  
	}  

	
	
	public static void main (String[] args) {
		Scanner s = new Scanner(System.in);
		String tree = s.next();
		int k = s.nextInt();
		kthLevelSum = sumAtKthLevel(tree, k);
		System.out.println("Sum of nodes at kth level is : "+kthLevelSum);
	}
}