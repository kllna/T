package game;

import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            int r = -1;
            int c = -1;
            int flag = 0;
            try {
                String row = null;
                String column = null;
                while (true) {
                        if (in.hasNext()) {
                            row = in.next();
                        }
                        if (in.hasNext()) {
                            column = in.next();
                        }
                        else {
                            flag = 1;
                            break;
                        }
                        if (row != null && column != null) {
                            try {
                                r = Integer.parseInt(row);
                                c = Integer.parseInt(column);
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Try again");
                            }
                        }
                }
            } catch (NoSuchElementException e) {
                System.out.println("Game over");
            }
            try {
                final Move move = new Move(r, c, cell);
                if (position.isValid(move)) {
                    return move;
                }
                out.println("Move " + move + " is invalid");
            } catch (NoSuchElementException e) {
                System.out.println("Game over");
            }
            if (flag == 1) {
                return null;
            }
        }
    }
}
