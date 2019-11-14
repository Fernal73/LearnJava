package refactoringguru.visitor.example.shapes;

import refactoringguru.visitor.example.visitor.Visitor;

public class Rectangle implements Shape {
  private final int id;
  private final int x;
  private final int y;
  private final int width;
  private final int height;

  public Rectangle(int id, int x, int y, int width, int height) {
    this.id = id;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  @Override
  public String accept(Visitor visitor) {
    return visitor.visitRectangle(this);
  }

  @Override
  public void move(int xcoord, int ycoord) {
    // move shape
  }

  @Override
  public void draw() {
    // draw shape
  }

  public int getId() {
    return id;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}
