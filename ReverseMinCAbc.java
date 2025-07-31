import java.io.*;
import java.util.Arrays;
public class ReverseMinCAbc {
    public static void main(String[] args) throws UnsupportedEncodingException, IOException {
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
                String str = s2.next();
                StringBuilder str2 = new StringBuilder();
                if (str.charAt(0) == '-') {
                    str2.append('-');
                    for (int i = 1; i < str.length(); i++) {
                        str2.append(str.charAt(i) - 'a');
                    }
                } else {
                    for (int i = 0; i < str.length(); i++) {
                        str2.append(str.charAt(i) - 'a');
                    }

                }
                ints1[numbers - 1] = Integer.parseInt(str2.toString());
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
                int length = String.valueOf(mins[j]).length();
                if (String.valueOf(mins[j]).charAt(0) != '-') {
                    for (int l = 0; l < length; l++) {
                        System.out.print((char) (String.valueOf(mins[j]).charAt(l) + 49));
                    }
                } else {
                    System.out.print("-");
                    for (int l = 1; l < length; l++) {
                        System.out.print((char) (String.valueOf(mins[j]).charAt(l) + 49));
                    }
                }
                System.out.print(" ");
                count++;
            }
            System.out.println();
        }
    }
}