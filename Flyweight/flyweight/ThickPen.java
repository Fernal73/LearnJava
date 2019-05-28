package flyweight;

/**
 * Describe class <code>ThickPen</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class ThickPen implements Pen {
  final BrushSize brushSize = BrushSize.THICK; // intrinsic state - shareable
  private String color = null;
  // extrinsic state - supplied by client

  /**
   * Describe <code>setColor</code> method here.
   *
   * @param color a <code>String</code> value
   */
  @Override
  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public void draw(String content) {
    System.out.println("Drawing THICK content in color : " + color);
  }
}
