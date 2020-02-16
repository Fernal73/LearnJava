package lexpr;

import java.util.Stack;

/** Sample "calculator". */
public class Evaluator extends LExprBaseListener {
  Stack<Integer> stack = new Stack<>();

  @Override
  public void exitMult(LExprParser.MultContext ctx) {
    int right = stack.pop();
    int left = stack.pop();
    stack.push(left * right);
  }

  @Override
  public void exitAdd(LExprParser.AddContext ctx) {
    int right = stack.pop();
    int left = stack.pop();
    stack.push(left + right);
  }

  @Override
  public void exitMod(LExprParser.ModContext ctx) {
    int right = stack.pop();
    int left = stack.pop();
    stack.push(left % right);
  }

  @Override
  public void exitSub(LExprParser.SubContext ctx) {
    int right = stack.pop();
    int left = stack.pop();
    stack.push(left - right);
  }

  @Override
  public void exitDiv(LExprParser.DivContext ctx) {
    int right = stack.pop();
    int left = stack.pop();
    stack.push(left / right);
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public void exitInt(LExprParser.IntContext ctx) {
    stack.push(Integer.valueOf(ctx.INT().getText()));
  }
}