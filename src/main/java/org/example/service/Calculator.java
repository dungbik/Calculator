package org.example.service;

import org.example.operation.AbstractOperation;
import org.example.operation.annotation.Operation;
import org.example.util.NumberUtil;
import org.example.vo.EvalHistory;
import org.example.constant.Operator;
import org.reflections.Reflections;

import java.util.*;

public class Calculator <T extends Number> {

    private final Map<Operator, AbstractOperation> operations = new HashMap<>();
    private final Deque<EvalHistory> evalHistory = new ArrayDeque<>();

    public Calculator() {
        loadOperations();
    }

    private void loadOperations() {
        Reflections reflections = new Reflections("org.example.operation");
        reflections.getTypesAnnotatedWith(Operation.class).forEach(cls -> {
            Operation annotation = cls.getAnnotation(Operation.class);
            try {
                AbstractOperation operation = (AbstractOperation) cls.getDeclaredConstructor().newInstance();
                this.operations.put(annotation.operator(), operation);
            } catch (Exception e) {
                System.err.printf("%s could not be instantiated. Reason: %s%n", cls.getName(), e.getMessage());
            }
        });
    }

    /**
     * 피연산자 2개와 연산자를 계산하여 결과를 반환한다.
     *
     * @param operand1 피연산자1
     * @param operand2 피연산자2
     * @param operator 연산자
     * @return 계산한 결과
     */
    public Double eval(T operand1, T operand2, Operator operator) {
        try {
            AbstractOperation operation = this.operations.get(operator);
            if (operation == null) {
                throw new IllegalArgumentException("Operator not supported: " + operator);
            }

            return operation.operate(operand1.doubleValue(), operand2.doubleValue());
        } catch (Exception e) {
            String operand1Str = NumberUtil.stripTrailingZeros(operand1);
            String operand2Str = NumberUtil.stripTrailingZeros(operand2);
            System.err.printf("'%s %s %s' could not be evaluated. Reason: %s%n",
                    operand1Str, operator.getSymbol(), operand2Str, e.getMessage());
            return null;
        }
    }


    public Deque<EvalHistory> getEvalHistory() {
        return evalHistory;
    }

    public void addHistory(EvalHistory history) {
        this.evalHistory.add(history);
    }

    /**
     * 가장 먼저 저장된 계산 기록을 삭제한다.
     */
    public void removeFirstHistory() {
        if (!evalHistory.isEmpty()) {
            evalHistory.removeFirst();
        }
    }


    /**
     * threshold 보다 큰 결과를 가진 계산 기록을 반환한다.
     *
     * @param threshold 기준값
     * @return 기준값보다 큰 결과를 가진 계산 기록들
     */
    public List<EvalHistory> getHistoryGreaterThan(T threshold) {
        return this.evalHistory.stream()
                .filter(h -> h.result().doubleValue() > threshold.doubleValue())
                .toList();
    }
}
