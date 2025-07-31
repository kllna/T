import java.io.*;

public class MyScanner {
    private final Reader reader;
    private int count;
    private char[] buffer = new char[4096];
    private int index = 0;

    public MyScanner(InputStream is) throws UnsupportedEncodingException, IOException {
        this.reader = new InputStreamReader(is, "UTF-8");
        reading();
    }

    public MyScanner(String s) {
        this.reader = new StringReader(s);
        reading();
    }

    public void reading() {
        try {
            count = this.reader.read(buffer);
            index = 0;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void close() {
        try {
            this.reader.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    public boolean hasNextLine() {
        return count > 0;
    }

    public String nextLine() {
        StringBuilder sb = new StringBuilder();
        while (count > 0 && !(buffer[index] == '\n'
                || buffer[index] == '\u2028'
                || buffer[index] == '\u2029'
                || buffer[index] == '\u0085')) { // System.lineSeaprator
            sb.append(buffer[index]);
            index++;
            if (index == count) {
                reading();
            }
        }
        index++;
        if (count <= index) {
            reading();
        }
        return sb.toString();
    }

    public String nextLineNew() {
        StringBuilder sb = new StringBuilder();
        String s = System.lineSeparator();
        int l = s.length();
        int k = 0;
        while (count > 0 && l != k) {
            if (s.charAt(k) == buffer[index]) {
                k++;
                index++;
            }
            else {
                k = 0;
                sb.append(buffer[index]);
                index++;
            }
            if (index == count) {
                reading();
            }
        }
        if (count == index) {
            reading();
        }
        return sb.toString();
    }

    public String next() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (count > 0 && !Character.isWhitespace(buffer[index])) {
            sb.append(buffer[index]);
            index++;
            if (index == count) {
                reading();
            }
        }
        return sb.toString();
    }

    // make getChar func
    public boolean hasNext() {
        while (count > 0 && Character.isWhitespace(buffer[index])) {
            index++;
            if (index == count) {
                reading();
            }
        }
        return count > 0;
    }

    public boolean hasNextSymbol() {
        while (count > 0) {
            if (index == count) {
                reading();
            }
            else {
                return true;
            }
        }
        return false;
    }
    
    public char nextSymbol() {
        while (count > 0) {
            if (index == count) {
                reading();
            }
            else {
                index++;
                return buffer[index - 1];
            }
        }
        return '0';
    }

    public int nextInt() {
        try {
            String s = next();
            return Integer.parseInt(s);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return 0;
    }

    //copy paste
    public String nextWord() {
        StringBuilder sb = new StringBuilder();
        while (count > 0
                && (Character.isLetter(buffer[index])
                || buffer[index] == '\''
                || Character.getType(buffer[index]) == Character.DASH_PUNCTUATION)) {
            sb.append(buffer[index]);
            index++;
            if (index == count) {
                reading();
            }
        }
        return sb.toString().toLowerCase();
    }
    public boolean hasNextWord() {
        while (count > 0 && !(Character.isLetter(buffer[index])
                || buffer[index] == '\''
                || Character.getType(buffer[index]) == Character.DASH_PUNCTUATION)) {
            index++;
            if (index == count) {
                reading();
            }
        }
        return count > 0;
    }
}