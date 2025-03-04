package org.example.operation;

import org.example.constant.Operator;
import org.example.operation.annotation.Operation;

@Operation(operator = Operator.SUBTRACT)
public class SubtractOperation implements AbstractOperation {

    @Override
    public Double operate(double operand1, double operand2) {
        return operand1 - operand2;
    }
}
