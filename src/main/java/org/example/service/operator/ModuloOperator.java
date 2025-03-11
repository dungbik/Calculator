package org.example.service.operator;

import org.example.constant.OperatorType;
import org.example.annotation.Operation;
import org.example.exception.DivideByZeroException;
import org.example.service.AbstractOperator;

@Operation(operator = OperatorType.MODULO)
public class ModuloOperator implements AbstractOperator {

    @Override
    public Double operate(double operand1, double operand2) {
        if (operand2 == 0) {
            throw new DivideByZeroException();
        }
        return operand1 % operand2;
    }
}
