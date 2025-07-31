package expression;

public class Minus extends UnaryOperation{
    public Minus(Element expression) {
        super(expression);
    }
    @Override
    protected int operation(int n) {
        return n*(-1);
    }
    @Override
    public String toString() {
        return "-(" + expression.toString() + ")";
    }
}
