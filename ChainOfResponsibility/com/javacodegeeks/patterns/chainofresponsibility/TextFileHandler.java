package com.javacodegeeks.patterns.chainofresponsibility;
/**
 * Describe class <code>TextFileHandler</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class TextFileHandler implements Handler {
  private Handler handler;
  private final String handlerName;

  /**
   * Creates a new <code>TextFileHandler</code> instance.
   *
   * @param handlerName a <code>String</code> value
   */
  public TextFileHandler(String handlerName) {
    this.handlerName = handlerName;
  }

  @Override
  public void setHandler(Handler handler) {
    this.handler = handler;
  }

  @SuppressWarnings({"PMD.LawOfDemeter", "PMD.SystemPrintln"})
  @Override
  public void process(File file) {
    if ("text".equals(file.getFileType())) {
      System.out.println("Process and saving text file... by " + handlerName);
      return;
    }
    if (handler != null) {
      System.out.println(handlerName + " fowards request to " + handler.getHandlerName());
      handler.process(file);
      return;
    }
    System.out.println("File not supported");
  }

  @Override
  public String getHandlerName() {
    return handlerName;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "TextFileHandler(handler=" + this.handler + ", handlerName=" + this.getHandlerName() + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof TextFileHandler)) return false;
    TextFileHandler other = (TextFileHandler) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$handler = this.handler;
    Object other$handler = other.handler;
    if (this$handler == null ? other$handler != null : !this$handler.equals(other$handler)) return false;
    Object this$handlerName = this.getHandlerName();
    Object other$handlerName = other.getHandlerName();
    if (this$handlerName == null ? other$handlerName != null : !this$handlerName.equals(other$handlerName)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof TextFileHandler;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $handler = this.handler;
    result = result * PRIME + ($handler == null ? 43 : $handler.hashCode());
    Object $handlerName = this.getHandlerName();
    result = result * PRIME + ($handlerName == null ? 43 : $handlerName.hashCode());
    return result;
  }
}
