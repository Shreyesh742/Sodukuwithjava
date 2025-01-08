import java.util.Scanner;

public class SudokuSolver {

    private static final int SIZE = 9;
    private static int[][] grid = new int[SIZE][SIZE];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Shreyesh's Sudoku Solver!");

        // Load a random puzzle or enter your own
        System.out.println("Would you like to load a random puzzle? (yes/no)");
        String choice = scanner.nextLine();
        if ("yes".equalsIgnoreCase(choice)) {
            loadRandomPuzzle();
        } else {
            System.out.println("Enter your Sudoku puzzle row by row (use 0 for empty cells):");
            for (int row = 0; row < SIZE; row++) {
                String rowInput = scanner.nextLine();
                String[] numbers = rowInput.split(" ");
                for (int col = 0; col < SIZE; col++) {
                    grid[row][col] = Integer.parseInt(numbers[col]);
                }
            }
        }

        // Display the initial puzzle
        System.out.println("Initial Sudoku Puzzle:");
        printGrid();

        // Solve the puzzle
        if (solveSudoku(grid)) {
            System.out.println("Solved Sudoku Puzzle:");
            printGrid();
        } else {
            System.out.println("The Sudoku puzzle is unsolvable!");
        }

        scanner.close();
    }

    // Solves the Sudoku puzzle using backtracking
    private static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) { // Empty cell
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true;
                            }
                            board[row][col] = 0; // Backtrack
                        }
                    }
                    return false; // No valid number found
                }
            }
        }
        return true; // Solved
    }

    // Checks if placing the number at (row, col) is valid
    private static boolean isValid(int[][] board, int row, int col, int num) {
        // Check row
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }
        // Check column
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }
        // Check 3x3 grid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    // Prints the Sudoku board
    private static void printGrid() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
    }

    // Loads a random puzzle into the grid
    private static void loadRandomPuzzle() {
        grid = new int[][]{
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
    }
}
