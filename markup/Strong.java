package markup;

import java.util.List;

public class Strong extends AbstractElement {
    public Strong(List<AbstractElement> listik) {
        super(listik, "__", "[b]", "[/b]");
    }
}