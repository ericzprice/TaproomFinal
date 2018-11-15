package taproom.jetty.server;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;

public class EmbeddedJettyWebXmlConfigurationMain {

	public static void main(String[] args) throws Exception {

		
		WebAppContext ctx = new WebAppContext();
		
		        
		
		
		/*org.eclipse.jetty.webapp.Configuration.ClassList classlist = org.eclipse.jetty.webapp.Configuration.ClassList.setServerDefault(server);
	      classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration", "org.eclipse.jetty.plus.webapp.EnvConfiguration", "org.eclipse.jetty.plus.webapp.PlusConfiguration");
	      classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", "org.eclipse.jetty.annotations.AnnotationConfiguration");
*/
		Server server = new Server(8085);
		 /*ServerConnector connector = new ServerConnector(server);
		  connector.setPort(9999);
		  HttpConfiguration https = new HttpConfiguration();
		  https.addCustomizer(new SecureRequestCustomizer());
		  SslContextFactory sslContextFactory = new SslContextFactory();
		 sslContextFactory.setKeyStorePath(EmbeddedJettyWebXmlConfigurationMain.class.getResource(
		         "/keystore.jks").toExternalForm());
		 sslContextFactory.setKeyStorePassword("123456");
		 sslContextFactory.setKeyManagerPassword("123456");
		 ServerConnector sslConnector = new ServerConnector(server,
		         new SslConnectionFactory(sslContextFactory, "http/1.1"),
		         new HttpConnectionFactory(https));
		 sslConnector.setPort(9998);
		 server.setConnectors(new Connector[] { connector, sslConnector });
		// Handler for multiple web apps
*/		//HandlerCollection handlers = new HandlerCollection();

		// Creating the first web application context
		WebAppContext webapp1 = new WebAppContext();
		webapp1.setResourceBase("src");
		//ctx.setResourceBase("src/main/webapp");
		
        webapp1.setContextPath("/Taproom");
		webapp1.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",".*/[^/]*jstl.*\\.jar$");
		//webapp1.setContextPath("/");
		//webapp1.setDefaultsDescriptor("src/main/webdefault/webdefault.xml");
		webapp1.setWar("/Users/ericprice/eclipse-workspace2/Taproom/target/Taproom-0.0.1-SNAPSHOT.war");
		server.setHandler(webapp1);
		// Adding the handlers to the server
		//server.setHandler(handlers);

		// Creating the second web application context
		/*WebAppContext webapp2 = new WebAppContext();
		webapp2.setResourceBase("src/main/webapp2");
		webapp2.setContextPath("/webapp2");
		webapp2.setDefaultsDescriptor("src/main/webdefault/webdefault.xml");
		handlers.addHandler(webapp2);*/


		// Starting the Server
		server.start();
		System.out.println("Started!");
		server.join();

	}
}
