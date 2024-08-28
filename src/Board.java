public class Board {
    // ATTRIBUTES
    private int board[][];

    // CONSTRUCTOR
    public Board() {
        // Initial Configuration is the Goal Configuration
        this.board = new int[][]{{1, 2, 3}, {4, 0, 5}, {6, 7, 8}};
    }

    // METHODS
    public void generateRandomBoard(int moves) {

        // We will generate a random board with a number of random moves from the goal configuration.
        while (moves != 0) {

            // We will get the empty position and the near positions of the empty position in the board.
            int emptyPosition[] = getEmptyPosition();
            int nearPositions[][] = getNearPositions(emptyPosition);

            // Some short names for variables.
            int row = emptyPosition[0];
            int col = emptyPosition[1];
            int upRow = nearPositions[0][0];
            int upCol = nearPositions[0][1];
            int downRow = nearPositions[1][0];
            int downCol = nearPositions[1][1];
            int leftRow = nearPositions[2][0];
            int leftCol = nearPositions[2][1];
            int rightRow = nearPositions[3][0];
            int rightCol = nearPositions[3][1];

            /*
            In this cycle we try to get a random number to move one position in the board.
            The cycle will continue until we get a random number and a valid direction to move.
            */
            while (true) {

                // We will get a random number between 0 and 3 to move the empty position to a near position.
                int random = (int) (Math.random() * 4);

                // UP
                if (random == 0 && upRow != -1) {
                    this.board[row][col] = this.board[upRow][upCol];
                    this.board[upRow][upCol] = 0;
                    row = upRow;
                    col = upCol;
                    break;
                }

                // DOWN
                if (random == 1 && downRow != -1) {
                    this.board[row][col] = this.board[downRow][downCol];
                    this.board[downRow][downCol] = 0;
                    row = downRow;
                    col = downCol;
                    break;
                }

                // LEFT
                if (random == 2 && leftRow != -1) {
                    this.board[row][col] = this.board[leftRow][leftCol];
                    this.board[leftRow][leftCol] = 0;
                    row = leftRow;
                    col = leftCol;
                    break;
                }

                // RIGHT
                if (random == 3 && rightRow != -1) {
                    this.board[row][col] = this.board[rightRow][rightCol];
                    this.board[rightRow][rightCol] = 0;
                    row = rightRow;
                    col = rightCol;
                    break;
                }
            }
            
            // Discount the movement.
            moves--;
        }

    }

    public int[] getEmptyPosition() {
        /*
        position[0] = row for empty position
        position[1] = column for empty position
        */
        int position[] = new int[2];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.board[i][j] == 0) {
                    position[0] = i;
                    position[1] = j;
                    return position;
                }
            }
        }

        return position;
    }

    public int[][] getNearPositions(int position[]) {
        /* 
        position[0] = row for empty position
        position[1] = column for empty position
        */
        int row = position[0];
        int col = position[1];

        /*
        nearPositions[0][{0|1}] = position up 0=row 1=col
        nearPositions[1][{0|1}] = position down 0=row 1=col
        nearPositions[2][{0|1}] = position left 0=row 1=col
        nearPositions[3][{0|1}] = position right 0=row 1=col
        */
        int nearPositions[][] = new int[4][2];

        // UP
        if (row - 1 >= 0) {
            nearPositions[0][0] = row - 1;
            nearPositions[0][1] = col;
        }
        else {
            nearPositions[0][0] = -1;
            nearPositions[0][1] = -1;
        }

        // DOWN
        if (row + 1 <= 2) {
            nearPositions[1][0] = row + 1;
            nearPositions[1][1] = col;
        }
        else {
            nearPositions[1][0] = -1;
            nearPositions[1][1] = -1;
        }

        // LEFT
        if (col - 1 >= 0) {
            nearPositions[2][0] = row;
            nearPositions[2][1] = col - 1;
        }
        else {
            nearPositions[2][0] = -1;
            nearPositions[2][1] = -1;
        }

        // RIGHT
        if (col + 1 <= 2) {
            nearPositions[3][0] = row;
            nearPositions[3][1] = col + 1;
        }
        else {
            nearPositions[3][0] = -1;
            nearPositions[3][1] = -1;
        }

        // When row and col are equal to -1 there are not a near position for the respective way.

        return nearPositions;
    }

    // Function to check if the board has a goal configuration.
    public boolean isGoal() {
        // Goal Configuration
        int goal[][] = {{1, 2, 3}, {4, 0, 5}, {6, 7, 8}};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.board[i][j] != goal[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    // Function to print the board in the console.
    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.board[i][j] == 0) {
                    System.out.print("_ ");
                }
                else {
                    System.out.print(this.board[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // SETTER
    public void setBoard(int[][] newBoard) {
        this.board = newBoard;
    }

    // GETTER
    public int[][] getBoard() {
        return this.board;
    }
}