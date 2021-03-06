package taproom.jetty.server;
import taproom.controller.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.tomcat.util.scan.StandardJarScanner;
import org.eclipse.jetty.apache.jsp.JettyJasperInitializer;
import org.eclipse.jetty.jsp.JettyJspServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.component.AbstractLifeCycle;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.Configuration;

/**
 * Example of using JSP's with embedded jetty and using a
 * lighter-weight ServletContextHandler instead of a WebAppContext. 
 * 
 * This example is somewhat odd in that it uses custom tag libs which reside
 * in a WEB-INF directory, even though WEB-INF is not meaningful to
 * a ServletContextHandler. This just shows that once we have
 * properly initialized the jsp engine, you can even use this type of
 * custom taglib, even if you don't have a full-fledged webapp.
 * 
 */
public class ServerStarter
{
    // Resource path pointing to where the WEBROOT is
    private static final String WEBROOT_INDEX = "./WebContent/";///webroot: It is a particular folder on the web server but 
//    can be a different folder for different situations. For web pages visible on the web the root folder is the one that the domain name itself points to.
//If your hosting has multiple domains then each domain will have its own separate folder that is the web root for that domain.
//For any scripts that you have running outside of a web page the root will be the top level folder of your account/
    
    /**
     * JspStarter for embedded ServletContextHandlers
     * This is added as a bean that is a jetty LifeCycle on the ServletContextHandler.
     * A bean is a reusable software component that encapsulates many objects into one object, so we can access this object from multiple places. 
     * Moreover, it provides the easy maintenance. This bean's doStart method will be called as the ServletContextHandler starts,
     * and will call the ServletContainerInitializer for the jsp engine. It should have a no-arg constructor. It should be Serializable.It should 
     * provide methods to set and get the values of the properties, known as getter and setter methods.
     */
    public static class JspStarter extends AbstractLifeCycle implements ServletContextHandler.ServletContainerInitializerCaller
    {
        JettyJasperInitializer sci;
        ServletContextHandler context;
        
        public JspStarter (ServletContextHandler context)
//        ServletContextHandler is from jetty server
//        JspStarter is the constructor above
        {
            this.sci = new JettyJasperInitializer();
            this.context = context;
            this.context.setAttribute("org.apache.tomcat.JarScanner", new StandardJarScanner());
        }

