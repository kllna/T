package game;

import java.util.*;

public class OlympicSystem {

    private final int m;
    private final int n;
    private final int k;
    private final int p;

    public OlympicSystem(int m, int n, int k, int p) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.p = p;
    }

    public void Play() {
        int[] players = new int[p];
        for (int i = 0; i < p; i++) {
            players[i] = i + 1;
        }
        int degree = 1;
        while (2 * degree < p) {
            degree *= 2;
        }
        int[] playersNew = new int[degree];
        if (players.length != degree) {
            playersNew = Arrays.copyOf(players, degree);
            for (int i = 0; i < players.length - degree; i++) {
                final Game game = new Game(false, new HumanPlayer(), new HumanPlayer());
                int result;
                do {
                    result = game.play(new TicTacToeBoard(m, n, k));
                    if (result == 1) {
                        System.out.println(players[i] + " vs " + players[degree + i]);
                        System.out.println("Game result: " + playersNew[i]);
                    }
                    else if (result == 2) {
                        System.out.println(players[i] + " vs " + players[degree + i]);
                        playersNew[i] = players[degree + i];
                        System.out.println("Game result: " + playersNew[i]);
                    }
                } while (result != 1 && result != 2);
            }
            players = playersNew;
        }
        playersNew = Arrays.copyOf(players, players.length / 2);
        while (players.length != 1) {
            int count = 0;
            for (int i = 0; i < players.length - 1; i += 2) {
                final Game game = new Game(false, new HumanPlayer(), new HumanPlayer());
                int result;
                do {
                    result = game.play(new TicTacToeBoard(m, n, k));
                    if (result == 1) {
                        System.out.println(players[i] + " vs " + players[i + 1]);
                        System.out.println("Game result: " + players[i]);
                        playersNew[count] = players[i];
                    }
                    else if (result == 2) {
                        System.out.println(players[i] + " vs " + players[i + 1]);
                        System.out.println("Game result: " + players[i + 1]);
                        playersNew[count] = players[i + 1];
                    }
                } while (result != 1 && result != 2);
                count++;
            }
            players = playersNew;
            playersNew = Arrays.copyOf(players, players.length / 2);
        }
        System.out.println("Winner: " + players[0]);
    }
}
