package org.example;

import org.example.constant.EvalHistory;
import org.example.constant.Operator;

public class Main {

    public static void main(String[] args) {
        Parser parser = new Parser();
        Calculator<Number> calc = new Calculator<>();

        do {
            Number operand1 = parser.getValidOperand("첫 번째 숫자를 입력해주세요. ");
            Number operand2 = parser.getValidOperand("두 번째 숫자를 입력해주세요. ");
            Operator operator = parser.getValidOperator();

            Number result = calc.eval(operand1, operand2, operator);
            calc.addHistory(new EvalHistory(operand1, operand2, operator, result));

            // 계산 기록이 10개보다 많을 때 가장 처음 기록을 삭제한다.
            if (calc.getHistory().size() > 10) {
                calc.removeFirstHistory();
            }

            System.out.printf("%s %s %s = %s%n",
                    stripTrailingZeros(operand1), operator.getSymbol(), stripTrailingZeros(operand2), stripTrailingZeros(result));
        } while (!parser.checkExit());
    }

    private static String stripTrailingZeros(Number number) {
        return String.valueOf(number)
                .replaceAll("\\.0+$", "")
                .replaceAll("(\\.\\d*?)0+$", "$1");
    }
}
