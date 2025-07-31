package expression;

import java.util.Objects;

public class Const implements Element {
    private final int a;
    public Const(int a) {
        this.a = a;
    }
    public int getConst() {
        return a;
    }
    @Override
    public String toString() {
        return Integer.toString(a);
    }
    @Override
    public int evaluate(int n) {
        return a;
    }
    @Override
    public int evaluate(int m, int n, int k) {
        return a;
    }
    @Override
    public boolean equals(Object object) {
        if (object == null || object.getClass() != getClass()) {
            return false;
        }
        return ((Const) object).getConst() == a;
    }
    @Override
    public int hashCode() {
        return Objects.hash(a, getClass());
    }
}