        @Override
        protected void doStart() throws Exception
//        The throw keyword in Java is used to explicitly throw an exception from a method or any block of code. 
//        We can throw either checked or unchecked exception. The throw keyword is mainly used to throw custom exceptions.
        {
            ClassLoader old = Thread.currentThread().getContextClassLoader();
            Thread.currentThread().setContextClassLoader(context.getClassLoader());
            try
            {
                sci.onStartup(null, context.getServletContext());   
                super.doStart();
            }
            finally
            {
                Thread.currentThread().setContextClassLoader(old);
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        int port = 8097;
       // LoggingUtil.config();

        ServerStarter main = new ServerStarter(port);
        main.start();
        main.waitForInterrupt();
    }

    private static final Logger LOG = Logger.getLogger(ServerStarter.class.getName());

    private int port;
    private Server server;

    public ServerStarter(int port)
    {
        this.port = port;
    }

    public void start() throws Exception
    {
        server = new Server();//getClass().getProtectionDomain().getCodeSource().getLocation();
   // Path f=new Path(".");
    //  System.out.println(f.);
        // Define ServerConnector
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.addConnector(connector);
    
        // Add annotation scanning (for WebAppContexts)
        Configuration.ClassList classlist = Configuration.ClassList
                .setServerDefault( server );
        classlist.addBefore(
                "org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
                "org.eclipse.jetty.annotations.AnnotationConfiguration" );

        // Base URI for servlet context
       // URI baseUri = getWebRootResourceUri();
       // System.out.println("Base URI: " + baseUri);
       // LOG.info("Base URI: " + baseUri);
        
        // Create Servlet context
        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.setContextPath("/Taproom");
       servletContextHandler.setResourceBase("WebContent");

        // Since this is a ServletContextHandler we must manually configure JSP support.
        enableEmbeddedJspSupport(servletContextHandler);
    
        // Add Application Servlets for Jetty
        servletContextHandler.addServlet(DisplayLocationsServlet.class, "/DisplayLocationsServlet");
        servletContextHandler.addServlet(DisplayBeersServlet.class, "/DisplayBeersServlet");
        servletContextHandler.addServlet(PurchaseServlet.class, "/PurchaseServlet");
        ServletHolder jerseyServlet = servletContextHandler.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/webapi/*");
        jerseyServlet.setInitOrder(0);
           
           jerseyServlet.setInitParameter(
                   "jersey.config.server.provider.packages",
                   "taproom.model.service");
        // Create Example of mapping jsp to path spec
       // ServletHolder holderAltMapping = new ServletHolder();
       // holderAltMapping.setName("displayLocations.jsp");
      // holderAltMapping.setForcedPath("displayLocations.jsp");
       // servletContextHandler.addServlet(holderAltMapping, "/test/foo/");
       // ServletHolder holderAltMapping2 = new ServletHolder();
       // holderAltMapping.setName("displayBeers.jsp");
       // holderAltMapping.setForcedPath("displayBeers.jsp");
       // servletContextHandler.addServlet(holderAltMapping2 , "/test/foo2/");
        // Default Servlet (always last, always named "default")
        ServletHolder holderDefault = new ServletHolder("default", DefaultServlet.class);
       // holderDefault.setInitParameter("resourceBase", baseUri.toASCIIString());
        holderDefault.setInitParameter("dirAllowed", "true");
        servletContextHandler.addServlet(holderDefault, "/");
        server.setHandler(servletContextHandler);

        
     //   ServerConnector connector = new ServerConnector(server);
      //connector.setPort(9999);
        HttpConfiguration https = new HttpConfiguration();
        https.addCustomizer(new SecureRequestCustomizer());
        SslContextFactory sslContextFactory = new SslContextFactory();
       sslContextFactory.setKeyStorePath(
               ".keystore");
       sslContextFactory.setKeyStorePassword("password");
       sslContextFactory.setKeyManagerPassword("password");
       ServerConnector sslConnector = new ServerConnector(server,
               new SslConnectionFactory(sslContextFactory, "http/1.1"),
               new HttpConnectionFactory(https));
       sslConnector.setPort(9997);
       server.setConnectors(new Connector[] { connector, sslConnector });
        // Start Server
        server.start();

        // Show server state
        if (LOG.isLoggable(Level.FINE))
        {
            LOG.fine(server.dump());
        }
    }
    
    /**
     * Setup JSP Support for ServletContextHandlers.
     * <p>
     *   NOTE: This is not required or appropriate if using a WebAppContext.
     * </p>
     *
     * @param servletContextHandler the ServletContextHandler to configure
     * @throws IOException if unable to configure
     */
    private void enableEmbeddedJspSupport(ServletContextHandler servletContextHandler) throws IOException
    {
        // Establish Scratch directory for the servlet context (used by JSP compilation)
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        File scratchDir = new File(tempDir.toString(), "embedded-jetty-jsp");
//        creating the jetty directory
    
        if (!scratchDir.exists())
        {
            if (!scratchDir.mkdirs())
            {
                throw new IOException("Unable to create scratch directory: " + scratchDir);
            }
        }
        servletContextHandler.setAttribute("javax.servlet.context.tempdir", scratchDir);
    
        // Set Classloader of Context to be sane (needed for JSTL)
        // JSP requires a non-System classloader, this simply wraps the
        // embedded System classloader in a way that makes it suitable
        // for JSP to use
        ClassLoader jspClassLoader = new URLClassLoader(new URL[0], this.getClass().getClassLoader());
        servletContextHandler.setClassLoader(jspClassLoader);
        
        // Manually call JettyJasperInitializer on context startup
        servletContextHandler.addBean(new JspStarter(servletContextHandler));
        
        // Create / Register JSP Servlet (must be named "jsp" per spec)
        ServletHolder holderJsp = new ServletHolder("jsp", JettyJspServlet.class);
        holderJsp.setInitOrder(0);
        holderJsp.setInitParameter("logVerbosityLevel", "DEBUG");
        holderJsp.setInitParameter("fork", "false");
        holderJsp.setInitParameter("xpoweredBy", "false");
        holderJsp.setInitParameter("compilerTargetVM", "1.8");
        holderJsp.setInitParameter("compilerSourceVM", "1.8");
        holderJsp.setInitParameter("keepgenerated", "true");
        servletContextHandler.addServlet(holderJsp, "*.jsp");
    }
    
    private URI getWebRootResourceUri() throws FileNotFoundException, URISyntaxException
    {
        URL indexUri=null;
		
		//	indexUri = this.getClass().
		//	System.out.println("."+ indexUri);
		
        if (indexUri == null)
        {
            //throw new FileNotFoundException("Unable to find resource " + WEBROOT_INDEX);
        }
        // Points to wherever /webroot/ (the resource) is
        return indexUri.toURI();
    }

    public void stop() throws Exception
    {
        server.stop();
    }

    /**
     * Cause server to keep running until it receives a Interrupt.
     * <p>
     * Interrupt Signal, or SIGINT (Unix Signal), is typically seen as a result of a kill -TERM {pid} or Ctrl+C
     * @throws InterruptedException if interrupted
     */
    public void waitForInterrupt() throws InterruptedException
    {
        server.join();
    }
}
