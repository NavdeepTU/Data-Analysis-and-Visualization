/*
Given a Binary Tree and a node x in it, find the distance of the closest leaf to x in Binary Tree. If given node itself is a leaf, then the distance is 0.
Input: Root of below tree
       And x = pointer to node 13
          



	     10
       /     \
     12       13
              /     \
          14       15    
         /   \      /  \
       21   22   23   24
       /\     /\    /\     /\
      1 2   3 4  5 6  7  8

Output 2
Closest leaf is 12 through 10.

 */



class Node  
{ 
    int key; 
    Node left, right; 
   
    public Node(int key)  
    { 
        this.key = key; 
        left = right = null; 
    } 
} 
   
class Distance  
{ 
    int minDis = Integer.MAX_VALUE; 
} 
   
class BinaryTree  
{ 
    Node root; 
   
    // This function finds closest leaf to root.  This distance 
    // is stored at minDist. 
    void findLeafDown(Node root, int lev, Distance minDist)  
    { 
           
        // base case 1
        if (root == null)  
            return; 
   		
   		// base case 2
        // If this is a leaf node, then check if it is closer 
        // than the closest so far 
        if (root.left == null && root.right == null)  
        { 
            if (lev < (minDist.minDis))  
                minDist.minDis = lev; 
               
            return; 
        } 
   
        // Recur for left and right subtrees 
        findLeafDown(root.left, lev + 1, minDist); 
        findLeafDown(root.right, lev + 1, minDist); 
    } 
   
    // This function finds if there is closer leaf to x through  
    // parent node. 
    int findThroughParent(Node root, Node x, Distance minDist)  
    { 
        // Base cases 
        if (root == null)  
            return -1; 
           
        if (root == x)  
            return 0; 
           
        // Search x in left subtree of root 
        int L = findThroughParent(root.left, x, minDist); 
   
        // If left subtree has x 
        if (L != -1)  
        {     
            // Find closest leaf in right subtree 
            findLeafDown(root.right, L + 2, minDist);  
            return L + 1; 
        } 
   
        // Search x in right subtree of root 
        int R = findThroughParent(root.right, x, minDist); 
   
        // If right subtree has x 
        if (R != -1)  
        { 
            // Find closest leaf in left subtree 
            findLeafDown(root.left, R + 2, minDist); 
            return R + 1; 
        } 
   
        return -1;   // can return any number that does not matter because actual result is stored in minDist object
    } 
   
    // Returns minimum distance of a leaf from given node x 
    int minimumDistance(Node root, Node x)  
    { 
        // Initialize result (minimum distance from a leaf) 
        Distance d = new Distance(); 
   
        // Find closest leaf down to x 
        findLeafDown(x, 0, d); 
   
        // See if there is a closer leaf through parent 
        findThroughParent(root, x, d); 
   
        return d.minDis; 
    } 
   
    // Driver program 
    public static void main(String[] args)  
    { 
        BinaryTree tree = new BinaryTree();   // since main is a static function, need to create an object to access non static functions of same class
           
        // Let us create Binary Tree shown in above example 
        tree.root = new Node(1); 
        tree.root.left = new Node(12); 
        tree.root.right = new Node(13); 
   
        tree.root.right.left = new Node(14); 
        tree.root.right.right = new Node(15); 
   
        tree.root.right.left.left = new Node(21); 
        tree.root.right.left.right = new Node(22); 
        tree.root.right.right.left = new Node(23); 
        tree.root.right.right.right = new Node(24); 
   
        tree.root.right.left.left.left = new Node(1); 
        tree.root.right.left.left.right = new Node(2); 
        tree.root.right.left.right.left = new Node(3); 
        tree.root.right.left.right.right = new Node(4); 
        tree.root.right.right.left.left = new Node(5); 
        tree.root.right.right.left.right = new Node(6); 
        tree.root.right.right.right.left = new Node(7); 
        tree.root.right.right.right.right = new Node(8); 
   
        Node x = tree.root.right;   // node that we want to find the closest leaf to
   
        System.out.println("The closest leaf to node with value "
                + x.key + " is at a distance of "
                + tree.minimumDistance(tree.root, x)); 
    } 
} 