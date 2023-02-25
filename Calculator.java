public class Calculator {
    import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

    public class Main {
        static char[] operators = {'+', '-', '*', '/'};
        static List<String> numbers = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        static List<String> romanNumbers = Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");

        public static void main(String[] args) throws Exception {
            Scanner console = new Scanner(System.in);
            System.out.println(calc(console.nextLine()));
            console.close();
        }

        public static String calc(String input) throws Exception {
            isEmpty(input);
            int index = indexOfOperator(input);
            char x = input.charAt(index);
            int operator = 0;
            for (int i = 0; i < operators.length; i++) {
                if (x == operators[i]) {
                    operator = i;
                }
            }

            String operand1 = input.substring(0, index).trim();
            String operand2 = input.substring(index + 1).trim();
            isNumberInTheRange(operand1, operand2);

            int number1;
            int number2;
            int result = 0;

            if (romanNumbers.contains(operand1) && romanNumbers.contains(operand2)) {

                number1 = Integer.parseInt(numbers.get(romanNumbers.indexOf(operand1)));
                number2 = Integer.parseInt(numbers.get(romanNumbers.indexOf(operand2)));

                switch (operator) {
                    case 0:
                        result = number1 + number2;
                        break;
                    case 1:
                        result = number1 - number2;
                        break;
                    case 2:
                        result = number1 * number2;
                        break;
                    case 3:
                        result = number1 / number2;
                        break;
                }
                return toRoman(result);

            } else if (numbers.contains(operand1) && numbers.contains(operand2)) {
                number1 = Integer.parseInt(operand1);
                number2 = Integer.parseInt(operand2);

                switch (operator) {
                    case 0:
                        result = number1 + number2;
                        break;
                    case 1:
                        result = number1 - number2;
                        break;
                    case 2:
                        result = number1 * number2;
                        break;
                    case 3:
                        result = number1 / number2;
                        break;
                }
                return result + "";
            }
            return "";
        }

        public static void isEmpty(String input) throws Exception {
            if (input.isEmpty()) {
                throw new Exception("ERROR!!!Строка не введена!");
            }
        }

        public static int indexOfOperator(String str) throws Exception {
            int count = 0;
            int index = 0;
            char[] symbols = str.toCharArray();
            for (int i = 0; i < symbols.length; i++) {
                for (int j = 0; j < operators.length; j++) {
                    if (symbols[i] == operators[j]) {
                        count++;
                        index = i;
                    }
                }
            }
            if (count == 0) {
                throw new Exception("ERROR!!!Строка не является математической операцией!");
            } else if (count > 1) {
                throw new Exception("ERROR!!!Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            } else
                return index;
        }

        public static void isNumberInTheRange(String operand1, String operand2) throws Exception {
            if (!numbers.contains(operand1) && !romanNumbers.contains(operand1)) {
                throw new Exception("ERROR!!!Первый операнд не удовлетворяет условию - числа от 1 до 10 включительно!");
            }
            if (!numbers.contains(operand2) && !romanNumbers.contains(operand2)) {
                throw new Exception("ERROR!!!Второй операнд не удовлетворяет условию - числа от 1 до 10 включительно!");
            }
            if ((numbers.contains(operand1) && romanNumbers.contains(operand2)) || (numbers.contains(operand2) && romanNumbers.contains(operand1))) {
                throw new Exception("ERROR!!!Используются разные системы счисления!");
            }
        }

        public static String toRoman(int n) throws Exception {

            if( n <= 0) {
                throw new Exception("ERROR!!!В римской системе нет отрицательных чисел и 0!");
            }

            StringBuilder buf = new StringBuilder();

            RomanNumeral[] values = RomanNumeral.values();
            for (int i = values.length - 1; i >= 0; i--) {
                while (n >= values[i].weight) {
                    buf.append(values[i]);
                    n -= values[i].weight;
                }
            }
            return buf.toString();
        }
    }
}
