package markup;
import java.util.List;
public abstract class AbstractElement implements Element {
    protected List<AbstractElement> listik;
    protected String obosobl;
    protected String obosobl1;
    protected String obosobl2;
    protected AbstractElement(final List<AbstractElement> listik, final String obosobl, final String obosobl1, final String obosobl2) {
        this.listik = listik;
        this.obosobl = obosobl;
        this.obosobl1 = obosobl1;
        this.obosobl2 = obosobl2;
    }
    public void toMarkdown(StringBuilder sb) {
        sb.append(obosobl);
        for (AbstractElement l : listik) {
            l.toMarkdown(sb);
        }
        sb.append(obosobl);
    }

    public void toBBCode(StringBuilder sb) {
        sb.append(obosobl1);
        for (AbstractElement l : listik) {
            l.toBBCode(sb);
        }
        sb.append(obosobl2);
    }
}