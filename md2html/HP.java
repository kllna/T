package md2html;

public class HP {
    private final StringBuilder inputText;

    public HP(StringBuilder inputText) {
        this.inputText = inputText;
    }

    private int HeaderNumber(StringBuilder text) {
        int i = 0;
        while (i < text.length() && text.charAt(i) == '#') {
            i++;
        }
        if (i > 0 && i < text.length() && text.charAt(i) == ' ') {
            return i;
        }
        else {
            return 0;
        }
    }

    public void toHtmls(StringBuilder ans) {
        int hn = HeaderNumber(inputText);
        if (hn != 0) {
            new Header(inputText, hn).toHtmlh(ans);
        } else {
            new Paragraph(inputText).toHtmlp(ans);
        }
    }
}
