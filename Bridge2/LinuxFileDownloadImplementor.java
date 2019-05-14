/**
 * Describe class <code>LinuxFileDownloadImplementor</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class LinuxFileDownloadImplementor implements FileDownloadImplementor {
  @Override
  public Object downloadFile(String path) {
    return new Object();
  }

  @Override
  public boolean storeFile(Object object) {
    System.out.println("File downloaded successfully in LINUX !!");
    return true;
  }
}
