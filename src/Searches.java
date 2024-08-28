import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Searches {

    // In this set we'll add each board configuration that we have already checked.
    private Set<String> visited = new HashSet<>();
    private int[][] randomBoard;

    public boolean solveWithBFS (Board board) {

        // Making sure that the set is empty.
        this.visited.clear();

        // In the queue we'll add all the posible board configurations in each movement.
        Queue<int[][]> queue = new ArrayDeque<>();

        // Add the initial board configuration to the queue and the set.
        queue.add(randomBoard);
        this.visited.add(Arrays.deepToString(randomBoard)); // Matrix to String

        /*
        In each iteration of this cycle we'll check if the actual configuration is the goal.
        While we don't get the goal we'll search for more configurations to add in the queue.
        */
        while (!queue.isEmpty()) {

            // Set the actual board from the queue.
            board.setBoard(queue.poll());
            
            // Checking if we got the goal.
            if (board.isGoal()) {
                return true;
            }

            // We get a list of matrices that can represent posible movements from the actual board.
            List<int[][]> neighbors = getNeighbors(board);

            // We'll add each neighbor to the queue and visited set.
            for (int[][] neighbor : neighbors) {

                // Matrix to String
                String neighborString = Arrays.deepToString(neighbor);

                // We'll not add board configurations that we have already visited.
                if (!this.visited.contains(neighborString)) {
                    queue.add(neighbor);
                    this.visited.add(neighborString);
                }
            }

        }

        // If we don't get the goal configuration we'll return false.
        return false;
    }

    public boolean solveWithDFS(Board board) {

        // Making sure that the set is empty.
        this.visited.clear();

        // In the stack we'll add all the posible board configurations in each movement.
        Stack<int[][]> stack = new Stack<>();

        // Add the initial board configuration to the stack and the set.
        stack.push(randomBoard);
        this.visited.add(Arrays.deepToString(randomBoard));

        /*
        In each iteration of this cycle we'll check if the actual configuration is the goal.
        While we don't get the goal we'll search for more configurations to add in the stack.
        */
        while (!stack.isEmpty()) {

            // Set the actual board from the stack.
            board.setBoard(stack.pop());

            // Checking if we got the goal.
            if (board.isGoal()) {
                return true;
            }

            // We get a list of matrices that can represent posible movements from the actual board.
            List<int[][]> neighbors = getNeighbors(board);
            
            // We'll add each neighbor to the stack and visited set.
            for (int[][] neighbor : neighbors) {

                // Matrix to String
                String neighborString = Arrays.deepToString(neighbor);

                // We'll not add board configurations that we have already visited.
                if (!this.visited.contains(neighborString)) {
                    stack.push(neighbor);
                    this.visited.add(neighborString);
                }
            }

        }
        
        // If we don't get the goal configuration we'll return false.
        return false;
    }

    private List<int[][]> getNeighbors(Board board) {

        // We'll save a list of board configurations that are in one movement from actual board.
        List<int[][]> neighbors = new ArrayList<>();

        // We get the empty position.
        int emptyPosition[] = board.getEmptyPosition();
        int row = emptyPosition[0];
        int col = emptyPosition[1];

        // We'll generate a maximum of 4 neighbor board configuration depending on the position.
        if (row > 0) {
            // UP
            neighbors.add(swap(board.getBoard(), row, col, row - 1, col));
        }
        if (row < 2) {
            // DOWN
            neighbors.add(swap(board.getBoard(), row, col, row + 1, col));
        }
        if (col > 0) {
            // LEFT
            neighbors.add(swap(board.getBoard(), row, col, row, col - 1));
        }
        if (col < 2) {
            // RIGHT
            neighbors.add(swap(board.getBoard(), row, col, row, col + 1));
        }

        return neighbors;

    }

    private int[][] swap(int[][] board, int row, int col, int newRow, int newCol) {

        // We generate a copy from the board.
        int[][] newBoard = new int[3][3];
        for (int i = 0; i < 3; i++) {
            newBoard[i] = board[i].clone();
        }

        // Then we swap the given positions (empty position and movement position).
        int aux = newBoard[row][col];
        newBoard[row][col] = newBoard[newRow][newCol];
        newBoard[newRow][newCol] = aux;

        return newBoard;
    }

    public void setRandomBoard(int[][] board) {
        this.randomBoard = board;
    }

    // Number of checked board configurations.
    public int getVisitedCount() {
        return this.visited.size();
    }

}