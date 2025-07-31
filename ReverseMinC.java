import java.io.IOException;
import java.util.Arrays;

public class ReverseMinC {
    public static void main(String[] args) throws IOException {
        int[] ints1 = new int[1];
        int[] ints2 = new int[1];
        MyScanner s1 = new MyScanner(System.in);
        int k = 0;
        int numbers = 0;
        int strs = 0;
        int w = 0;
        while (s1.hasNextLine()) {
            strs++;
            MyScanner s2 = new MyScanner(s1.nextLineNew());
            while (s2.hasNext()) {
                k++;
                numbers++;
                if (numbers >= ints1.length) {
                    ints1 = Arrays.copyOf(ints1, ints1.length * 2);
                }
                ints1[numbers - 1] = s2.nextInt();
            }
            if (strs >= ints2.length) {
                ints2 = Arrays.copyOf(ints2, ints2.length * 2);
            }
            ints2[strs - 1] = k;
            if (w < k) {
                w = k;
            }
            k = 0;
        }
        int[] mins = new int[w];
        for (int i = 0; i < w; i++) {
            mins[i] = Integer.MAX_VALUE;
        }
        int count = 0;
        for (int i = 0; i < strs; i++) {
            for (int j = 0; j < ints2[i]; j++) {
                if (ints1[count] < mins[j]) {
                    mins[j] = ints1[count];
                }
                System.out.print(mins[j]);
                System.out.print(" ");
                count++;
            }
            System.out.println();
        }
    }
}