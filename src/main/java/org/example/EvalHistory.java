package org.example;

public record EvalHistory(
        int operand1,
        int operand2,
        char operation,
        int result
) {
}
