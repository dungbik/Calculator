package org.example.constant;

public enum OperatorType {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    MODULO("%"),
    POWER("^"),
    ;

    final String symbol;

    OperatorType(String symbol) {
        this.symbol = symbol;
    }

    public static OperatorType parse(String symbol) {
        for (OperatorType op : OperatorType.values()) {
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