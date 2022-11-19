/*
 
You are busy to promote a newly released film in a movie theater. The title is ‘Biochemical Laughing Bomb’ which is about terror.
Guerrillas drop a biochemical laughing bomb in the middle of a city. Once exposed, you have to laugh all your life. The bomb will contaminate four people around it during 1 second, 
and another four around each of them during another one second. However, you won’t be contaminated if you are not in the adjacent four directions as the below shows the location of the bomb and affected people, 
and shows contamination process in seconds and you can figure out that the whole city is contaminated in 8 seconds.
In order to protect the city from the epidemic, create a program that figures out when the city will be contaminated by the bomb for the last.

Time limit: 1 second (java: 2 seconds)

[Input]
Several test cases can be included in the inputs. T, the number of cases is given in the first row of the inputs. After that, the test cases as many as T (T = 30) are given in a row.
The row and column of the city, N and M are given by being separated with a blank on the first row of each test case. (1 = N, M = 100)
The status within city is given by being separated with a blank from the second row to N number rows. 1 means people exist and 0 means people do not exist.
The coordinate of the row and column on which the bomb fall is given by being separated with a blank on the last row.

[Output]
For each test case, you should print "Case #T" in the first line where T means the case number. For each test case, you should output how long does it take to contaminate al people on the first row of each test case.

[I/O Example]
Input
2
7 8
0 0 1 1 0 0 0
1 1 1 1 0 1 0
0 0 1 1 1 1 1
0 1 1 1 1 1 1
0 1 0 0 1 1 0
0 1 1 1 1 0 0
0 0 1 0 1 1 1
0 0 0 0 1 0 0
2 5
10 10
1 1 1 1 0 1 1 0 0 0
0 1 1 1 1 1 0 1 1 0
0 0 1 1 0 1 0 1 1 1
0 1 1 1 1 1 0 0 1 1
0 1 0 1 0 1 1 1 1 0
0 0 0 0 0 1 1 0 0 0
1 0 1 0 1 0 1 1 0 0
0 0 1 1 1 1 1 1 1 1
1 0 1 0 0 1 0 1 1 0
1 1 1 0 0 0 0 1 1 1
2 2

Output
Case #1
8
Case #2
21

 */



import java.util.Scanner;

class Bomb {
    int sourceX;
    int sourceY;

    Bomb(int sourceX, int sourceY) {
        this.sourceX = sourceX;
        this.sourceY = sourceY;
    }
}

class People {
    int sourceX;
    int sourceY;
    int timeOfLaugh;

}

class Queue {     // queue of people objects
	
    int front, rear, size;
    int capacity;
    People[] arr;

    Queue(int capacity) {
        this.front = this.size = 0;
        this.capacity = capacity;
        this.rear = this.capacity - 1;
        this.arr = new People[capacity];
        for (int i = 0; i < this.arr.length; i++) {
            this.arr[i] = new People();
        }
    }

    void enQueue(People people) {
        if (this.size == this.capacity) {
            return;
        }
        this.rear = (this.rear + 1) % this.capacity;
        this.arr[this.rear] = people;
        this.size++;
    }

    People deQueue() {
        if (this.size == 0) {
            return null;
        }
        People people = this.arr[this.front];
        this.front = (this.front + 1) % this.capacity;
        this.size--;
        return people;
    }
}

class bioChemicalLaughingBomb {
	
    static int[][] peoplePositions;      
    static boolean[][] visitedPeople;   // To keep track if the person is already in the queue
    static int[] rowMove = new int[] { 1, -1, 0, 0 };
    static int[] columnMove = new int[] { 0, 0, 1, -1 };
    static int timeToExplode = 0;  // this will contain the final result

    static boolean isValid(int row, int column) {
        if ( row < 1 || column < 1 || row >= peoplePositions.length || column >= peoplePositions[0].length || visitedPeople[row][column] || peoplePositions[row][column] == 0)
            return false;
        return true;
    }

    static int timeToExplode(int sourceX, int sourceY) {
        Queue queue = new Queue(100);
        People people = new People();
        people.sourceX = sourceX;
        people.sourceY = sourceY;
        people.timeOfLaugh = 1;
        queue.enQueue(people);

        while (queue.size != 0) {   // using BFS approach to find the timeToExplode
            People popped = queue.deQueue();
            visitedPeople[popped.sourceX][popped.sourceY] = true;
            timeToExplode = popped.timeOfLaugh;
            for (int i = 0; i < 4; i++) {
                People surroundingPerson = new People();
                surroundingPerson.sourceX = popped.sourceX + rowMove[i];
                surroundingPerson.sourceY = popped.sourceY + columnMove[i];
                if (isValid(surroundingPerson.sourceX, surroundingPerson.sourceY)) {
                	surroundingPerson.timeOfLaugh = popped.timeOfLaugh + 1;
                    queue.enQueue(surroundingPerson);
                }
            }
        }
        return timeToExplode;   // last popped person's timeOfLaugh will be timeToExplode
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int columns = sc.nextInt();
        peoplePositions = new int[rows+1][columns+1]; // +1 because we are avoiding 0 index
        visitedPeople = new boolean[rows+1][columns+1]; 
		// people info input
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                peoplePositions[i][j] = sc.nextInt();
                visitedPeople[i][j] = false;
            }
        }
        // Bomb drop position input
        int sourceX = sc.nextInt();
        int sourceY = sc.nextInt();
        sc.close();
        Bomb bomb = new Bomb(sourceX, sourceY);

        System.out.println("Time to Explode " + timeToExplode(bomb.sourceX, bomb.sourceY) + " second/s");
    }
}