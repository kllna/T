package expression;

import java.util.Objects;

public class Variable implements Element {
    private final String sign;
    public Variable(String sign) {
        this.sign = sign;
    }
    @Override
    public String toString() {
        return sign;
    }
    @Override
    public int evaluate(int m) {
        return m;
    }
    @Override
    public int evaluate(int m, int n, int k) {
        if (sign.equals("x")) {
            return m;
        }
        else if (sign.equals("y")) {
            return n;
        }
        return k;
    }
    @Override
    public boolean equals(Object object) {
        if (object == null || object.getClass() != getClass()) {
            return false;
        }
        return ((Variable) object).sign.equals(sign);
    }
    @Override
    public int hashCode() {
        return Objects.hash(sign, getClass());
    }
}