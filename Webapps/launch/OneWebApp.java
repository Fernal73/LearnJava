package launch;

//  ========================================================================
//  Copyright (c) 1995-2019 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//
import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.logging.Logger;
import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public final class OneWebApp {
  private static final Logger LOGGER =
      Logger.getLogger(OneWebApp.class.getName());

  private OneWebApp() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }

  @SuppressWarnings({"PMD.AvoidCatchingGenericException", "PMD.LawOfDemeter"})
  public static void main(String[] args) {
    try {
      // Create a basic jetty server object that will listen on port 8080.
      // Note that if you set this to port 0 then a randomly available port
      // will be assigned that you can either look in the logs for the port,
      // or programmatically obtain it for use in test cases.
      Server server = new Server(8080);

      // Setup JMX
      MBeanContainer mbContainer =
          new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
      server.addBean(mbContainer);

      // The WebAppContext is the entity that controls the environment in
      // which a web application lives and breathes. In this example the
      // context path is being set to "/" so it is suitable for serving root
      // context requests and then we see it setting the location of the war.
      // A whole host of other configurations are available, ranging from
      // configuring to support annotation scanning in the webapp (through
      // PlusConfiguration) to choosing where the webapp will unpack itself.
      WebAppContext webapp = new WebAppContext();
      webapp.setContextPath("/Webapp");

      File warFile = new File("dist/Webapps-2.0.0.war");
      webapp.setWar(warFile.getAbsolutePath());

      // A WebAppContext is a ContextHandler as well so it needs to be set to
      // the server so it is aware of where to
      // send the appropriate requests.
      server.setHandler(webapp);

      // Start things up!
      server.start();

      server.dumpStdErr();

      // The use of server.join() the will make the current thread join and
      // wait until the server is done executing.
      // See
      // http://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html#join()
      server.join();
    } catch (InterruptedException ie) {
      Thread.currentThread().interrupt();
      LOGGER.severe(ie.getMessage().replaceAll("[\r\n]",""));

    } catch (Exception e) {
      LOGGER.severe(e.getMessage().replaceAll("[\r\n]",""));
    }
  }
}
