package expression;

public class Divide extends Operation {
    public Divide(Element firstExpression, Element secondExpression) {
        super(firstExpression, secondExpression, "/");
    }
    @Override
    protected int operation(int m, int n) {
        return m / n;
    }
}
