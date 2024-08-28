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
            if (arg < 1 || arg > 2) {
                System.err.println("The argument must be 1 or 2");
                System.exit(0);
            }
        } catch (NumberFormatException e) {
            System.err.println("The argument must be an integer");
            System.exit(0);
        }

        // GENERATE RANDOM BOARD
        Board board = new Board();
        System.out.println("Goal Configuration of the Board:");
        board.printBoard();

        board.generateRandomBoard(50);
        System.out.println("Random Initial Configuration of the board:");
        board.printBoard();

        

        // RESOLVE BOARD WITH THE SELECTED ALGORITHM
        int opc = Integer.parseInt(args[0]);
        Searches search = new Searches();

        if (opc == 1) {
            if (search.solveWithBFS(board)){
                System.out.println("The Puzzle was resolved with BFS:");
                board.printBoard();
                System.out.println("Number of nodes checked: " + search.getVisitedCount());
            }
            else {
                System.out.println("The Puzzle can't be resolved with BFS.");
            }
        }
        else if (opc == 2) {
            if (search.solveWithDFS(board)){
                System.out.println("The Puzzle was resolved with DFS:");
                board.printBoard();
                System.out.println("Number of nodes checked: " + search.getVisitedCount());
            }
            else {
                System.out.println("The Puzzle can't be resolved with DFS.");
            }
        }

    }

}