package flyweight;

/**
 * Describe class <code>MediumPen</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class MediumPen implements Pen {
  final BrushSize brushSize = BrushSize.MEDIUM;
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
    System.out.println("Drawing MEDIUM content in color : " + color);
  }
}
