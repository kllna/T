package md2html;

public class Header {
    private final StringBuilder inputText;
    private final int headNumber;

    public Header(StringBuilder inputText, int headNumber) {
        this.inputText = inputText;
        this.headNumber = headNumber;
    }

    public void toHtmlh(StringBuilder s) {
        int l = headNumber;
        s.append('<');
        s.append('h');
        s.append(l);
        s.append('>');
        new Text(new StringBuilder(inputText.substring(l + 1))).toHtml(s);
        s.append('<');
        s.append('/');
        s.append('h');
        s.append(l);
        s.append('>');
    }
}
