import java.util.*;
import java.io.*;
public class Wspp {
    public static void main(String[] args) {
        int[] count;
        int k;
        int number = 0;
        LinkedHashMap<String, int[]> hm = new LinkedHashMap<>();
        try {
            MyScanner sc = new MyScanner(new FileInputStream(args[0]));
            try {
                while (sc.hasNextWord()) {
                    String s = sc.nextWord();
                    if (s != null) {
                        number++;
                        k = 0;
                        for (int j = 0; j < hm.size(); j++) {
                            if (hm.containsKey(s)) {
                                count = hm.get(s);
                                if (count[0] >= count.length) {
                                    count = Arrays.copyOf(count, count.length * 2);
                                }
                                count[0]++;
                                count[count[0] - 1] = number;
                                hm.put(s, count);
                                k++;
                                break;
                            }
                        }
                        if (k == 0) {
                            count = new int[2];
                            count[0]++;
                            count[1] = number;
                            hm.put(s, count);
                            count[0]++;
                        }
                    }
                    else {
                        break;
                    }
                }
            } finally {
                sc.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported encoding: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "UTF-8"));
            try {
                for (Map.Entry<String, int[]> entry : hm.entrySet()) {
                    writer.write(entry.getKey());
                    writer.write(" ");
                    int[] m = entry.getValue();
                    writer.write(Integer.toString(m[0] - 1) + " ");
                    for (int i = 1; i < m[0] - 1; i++)
                        writer.write(m[i] + " ");
                    writer.write(m[m[0] - 1] + "\n");
                }
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("IOException: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}