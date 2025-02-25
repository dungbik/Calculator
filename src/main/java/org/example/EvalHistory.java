package org.example;

public record EvalHistory(
        Number operand1,
        Number operand2,
        Operator operation,
        Number result
) {
}
