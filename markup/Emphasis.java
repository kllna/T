package markup;

import java.util.List;

public class Emphasis extends AbstractElement {
    public Emphasis(List<AbstractElement> listik) {
        super(listik, "*", "[i]", "[/i]");
    }
}
