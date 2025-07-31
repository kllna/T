package markup;

import java.util.List;

public class Strikeout extends AbstractElement {
    public Strikeout(List<AbstractElement> listik) {
        super(listik, "~", "[s]", "[/s]");
    }
}