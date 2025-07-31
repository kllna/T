package game;

import java.util.*;

public class Main {
    public static int getNumber(Scanner s) {
        int v;
        while (true) {
            if (s.hasNextInt()) {
                v = s.nextInt();
                if (v > 0) {
                    break;
                }
            }
            else {
                s.next();
            }
            System.out.println("Try again");
        }
        return v;
    }
    public static void main(String[] args) {
        try {
            Scanner s = new Scanner(System.in);
            int m = getNumber(s);
            int n = getNumber(s);
            int k = getNumber(s);
            int p = getNumber(s);
            new OlympicSystem(m, n, k, p).Play();
        } catch (NoSuchElementException e) {
            System.out.println("Game over");
        } catch (NullPointerException e) {
            System.out.println("Game over");
        }
    }
}
