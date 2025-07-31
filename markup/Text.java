package markup;

import java.util.Collections;

public class Text extends AbstractElement {
    private String text;
    public Text(String text) {
        super(Collections.emptyList(), "", "", "");
        this.text = text;
    }
    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(text);
    }
    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append(text);
    }
}
