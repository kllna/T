import java.util.*;
import java.io.*;
public class WsppSortedPosition {
    public static void main(String[] args) {
        int number = 0;
        String[] words = new String[1];
        int k;
        int nomerstroki = 1;
        String str;
        StringBuilder s = new StringBuilder();
        ArrayList<Integer> count = new ArrayList<>();
        ArrayList<Integer> slova = new ArrayList<>();
        TreeMap<String, ArrayList<Integer>> hm = new TreeMap<>();
        try {
            MyScanner sc = new MyScanner(new FileInputStream(args[0]));
            try {
                boolean end = false;
                while (true) {
                    if (sc.hasNextSymbol()) {
                        char ch = sc.nextSymbol();
                        if (end) {
                            slova.add(number);
                            nomerstroki++;
                            number = 0;
                        }
                        if ((Character.getType(ch) == Character.DASH_PUNCTUATION) ||
                                (Character.isLetter(ch)) ||
                                (ch == '\'')) {
                            s.append(ch);
                        }

                        else {
                            if (!s.isEmpty()) {
                                str = s.toString().toLowerCase();
                                number++;
                                k = 0;
                                for (int j = 0; j < hm.size(); j++) {
                                    if (hm.containsKey(str)) {
                                        count = hm.get(str);
                                        count.add(nomerstroki);
                                        count.add(number);
                                        hm.put(str, count);
                                        k++;
                                        break;
                                    }
                                }
                                if (k == 0) {
                                    count = new ArrayList<>();
                                    count.add(nomerstroki);
                                    count.add(number);
                                    hm.put(str, count);
                                }
                                s.delete(0, s.length());
                            }
                        }
                        end = false;
                        if (ch == '\n') {
                            end = true;
                        }
                        if (ch == '\r') {
                            ch = sc.nextSymbol();
                            if (ch == '\n') {
                                end = true;
                            }
                        }
                    }
                    else {
                        if (!s.isEmpty()) {
                            str = s.toString().toLowerCase();
                            number++;
                            k = 0;
                            for (int j = 0; j < hm.size(); j++) {
                                if (hm.containsKey(str)) {
                                    count = hm.get(str);
                                    count.add(nomerstroki);
                                    count.add(number);
                                    hm.put(str, count);
                                    k++;
                                    break;
                                }
                            }
                            if (k == 0) {
                                count = new ArrayList<>();
                                count.add(nomerstroki);
                                count.add(number);
                                hm.put(str, count);
                            }
                        }
                        break;
                    }
                }
            } finally {
                slova.add(number);
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
                for (Map.Entry<String, ArrayList<Integer>> entry : hm.entrySet()) {
                    writer.write(entry.getKey());
                    writer.write(" ");
                    ArrayList<Integer> m = entry.getValue();
                    writer.write(Integer.toString(m.size() / 2));
                    for (int i = 0; i < m.size() - 1; i += 2) {
                        writer.write(" ");
                        writer.write(m.get(i) + ":" + (slova.get(m.get(i) - 1) - m.get(i + 1) + 1));
                    }
                    writer.write(System.lineSeparator());
                }
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}