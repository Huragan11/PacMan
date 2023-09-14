package PacmanTableModel;

import javax.swing.table.AbstractTableModel;
import java.util.Iterator;
import java.util.Random;

public class PacmanTableModel extends AbstractTableModel {

    private final int[][] gameBoard; // 2D array to represent the game board
    private static final int PACMAN = 1;
    private static final int WALL = 2;
    private static final int DOT = 3;
    private static final int GHOST = 4;
    private static final int EMPTY = 5;
    private int rows;
    private int cols;

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    // Constructor to create the game board and initialize it with game elements
    public PacmanTableModel(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        gameBoard = new int[rows][cols];
        initializeBoard();
    }

    // Method to initialize the game board with game elements
    private void initializeBoard() {
        Random random = new Random();
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                if (row == 0 || row == getRows() - 1 || col == 0 || col == getCols() - 1) {
                    setValueAt(WALL, row, col);
                } else {
                    if (col % 2 != 0) {
                        if (random.nextBoolean()) {
                            setValueAt(WALL, row, col);
                        } else {
                            setValueAt(DOT, row, col);
                        }
                    } else {
                        setValueAt(DOT, row, col);
                    }
                }
            }
        }
    }

    @Override
    public int getRowCount() {
        return gameBoard.length;
    }

    @Override
    public int getColumnCount() {
        return gameBoard[0].length;
    }

    public int getGhostStartX() {
        return gameBoard[0].length / 2;
    }

    public int getGhostStartY() {
        return gameBoard.length / 2;
    }

    @Override
    public Object getValueAt(int row, int col) {
        int element = gameBoard[row][col];
        switch (element) {
            case PACMAN:
                return PACMAN;
            case WALL:
                return WALL;
            case DOT:
                return DOT;
            case GHOST:
                return GHOST;
            case EMPTY:
                return EMPTY;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                return 8;
            case 9:
                return 9;
            case 10:
                return 10;

        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        int element = (int) value;
        gameBoard[row][col] = element;
        fireTableCellUpdated(row, col); // Notify the table that the cell has been updated
    }

}

