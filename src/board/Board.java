package board;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Board {
//    private final int boardLength = 64;
    private Cell[] board = new Cell[64];

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

    @Override
    public String toString() {
        return "plateau = " + Arrays.toString(board);
    }
}
