package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Md2Html {
    public static void main(String[] args) {
        StringBuilder ans = new StringBuilder();
        StringBuilder par = new StringBuilder();
        String s = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8));
            try {
                while (s != null) {
                    s = reader.readLine();
                    while (s != null && !s.isEmpty()) {
                        par.append(s);
                        par.append('\n');
                        s = reader.readLine();
                    }
                    if (!par.isEmpty()) {
                        par.setLength(par.length() - 1);
                        new HP(par).toHtmls(ans);
                        ans.append('\n');
                        par = new StringBuilder();
                    }
                }
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Input IOException: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.err.println("Unsupported encoding: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Input IOException: " + e.getMessage());
        }
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8));
            try {
                writer.write(ans.toString());
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println("Output IOException: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Output IOException: " + e.getMessage());
        }
    }
}
