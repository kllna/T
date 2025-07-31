package expression;

public class Not extends UnaryOperation{
    public Not(Element expression) {
        super(expression);
    }
    @Override
    protected int operation(int n) {
        return ~n;
    }
    @Override
    public String toString() {
        return "~(" + expression.toString() + ")";
    }
}