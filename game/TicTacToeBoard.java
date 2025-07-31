package game;

import java.util.Arrays;
import java.util.Map;

public class TicTacToeBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private Cell turn;
    private final int m;
    private final int n;
    private final int k;

    public TicTacToeBoard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.cells = new Cell[m][n];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    public boolean check(int row, int column, int l, int h) {
        int count = 0;
        for (int i = -k + 1; i < k; i++) {
            int r = row + i*l;
            int c = column + i*h;
            if (0 <= r && r < m && 0 <= c && c < n && cells[r][c] == turn) {
                count++;
                if (count == k) {
                    return true;
                }
            }
            else {
                count = 0;
            }
        }
        return false;
    }

    public int extra(int row, int column, int l, int h) {
        int count = 0;
        int flag = 0;
        for (int i = -3; i < 4; i++) {
            int r = row + i*l;
            int c = column + i*h;
            if (0 <= r && r < m && 0 <= c && c < n && cells[r][c] == turn) {
                count++;
                if (count >= 4) {
                    flag = 1;
                }
            }
            else {
                count = 0;
            }
        }
        return flag;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        cells[move.getRow()][move.getColumn()] = move.getValue();
        int row = move.getRow();
        int column = move.getColumn();
        if (check(row, column, 0, 1) ||
                check(row, column, 1, 0) ||
                check(row, column, 1, 1) ||
                check(row, column, 1, -1)) {
            return Result.WIN;
        }
        if (extra(row, column, 0, 1) == 1 ||
                extra(row, column, 1, 0) == 1 ||
                extra(row, column, 1, 1) == 1 ||
                extra(row, column, 1, -1) == 1) {
            return Result.EXTRA;
        }

        int empty = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j] == Cell.E) {
                    empty++;
                }
            }
        }

        if (empty == 0) {
            return Result.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getColumn() && move.getColumn() < n
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        for (int i = 0; i < n; i++) {
            sb.append(i);
        }
        for (int r = 0; r < m; r++) {
            sb.append("\n");
            sb.append(r);
            for (int c = 0; c < n; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }
}
