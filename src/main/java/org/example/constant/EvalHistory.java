package org.example.constant;

public record EvalHistory(
        Number operand1,
        Number operand2,
        Operator operator,
        Number result
) {
}
