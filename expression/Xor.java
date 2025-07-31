package expression;

public class Xor extends Operation {
    public Xor(Element firstExpression, Element secondExpression) {
        super(firstExpression, secondExpression, "^");
    }

    @Override
    protected int operation(int m, int n) {
        return m^n;
    }
}