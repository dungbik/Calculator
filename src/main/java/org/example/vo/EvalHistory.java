package org.example.vo;

import org.example.constant.Operator;
import org.example.util.NumberUtil;

public record EvalHistory(
        Number operand1,
        Number operand2,
        Operator operator,
        Number result
) {

    @Override
    public String toString() {
        return String.format("%s %s %s = %s",
                NumberUtil.stripTrailingZeros(operand1),
                operator.getSymbol(),
                NumberUtil.stripTrailingZeros(operand2),
                NumberUtil.stripTrailingZeros(result));
    }
}
