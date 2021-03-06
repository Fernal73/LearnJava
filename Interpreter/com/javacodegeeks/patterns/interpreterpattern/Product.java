package com.javacodegeeks.patterns.interpreterpattern;

@SuppressWarnings("PMD.DataClass")
public class Product implements Expression {
  private final Expression leftExpression;
  private final Expression rightExpression;

  public Product(Expression leftExpression, Expression rightExpression) {
    this.leftExpression = leftExpression;
    this.rightExpression = rightExpression;
  }

  @Override
  public int interpret() {
    return leftExpression.interpret() * rightExpression.interpret();
  }

  public Expression getLeftExpression() {
    return leftExpression;
  }

  public Expression getRightExpression() {
    return rightExpression;
  }

  @Override
  public String toString() {
    return leftExpression + " * " + rightExpression;
  }
}
