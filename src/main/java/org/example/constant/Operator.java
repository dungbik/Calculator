package org.example.constant;

public enum Operator {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/")
    ;

    final String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    public static Operator parse(String symbol) {
        for (Operator op : Operator.values()) {
            if (op.symbol.equals(symbol)) {
                return op;
            }
        }
        return null;
    }

    public String getSymbol() {
        return symbol;
    }
}