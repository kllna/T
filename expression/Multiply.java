package expression;

public class Multiply extends Operation {
    public Multiply(Element firstExpression, Element secondExpression) {
        super(firstExpression, secondExpression, "*");
    }
    @Override
    public int operation(int m, int n) {
        return m * n;
    }
}
