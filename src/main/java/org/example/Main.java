package org.example;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    private final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        do {
            System.out.print("첫 번째 숫자를 입력해주세요. ");
            int operand1 = getValidOperand();

            System.out.print("두 번째 숫자를 입력해주세요. ");
            int operand2 = getValidOperand();

            System.out.print("연산자를 입력해주세요. ");
            char operation = getValidOperation();

            Integer result = eval(operand1, operand2, operation);
            System.out.printf("%d %s %d = %d%n", operand1, operation, operand2, result);

        } while (!checkExit());
    }

    /**
     * 올바른 피연산자를 입력받아서 반환한다.
     * @return 입력받은 피연산자
     */
    private static int getValidOperand() {
        int number;

        while (true) {
            try {
                number = sc.nextInt();
                if (number >= 0) {
                    break;
                }
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
    private static char getValidOperation() {
        String input;
        
        while (true) {
            try {
                input = sc.nextLine();
                if (isValidOperation(input)) {
                    break;
                }
            } catch (Exception e) {
                sc.next(); // Clear EOL input
            }
            System.out.print("연산자를 다시 입력해주세요. ");
        }

        return input.charAt(0);
    }

    /**
     * 입력받은 문자열이 올바른 연산자인지 확인한다.
     * @param operation 연산자
     * @return 올바른 연산자인지 여부
     */
    private static boolean isValidOperation(String operation) {
        return operation.length() == 1 
                && (Objects.equals(operation, "+") || Objects.equals(operation, "-") 
                || Objects.equals(operation, "*") || Objects.equals(operation, "/"));
    }

    /**
     * 피연산자 2개와 연산자를 계산하여 결과를 반환한다.
     * @param operand1 피연산자1
     * @param operand2 피연산자2
     * @param operation 연산자
     * @return 계산한 결과
     */
    private static Integer eval(int operand1, int operand2, char operation) {
        return switch (operation) {
            case '+' -> operand1 + operand2;
            case '-' -> operand1 - operand2;
            case '*' -> operand1 * operand2;
            case '/' -> {
                if (operand2 == 0) {
                    yield null;
                }
                yield operand1 / operand2;
            }
            default -> throw new RuntimeException("Unexpected operation: " + operation);
        };
    }

    private static boolean checkExit() {
        try {
            System.out.print("종료하시려면 exit를 입력해주세요. ");
            String input = sc.nextLine();
            if (Objects.equals(input, "exit")) {
                return true;
            }
        } catch (Exception e) {
            sc.next(); // Clear EOL input
        }
        return false;
    }
}
