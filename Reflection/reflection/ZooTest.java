package reflection;

import java.io.IOException;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public final class ZooTest {

  private static final Logger LOGGER =
      Logger.getLogger(ZooTest.class.getName());

  private ZooTest() {
    throw new IllegalStateException("Private constructor invoked for class: "
                                    + getClass());
  }

  public static void main(String[] args) {
    Animal panda1 =
        new Animal("Tian Tian", "male", "Ailuropoda melanoleuca", 271);
    Animal panda2 =
        new Animal("Mei Xiang", "female", "Ailuropoda melanoleuca", 221);
    Zoo national = new Zoo("National Zoological Park", "Washington, D.C.");

    national.add(panda1);
    national.add(panda2);

    try {
      XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
      Document d = XmlSerializer.serializeObject(national);
      out.output(d, System.out);
    } catch (IllegalAccessException | IOException ex) {
      LOGGER.severe(ex.getMessage());
    }
  }
}
