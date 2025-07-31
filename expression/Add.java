package expression;

public class Add extends Operation {
    public Add(Element firstExpression, Element secondExpression) {
        super(firstExpression, secondExpression, "+");
    }

    @Override
    protected int operation(int m, int n) {
        return m + n;
    }
}
