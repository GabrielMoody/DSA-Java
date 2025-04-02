/** 
 * @author Gabriel Waworundeng
*/

package org.gabriel.DSA;

public class Postfix{
  
  private int getPrecedence(String operand) {
    int precedence = switch (operand) {
      case "(", ")" -> 1;
      case "^" -> 2;
      case "*", "/" -> 3;
      case "+", "-" -> 4;
      default -> 0;
    };

    return precedence; 
  }

  public void infixToPostfix(String notation) {
    Stack<String> s = new Stack<>();
    String[] token = notation.split(" ");

    for(String n : token) {
      if(getPrecedence(n) == 0) {
        System.out.printf("%s ", n);
      } else if(n.equals(")")) {
        while(!s.peek().equals("(")) {
          System.out.printf("%s ", s.pop());
        }
        s.pop();
      } else if(s.isEmpty() || getPrecedence(n) < getPrecedence(s.peek()) || s.peek().equals("(") || n.equals("^")){
        s.push(n);
      } else if (getPrecedence(n) >= getPrecedence(s.peek()) && !n.equals("^")) {
        System.out.printf("%s ", s.pop());
        s.push(n);
      }
    }

    while(!s.isEmpty()) {
      System.out.printf("%s ", s.pop());
    }
  }

  private double operation(double a, double b, String operator) {
    return switch (operator) {
      case "+" -> a + b;
      case "-" -> a - b;
      case "*" -> a * b;
      case "/" -> a / b;
      case "^" -> Math.pow(a, b);
      default -> 0;
    };
  }

  public double calculate(String notation) {
    String[] token = notation.split(" ");
    Stack<String> calculation = new Stack<>();

    for(String i : token) {
      if(getPrecedence(i) == 0) {
        calculation.push(i);
      } else {
        double b = Double.parseDouble(calculation.pop());
        double a = Double.parseDouble(calculation.pop());
        double result = operation(a, b, i);
        calculation.push(Double.toString(result));
      }
    }

    return Double.parseDouble(calculation.pop());
  }
  
  public static void main(String[] args) {
    String notation = "( 20 + 11 - 5 ) * ( 8 - 2 ) / 12 / 3 / 5 / 2 ^ 2 ^ 2";
    String postfix = "20 11 + 5 - 8 2 - * 12 / 3 / 5 / 2 2 2 ^ ^ /";
    Postfix p = new Postfix();

    p.infixToPostfix(notation);
    double result = p.calculate(postfix);
    System.out.printf("Result : %.2f", result);
  }
}