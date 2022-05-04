package board;
import java.util.Arrays;

public class Board {
    private Cell[] board = new Cell[64];

    /**
     * Create the board for each index instantiate a Cell with on index 0 the player
     */
    public Board() {
        this.board[0] = new Cell();
        this.board[0].setValue("joueur");
        // Loop to instantiate cells and create board
        for (int i = 1; i< board.length; i++) {
            this.board[i] = new Cell();
        }
    }


    public static void main(String[] args) {
        Board board = new Board();
        System.out.println(board);
    }

    /**
     * Get the length of the board
     * @return int board length
     */
    public int getBoardLength() {
        return this.board.length;
    }
    @Override
    public String toString() {
        return "plateau = " + Arrays.toString(board);
    }
}
