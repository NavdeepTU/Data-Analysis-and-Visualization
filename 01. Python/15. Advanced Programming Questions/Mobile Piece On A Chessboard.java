/*
There is a mobile piece and a stationary piece on the N×M chessboard. The available moves of the mobile piece are the same as set out in the image below. 
You need to capture the stationary piece by moving the mobile piece with the minimum amount of moves.
Write a program to find out the minimum number moves to catch a piece.
Time limit:1 second (java: 2 seconds)

Input
Several test cases can be included in the inputs. T, the number of cases is given in the first row of the inputs. After that, the test cases as many as T (T = 20) are given in a row. N, the numbers of the rows and M, the number of columns of the chessboard are given in the first row of each test case. R & C is the location information of the attacking piece and S & K is the location of the defending pieces and are given in the row at the second line. However, the location of the uppermost end of the left end is (1, 1)
Output
For each test case, you should print "Case #T" in the first line where T means the case number. For each test case, you should output the minimum number of movements to catch a defending piece at the first line of each test case. If not moveable, output equals ‘-1’.
[I/O Example]
	
	Input 
	2
	9 9
	3 5 2 8
	20 20
	2 3 7 9
	
	Output
	2
	
	5
	
	Sample input
	5
	10 10
	1 1 10 10
	20 20
	2 3 7 9
	30 30
	2 15 29 29
	40 40
	2 3 1 40
	45 45
	40 10 27 40

 */





//BFS Approach
import java.util.Scanner;

class Piece {
    int sourceX;
    int sourceY;

    Piece() {

    }

    Piece(int sourceX, int sourceY) {
        this.sourceX = sourceX;
        this.sourceY = sourceY;
    }
}

class Queue {
    int front, rear, size;
    int capacity;
    Piece[] arr;

    Queue(int capacity) {
        this.front = this.size = 0;
        this.capacity = capacity;
        this.rear = this.capacity - 1;
        this.arr = new Piece[capacity];
        for (int i = 0; i < this.arr.length; i++) {
            this.arr[i] = new Piece();
        }
    }

    void enQueue(Piece piece) {
        if (this.size == this.capacity) {
            return;
        }
        this.rear = (this.rear + 1) % this.capacity;
        this.arr[this.rear] = piece;
        this.size++;
    }

    Piece deQueue() {
        if (this.size == 0) {
            return null;
        }
        Piece piece = this.arr[this.front];
        this.front = (this.front + 1) % this.capacity;
        this.size--;
        return piece;
    }
}

class chessboard {
    static Piece defendingPience;
    static int[][] boardPositions;          // To calculate number of moves
    static boolean[][] visitedPositions;        // So that we dont traverse the same path from where we came
    static int[] rowMove = new int[] { 1, -1, 1, -1, 2, 2, -2, -2 };
    static int[] columnMove = new int[] { 2, 2, -2, -2, 1, -1, 1, -1 };

    static boolean isValid(int row, int column) {
        if (row < 1 || column < 1 || row >= boardPositions.length || column >= boardPositions[0].length || visitedPositions[row][column]) {
            return false;
        }
        return true;
    }

    static int movesToKill(int sourceX, int sourceY) {
        Queue queue = new Queue(100);
        Piece piece = new Piece(sourceX, sourceY);
        queue.enQueue(piece);
        boardPositions[sourceX][sourceY] = 0;
        while (queue.size != 0) {
            Piece poppedPiece = queue.deQueue();
            visitedPositions[poppedPiece.sourceX][poppedPiece.sourceY] = true;
            for (int i = 0; i < 8; i++) {
                int row = poppedPiece.sourceX + rowMove[i];
                int column = poppedPiece.sourceY + columnMove[i];
                if (isValid(row, column)) {
                    boardPositions[row][column] += boardPositions[poppedPiece.sourceX][poppedPiece.sourceY];
                    queue.enQueue(new Piece(row, column));
                }
                if (row == defendingPience.sourceX && column == defendingPience.sourceY) {
                    return boardPositions[row][column];
                }
            }

        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int k=0; k<T; k++)
        {
	        int rows = sc.nextInt();
	        int columns = sc.nextInt();
	        boardPositions = new int[rows+1][columns+1];
	        visitedPositions = new boolean[rows+1][columns+1];
	
	        for (int i = 1; i <= rows; i++) {
	            for (int j = 1; j <= columns; j++) {
	                boardPositions[i][j] = 1;
	                visitedPositions[i][j] = false;
	            }
	        }
	        int sourceX = sc.nextInt();
	        int sourceY = sc.nextInt();
	        Piece attackingPiece = new Piece(sourceX, sourceY);
	        sourceX = sc.nextInt();
	        sourceY = sc.nextInt();
	        defendingPience = new Piece(sourceX, sourceY);
	
	        System.out.println("Moves to Kill " + movesToKill(attackingPiece.sourceX, attackingPiece.sourceY));
        }
    }
}