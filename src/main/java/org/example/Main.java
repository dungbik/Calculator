package org.example;

import org.example.service.Calculator;
import org.example.service.Parser;
import org.example.vo.EvalHistory;
import org.example.constant.Operator;

public class Main {

    public static void main(String[] args) {
        Parser parser = new Parser();
        Calculator<Number> calc = new Calculator<>();

        do {
            Number operand1 = parser.getValidNumber("첫 번째 숫자를 입력해주세요. ");
            Number operand2 = parser.getValidNumber("두 번째 숫자를 입력해주세요. ");
            Operator operator = parser.getValidOperator();

            Number result = calc.eval(operand1, operand2, operator);
            EvalHistory evalHistory = new EvalHistory(operand1, operand2, operator, result);
            calc.addHistory(evalHistory);

            // 계산 기록이 10개보다 많을 때 가장 처음 기록을 삭제한다.
            if (calc.getHistory().size() > 10) {
                calc.removeFirstHistory();
            }

            System.out.println(evalHistory);
        } while (!parser.checkExit());

        do {
            Number condNum = parser.getValidNumber("N보다 큰 결과를 가진 계산 기록을 출력합니다. N을 입력해주세요. ");
            calc.getHistoryGreaterThan(condNum).forEach(System.out::println);
        } while (!parser.checkExit());

    }
}
