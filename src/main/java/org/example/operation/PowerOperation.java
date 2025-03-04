package org.example.operation;

import org.example.constant.Operator;
import org.example.operation.annotation.Operation;

@Operation(operator = Operator.POWER)
public class PowerOperation implements AbstractOperation {

    @Override
    public Double operate(double operand1, double operand2) {
        return Math.pow(operand1, operand2);
    }
}
