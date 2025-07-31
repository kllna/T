package md2html;

public class Paragraph {
    private final StringBuilder inputText;

    public Paragraph(StringBuilder inputText) {
        this.inputText = inputText;
    }

    public void toHtmlp(StringBuilder s) {
        s.append('<');
        s.append('p');
        s.append('>');
        new Text(inputText).toHtml(s);
        s.append('<');
        s.append('/');
        s.append('p');
        s.append('>');
    }
}
