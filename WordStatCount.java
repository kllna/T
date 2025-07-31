import java.util.*;
import java.io.*;
public class WordStatCount {
    public static void main(String[] args) {
        String[] words = new String[1];
        int[] count = new int[1];
        int slova = 0;
        int k = 0;
        int p = 0;
        int l = 0;
        try {
            MyScanner sc = new MyScanner(new FileInputStream(args[0]));
            try {
                while (sc.hasNextWord()) {
                    String s = sc.nextWord();
                    if (s != null) {
                        if (slova >= words.length) {
                            words = Arrays.copyOf(words, words.length * 2);
                        }
                        k = 0;
                        for (int j = 0; j < slova; j++) {
                            if (s.equals(words[j])) {
                                k++;
                                l = j;
                                break;                                          
                            }
                        }
                        if (k == 0) {
                            slova++;
                            words[slova - 1] = s;
                            if (p >= count.length) {
                                count = Arrays.copyOf(count, count.length * 2);
                            }
                            count[p] = 1;
                            p++;
                        }
                        else {
                            count[l] += 1;                                      
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
                String temp1 = "";
                int temp2 = 0;
                for (int i = 0; i < slova - 1; i++) {
                    for (int j = 0; j < slova - 1; j++) {
                        if (count[j] > count[j + 1]) {
                            temp1 = words[j + 1];
                            words[j + 1] = words[j];
                            words[j] = temp1;
                            temp2 = count[j];
                            count[j] = count[j + 1];
                            count[j + 1] = temp2;
                        }
                    }
                }
                for (int i = 0; i < slova; i++) {
                    writer.write(words[i]);
                    writer.write(" ");
                    writer.write(count[i] + "\n");
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