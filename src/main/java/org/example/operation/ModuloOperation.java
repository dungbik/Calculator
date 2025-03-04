package org.example.operation;

import org.example.constant.Operator;
import org.example.operation.annotation.Operation;

@Operation(operator = Operator.MODULO)
public class ModuloOperation implements AbstractOperation {

    @Override
    public Double operate(double operand1, double operand2) {
        if (operand2 == 0) {
            throw new ArithmeticException("Divide by zero");
        }
        return operand1 % operand2;
    }
}
