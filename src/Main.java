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
        board.printBoard();
        board.generateRandomBoard(50);
        board.printBoard();

        // RESOLVE BOARD WITH THE SELECTED ALGORITHM
        /* int opc = Integer.parseInt(args[0]);

        if (opc == 1){

        }
        else {

        } */

    }

}