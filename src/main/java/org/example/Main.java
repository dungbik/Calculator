package org.example;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    private final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        do {
            Calculator calc = new Calculator();

            System.out.print("첫 번째 숫자를 입력해주세요. ");
            int operand1 = getValidOperand();

            System.out.print("두 번째 숫자를 입력해주세요. ");
            int operand2 = getValidOperand();

            System.out.print("연산자를 입력해주세요. ");
            Operator operator = getValidOperator();

            Integer result = calc.eval(operand1, operand2, operator);

            calc.addHistory(new EvalHistory(operand1, operand2, operator, result));

            // 계산 기록이 10개보다 많을 때 가장 처음 기록을 삭제한다.
            if (calc.getHistory().size() > 10) {
                calc.removeFirstHistory();
            }

            System.out.printf("%d %s %d = %d%n", operand1, operator.getSymbol(), operand2, result);

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
    private static Operator getValidOperator() {
        String input;
        Operator operator;
        
        while (true) {
            try {
                input = sc.nextLine();
                if ((operator = Operator.parse(input)) != null) {
                    break;
                }
            } catch (Exception e) {
                sc.next(); // Clear EOL input
            }
            System.out.print("연산자를 다시 입력해주세요. ");
        }

        return operator;
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
