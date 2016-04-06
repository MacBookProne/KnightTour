package knighttourprogram;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author justin O'Dea, Zach Hutchins, Joey Marinello
 */
public class KnighttourProgram { // knighttourprogram class 

    private static int[][] moves8
            = {{+1, -2}, {+2, -1}, {+2, +1}, {+1, +2}, {-1, +2}, {-2, +1}, {-2, -1}, {-1, -2}};

    public static void main(String[] args) { // main class 

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter board size (8 for 8x8): "); //takes in the size of the board 
        int sizeOfBoard = Integer.parseInt(scan.nextLine());
        System.out.print("Enter the beginning square (1 to " + (sizeOfBoard * sizeOfBoard) + "): "); //takes in a # 
        int start = Integer.parseInt(scan.nextLine());
        scan.close();

        Stack<Integer> itmoves = new Stack<>();
        boolean[] squares = new boolean[sizeOfBoard * sizeOfBoard];
        squares[start - 1] = true; // boolean 
        itmoves.add(start);
        int[] count = new int[1];
        count[0] = 0;
        boolean solution = solve(itmoves, squares, count, sizeOfBoard); //boolean is true 

        if (solution == true) {
            System.out.println("SUCCESS:"); //lucky you its a success
        } else {
            System.out.println("FAILURE:"); // this was a failure 
        }
        System.out.println("Total Number of Moves = " + count[0]); //prints my total number of moves 

        System.out.print("Moving Sequence:"); // prints the moving sequence 

        for (int j = 0; j < itmoves.size(); j++) {
            if (j == itmoves.size() - 1) { //number of moves -1 
                System.out.println("(" + itmoves.get(j) + ")");
            } else {
                System.out.print(itmoves.get(j) + " ");
            }//ends the else 

        }//ends the for 
    }//ends main class 

    private static boolean solve(Stack<Integer> itmoves, boolean[] squares, int[] count, int sizeOfBoard) {

        count[0]++; //counts the moves
        if (itmoves.isEmpty()) { // possible
            return false; // move
        }
        if (itmoves.size() == sizeOfBoard * sizeOfBoard) {
            return true;
        }
        int position = itmoves.peek(); // looks at the next location on the stack.   

        for (int i = 0; i < moves8.length; i++) {

            int dx = moves8[i][0];
            int dy = moves8[i][1];
            int x = (position - 1) % sizeOfBoard; // to x y format 
            int y = (position - 1) / sizeOfBoard; // to x y format 

            int x2 = x + dx; //moves adds to 
            int y2 = y + dy; // position 

            if (x2 >= 0 && x2 < sizeOfBoard && y2 >= 0 && y2 < sizeOfBoard) {
                int nextPosition = x2 + y2 * sizeOfBoard + 1;

                if (squares[nextPosition - 1] == true) {
                    continue;
                }
                itmoves.add(nextPosition);
                squares[nextPosition - 1] = true;
                if (!solve(itmoves, squares, count, sizeOfBoard)) {
                    squares[nextPosition - 1] = false;
                    itmoves.pop(); //pops it off 
                } else {
                    return true;

                } //ends else 
            }//ends if 
        } //ends for 
        return false;
    }//ends solve 

}// ends the knighttourprogram class 



