package org.example;

public record EvalHistory(
        int operand1,
        int operand2,
        Operator operation,
        int result
) {
}
