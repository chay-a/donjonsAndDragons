package board;
import event.IEvent;

import java.util.Arrays;

public class Board {
    private Cell[] board = new Cell[64];

    /**
     * Create the board for each index instantiate a Cell with on index 0 the player
     */
    public Board() {
        // Loop to instantiate cells and create board
        for (int i = 0; i< board.length; i++) {
            this.board[i] = new Cell();
        }
    }


    public static void main(String[] args) {
        Board board = new Board();
        System.out.println(board);
    }

    public String getEventByIndex(int index) {
        if (this.board[index].getValue() == null) {
            return "Case vide";
        } else {
            IEvent event = (IEvent)this.board[index].getValue();
            return event.trigger();
        }
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

    public Cell[] getBoard() {
        return board;
    }
}
