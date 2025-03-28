package toDo_expressaoMatematica;

import java.util.Scanner;
import java.util.Stack;

public class Solucao {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String expression = scanner.nextLine();

		Stack<Integer> values = new Stack<>();

		Stack<Character> operators = new Stack<>();

		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);

			if (Character.isDigit(c)) {
				int num = 0;

				while (i < expression.length() && Character.isDigit(expression.charAt(i)))
					num = num * 10 + (expression.charAt(i++) - '0');
				i--;
				values.push(num);
			}

			else if (c == '(')
				operators.push(c);

			else if (c == ')') {
				while (operators.peek() != '(')
					values.push(applyOperator(operators.pop(), values.pop(), values.pop()));

				operators.pop();
			}

			else if (c == '+' || c == '-' || c == '*' || c == '/') {
				while (!operators.isEmpty() && hasPrecedence(c, operators.peek()))
					values.push(applyOperator(operators.pop(), values.pop(), values.pop()));

				operators.push(c);
			}
		}

		while (!operators.isEmpty())
			values.push(applyOperator(operators.pop(), values.pop(), values.pop()));

		scanner.close();

		System.out.println(values.pop());

	}

	public static boolean hasPrecedence(char op1, char op2) {
		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
			return true;

		return false;
	}

	public static int applyOperator(char op, int b, int a) {
		switch (op) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			return a / b;
		}

		return 0;
	}

}