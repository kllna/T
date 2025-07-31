import java.util.Arrays;
import java.io.*;
public class Reverse {
	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		int[] ints1 = new int[0];
		int[] ints2 = new int[0];
		MyScanner s1 = new MyScanner(System.in);
		int k = 0;
		int elements = 0;
		int strs = 0;
		while (s1.hasNextLine()) {
			MyScanner s2 = new MyScanner(s1.nextLineNew());
			while (s2.hasNext()) {
				k++;
				elements++;
				int[] ints3;
				if (ints1.length == 0 || elements >= ints1.length) {
					if (elements == 1) {
						ints3 = ints3 = new int[1];
					}
					else {
						ints3 = Arrays.copyOf(ints1, ints1.length * 2);
					}
					ints1 = ints3;
				}
				ints1[elements - 1] = s2.nextInt();
			}
			int[] ints4;
			if (ints2.length == 0 || strs >= ints2.length) {
				if (strs == 0) {
					ints4 = new int[1];
				}
				else {
					ints4 = Arrays.copyOf(ints2, ints2.length * 2);
				}
				ints2 = ints4;
			}
			ints2[strs] = k;
			k = 0;
			strs++;
		}
		int l = 0;
		int count = 0;
		for (int i = 0; i < strs; i++) {
			for (int j = 0; j < ints2[strs - 1 - i]; j++) {
				l = ints1[elements - 1 - count];
				System.out.print(l);
				System.out.print(" ");
				count++;
			}
			System.out.println();
		}
	}
}