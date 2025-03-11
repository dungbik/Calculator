package org.example.service;

import org.example.constant.OperatorType;

import java.util.Objects;
import java.util.Scanner;

public class Parser {

    private final static Scanner sc = new Scanner(System.in);

    /**
     * 올바른 피연산자를 입력받아서 반환한다.
     * @return 입력받은 피연산자
     */
    public Number getValidNumber(String prompt) {
        Number number;

        System.out.print(prompt);
        while (true) {
            try {
                number = sc.nextDouble();
                break;
            } catch (Exception ignored) {
            } finally {
                sc.nextLine();
            }
            System.out.print("숫자를 다시 입력해주세요. ");
        }

        return number;
    }

    /**
     * 정해진 연산자만 입력받아서 반환한다.
     * @return 입력받은 연산자
     */
    public OperatorType getValidOperator() {
        String input;
        OperatorType operator;

        System.out.print("연산자를 입력해주세요. ");
        while (true) {
            try {
                input = sc.nextLine();
                if ((operator = OperatorType.parse(input)) != null) {
                    break;
                }
            } catch (Exception ignored) {}
            System.out.print("연산자를 다시 입력해주세요. ");
        }

        return operator;
    }

    /**
     * 종료 여부를 입력받아서 참, 거짓으로 반환한다.
     * @return 종료 여부
     */
    public String getCommand(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }
}
