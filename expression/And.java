package expression;

public class And extends Operation {
    public And(Element firstExpression, Element secondExpression) {
        super(firstExpression, secondExpression, "&");
    }

    @Override
    protected int operation(int m, int n) {
        return m&n;
    }
}