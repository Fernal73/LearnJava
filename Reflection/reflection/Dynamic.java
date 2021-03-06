package reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@SuppressWarnings(
    {"PMD.BeanMembersShouldSerialize", "PMD.UseConcurrentHashMap"})
public final class Dynamic {
  private static final String HELLO = "Hello";

  private Dynamic() {
    throw new IllegalStateException("Private constructor invoked for class: "
                                    + getClass());
  }

  @SuppressWarnings({"PMD.UseConcurrentHashMap",
                     "unchecked",
                     "PMD.LawOfDemeter",
                     "PMD.SystemPrintln"})
  public static void
  main(String... args) {
    Map<Object, Object> proxyInstance =
        (Map<Object, Object>)Proxy.newProxyInstance(
            Thread.currentThread().getContextClassLoader(),
            new Class<?>[] {Map.class},
            new DynamicInvocationHandler());
    proxyInstance.put(HELLO, "world");
    System.out.println(proxyInstance.get(HELLO));

    proxyInstance = (Map<Object, Object>)Proxy.newProxyInstance(
        Thread.currentThread().getContextClassLoader(),
        new Class<?>[] {Map.class},
        new TimingDynamicInvocationHandler(new HashMap<Object, Object>()));

    CharSequence csProxyInstance = (CharSequence)Proxy.newProxyInstance(
        Thread.currentThread().getContextClassLoader(),
        new Class<?>[] {CharSequence.class},
        new TimingDynamicInvocationHandler("Hello World"));

    proxyInstance.put(HELLO, "world");
    System.out.println(proxyInstance.get(HELLO));
    csProxyInstance.length();
  }

  static class DynamicInvocationHandler implements InvocationHandler {
    private static final Logger LOGGER =
        Logger.getLogger(DynamicInvocationHandler.class.getName());

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {
      // clang-format off
      LOGGER.info(
          () -> {
            return "Invoked method: " + method.getName();
          });

      // clang-format on
      return 42;
    }
  }

  static class TimingDynamicInvocationHandler implements InvocationHandler {
    private static final Logger LOGGER =
        Logger.getLogger(TimingDynamicInvocationHandler.class.getName());

    private final Map<String, Method> methods = new HashMap<>();

    private final Object target;

    TimingDynamicInvocationHandler(Object target) {
      this.target = target;
      for (Method method: target.getClass().getDeclaredMethods()) {
        this.methods.put(method.getName(), method);
      }
    }

    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {
      long start = System.nanoTime();
      Method meth = methods.get(method.getName());
      Object result = invokeMethod(meth, args);
      long elapsed = System.nanoTime() - start;

      // clang-format off
      LOGGER.info(
          () -> {
            return String.format("Executing %s finished in %d ns", method.getName(), elapsed);
          });

      // clang-format on
      return result;
    }

    private Object invokeMethod(Method method, Object... args)
        throws IllegalAccessException, InvocationTargetException {
      return method.invoke(target, args);
    }
  }
}
