package org.example;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private final List<EvalHistory> history = new ArrayList<>();

    /**
     * 피연산자 2개와 연산자를 계산하여 결과를 반환한다.
     * @param operand1 피연산자1
     * @param operand2 피연산자2
     * @param operator 연산자
     * @return 계산한 결과
     */
    public Integer eval(int operand1, int operand2, char operator) {
        return switch (operator) {
            case '+' -> operand1 + operand2;
            case '-' -> operand1 - operand2;
            case '*' -> operand1 * operand2;
            case '/' -> {
                if (operand2 == 0) {
                    yield null;
                }
                yield operand1 / operand2;
            }
            default -> throw new RuntimeException("Unexpected operator: " + operator);
        };
    }

    public List<EvalHistory> getHistory() {
        return history;
    }

    public void addHistory(EvalHistory history) {
        this.history.add(history);
    }

    /**
     * 가장 먼저 저장된 계산 기록을 삭제한다.
     */
    public void removeFirstHistory() {
        this.history.removeFirst();
    }
}
