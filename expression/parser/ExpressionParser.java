package expression.parser;

import expression.*;
import java.util.Map;

public class ExpressionParser implements Parser {
    protected final Map<Character, Integer> m = Map.of(
            '~', 0,
            '*', 1,
            '/', 1,
            '+', 2,
            '-', 2,
            '&', 3,
            '^', 4,
            '|', 5
    );
    public Element parse(String expression) {
        int count = 0;
        StringBuilder sbl = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            if (!(Character.isWhitespace(expression.charAt(i)))) {
                sbl.append(expression.charAt(i));
            }
        }
        expression = String.valueOf(sbl);
        int currentBrakets = 0;
        int Brakets = expression.length();
        int index = 0;
        int numberOfOperations = 0;
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == '(') {
                currentBrakets++;
            }
            else if (ch == ')') {
                currentBrakets--;
            }
            else {
                if (currentBrakets <= Brakets && m.containsKey(ch)) {
                    if (ch == '-') {
                        if (i != 0) {
                            char prev = expression.charAt(i-1);
                            if (Character.isDigit(prev) || prev == 'x' || prev == 'y' || prev == 'z' || prev == ')') {
                                if (m.containsKey(expression.charAt(index))) {
                                    if (!(m.get(expression.charAt(index)) > m.get(ch)) || currentBrakets < Brakets) {
                                        numberOfOperations++;
                                        index = i;
                                        Brakets = currentBrakets;
                                    }
                                }
                                else {
                                    numberOfOperations++;
                                    index = i;
                                    Brakets = currentBrakets;
                                }
                            }
                            else {
                                if (i + 1 < expression.length()) {
                                    char sled = expression.charAt(i+1);
                                    if (Character.isDigit(sled)) {
                                        continue;
                                    }
                                }
                                if (numberOfOperations == count) {
                                    if (numberOfOperations == 0) {
                                        index = i;
                                    }
                                    count++;
                                    numberOfOperations++;
                                    Brakets = currentBrakets;
                                }
                            }
                        }
                        else {
                            char sled = expression.charAt(i+1);
                            if (!Character.isDigit(sled)) {
                                count++;
                                numberOfOperations++;
                                index = i;
                                Brakets = currentBrakets;
                            }
                        }
                    }
                    else if (currentBrakets < Brakets || count == numberOfOperations || ch == '|') {
                        if (ch == '~') {
                            count++;
                            if (i != 0) {
                                if (expression.charAt(i - 1) == '~' || expression.charAt(i - 1) == '-') {
                                    numberOfOperations++;
                                    continue;
                                }
                            }
                        }
                        numberOfOperations++;
                        index = i;
                        Brakets = currentBrakets;
                    }
                    else if (!(m.get(expression.charAt(index)) > m.get(ch))) {
                        if (ch == '~') {
                            count++;
                            if (i != 0) {
                                if (expression.charAt(i - 1) == '~' || expression.charAt(i - 1) == '-') {
                                    numberOfOperations++;
                                    continue;
                                }
                            }
                        }
                        index = i;
                        Brakets = currentBrakets;
                        numberOfOperations++;
                    }
                }
            }
        }
        String operation = String.valueOf(expression.charAt(index));
        if (numberOfOperations != 0) {
            if (count == numberOfOperations) {
                Element right = parse(expression.substring(index + 1));
                return makeExpression(right, operation);
            }
            Element left = parse(expression.substring(0, index));
            Element right = parse(expression.substring(index + 1));
            return makeExpression(left, right, operation);
        }
        else {
            int leftBrakets = 0;
            if (expression.charAt(leftBrakets) == '(') {
                do {
                    leftBrakets++;
                    if (leftBrakets >= expression.length()) {
                        break;
                    }
                } while (expression.charAt(leftBrakets) == '(');
            }
            int rightBrakets = 0;
            if (expression.charAt(expression.length() - 1 - rightBrakets) == ')') {
                do {
                    rightBrakets++;
                    if (rightBrakets >=  expression.length()) {
                        break;
                    }
                } while (expression.charAt(expression.length() - 1 - rightBrakets) == ')');
            }
            if (leftBrakets != 0) {
                expression = expression.substring(leftBrakets);
                index = 0;
            }
            if (rightBrakets != 0) {
                expression = expression.substring(0, expression.length() - rightBrakets);
            }
            char ch = expression.charAt(index);
            if (Character.isDigit(ch)) {
                StringBuilder sb = new StringBuilder();
                do {
                    sb.append(ch);
                    index++;
                    if (index < expression.length()) {
                        ch = expression.charAt(index);
                    }
                    else {
                        break;
                    }
                } while (Character.isDigit(ch));
                return new Const(Integer.parseInt(String.valueOf(sb)));
            }
            else {
                if (ch == 'x') {
                    return new Variable("x");
                }
                else if (ch == 'y') {
                    return new Variable("y");
                }
                else if (ch == 'z') {
                    return new Variable("z");
                }
                else if (ch == '-') {
                    StringBuilder sb = new StringBuilder();
                    sb.append('-');
                    index++;
                    ch = expression.charAt(index);
                    if (Character.isDigit(ch)) {
                        do {
                            sb.append(ch);
                            index++;
                            if (index < expression.length()) {
                                ch = expression.charAt(index);
                            }
                            else {
                                break;
                            }
                        } while (Character.isDigit(ch));
                        return new Const(Integer.parseInt(String.valueOf(sb)));
                    }
                }
                return null;
            }
        }
    }
    public Element makeExpression(Element firstExpression, String sign) {
        return switch (sign) {
            case "-" -> new Minus(firstExpression);
            case "~" -> new Not(firstExpression);
            default -> null;
        };
    }
    public Element makeExpression(Element firstExpression, Element secondExpression, String sign) {
        return switch (sign) {
            case "+" -> new Add(firstExpression, secondExpression);
            case "-" -> new Subtract(firstExpression, secondExpression);
            case "*" -> new Multiply(firstExpression, secondExpression);
            case "/" -> new Divide(firstExpression, secondExpression);
            case "&" -> new And(firstExpression, secondExpression);
            case "|" -> new Or(firstExpression, secondExpression);
            case "^" -> new Xor(firstExpression, secondExpression);
            default -> null;
        };
    }
}
