package org.example.service.operator;

import org.example.constant.OperatorType;
import org.example.annotation.Operation;
import org.example.service.AbstractOperator;

@Operation(operator = OperatorType.SUBTRACT)
public class SubtractOperator implements AbstractOperator {

    @Override
    public Double operate(double operand1, double operand2) {
        return operand1 - operand2;
    }
}
