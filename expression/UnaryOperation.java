package expression;

public abstract class UnaryOperation implements Element{
    protected Element expression;
    protected UnaryOperation(final Element expression) {
        this.expression = expression;
    }
    protected abstract int operation(int n);
    public int evaluate(int x) {
        return operation(expression.evaluate(x));
    }
    public int evaluate(int x, int y, int z) {
        return operation(expression.evaluate(x, y, z));
    }
    public abstract String toString();

}
