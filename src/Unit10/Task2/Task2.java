package Unit10.Task2;

import Unit10.Task1.CRUDServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Task2 {

    public static void main(String[] args) throws Exception {
        Server server = new Server(1235);
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new RedirectServlet()), "/redirect");
        context.addServlet(new ServletHolder(new ForwardServlet()), "/forward");
        context.addServlet(new ServletHolder(new TestServlet()), "/test");
        server.start();
        server.join();
    }
}
