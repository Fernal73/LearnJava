package design.composite;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Describe class <code>PropertyLoader</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public final class PropertyLoader {
  private static final boolean THROW_ON_LOAD_FAILURE = true;
  private static final boolean LOAD_AS_RESOURCE_BUNDLE = false;
  private static final String SUFFIX = ".properties";

  private PropertyLoader() {
    throw new IllegalStateException("Private constructor");
  }

  /**
   * An overloaded LoadProperties. A convenience overload of {@link
   * #loadProperties(String, ClassLoader)} that uses the current thread's
   * context classloader
   *
   * @param name path of property resource
   * @return Properties object
   */
  public static Properties loadProperties(final String name) {
    return loadProperties(name, Thread.currentThread().getContextClassLoader());
  }

  /**
   * * Looks up a resource named 'name' in the classpath. The resource must map
   * * to a file with .properties extention. The name is assumed to be absolute
   * * and can use either "/" or "." for package segment separation with an *
   * optional leading "/" and optional ".properties" suffix. Thus, the *
   * following names refer to the same resource: *
   *
   * <p>some.pkg.Resource * some.pkg.Resource.properties some/pkg/Resource
   * some/pkg/Resource.properties /some/pkg/Resource
   * /some/pkg/Resource.properties
   *
   * @param nome classpath resource name [may not be null] *
   * @param loader classloader through which to load the resource [null * is
   *     equivalent to the application loader]
   * @return resource converted to java.util.Properties [may be null if the
   *     resource was not found and THROW_ON_LOAD_FAILURE is false] * @throws
   *     IllegalArgumentException if the resource was not found and *
   *     THROW_ON_LOAD_FAILURE is true
   */
  public static Properties loadProperties(String nome, ClassLoader loader) {
    String name = normalizeName(nome);
    Properties result = null;
    try {
      ClassLoader cl =
          (loader == null) ? ClassLoader.getSystemClassLoader() : loader;

      if (LOAD_AS_RESOURCE_BUNDLE) {
        result = loadAsResourceBundle(cl, name);
      } else {
        result = loadAsStream(cl, name);
      }
    } catch (MissingResourceException e) {
      System.err.println("Error locating resource " + name + " : "
                         + e.getMessage());
      result = null;
    }
    if (THROW_ON_LOAD_FAILURE && result == null) {
      throw new IllegalArgumentException("could not load [" + name + "]"
                                         + " as "
                                         + (LOAD_AS_RESOURCE_BUNDLE
                                                ? "a resource bundle"
                                                : "a classloader resource"));
    }
    return result;
  }

  private static Properties loadAsStream(ClassLoader loader, String nome) {
    String name = nome.replace('.', '/');
    if (!name.endsWith(SUFFIX))
      name = name.concat(SUFFIX);
    Properties result = null;
    try (InputStream in = loader.getResourceAsStream(name)) {
      if (in != null) {
        result = new Properties();
        result.load(in);
      }
    } catch (IOException ioe) {
      System.err.println("Error reading from resource " + name + " : "
                         + ioe.getMessage());
    }
    return result;
  }

  private static Properties loadAsResourceBundle(ClassLoader loader,
                                                 String nome) {
    String name = nome.replace('/', '.');
    final ResourceBundle rb =
        ResourceBundle.getBundle(name, Locale.getDefault(), loader);
    Properties result = new Properties();
    for (Enumeration<String> keys = rb.getKeys(); keys.hasMoreElements();) {
      final String key = keys.nextElement();
      final String value = rb.getString(key);
      result.put(key, value);
    }
    return result;
  }

  private static String normalizeName(String nome) {
    if (nome == null)
      throw new IllegalArgumentException("null input: name");
    String name = nome;
    if (name.charAt(0) == '/')
      name = name.substring(1);
    if (name.endsWith(SUFFIX))
      name = name.substring(0, name.length() - SUFFIX.length());
    return name;
  }
}
