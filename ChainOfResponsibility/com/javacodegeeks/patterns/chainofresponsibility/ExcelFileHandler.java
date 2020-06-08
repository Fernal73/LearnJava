package com.javacodegeeks.patterns.chainofresponsibility;
/**
 * Describe class <code>ExcelFileHandler</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class ExcelFileHandler implements Handler {
  private Handler handler;
  private final String handlerName;

  /**
   * Creates a new <code>ExcelFileHandler</code> instance.
   *
   * @param handlerName a <code>String</code> value
   */
  public ExcelFileHandler(String handlerName) {
    this.handlerName = handlerName;
  }

  @Override
  public void setHandler(Handler handler) {
    this.handler = handler;
  }

  @SuppressWarnings({"PMD.LawOfDemeter", "PMD.SystemPrintln"})
  @Override
  public void process(File file) {
    if ("excel".equals(file.getFileType())) {
      System.out.println("Process and saving excel file... by " + handlerName);
      return;
    }
    if (handler != null) {
      System.out.println(handlerName + " forwards request to " + handler.getHandlerName());
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
    return "ExcelFileHandler(handler=" + this.handler + ", handlerName=" + this.getHandlerName() + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof ExcelFileHandler)) return false;
    ExcelFileHandler other = (ExcelFileHandler) o;
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
    return other instanceof ExcelFileHandler;
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
