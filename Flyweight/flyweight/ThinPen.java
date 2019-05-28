package flyweight;

public class ThinPen implements Pen {
  final BrushSize brushSize = BrushSize.THIN;
  private String color = null;

  /**
   * Describe <code>setColor</code> method here.
   *
   * @param color a <code>String</code> value
   */
  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public void draw(String content) {
    System.out.println("Drawing THIN content in color : " + color);
  }
}
