package expression;

import java.util.Objects;

public abstract class Operation implements Element {
    protected Element firstExpression;
    protected Element secondExpression;
    protected String sign;
    protected Operation(final Element firstExpression, final Element secondExpression, final String sign) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.sign = sign;
    }
    public String toString() {
        return "(" + firstExpression.toString() + " " + sign + " " + secondExpression.toString() + ")";
    }
    protected abstract int operation(int m, int n);
    public int evaluate(int x) {
        return operation(firstExpression.evaluate(x), secondExpression.evaluate(x));
    }
    public int evaluate(int x, int y, int z) {
        return operation(firstExpression.evaluate(x, y, z), secondExpression.evaluate(x, y, z));
    }
    public boolean equals(Object object) {
        if (object == null || object.getClass() != getClass()) {
            return false;
        }
        return firstExpression.equals(((Operation) object).firstExpression) && secondExpression.equals(((Operation) object).secondExpression);
    }
    public int hashCode() {
        return Objects.hash(firstExpression, secondExpression, sign, getClass());
    }
}
