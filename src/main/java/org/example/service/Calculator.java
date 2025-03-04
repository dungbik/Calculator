package org.example.service;

import org.example.operation.AbstractOperation;
import org.example.operation.annotation.Operation;
import org.example.util.NumberUtil;
import org.example.vo.EvalHistory;
import org.example.constant.Operator;
import org.reflections.Reflections;

import java.util.*;

public class Calculator <T extends Number> {

    private final Map<Operator, AbstractOperation> operations;

    private final List<EvalHistory> history = new ArrayList<>();

    public Calculator() {
        this.operations = new HashMap<>();

        Reflections reflections = new Reflections("org.example.operation");
        Set<Class<?>> operationClasses = reflections.getTypesAnnotatedWith(Operation.class);

        for (Class<?> cls : operationClasses) {
            Operation op = cls.getAnnotation(Operation.class);
            try {
                this.operations.put(op.operator(), (AbstractOperation) cls.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                System.err.printf("%s could not be instantiated.\n", cls.getName());
            }
        }
    }

    /**
     * 피연산자 2개와 연산자를 계산하여 결과를 반환한다.
     * @param operand1 피연산자1
     * @param operand2 피연산자2
     * @param operator 연산자
     * @return 계산한 결과
     */
    public Double eval(T operand1, T operand2, Operator operator) {
        try {
            return this.operations.get(operator).operate(operand1.doubleValue(), operand2.doubleValue());
        } catch (Exception e) {
            System.err.printf("'%s %s %s' could not be evaluated. reason: %s\n",
                    NumberUtil.stripTrailingZeros(operand1), operator.getSymbol(), NumberUtil.stripTrailingZeros(operand2), e.getMessage());
            return null;
        }
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

    /**
     * number 보다 큰 결과를 가진 계산 기록을 반환한다.
     * @param number 실수
     * @return number보다 큰 결과를 가진 계산 기록들
     */
    public List<EvalHistory> getHistoryGreaterThan(T number) {
        return this.history.stream()
                .filter(h -> h.result().doubleValue() > number.doubleValue())
                .toList();
    }
}
