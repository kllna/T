package expression;

public class Subtract extends Operation {
    public Subtract(Element firstExpression, Element secondExpression) {
        super(firstExpression, secondExpression, "-");
    }
    @Override
    public int operation(int m, int n) {
        return m - n;
    }
}
