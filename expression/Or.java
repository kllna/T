package expression;

public class Or extends Operation {
    public Or(Element firstExpression, Element secondExpression) {
        super(firstExpression, secondExpression, "|");
    }

    @Override
    protected int operation(int m, int n) {
        return m|n;
    }
}