/**
* @author Gabriel Waworundeng
*/

package org.gabriel.DSA;

public class Prefix{
  
  private int getPrecedence(String operand) {
    return  switch (operand) {
      case "(", ")" -> 1;
      case "^" -> 2;
      case "*", "/" -> 3;
      case "+", "-" -> 4;
      default -> 0;
    };
  }

  public void infixToPrefix(String notation) {
    Stack<String> s = new Stack<>();
    Stack<String> prefix = new Stack<>();
    String[] token = notation.split(" ");

    for(int i = token.length - 1; i >= 0; i--) {
      if(getPrecedence(token[i]) == 0) {
        prefix.push(token[i]);
      } else if (token[i].equals("(")) {
        while(!s.peek().equals(")")) {
          prefix.push(s.pop());
        }
        s.pop();
      } else if (s.isEmpty() || getPrecedence(token[i]) <= getPrecedence(s.peek()) || s.peek().equals(")")) {
        s.push(token[i]);
      } else if (getPrecedence(token[i]) > getPrecedence(s.peek()) && !s.peek().equals(")")) {
        while(getPrecedence(token[i]) > getPrecedence(s.peek()) && !s.isEmpty()) {
          prefix.push(s.pop());
        }
        s.push(token[i]);
      }
    }

    while(!s.isEmpty()) {
      prefix.push(s.pop());
    }

    prefix.print();
  }

  private double operatorCalculation(double a, double b, String operator) {
    return switch (operator) {
      case "+" -> a + b;
      case "-" -> a - b;
      case "*" -> a * b;
      case "/" -> a / b;
      case "^" -> Math.pow(a, b);
      default -> 0;
    };
  }

  public void calculate(String prefix) {
    Stack<String> calculation = new Stack<>();
    String[] token = prefix.split(" ");
    for(int i = token.length - 1; i >= 0; i--) {
      if(getPrecedence(token[i]) == 0) {
        calculation.push(token[i]);
      } else {
        double a = Double.parseDouble(calculation.pop());
        double b = Double.parseDouble(calculation.pop());
        double result = operatorCalculation(a,b, token[i]);
        calculation.push(Double.toString(result));
      }
    }

    calculation.print();
  }
  
  public static void main(String[] args) {
    String notation = "20 + 1 - 14 * 8 + ( 2 ^ 3 ) * 4 / 5 / 12 * 8 + 90";
    String prefix = "+ + - + 20 1 * 14 8 * / / * ^ 2 3 4 5 12 8 90";
    Prefix p = new Prefix(); 

    System.out.println("Prefix notation : ");
    p.infixToPrefix(notation);
    System.out.println("\nResult : ");
    p.calculate(prefix);
  }
}