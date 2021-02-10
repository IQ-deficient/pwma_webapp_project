package pwma.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

// allows application developers to easily expose Java services as RESTful web services
@ApplicationPath("/rest")
public class JaxRsActivator extends Application {
}
