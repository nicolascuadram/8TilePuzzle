public class Main {

    public static void main(String[] args) {

        // ARGUMENTS VALIDATION
        // If the number of arguments is not 1, print an error and exit the program.
        if (args.length != 1) {
            System.err.println("There are no arguments");
            System.exit(0);
        }

        // If the argument is not an integer, print an error and exit the program.
        try {
            int arg = Integer.parseInt(args[0]);

            // If the argument is not 1 or 2, print an error and exit the program.
            if (arg < 1 || arg > 3) {
                System.err.println("The argument must be 1, 2, or 3");
                System.err.println("1: Solve with BFS");
                System.err.println("2: Solve with DFS");
                System.err.println("3: Solve with both");
                System.exit(0);
            }
        } catch (NumberFormatException e) {
            System.err.println("The argument must be an integer");
            System.exit(0);
        }

        // GENERATE RANDOM BOARD
        // Creating a initial board with goal configuration.
        Board board = new Board();

        // Generating a random board with 50 movements and printing in the console.
        board.generateRandomBoard(50);
        System.out.println("");
        System.out.println("Random Initial Configuration of the board:");
        board.printBoard();

        // RESOLVE BOARD WITH THE SELECTED ALGORITHM
        int opc = Integer.parseInt(args[0]);
        Searches search = new Searches(board.getBoard());

        if (opc == 1 || opc == 3) {
            if (search.solveWithBFS(board)){
                System.out.println("The Puzzle was resolved with BFS:");
                board.printBoard();
                System.out.println("Time to solve the puzzle: " + (search.getTimeUsed() / 1_000_000) + " ms");
                System.out.println("Number of nodes in memory for the queue: " + search.getMemoryCount());
                System.out.println("Number of nodes checked: " + search.getVisitedCount());
                System.out.println("");
            }
            else {
                System.out.println("The Puzzle can't be resolved with BFS.");
            }
        }
        if (opc == 2 || opc == 3) {
            if (search.solveWithDFS(board)){
                System.out.println("The Puzzle was resolved with DFS:");
                board.printBoard();
                System.out.println("Time to solve the puzzle: " + (search.getTimeUsed() / 1_000_000) + " ms");
                System.out.println("Number of nodes in memory for the stack: " + search.getMemoryCount());
                System.out.println("Number of nodes checked: " + search.getVisitedCount());
            }
            else {
                System.out.println("The Puzzle can't be resolved with DFS.");
            }
        }

    }

}