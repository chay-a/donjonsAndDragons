package dnd.board;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Cell> board = new ArrayList<>();

    /**
     * Create the dnd.board for each index instantiate a Cell with on index 0 the player
     */
    public Board() {
        for (int i = 0; i< 64; i++) {
            this.board.add(new Cell());
        }
    }


    public static void main(String[] args) {
        Board board = new Board();
        System.out.println(board);
    }

    /**
     * Get the length of the dnd.board
     * @return int dnd.board length
     */
    public int getBoardLength() {
        return this.board.size();
    }
    @Override
    public String toString() {
        return "plateau = " + board;
    }

    /**
     * Get the dnd.board of the game
     * @return List<Cell>
     */
    public List<Cell> getBoard() {
        return board;
    }
}
