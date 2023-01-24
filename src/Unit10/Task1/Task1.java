package Unit10.Task1;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Task1 {

    public static void main(String[] args) throws Exception {
        Server server = new Server(1234);
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new CRUDServlet()), "/cars");
        context.addServlet(new ServletHolder(new CRUDServlet()), "/contacts");
        context.addServlet(new ServletHolder(new CRUDServlet()), "/brands");
        context.addServlet(new ServletHolder(new CRUDServlet()), "/contactscar");
        server.start();
        server.join();
    }
}
